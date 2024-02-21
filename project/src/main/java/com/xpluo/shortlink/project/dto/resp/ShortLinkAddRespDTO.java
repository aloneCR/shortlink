package com.xpluo.shortlink.project.dto.resp;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * @author luoxiaopeng
 * @date 2024/2/15
 */
@Getter
@Setter
@Builder
public class ShortLinkAddRespDTO {
    /**
     * 短链接
     */
    private String shortUrl;

    /**
     * 完整短链接
     */
    private String fullShortUrl;

    /**
     * 原始链接
     */
    private String originUrl;

    /**
     * 分组id
     */
    private String gid;
}
