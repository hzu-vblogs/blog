package com.hzu.blog.service;

import com.hzu.blog.abstracts.BaseService;
import com.hzu.blog.domain.AlbumPic;

import java.util.List;

public interface AlbumPicService extends BaseService<AlbumPic> {


    List<AlbumPic> selectByUserId(Long albumId);
}
