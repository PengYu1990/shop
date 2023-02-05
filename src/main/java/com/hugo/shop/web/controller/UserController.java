package com.hugo.shop.web.controller;

import com.hugo.shop.biz.model.User;
import com.hugo.shop.biz.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @ModelAttribute("users")
    public List<User> getUsers(){
        return userService.findAll();
    }

    @ModelAttribute
    public User getUser(){
        User user = new User();
        user.setEmail("yp1990@gmail.com");
        return user;
    }

    @PostMapping("/login")
    public String login(String username, String password) {
        User loginUser = userService.login(username, password);
        if(loginUser != null ) {
            System.out.printf("登录成功"+loginUser.toString());
        } else {
            System.out.printf("登录失败");
        }
        return "redirect:/user/login";
    }

    @PostMapping("/register")
    public String register(User user){
        userService.register(user);
        return "redirect:/user/login";
    }

    @GetMapping("/login")
    public String showLoginPage(Model model) {
//        List<User> users = userService.findAll();
//        model.addAttribute("users", users);
        return "default/login";
    }
}
