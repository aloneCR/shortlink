package com.xpluo.shortlink.project.controller;

import com.github.pagehelper.PageInfo;
import com.xpluo.shortlink.project.common.convention.result.Result;
import com.xpluo.shortlink.project.common.convention.result.Results;
import com.xpluo.shortlink.project.dto.req.ShortLinkAddReqDTO;
import com.xpluo.shortlink.project.dto.req.ShortLinkPageQueryReqDTO;
import com.xpluo.shortlink.project.dto.resp.ShortLinkAddRespDTO;
import com.xpluo.shortlink.project.dto.resp.ShortLinkPageQueryRespDTO;
import com.xpluo.shortlink.project.service.ShortLinkService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

/**
 * 短链接Controller层
 * @author luoxiaopeng
 * @date 2024/2/15
 */
@RestController
@RequestMapping("/api/shortlink/v1/url/")
public class ShortLinkController {
    @Resource
    private ShortLinkService shortLinkService;

    @PostMapping("/addShortUrl")
    public Result<ShortLinkAddRespDTO> addShortUrl(@RequestBody ShortLinkAddReqDTO req) {
        return Results.success(shortLinkService.addShortLink(req));
    }

    @GetMapping("/pageQueryShortUrl")
    public Result<PageInfo<ShortLinkPageQueryRespDTO>> pageQueryShortUrl(@RequestBody ShortLinkPageQueryReqDTO req) {
        return Results.success(shortLinkService.pageQueryShortUrl(req));
    }

    @GetMapping("/ping")
    public String ping() {
        return "pong";
    }

}
