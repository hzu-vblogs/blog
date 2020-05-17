package com.hzu.blog.service.impl;

import com.hzu.blog.abstracts.AbstractBaseServiceImpl;
import com.hzu.blog.domain.User;
import com.hzu.blog.mapper.UserMapper;
import com.hzu.blog.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl extends AbstractBaseServiceImpl<User, UserMapper> implements UserService{


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
}
