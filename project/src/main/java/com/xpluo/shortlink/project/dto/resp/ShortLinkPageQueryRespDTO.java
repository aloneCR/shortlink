package com.xpluo.shortlink.project.dto.resp;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 短链接分页查询响应
 * @author luoxiaopeng
 * @date 2024/2/21
 */
@Getter
@Setter
public class ShortLinkPageQueryRespDTO {
    /**
     * 主键
     */
    private Long id;

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

    /**
     * 网站图标
     */
    private String favicon;
}
