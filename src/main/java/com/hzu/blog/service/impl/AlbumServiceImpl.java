package com.hzu.blog.service.impl;

import com.hzu.blog.abstracts.AbstractBaseServiceImpl;
import com.hzu.blog.domain.Album;
import com.hzu.blog.mapper.AlbumMapper;
import com.hzu.blog.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlbumServiceImpl extends AbstractBaseServiceImpl<Album, AlbumMapper> implements AlbumService{
    @Autowired
    private AlbumMapper albumMapper;

    @Override
    public List<Album> selectByUserId(Long userId) {
        return albumMapper.selectByUserId(userId);
    }

    @Override
    public Album lookDetail(Long albumId) {
        return albumMapper.selectByPrimaryKey(albumId);
    }
}
