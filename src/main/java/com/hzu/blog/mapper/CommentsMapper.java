package com.hzu.blog.mapper;

import com.hzu.blog.domain.Comments;
import tk.mybatis.mapper.MyMapper;

import java.util.List;

public interface CommentsMapper extends MyMapper<Comments> {
    Comments selectByParentId(Long parentId);

    List<Comments> selectByArticleId(Long articleId);
}