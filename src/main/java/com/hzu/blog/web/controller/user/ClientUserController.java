package com.hzu.blog.web.controller.user;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hzu.blog.common.dto.BaseResult;
import com.hzu.blog.common.dto.PaginationDto;
import com.hzu.blog.domain.Article;
import com.hzu.blog.domain.Music;
import com.hzu.blog.domain.User;
import com.hzu.blog.mapper.*;
import com.hzu.blog.pojo.dto.PageDto;
import com.hzu.blog.pojo.vo.CategoryVO;
import com.hzu.blog.service.MusicService;
import com.hzu.blog.service.UserService;
import com.hzu.blog.service.VisitLogService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotNull;
import java.util.List;

@RequestMapping("user")
@Controller
@Validated
public class ClientUserController {
    @Autowired
    private UserService userService;
    @Autowired
    private MusicService musicService;
    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private AlbumMapper albumMapper;
    @Autowired
    private LeaveMessageMapper leaveMessageMapper;
    @Autowired
    private VisitLogService visitLogService;

    @RequestMapping("/index/{userId}")
    public String index(@NotNull @PathVariable("userId") Long userId, Model model, PaginationDto paginationDto, HttpSession httpSession, HttpServletRequest request){
        User user = userService.getById(userId);
        httpSession.setAttribute("myUser",user);
        String url = "http://47.112.174.9:8088/blogs/temp-rainy/a1ab2b6196c8435e8ec41b5d9b4598ac.mp3";
        Music byUserId = musicService.getByUserId(userId);
        if (byUserId == null){
            Music music = new Music();
            music.setName("演员");
            music.setSinger("薛之谦");
            music.setUrl(url);
            music.setUserId(userId);
            music.setId(0L);
            model.addAttribute("music",music);
        }else
            model.addAttribute("music",byUserId);
        List<Article> recommendArticles = userService.getRecommendArticles(userId);
        List<CategoryVO> categoryByUserId = categoryMapper.getCategoryByUserId(userId);
        model.addAttribute("leaveNum",leaveMessageMapper.selectCountByUserId(userId));
        model.addAttribute("albumNum",albumMapper.selectCountByUserId(userId));
        model.addAttribute("categorys",categoryByUserId);
        model.addAttribute("recommendArticles",recommendArticles);
        if (paginationDto.getPage() == null)
            paginationDto.setPage(1);
        if (paginationDto.getLimit() == null)
            paginationDto.setLimit(5);
        PageHelper.startPage(paginationDto.getPage(),paginationDto.getLimit());
        PageInfo<Article> articlePageInfo = new PageInfo<>(articleMapper.selectByUserIdWithCateGory(userId,null,null));

        PageDto pageDto = new PageDto();
        BeanUtils.copyProperties(articlePageInfo,pageDto);
        System.out.println(pageDto);
        model.addAttribute("articles",articlePageInfo.getList());
        model.addAttribute("page",pageDto);
        visitLogService.saveLog(request, userId);

        return "user/index2";
    }
    @ResponseBody
    @RequestMapping("/messages/{userId}")
    public BaseResult messages(@PathVariable("userId") Long userId){
        User user = userService.getById(userId);
        return BaseResult.success("success",user);
    }


}
