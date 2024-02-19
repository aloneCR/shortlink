package com.xpluo.shortlink.project.dto.req;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @author luoxiaopeng
 * @date 2024/2/15
 */
@Getter
@Setter
public class ShortLinkAddReqDTO {
    /**
     * 域名
     */
    private String domain;

    /**
     * 原始链接
     */
    private String originUrl;

    /**
     * 分组id
     */
    private String gid;

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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date validDate;

    /**
     * 描述
     */
    private String description;
}
