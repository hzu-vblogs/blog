package com.hzu.blog;

import com.hzu.blog.domain.Category;
import com.hzu.blog.mapper.CategoryMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BlogApplicationTests {

    @Autowired
    CategoryMapper categoryMapper;
    @Test
    void contextLoads() {
        Category category = categoryMapper.selectByPrimaryKey(1L);
        System.out.println(category);
    }

}
