package com.xpluo.shortlink.admin.dto.resp;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

/**
 * @author luoxiaopeng
 * @date 2024/1/22
 */
public class GroupRespDTO {
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 分组id
     */
    private String gid;

    /**
     * 分组名称
     */
    private String name;

    /**
     * 创建分组的用户名
     */
    private String username;

    /**
     * 分组排序
     */
    private int sortOrder;
}
