package com.hzu.blog.web.controller.admin;

import com.hzu.blog.common.dto.BaseResult;
import com.hzu.blog.domain.*;
import com.hzu.blog.mapper.AlbumMapper;
import com.hzu.blog.mapper.ArticleMapper;
import com.hzu.blog.mapper.CategoryMapper;
import com.hzu.blog.mapper.VisitLogMapper;
import com.hzu.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotNull;

@Controller
@RequestMapping(value = "/admin")
public class IndexController {
    @Autowired
    private UserService userService;
    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private VisitLogMapper visitLogMapper;
    @Autowired
    private AlbumMapper albumMapper;


    @RequestMapping(value = "/welcome")
    public String welcome(Model model, HttpServletRequest httpServletRequest){
        User user = (User) httpServletRequest.getSession().getAttribute("user");
        Category category = new Category();
        category.setUserId(user.getId());
        Article article = new Article();
        article.setUserId(user.getId());
        VisitLog visitLog = new VisitLog();
        visitLog.setUserId(user.getId());
        Album album = new Album();
        album.setUserId(user.getId());
        int categoryNums = categoryMapper.totalCount(category);


        model.addAttribute("categoryNums", categoryNums);
        model.addAttribute("articleNums",articleMapper.totalCount(article));
        model.addAttribute("visitNums",visitLogMapper.totalCount(visitLog));
        model.addAttribute("albumNums",albumMapper.totalCount(album));

        return "admin/welcome";
    }

    @RequestMapping(value = "/index")
    public String index(){
        return "admin/index";
    }

    @RequestMapping(value = "/page/login")
    public String pageLogin(){
        return "admin/login";
    }

    @RequestMapping(value = "/page/register")
    public String pageRegister(){
        return "admin/register";
    }

    @RequestMapping(value = "/login")
    @ResponseBody
    public BaseResult login(@NotNull String email, @NotNull String password, HttpSession session,@NotNull String captcha){
        User user = userService.getByEmail(email);
        String code = (String) session.getAttribute("code");
        if (!captcha.equals(code)){
            return BaseResult.fail("验证码错误");
        }
        if (user==null){
            return BaseResult.fail("用户不存在");
        }
        if (user.getPassword().equals(password)){
            session.setAttribute("user",user);
            return BaseResult.success("登录成功");
        }
        return BaseResult.fail("用户名或密码错误");
    }

    @RequestMapping(value = "/register")
    @ResponseBody
    public BaseResult register(@NotNull String email, @NotNull String password, HttpSession session, @NotNull String captcha){
        User user = userService.getByEmail(email);
        String code = (String) session.getAttribute("registerCode");
        if (user!=null){
            return BaseResult.fail("该邮箱已注册");
        }

        if (!captcha.equals(code)){
            return BaseResult.fail("验证码错误");
        }

        User registerUser = new User();
        registerUser.setEmail(email);
        registerUser.setPassword(password);
        userService.insert(registerUser);

        return BaseResult.success("注册成功");
    }
}
