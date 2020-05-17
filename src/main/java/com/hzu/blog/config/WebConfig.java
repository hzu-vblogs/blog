package com.hzu.blog.config;

import com.hzu.blog.interceptor.SessionAuthenticationInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.AbstractEnvironment;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;


/**
 * WebMvcConfigurationSupport 继承此类会导致自动配置失效
 * 建议实现WebMvcConfigurer接口覆写部分配置
 */
@Configuration
@PropertySource("classpath:config/vblogs.properties")
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    AbstractEnvironment environment;

    @Value("${excludeUrls.sessionAuthenticationInterceptor}")
    private String sessionAuthenticationInterceptorExcludeUrls;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String fileLocation = "/usr/temp-rainy/";
        String osName = environment.getProperty("os.name");
        if (osName==null||"".equals(osName))
            osName = "Windows";
        if (osName.contains("Windows")||osName.contains("windows"))
            fileLocation = "D:/temp-rainy/";

        System.out.println(osName+"  系统类型");
        System.out.println(fileLocation);
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        registry.addResourceHandler("/temp-rainy/**").addResourceLocations("file:"+fileLocation);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new SessionAuthenticationInterceptor()).addPathPatterns("/admin/**").excludePathPatterns(Arrays.asList(sessionAuthenticationInterceptorExcludeUrls.split(",")));    //设置Web相关API的session拦截验证
    }

}
