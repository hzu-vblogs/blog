package com.hzu.blog.service;

import com.hzu.blog.abstracts.BaseService;
import com.hzu.blog.domain.Music;

public interface MusicService extends BaseService<Music> {
    Music getByUserId(Long userId);

}
