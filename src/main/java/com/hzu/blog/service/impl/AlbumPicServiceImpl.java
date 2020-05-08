package com.hzu.blog.service.impl;

import com.hzu.blog.abstracts.AbstractBaseServiceImpl;
import com.hzu.blog.domain.AlbumPic;
import com.hzu.blog.mapper.AlbumPicMapper;
import com.hzu.blog.service.AlbumPicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlbumPicServiceImpl extends AbstractBaseServiceImpl<AlbumPic,AlbumPicMapper> implements AlbumPicService {
    @Autowired
    private AlbumPicMapper albumPicMapper;

    @Override
    public List<AlbumPic> selectByUserId(Long albumId) {
        return albumPicMapper.selectByAlbumId(albumId);
    }

}
