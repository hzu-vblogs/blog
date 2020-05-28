package com.hzu.blog.mapper;

import com.hzu.blog.domain.Music;
import tk.mybatis.mapper.MyMapper;

public interface MusicMapper extends MyMapper<Music> {
    Music selectByUserId(Long userId);
}