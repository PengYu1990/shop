package com.hugo.shop.web.controller;

import com.hugo.shop.biz.model.User;
import com.hugo.shop.biz.service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminUserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String index(HttpSession session) {
        if(session.getAttribute("user") != null) {
            return "redirect:/admin/product/list";
        } else {
            return "redirect:/admin/login";
        }

    }

    @ModelAttribute
    public User getUser() {
        return new User();
    }


    @GetMapping("/login")
    public String login() {
        return "default/admin/login";
    }


    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, HttpSession session) {

        User user = userService.login(username, password);
        if(user != null && user.getType() == 0) {
            session.setAttribute("user", user);
        }

        return "redirect:/admin/product/list";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        if(session.getAttribute("user") != null) {
            session.removeAttribute("user");
        }
        return "redirect:/admin/login";
    }

    @GetMapping("/user/list")
    public String userList(Model model) {
        List<User>  users = userService.findAll();
        model.addAttribute("users",users);
        return "default/admin/user_list";
    }

    @GetMapping("/user/add")
    public String showAddUser() {
        return "default/admin/user_add";
    }

    @PostMapping("/user/add")
    public String addUser(@Valid User user, Errors errors) {
        if(!errors.hasErrors()) {
            userService.register(user);
            return "redirect:/admin/user/list";
        }
        return "/default/admin/user_add";

    }

    @GetMapping("/user/edit/{id}")
    public String showEdit(@PathVariable("id") Long id, Model model) {
        User user = userService.findById(id);
        model.addAttribute("user", user);
        return "/default/admin/user_edit";
    }

    @PostMapping("/user/update")
    public String updateUser(@Valid User user, Errors errors) {
        if(!errors.hasErrors()) {
            userService.update(user);
            return "redirect:/admin/user/list";
        }
        return "/default/admin/user_edit";
    }
}
