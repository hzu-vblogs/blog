package com.hzu.blog.interceptor;

import com.hzu.blog.domain.User;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SessionAuthenticationInterceptor implements HandlerInterceptor {
    /**
     * 进入controller层之前拦截请求
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {

        User user = (User) request.getSession().getAttribute("user");
        if(user==null ){//验证失败，返回false，不继续执行Controller中的方法
            System.out.println("session过期");
            response.sendRedirect(request.getContextPath()+"/admin/page/login");
            return false;
        }
        return true;

    }

}
