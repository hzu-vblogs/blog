package com.hzu.blog.service;

import com.hzu.blog.abstracts.BaseService;
import com.hzu.blog.domain.Article;
import com.hzu.blog.domain.User;

import java.util.List;

public interface UserService extends BaseService<User> {
    User getByEmail(String email);

    void updateTest();

    void insertTest();

    void lock(User user);

    void unLock(User user);

    List<Article> getRecommendArticles(Long userId);
}
