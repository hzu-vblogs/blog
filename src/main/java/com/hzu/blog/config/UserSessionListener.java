package com.hzu.blog.config;

import com.hzu.blog.domain.User;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
//使用说明  需要在启动类上添加@ServletComponentScan注解，以扫描到该监听器

@WebListener//监听器注解
public class UserSessionListener implements HttpSessionListener {
    /**
     * session被销毁时触发,如下情况
     * 		1.主动调用session.invalidate()
     * 		2.session超时
     */
    @Override
    public void sessionDestroyed(HttpSessionEvent event) {
        HttpSession session = event.getSession();
        //获取当前用户信息
        User loginUser = (User)session.getAttribute("user");

    }
}
