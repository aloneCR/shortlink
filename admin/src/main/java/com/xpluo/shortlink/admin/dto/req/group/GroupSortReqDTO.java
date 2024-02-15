package com.xpluo.shortlink.admin.dto.req.group;

import lombok.Data;

/**
 * 更新短链接分组排列顺序请求体
 * @author luoxiaopeng
 * @date 2024/2/13
 */
@Data
public class GroupSortReqDTO {
    /**
     * 分组id
     */
    private String gid;

    /**
     * 排序序号(从0开始，越小越靠前)
     */
    private int sortOrder;
}
