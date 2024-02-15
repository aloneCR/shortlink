package com.xpluo.shortlink.admin.controller;

import com.github.pagehelper.PageInfo;
import com.xpluo.shortlink.admin.common.convention.result.Result;
import com.xpluo.shortlink.admin.common.convention.result.Results;
import com.xpluo.shortlink.admin.dto.req.group.*;
import com.xpluo.shortlink.admin.dto.resp.GroupRespDTO;
import com.xpluo.shortlink.admin.service.GroupService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    /**
     * 分页查询分组列表
     */
    @PostMapping("/queryGroup")
    public Result<PageInfo<GroupRespDTO>> queryGroup(@RequestBody GroupQueryReqDTO request) {
        return Results.success(groupService.listGroupPage(request));
    }

    /**
     * 更新分组名称
     */
    @PostMapping("/updateGroupName")
    public Result<Void> updateGroupName(@RequestBody GroupUpdateReqDTO req) {
        groupService.updateGroupName(req);
        return Results.success();
    }

    /**
     * 删除短链接分组
     */
    @PostMapping("/deleteGroup")
    public Result<Void> deleteGroup(@RequestBody GroupDeleteReqDTO req) {
        groupService.deleteGroup(req);
        return Results.success();
    }

    /**
     * 短链接分组排序
     */
    @PostMapping("/sortGroup")
    public Result<Void> sortGroup(@RequestBody List<GroupSortReqDTO> req) {
        groupService.sortGroup(req);
        return Results.success();
    }
}
