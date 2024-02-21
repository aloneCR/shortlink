package com.xpluo.shortlink.project.service;

import com.github.pagehelper.PageInfo;
import com.xpluo.shortlink.project.dto.req.ShortLinkAddReqDTO;
import com.xpluo.shortlink.project.dto.req.ShortLinkPageQueryReqDTO;
import com.xpluo.shortlink.project.dto.resp.ShortLinkAddRespDTO;
import com.xpluo.shortlink.project.dto.resp.ShortLinkPageQueryRespDTO;

/**
 * @author luoxiaopeng
 * @date 2024/2/15
 */
public interface ShortLinkService {
    /**
     * 新增短链接
     */
    ShortLinkAddRespDTO addShortLink(ShortLinkAddReqDTO req);

    PageInfo<ShortLinkPageQueryRespDTO> pageQueryShortUrl(ShortLinkPageQueryReqDTO req);
}
