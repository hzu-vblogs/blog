package com.hzu.blog.web.controller.admin;

import com.hzu.blog.common.dto.BaseResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/admin")
public class IndexController {
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
    public BaseResult login(String email,String password){
        int a = 0;
        a++;
        System.out.println(a);
        return BaseResult.success("登录成功");
    }
}
