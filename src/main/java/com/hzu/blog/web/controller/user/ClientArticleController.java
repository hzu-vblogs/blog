package com.hzu.blog.web.controller.user;

import com.hzu.blog.common.dto.BaseResult;
import com.hzu.blog.domain.Article;
import com.hzu.blog.pojo.dto.CommentsDto;
import com.hzu.blog.service.ArticleService;
import com.hzu.blog.service.CommentsService;
import com.hzu.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    @Autowired
    private UserService userService;

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


    @RequestMapping("/detail/{articleId}")
    public String lookDetail(@PathVariable("articleId") Long articleId, Model model){
        Article article = articleService.lookDetail(articleId);
        model.addAttribute("article",article);
        model.addAttribute("author",userService.getById(article.getUserId()).getUsername());
        return "user/article_detail";
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
