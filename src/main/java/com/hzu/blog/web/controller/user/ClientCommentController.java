package com.hzu.blog.web.controller.user;

import com.hzu.blog.mapper.CommentsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("comments")
public class ClientCommentController {
    @Autowired
    private CommentsMapper commentsMapper;



}
