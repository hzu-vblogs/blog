package com.hzu.blog.service.impl;

import com.hzu.blog.abstracts.AbstractBaseServiceImpl;
import com.hzu.blog.domain.Music;
import com.hzu.blog.mapper.MusicMapper;
import com.hzu.blog.service.MusicService;
import org.springframework.stereotype.Service;

@Service
public class MusicServiceImpl extends AbstractBaseServiceImpl<Music, MusicMapper> implements MusicService{

    public Music getByUserId(Long userId) {
        return dao.selectByUserId(userId);
    }
}
