package com.xpluo.shortlink.admin.remote;

import com.github.pagehelper.PageInfo;
import com.xpluo.shortlink.admin.common.convention.result.Result;
import com.xpluo.shortlink.project.dto.req.ShortLinkAddReqDTO;
import com.xpluo.shortlink.project.dto.req.ShortLinkPageQueryReqDTO;
import com.xpluo.shortlink.project.dto.resp.ShortLinkAddRespDTO;
import com.xpluo.shortlink.project.dto.resp.ShortLinkPageQueryRespDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 短链接服务远程调用
 * @author luoxiaopeng
 * @date 2024/2/21
 */
@FeignClient(name = "short-link-project", path = "/api/shortlink/v1/url/")
public interface ShortLinkRemoteService {
    /**
     * 新增短链接
     */
    @PostMapping("/addShortUrl")
    Result<ShortLinkAddRespDTO> addShortUrl(@RequestBody ShortLinkAddReqDTO req);

    /**
     * 根据分组gid分页查询短链接列表
     */
    @GetMapping("/pageQueryShortUrl")
    Result<PageInfo<ShortLinkPageQueryRespDTO>> pageQueryShortUrl(@RequestBody ShortLinkPageQueryReqDTO req);
}
