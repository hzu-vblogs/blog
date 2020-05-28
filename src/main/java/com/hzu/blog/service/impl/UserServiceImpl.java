package com.hzu.blog.service.impl;

import com.hzu.blog.abstracts.AbstractBaseServiceImpl;
import com.hzu.blog.domain.Article;
import com.hzu.blog.domain.User;
import com.hzu.blog.mapper.ArticleMapper;
import com.hzu.blog.mapper.UserMapper;
import com.hzu.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl extends AbstractBaseServiceImpl<User, UserMapper> implements UserService{
    @Autowired
    private ArticleMapper articleMapper;


    @Override
    public User getByEmail(String email) {
        return dao.getByEmail(email);
    }

    public void updateTest(){
        insertTest();
    }

    @Transactional
    public void insertTest(){
        User user = new User();
        user.setPassword("123");
        user.setUsername("testtt");
        user.setEmail("dsadadasd@qq.com");
        dao.insert(user);
        int i = 100/0;
    }

    @Override
    public void lock(User user) {
        user.setLockStatus(true);
        dao.updateByPrimaryKeySelective(user);
    }

    @Override
    public void unLock(User user) {
        user.setLockStatus(false);
        dao.updateByPrimaryKeySelective(user);
    }

    @Override
    public List<Article> getRecommendArticles(Long userId) {
        return articleMapper.selectByUserIdAndRecommend(userId);
    }
}
