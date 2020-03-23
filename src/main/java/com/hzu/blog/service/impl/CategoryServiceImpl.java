package com.hzu.blog.service.impl;

import com.hzu.blog.abstracts.AbstractBaseServiceImpl;
import com.hzu.blog.domain.Category;
import com.hzu.blog.mapper.CategoryMapper;
import com.hzu.blog.service.CategoryService;
import org.springframework.stereotype.Service;
@Service
public class CategoryServiceImpl extends AbstractBaseServiceImpl<Category, CategoryMapper> implements CategoryService{


}
