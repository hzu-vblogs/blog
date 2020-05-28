package com.hzu.blog.service.impl;

import com.hzu.blog.abstracts.AbstractBaseServiceImpl;
import com.hzu.blog.domain.Album;
import com.hzu.blog.mapper.AlbumMapper;
import com.hzu.blog.mapper.AlbumPicMapper;
import com.hzu.blog.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class AlbumServiceImpl extends AbstractBaseServiceImpl<Album, AlbumMapper> implements AlbumService{
    @Autowired
    private AlbumMapper albumMapper;
    @Autowired
    private AlbumPicMapper albumPicMapper;

    @Override
    public List<Album> selectByUserId(Long userId) {
        List<Album> albums = albumMapper.selectByUserId(userId);
        for (Album album:albums){
            String strings = albumPicMapper.selectFirstPicByAlbumId(album.getId());
            if (!StringUtils.isEmpty(strings))
                album.setFirstPic(strings);
        }
        return albums;
    }

    @Override
    public Album lookDetail(Long albumId) {
        return albumMapper.selectByPrimaryKey(albumId);
    }
}
