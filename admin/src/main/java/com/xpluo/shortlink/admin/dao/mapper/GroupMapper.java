package com.xpluo.shortlink.admin.dao.mapper;

import com.xpluo.shortlink.admin.dao.entity.GroupDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 短链接分组持久层
 *
 * @author luoxiaopeng
 * @date 2023/12/24
 */
@Mapper
public interface GroupMapper {
    void insertShortLinkGroup(@Param("groupDO") GroupDO entity);

    List<GroupDO> queryShortLinkGroupByUsername(@Param("username") String username);

    int updateGroupName(@Param("groupDO") GroupDO entity);

    int deleteGroupByGid(@Param("username") String username,
                         @Param("gid") String gid);
}
