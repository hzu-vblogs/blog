package com.hzu.blog.service.impl;

import com.hzu.blog.abstracts.AbstractBaseServiceImpl;
import com.hzu.blog.domain.Article;
import com.hzu.blog.mapper.ArticleMapper;
import com.hzu.blog.service.ArticleService;
import org.springframework.stereotype.Service;
@Service
public class ArticleServiceImpl extends AbstractBaseServiceImpl<Article, ArticleMapper> implements ArticleService{


}
