package com.xpluo.shortlink.project.dto.req;

import lombok.Getter;
import lombok.Setter;

/**
 * 短链接分页查询请求
 * @author luoxiaopeng
 * @date 2024/2/21
 */
@Getter
@Setter
public class ShortLinkPageQueryReqDTO {
    /**
     * 页码
     */
    private Integer pageNum;

    /**
     * 页大小
     */
    private Integer pageSize;

    /**
     * 分组标识
     */
    private String gid;
}
