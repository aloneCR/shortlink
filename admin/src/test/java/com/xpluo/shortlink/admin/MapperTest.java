package com.xpluo.shortlink.admin;

import com.xpluo.shortlink.admin.dao.entity.GroupDO;
import com.xpluo.shortlink.admin.dao.mapper.GroupMapper;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author luoxiaopeng
 * @date 2024/1/23
 */
@SpringBootTest
public class MapperTest {
    @Resource
    private GroupMapper groupMapper;

    @Test
    public void test() {
        System.out.println("开始测试");
        List<GroupDO> groupDOList = groupMapper.queryShortLinkGroupByUsername("骆小朋");
        groupDOList.forEach(g -> System.out.println(g));
    }
}
