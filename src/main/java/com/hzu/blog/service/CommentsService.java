package com.hzu.blog.service;

import com.hzu.blog.abstracts.BaseService;
import com.hzu.blog.domain.Comments;
import com.hzu.blog.domain.User;
import com.hzu.blog.pojo.dto.CommentsDto;

public interface CommentsService extends BaseService<Comments> {
    void comments(CommentsDto commentsDto);

    void good(Long commentsId);

    boolean reply(Long id, User user, String content);
}
