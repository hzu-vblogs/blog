package com.hzu.blog.mapper;

import com.hzu.blog.domain.User;
import tk.mybatis.mapper.MyMapper;

import java.util.List;

public interface UserMapper extends MyMapper<User> {
    User getByEmail(String email);

    List<User> getByLockStatus(Boolean lockStatus);
}