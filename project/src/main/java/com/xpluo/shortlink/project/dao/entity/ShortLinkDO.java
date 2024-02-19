package com.xpluo.shortlink.project.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 短链接实体类
 *
 * @author luoxiaopeng
 * @date 2024/2/15
 */
@Getter
@Setter
@TableName("t_link")
public class ShortLinkDO extends BaseDO {
    /**
     * 主键
     */
    private Long id;

    /**
     * 域名
     */
    private String domain;

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

    /**
     * 访问量
     */
    private Integer clickNum;

    /**
     * 是否启用 0:不起用 1：启用
     */
    private Integer enableStatus;

    /**
     * 创建方式 0:接口创建 1：控制台创建
     */
    private Integer createdType;

    /**
     * 有效期类型 0：永久有效 1：自定义
     */
    private Integer validDateType;

    /**
     * 有效期
     */
    private Date validDate;

    /**
     * 描述
     */
    private String description;
}
