package com.hzu.blog.mapper;

import com.hzu.blog.domain.Article;
import tk.mybatis.mapper.MyMapper;import java.util.List;

public interface ArticleMapper extends MyMapper<Article> {
    List<Article> selectByUserId(Long userId);

    Article selectOneWithComments(Long articleId);
}