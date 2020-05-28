package com.hzu.blog.web.controller.user;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hzu.blog.common.dto.BaseResult;
import com.hzu.blog.common.dto.PaginationDto;
import com.hzu.blog.domain.Article;
import com.hzu.blog.domain.User;
import com.hzu.blog.mapper.ArticleMapper;
import com.hzu.blog.pojo.dto.CommentsDto;
import com.hzu.blog.pojo.dto.PageDto;
import com.hzu.blog.service.ArticleService;
import com.hzu.blog.service.CommentsService;
import com.hzu.blog.service.UserService;
import com.hzu.blog.service.VisitLogService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/articles")
public class ClientArticleController {
    @Autowired
    private ArticleService articleService;
    @Autowired
    private CommentsService commentsService;
    @Autowired
    private UserService userService;
    @Autowired
    private VisitLogService visitLogService;
    @Autowired
    private ArticleMapper articleMapper;
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
    public String lookDetail(@PathVariable("articleId") Long articleId, Model model, HttpSession httpSession, HttpServletRequest request){
        Article article = articleService.lookDetail(articleId);
        User user = userService.getById(article.getUserId());
        httpSession.setAttribute("myUser",user);
        model.addAttribute("article",article);
        model.addAttribute("author",userService.getById(article.getUserId()).getUsername());
        visitLogService.saveLog(request, article.getUserId());
        return "user/article_detail";
    }

    @RequestMapping("/search")
    public String search(Long userId, String title,Long categoryId, Model model, PaginationDto paginationDto, HttpSession httpSession, HttpServletRequest request){
        User user = userService.getById(userId);
        httpSession.setAttribute("myUser",user);
        if (paginationDto.getPage() == null)
            paginationDto.setPage(1);
        if (paginationDto.getLimit() == null)
            paginationDto.setLimit(5);
        PageHelper.startPage(paginationDto.getPage(),paginationDto.getLimit());
        PageInfo<Article> articlePageInfo = new PageInfo<>(articleMapper.selectByUserIdWithCateGory(userId,title,categoryId));
        PageDto pageDto = new PageDto();
        BeanUtils.copyProperties(articlePageInfo,pageDto);
        model.addAttribute("articles",articlePageInfo.getList());
        model.addAttribute("page",pageDto);
        model.addAttribute("title",title);
        visitLogService.saveLog(request, userId);
        return "user/essay";
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
        try {
            commentsService.comments(commentsDto);
        }catch (Exception e){
            return  BaseResult.fail("评论人邮箱错误或网络错误");
        }
        return BaseResult.success();
    }



}
