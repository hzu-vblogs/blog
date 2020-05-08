package com.hzu.blog;

import com.hzu.blog.domain.Article;
import com.hzu.blog.domain.Category;
import com.hzu.blog.mapper.ArticleMapper;
import com.hzu.blog.mapper.CategoryMapper;
import com.hzu.blog.mapper.CommentsMapper;
import com.hzu.blog.service.ArticleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BlogApplicationTests {

    @Autowired
    CategoryMapper categoryMapper;

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private CommentsMapper commentsMapper;

    @Autowired
    private ArticleService articleService;
    @Test
    void contextLoads() {
        Category category = categoryMapper.selectByPrimaryKey(1L);
        System.out.println(category);
    }


    @Test
    void testCommentsChildren() {
        Article article = articleService.lookDetail(9L);
        System.out.println(article.getComments());
    }
}
