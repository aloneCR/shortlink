package com.xpluo.shortlink.admin.controller;

import com.xpluo.shortlink.admin.common.convention.result.Result;
import com.xpluo.shortlink.admin.common.convention.result.Results;
import com.xpluo.shortlink.admin.dto.req.GroupAddReqDTO;
import com.xpluo.shortlink.admin.service.GroupService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author luoxiaopeng
 * @date 2023/12/24
 */
@RestController
@RequestMapping("/api/shortlink/v1/group")
public class GroupController {
    @Resource
    private GroupService groupService;

    @PostMapping("/addGroup")
    public Result<Void> addGroup(@RequestBody GroupAddReqDTO request) {
        groupService.addGroup(request.getName());
        return Results.success();
    }
}
