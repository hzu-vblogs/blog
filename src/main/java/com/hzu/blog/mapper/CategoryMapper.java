package com.hzu.blog.mapper;

import com.hzu.blog.domain.Category;
import com.hzu.blog.pojo.vo.CategoryVO;
import tk.mybatis.mapper.MyMapper;

import java.util.List;

public interface CategoryMapper extends MyMapper<Category> {

    List<CategoryVO> getCategoryByUserId(Long userId);
}