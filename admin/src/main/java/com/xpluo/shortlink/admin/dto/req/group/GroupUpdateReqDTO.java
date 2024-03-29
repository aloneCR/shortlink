package com.xpluo.shortlink.admin.dto.req.group;

import lombok.Data;

/**
 * 更新分组信息请求体
 *
 * @author luoxiaopeng
 * @date 2024/2/13
 */
@Data
public class GroupUpdateReqDTO {
    /**
     * 分组gid
     */
    private String gid;

    /**
     * 分组名称
     */
    private String name;
}
