package com.xpluo.shortlink.admin.dto.req.group;

import lombok.Data;

/**
 * 删除短链接分组
 * @author luoxiaopeng
 * @date 2024/2/13
 */
@Data
public class GroupDeleteReqDTO {
    /**
     * 分组id
     */
    private String gid;
}
