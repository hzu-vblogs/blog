package com.hzu.blog.web.controller.user;

import com.hzu.blog.common.dto.BaseResult;
import com.hzu.blog.domain.User;
import com.hzu.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("user")
@Controller
public class ClientUserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/index/{userId}")
    public String index(@PathVariable("userId") Long userId){

        return null;
    }
    @ResponseBody
    @RequestMapping("/messages/{userId}")
    public BaseResult messages(@PathVariable("userId") Long userId){
        User user = userService.getById(userId);
        return BaseResult.success("success",user);
    }


}
