package com.xpluo.shortlink.admin.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xpluo.shortlink.admin.common.biz.user.UserContext;
import com.xpluo.shortlink.admin.common.convention.exception.ClientException;
import com.xpluo.shortlink.admin.dao.entity.GroupDO;
import com.xpluo.shortlink.admin.dao.mapper.GroupMapper;
import com.xpluo.shortlink.admin.dto.req.group.GroupDeleteReqDTO;
import com.xpluo.shortlink.admin.dto.req.group.GroupQueryReqDTO;
import com.xpluo.shortlink.admin.dto.req.group.GroupUpdateReqDTO;
import com.xpluo.shortlink.admin.dto.resp.GroupRespDTO;
import com.xpluo.shortlink.admin.service.GroupService;
import com.xpluo.shortlink.admin.toolkit.RandomGenerator;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author luoxiaopeng
 * @date 2023/12/24
 */
@Slf4j
@Service
public class GroupServiceImpl implements GroupService {

    @Resource
    private GroupMapper groupMapper;

    @Override
    public void addGroup(String groupName) {
        GroupDO groupDO = new GroupDO();
        groupDO.setName(groupName);
        groupDO.setGid(RandomGenerator.generateRandomString(6));
        groupDO.setUsername(UserContext.getUsername());
        groupMapper.insertShortLinkGroup(groupDO);
    }

    @Override
    public List<GroupRespDTO> listGroup() {
        String username = UserContext.getUsername();
        List<GroupDO> groupDOList = groupMapper.queryShortLinkGroupByUsername(username);
        return BeanUtil.copyToList(groupDOList, GroupRespDTO.class);
    }

    @Override
    public PageInfo<GroupRespDTO> listGroupPage(GroupQueryReqDTO request) {
        return PageHelper.startPage(request.getPageNum(), request.getPageSize())
                .using("mysql")
                .doSelectPageInfo(this::listGroup);
    }

    @Override
    public void updateGroupName(GroupUpdateReqDTO req) {
        GroupDO groupDO = new GroupDO();
        groupDO.setGid(req.getGid());
        groupDO.setName(req.getName());
        groupDO.setUsername(UserContext.getUsername());

        int success = groupMapper.updateGroupName(groupDO);
        if (success <= 0) {
            throw new ClientException("更新短链接分组名称失败");
        }
    }

    @Override
    public void deleteGroup(GroupDeleteReqDTO req) {
        String username = UserContext.getUsername();
        int success = groupMapper.deleteGroupByGid(username, req.getGid());
        // TODO 删除分组下所有短链接(保证事务)
        if (success <= 0) {
            throw new ClientException("删除短链接分组失败");
        }
    }
}
