package com.hzu.blog.web.controller.admin;

import com.hzu.blog.common.dto.BaseResult;
import com.hzu.blog.domain.User;
import com.hzu.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/admin")
public class IndexController {
    @Autowired
    private UserService userService;


    @RequestMapping(value = "/welcome")
    public String welcome(){
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


    @RequestMapping(value = "/login")
    @ResponseBody
    public BaseResult login(String email, String password, HttpSession session){
        User user = userService.getByEmail(email);
        if (user==null){
            return BaseResult.fail("用户不存在");
        }
        if (user.getPassword().equals(password)){
            session.setAttribute("user",user);
            return BaseResult.success("登录成功");
        }
        return BaseResult.fail("用户名或密码错误");
    }
}
