package com.xpluo.shortlink.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xpluo.shortlink.admin.dao.entity.GroupDO;
import com.xpluo.shortlink.admin.dao.mapper.GroupMapper;
import com.xpluo.shortlink.admin.service.GroupService;
import com.xpluo.shortlink.admin.toolkit.RandomGenerator;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author luoxiaopeng
 * @date 2023/12/24
 */
@Slf4j
@Service
public class GroupServiceImpl extends ServiceImpl<GroupMapper, GroupDO> implements GroupService {

    @Resource
    private GroupMapper groupMapper;

    @Override
    public void addGroup(String groupName) {
        GroupDO groupDO = new GroupDO();
        groupDO.setName(groupName);
        groupDO.setGid(RandomGenerator.generateRandomString(6));
        // todo 设置用户名
        groupDO.setUsername("骆小朋");

        groupMapper.insert(groupDO);
    }
}
