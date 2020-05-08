package com.hzu.blog.service;

import com.hzu.blog.abstracts.BaseService;
import com.hzu.blog.domain.Article;

import java.util.List;

public interface ArticleService extends BaseService<Article> {

    List<Article> selectByUserId(Long userId);

    void good(Long articleId);

    Article lookDetail(Long articleId);
}
