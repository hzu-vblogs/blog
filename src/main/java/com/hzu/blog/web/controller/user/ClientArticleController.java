package com.hzu.blog.web.controller.user;

import com.hzu.blog.common.dto.BaseResult;
import com.hzu.blog.pojo.dto.CommentsDto;
import com.hzu.blog.service.ArticleService;
import com.hzu.blog.service.CommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/articles")
public class ClientArticleController {
    @Autowired
    private ArticleService articleService;
    @Autowired
    private CommentsService commentsService;

    /**
     * 根据用户id查询所有的文章
     * @param userId
     * @return
     */
    @ResponseBody
    @RequestMapping("/{userId}")
    public BaseResult articles(@PathVariable("userId") Long userId){
        return BaseResult.success("success",articleService.selectByUserId(userId));
    }

    @ResponseBody
    @RequestMapping("/detail/{articleId}")
    public BaseResult lookDetail(@PathVariable("articleId") Long articleId){
        return BaseResult.success("success",articleService.lookDetail(articleId));
    }


    /**
     *点赞文章
     * @param articleId
     * @return
     */
    @ResponseBody
    @RequestMapping("/good/{articleId}")
    public BaseResult good(@PathVariable("articleId") Long articleId){
        articleService.good(articleId);
        return BaseResult.success();
    }

    /**
     * 评论文章
     * @param commentsDto
     * @return
     */
    @ResponseBody
    @RequestMapping("/comment")
    public BaseResult comment(CommentsDto commentsDto){
        commentsService.comments(commentsDto);
        return BaseResult.success();
    }

}
