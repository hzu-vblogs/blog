package com.hzu.blog.service.impl;

import com.hzu.blog.abstracts.AbstractBaseServiceImpl;
import com.hzu.blog.domain.User;
import com.hzu.blog.mapper.UserMapper;
import com.hzu.blog.service.UserService;
import org.springframework.stereotype.Service;
@Service
public class UserServiceImpl extends AbstractBaseServiceImpl<User, UserMapper> implements UserService{


}
