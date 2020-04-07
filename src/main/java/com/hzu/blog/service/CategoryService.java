package com.hzu.blog.service;

import com.hzu.blog.abstracts.BaseService;
import com.hzu.blog.domain.Category;
import com.hzu.blog.pojo.vo.CategoryVO;

import java.util.List;

public interface CategoryService extends BaseService<Category> {

    List<CategoryVO> getCategoryByUserId(Long userId);
}
