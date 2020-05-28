package com.hzu.blog.mapper;

import com.hzu.blog.domain.AlbumPic;
import tk.mybatis.mapper.MyMapper;

import java.util.List;

public interface AlbumPicMapper extends MyMapper<AlbumPic> {
    List<AlbumPic> selectByAlbumId(Long albumId);
    String selectFirstPicByAlbumId(Long albumId);
}