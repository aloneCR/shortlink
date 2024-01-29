package com.xpluo.shortlink.admin.dto.req;

import lombok.Getter;
import lombok.Setter;

/**
 * 分页查询当前用户分组的请求体
 *
 * @author luoxiaopeng
 * @date 2024/1/22
 */
@Getter
@Setter
public class GroupQueryReqDTO {
    /**
     * 页码
     */
    private Integer pageNum;

    /**
     * 页大小
     */
    private Integer pageSize;
}
