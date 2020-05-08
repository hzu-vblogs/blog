package com.hzu.blog.service;

import com.hzu.blog.domain.Album;

import java.util.List;

public interface AlbumService{
    List<Album> selectByUserId(Long userId);

    Album lookDetail(Long albumId);
}
