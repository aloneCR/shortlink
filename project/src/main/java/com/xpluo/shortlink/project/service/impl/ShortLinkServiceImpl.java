package com.xpluo.shortlink.project.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xpluo.shortlink.project.common.convention.exception.ServiceException;
import com.xpluo.shortlink.project.dao.entity.ShortLinkDO;
import com.xpluo.shortlink.project.dao.mapper.ShortLinkMapper;
import com.xpluo.shortlink.project.dto.req.ShortLinkAddReqDTO;
import com.xpluo.shortlink.project.dto.req.ShortLinkPageQueryReqDTO;
import com.xpluo.shortlink.project.dto.resp.ShortLinkAddRespDTO;
import com.xpluo.shortlink.project.dto.resp.ShortLinkPageQueryRespDTO;
import com.xpluo.shortlink.project.service.ShortLinkService;
import com.xpluo.shortlink.project.toolkit.ShortUrlGenerator;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RBloomFilter;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author luoxiaopeng
 * @date 2024/2/15
 */
@Service
@Slf4j
public class ShortLinkServiceImpl implements ShortLinkService {
    @Resource
    private ShortLinkMapper shortLinkMapper;

    @Resource
    private RBloomFilter<String> shortLinkCreateBloomFilter;

    @Override
    public ShortLinkAddRespDTO addShortLink(ShortLinkAddReqDTO req) {
        // TODO 检查短链接中gid是否存在
        ShortLinkDO shortLinkDO = BeanUtil.toBean(req, ShortLinkDO.class);
        String shortUrl = doGetUniqueShortUrl(req.getOriginUrl(), req.getDomain());
        shortLinkDO.setShortUrl(shortUrl);
        shortLinkDO.setFullShortUrl(req.getDomain() + "/" + shortUrl);
        shortLinkDO.setClickNum(0);
        shortLinkDO.setEnableStatus(1);
        try {
            int success = shortLinkMapper.insertShortLink(shortLinkDO);
            if (success <= 0) {
                throw new ServiceException("插入短链接到数据库失败");
            }
        } catch (DuplicateKeyException ex) {
            log.warn("短链接{}重复入库", shortLinkDO.getFullShortUrl(), ex);
            throw new ServiceException("短链接生成重复");
        }
        // TODO：引入消息队列，保证数据库插入和布隆过滤器更新的事务！
        shortLinkCreateBloomFilter.add(shortLinkDO.getFullShortUrl());
        return ShortLinkAddRespDTO.builder()
                .shortUrl(shortUrl)
                .fullShortUrl(shortLinkDO.getFullShortUrl())
                .gid(req.getGid())
                .originUrl(req.getOriginUrl())
                .build();
    }

    @Override
    public PageInfo<ShortLinkPageQueryRespDTO> pageQueryShortUrl(ShortLinkPageQueryReqDTO req) {
        PageHelper.startPage(req.getPageNum(), req.getPageSize()).using("mysql");
        List<ShortLinkDO> shortLinkDOS = shortLinkMapper.pageQueryShortLinkByGid(req.getGid());
        List<ShortLinkPageQueryRespDTO> respDTOS = BeanUtil.copyToList(shortLinkDOS, ShortLinkPageQueryRespDTO.class);
        return new PageInfo<>(respDTOS);
    }

    /**
     * 获取不重复的6位短链接
     * 1.如果将每次生成的短链接都查询数据库进行比对，首先是性能损耗，其次当并发量较大时，数据库连接数不够用出现异常；
     * 2.考虑使用布隆过滤器，判断短链接是否已经生成过
     *
     * @param originUrl 原始链接
     * @param domain    域名
     * @return 短链接, 例如2RKIeO
     */
    private String doGetUniqueShortUrl(String originUrl, String domain) {
        // TODO 并发生成时，可能会生成重复的短链接，不过有数据库唯一索引兜底
        String shortUrl = ShortUrlGenerator.generateShortUrl(originUrl, 6);
        String fullUrl = domain + "/" + shortUrl;
        StringBuilder sb = new StringBuilder(originUrl);
        // 限制最大重试次数
        int maxRetryTime = 10;
        while (shortLinkCreateBloomFilter.contains(fullUrl)) {
            maxRetryTime--;
            if (maxRetryTime <= 0) {
                throw new ServiceException("生成短链接失败，请稍后重试");
            }
            sb.append(System.currentTimeMillis());
            shortUrl = ShortUrlGenerator.generateShortUrl(sb.toString(), 6);
            fullUrl = domain + "/" + shortUrl;
        }
        return shortUrl;
    }
}
