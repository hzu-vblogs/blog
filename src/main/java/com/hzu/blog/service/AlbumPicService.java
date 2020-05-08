package com.hzu.blog.service;

import com.hzu.blog.domain.AlbumPic;

import java.util.List;

public interface AlbumPicService{


    List<AlbumPic> selectByUserId(Long albumId);
}
