package com.xpluo.shortlink.admin.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xpluo.shortlink.admin.dao.entity.GroupDO;
import com.xpluo.shortlink.admin.dao.mapper.GroupMapper;
import com.xpluo.shortlink.admin.dto.req.GroupQueryReqDTO;
import com.xpluo.shortlink.admin.dto.resp.GroupRespDTO;
import com.xpluo.shortlink.admin.service.GroupService;
import com.xpluo.shortlink.admin.toolkit.RandomGenerator;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author luoxiaopeng
 * @date 2023/12/24
 */
@Slf4j
@Service
public class GroupServiceImpl implements GroupService {

    @Resource
    private GroupMapper groupMapper;

    @Override
    public void addGroup(String groupName) {
        GroupDO groupDO = new GroupDO();
        groupDO.setName(groupName);
        groupDO.setGid(RandomGenerator.generateRandomString(6));
        // todo 设置用户名
        groupDO.setUsername("骆小朋");
        groupMapper.insertShortLinkGroup(groupDO);
    }

    @Override
    public List<GroupRespDTO> listGroup() {
        // TODO 获取用户名
        String username = "骆小朋";
        List<GroupDO> groupDOList = groupMapper.queryShortLinkGroupByUsername(username);
        return BeanUtil.copyToList(groupDOList, GroupRespDTO.class);
    }

    @Override
    public PageInfo<GroupRespDTO> listGroupPage(GroupQueryReqDTO request) {
        return PageHelper.startPage(request.getPageNum(), request.getPageSize())
                .doSelectPageInfo(this::listGroup);
    }
}
