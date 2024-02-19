package com.xpluo.shortlink.project.controller;

import com.xpluo.shortlink.project.common.convention.result.Result;
import com.xpluo.shortlink.project.common.convention.result.Results;
import com.xpluo.shortlink.project.dto.req.ShortLinkAddReqDTO;
import com.xpluo.shortlink.project.dto.resp.ShortLinkAddResp;
import com.xpluo.shortlink.project.service.ShortLinkService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public Result<ShortLinkAddResp> addShortUrl(@RequestBody ShortLinkAddReqDTO req) {
        return Results.success(shortLinkService.addShortLink(req));
    }
}
