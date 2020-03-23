package com.hzu.blog.service.impl;

import com.hzu.blog.abstracts.AbstractBaseServiceImpl;
import com.hzu.blog.domain.Comments;
import com.hzu.blog.mapper.CommentsMapper;
import com.hzu.blog.service.CommentsService;
import org.springframework.stereotype.Service;
@Service
public class CommentsServiceImpl extends AbstractBaseServiceImpl<Comments, CommentsMapper> implements CommentsService{



}
