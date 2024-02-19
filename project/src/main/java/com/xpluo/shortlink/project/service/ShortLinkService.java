package com.xpluo.shortlink.project.service;

import com.xpluo.shortlink.project.dto.req.ShortLinkAddReqDTO;
import com.xpluo.shortlink.project.dto.resp.ShortLinkAddResp;

/**
 * @author luoxiaopeng
 * @date 2024/2/15
 */
public interface ShortLinkService {
    /**
     * 新增短链接
     */
    ShortLinkAddResp addShortLink(ShortLinkAddReqDTO req);
}
