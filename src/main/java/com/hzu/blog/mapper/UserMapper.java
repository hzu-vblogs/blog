package com.hzu.blog.mapper;

import com.hzu.blog.domain.User;
import tk.mybatis.mapper.MyMapper;

public interface UserMapper extends MyMapper<User> {
    User getByEmail(String email);
}