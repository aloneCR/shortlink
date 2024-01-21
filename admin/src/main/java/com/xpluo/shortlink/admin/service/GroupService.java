package com.xpluo.shortlink.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xpluo.shortlink.admin.dao.entity.GroupDO;

/**
 * 短链接分组接口层
 *
 * @author luoxiaopeng
 * @date 2023/12/24
 */
public interface GroupService extends IService<GroupDO> {
    /**
     * 添加分组
     * @param groupName 分组名
     */
    void addGroup(String groupName);

}
