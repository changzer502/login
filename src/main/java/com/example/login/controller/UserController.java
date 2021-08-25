package com.example.login.controller;

import com.example.login.entity.User;
import com.example.login.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping("/index")
    public String index(Model model,HttpServletRequest request){
        return "index";
    }

    @RequestMapping("/login")
    public String login(Model model){
        return "login";
    }

    @RequestMapping("/register")
    public String regist(){
        return "regist";
    }

    @RequestMapping("doLogin")
    public String doLogin(User user, HttpServletRequest request, HttpServletResponse response, Model model) throws IOException, ServletException {
        User getUser = userService.getUserByNameAndPwd(user.getName(), user.getPassword());

        if(getUser == null || user.getName() == "" || user.getPassword() == "") {
//            request.getSession().setAttribute("msg","用户名或密码错误");
//            response.sendRedirect("/login");
//            request.getRequestDispatcher("/login").forward(request,response);
            model.addAttribute("msg","用户名或密码错误");
            return "login";
        }
        else {
            request.getSession().setAttribute("session-user",user.getName());
            response.sendRedirect("/welcome");
            return "welcome";
        }
    }

    @RequestMapping("doRegist")
    public String doRegist(User user,Model model,HttpServletResponse response) throws IOException {
        User user1 = userService.getUserByName(user.getName());
        if(user1 != null){
            model.addAttribute("msg","该用户名已存在");
            return "regist";
        }else if(user.getName() == ""){
            model.addAttribute("msg","用户名不能为空");
            return "regist";
        }else if(user.getPassword() == ""){
            model.addAttribute("msg","密码不能为空");
            return "regist";
        }
        else
            userService.saveUser(user.getName(),user.getPassword());
            response.sendRedirect("/login");
        return "login";
    }


    @RequestMapping("outUser")
    public void outUser(HttpServletResponse response, HttpServletRequest request,HttpSession session) throws IOException {
       // request.getSession().removeAttribute("session-user");
        session.invalidate();
        response.sendRedirect("/index");
    }

    @RequestMapping("welcome")
    public String welcome(){
        return "welcome";
    }

}
