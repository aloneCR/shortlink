package com.xpluo.shortlink.admin.controller;

import com.xpluo.shortlink.admin.common.convention.result.Result;
import com.xpluo.shortlink.admin.common.convention.result.Results;
import com.xpluo.shortlink.admin.dto.req.UserRegisterReqDTO;
import com.xpluo.shortlink.admin.dto.resp.UserRespDTO;
import com.xpluo.shortlink.admin.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

/**
 * 用户管理控制层
 *
 * @author luoxiaopeng
 * @date
 */
@RestController
@RequestMapping("/api/shortlink/v1/user")
public class UserController {
    @Resource
    private UserService userService;

    /**
     * 根据用户名查询用户信息
     */
    @GetMapping("/{username}")
    public Result<UserRespDTO> getUserByUsername(@PathVariable("username") String username) {
        return Results.success(userService.getUserByUsername(username));
    }

    /**
     * 判断用户名是否已经被注册
     * @param username 用户名
     * @return True:已注册 False:还没注册
     */
    @GetMapping("/hasUsername")
    public Result<Boolean> hasUsername(@RequestParam("username") String username) {
        return Results.success(userService.hasUsername(username));
    }

    /**
     * 注册用户
     * @param requestParam 请求体
     * @return 结果
     */
    @PostMapping("")
    public Result<Void> register(@RequestBody UserRegisterReqDTO requestParam) {
        userService.register(requestParam);
        return Results.success();
    }
}
