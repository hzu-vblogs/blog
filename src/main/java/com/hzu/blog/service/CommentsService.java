package com.hzu.blog.service;

import com.hzu.blog.pojo.dto.CommentsDto;

public interface CommentsService{
    void comments(CommentsDto commentsDto);

    void good(Long commentsId);
}
