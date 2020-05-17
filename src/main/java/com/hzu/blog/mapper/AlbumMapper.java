package com.hzu.blog.mapper;

import com.hzu.blog.domain.Album;
import tk.mybatis.mapper.MyMapper;import java.util.List;

public interface AlbumMapper extends MyMapper<Album> {
    List<Album> selectByUserId(Long userId);
}