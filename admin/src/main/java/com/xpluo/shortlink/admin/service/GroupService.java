package com.xpluo.shortlink.admin.service;

import com.github.pagehelper.PageInfo;
import com.xpluo.shortlink.admin.dto.req.group.GroupDeleteReqDTO;
import com.xpluo.shortlink.admin.dto.req.group.GroupQueryReqDTO;
import com.xpluo.shortlink.admin.dto.req.group.GroupUpdateReqDTO;
import com.xpluo.shortlink.admin.dto.resp.GroupRespDTO;

import java.util.List;

/**
 * 短链接分组接口层
 *
 * @author luoxiaopeng
 * @date 2023/12/24
 */
public interface GroupService {
    /**
     * 添加分组
     * @param groupName 分组名
     */
    void addGroup(String groupName);

    /**
     * 查询当前用户短链接分组
     * @return 短链接分组信息列表
     */
    List<GroupRespDTO> listGroup();

    /**
     * 分页查询当前用户短链接分组
     * @return 短链接分组信息列表
     */
    PageInfo<GroupRespDTO> listGroupPage(GroupQueryReqDTO request);

    void updateGroupName(GroupUpdateReqDTO req);

    void deleteGroup(GroupDeleteReqDTO req);
}
