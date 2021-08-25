package com.example.login.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class MyMvcConfig implements WebMvcConfigurer {
    @Autowired
    LoginInterceptor loginInterceptor;

//    @Override
//    public void addViewControllers(ViewControllerRegistry registry) {
//        // super.addViewControllers(registry);视图映射
//        //浏览器发送 /atguigu 请求来到 success
//        registry.addViewController("/login").setViewName("/doLogin");
//    }
//
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        //registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
//        //此处还可继续增加目录。。。。
//        registry.addResourceHandler("/static/images/**").addResourceLocations("classpath:/static/");
//        //registry.addResourceHandler("/images/**").addResourceLocations("file:/E:/WebPackage/IdeaProjects/shiroLearn/src/main/resources/static/");
//    }


    // 读取到配置里面的静态资源访问路径
    @Value("${spring.mvc.static-path-pattern}")
    private String staticPathPattern;

    //注册一个拦截器
    public void addInterceptors(InterceptorRegistry registry){
        List list = new ArrayList();
        list.add("/index");
        list.add("/login");
        list.add("/doLogin");
        list.add("/register");
        list.add("/doRegist");
        list.add(this.staticPathPattern);

        //String[] strings = new String[]{"/index","/login","/images/a.jpeg"};
        registry.addInterceptor(loginInterceptor).addPathPatterns("/**").excludePathPatterns(list);

    }


//        list.add("/**/*.css");
//        list.add("/**/*.js");
//        list.add("/**/*.png");
//        list.add("/**/*.jpg");
//        list.add("/**/*.jpeg");
//        list.add("/**/*.gif");
//        list.add("/**/fonts/*");
//        list.add("/**/*.svg");
//        list.add("/static/**");

}