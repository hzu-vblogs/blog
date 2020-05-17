package com.hzu.blog.mapper;

import com.hzu.blog.domain.Music;
import tk.mybatis.mapper.MyMapper;

import java.util.List;

public interface MusicMapper extends MyMapper<Music> {
    List<Music> selectByUserId(Long userId);
}