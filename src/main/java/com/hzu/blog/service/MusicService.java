package com.hzu.blog.service;

import com.hzu.blog.abstracts.BaseService;
import com.hzu.blog.domain.Music;

import java.util.List;

public interface MusicService extends BaseService<Music> {
    List<Music> getByUserId(Long userId);

}
