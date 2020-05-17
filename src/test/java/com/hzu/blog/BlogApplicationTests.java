package com.hzu.blog;

import com.hzu.blog.domain.*;
import com.hzu.blog.mapper.*;
import com.hzu.blog.service.ArticleService;
import com.hzu.blog.service.MailService;
import com.hzu.blog.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.AbstractEnvironment;

import java.util.Map;

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

    @Autowired
    private UserService userService;

    @Autowired
    private MailService mailService;

    @Autowired
    private VisitLogMapper visitLogMapper;
    @Autowired
    private AlbumMapper albumMapper;
    @Test
    void contextLoads() {
        Category category = categoryMapper.selectByPrimaryKey(1L);
        System.out.println(category);
    }


    @Test
    void testCommentsChildren() {
        Article article = articleService.lookDetail(9L);
        for (Comments comments:article.getComments())
            System.out.println(comments.toString());
        //System.out.println(article.getComments());
    }

    @Test
    void testTran() {
        userService.updateTest();
    }



    @Test
    void testSendEmail(){

            mailService.sendSimpleTextMailActual("发送主题","发送内容",new String[]{"lightforstar@163.com"},null,null,null);

    }


    @Test
    void testWelcome(){
        Category category = new Category();
        category.setUserId(1L);
        Article article = new Article();
        article.setUserId(1L);
        VisitLog visitLog = new VisitLog();
        visitLog.setUserId(1L);
        Album album = new Album();
        album.setUserId(1L);
//        int categoryNums = categoryMapper.totalCount(category);
        int articleNums = articleMapper.totalCount(article);
        int visitNums = visitLogMapper.totalCount(visitLog);
        int albumNums = albumMapper.totalCount(album);
//        System.out.println(categoryNums);
    }

    @Autowired
    AbstractEnvironment environment;

    @Test
    void testSystemProperties(){
        Map<String, Object> systemProperties = environment.getSystemProperties();
        for (Map.Entry<String,Object> entry:systemProperties.entrySet()){
            System.out.println(entry.getKey()+"   "+entry.getValue());
        }
    }

}
