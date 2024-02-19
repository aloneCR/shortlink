package com.xpluo.shortlink.project.dao.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @author luoxiaopeng
 * @date 2024/1/19
 */
@Getter
@Setter
public class BaseDO {
    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 删除标识 0:未删除 1:已删除
     */
    private Integer delTag;
}
