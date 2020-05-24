package com.hzu.blog.mapper;

import org.apache.ibatis.annotations.Update;

public interface SystemMapper  {
    @Update(value = "TRUNCATE TABLE `user` ;TRUNCATE TABLE album;TRUNCATE TABLE album_pic;TRUNCATE TABLE article;TRUNCATE TABLE category;TRUNCATE TABLE comments;TRUNCATE TABLE leave_message;TRUNCATE TABLE music;TRUNCATE TABLE visit_log")
    void deleteMultiTable();
}