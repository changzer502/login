package com.example.login.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //目标方法执行之前
        System.out.println("执行到了prehandle方法");
        Object user = request.getSession().getAttribute("session-user");
        if (user==null) {
            response.sendRedirect(request.getContextPath()+"/index");//拦截后跳转的方法
            System.out.println("已成功拦截并转发跳转");
            return false;
        }
        System.out.println("合格不需要拦截，放行");
        return true;
    }
}