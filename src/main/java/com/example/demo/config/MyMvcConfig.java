package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyMvcConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("userLogin");
        registry.addViewController("/userLogin.html").setViewName("userLogin");
        registry.addViewController("/adminLogin.html").setViewName("adminLogin");
        registry.addViewController("/user-index.html").setViewName("user-index");
        registry.addViewController("/user-info.html").setViewName("user-info");
        registry.addViewController("/admin-info.html").setViewName("admin-info");
        registry.addViewController("/admin-publish-news.html").setViewName("admin-publish-news");
        registry.addViewController("/admin-news.html").setViewName("admin-news");
        registry.addViewController("/admin-user-manage.html").setViewName("admin-user-manage");
        registry.addViewController("/user-query.html").setViewName("user-query");
        registry.addViewController("/admin-new-message.html").setViewName("admin-new-message");
        registry.addViewController("/admin-received-items.html").setViewName("admin-received-items");
        registry.addViewController("/admin-sent-items.html").setViewName("admin-sent-items");
        registry.addViewController("/user-new-message.html").setViewName("user-new-message");
        registry.addViewController("/user-received-items.html").setViewName("user-received-items");
        registry.addViewController("/user-sent-items.html").setViewName("user-sent-items");


    }
}
