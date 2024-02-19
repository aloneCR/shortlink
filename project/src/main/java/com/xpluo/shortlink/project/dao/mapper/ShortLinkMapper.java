package com.xpluo.shortlink.project.dao.mapper;

import com.xpluo.shortlink.project.dao.entity.ShortLinkDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author luoxiaopeng
 * @date 2024/2/15
 */
@Mapper
public interface ShortLinkMapper {
    int insertShortLink(@Param("linkDO") ShortLinkDO linkDO);

    ShortLinkDO queryShortLinkByFullShortUrl(@Param("fullShortUrl") String fullShortUrl);
}
