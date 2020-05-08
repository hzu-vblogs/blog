package com.hzu.blog.web.controller.example;
import com.hzu.blog.domain.User;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
public class VueRequestController {

    @RequestMapping(value = "/vue/request",method = RequestMethod.GET)
    public User dataToVue(){
        User tbUser = new User();
        tbUser.setUsername("张三");
        tbUser.setPassword("123456");
        return tbUser;
    }


    @RequestMapping(value = "/test",method = RequestMethod.GET)
    public String test(Long count){
        int result=0;
        for (int i=0;i<count;i++){
            result = i+result;
        }
        return Integer.toString(result);
    }

    @RequestMapping(value = "/test/gg",method = RequestMethod.GET)
    public void testGG(Long count){
        Long romdow = 0L;
        while (true){
            romdow++;
        }

    }

}
