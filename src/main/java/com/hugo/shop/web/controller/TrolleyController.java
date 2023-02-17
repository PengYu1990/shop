package com.hugo.shop.web.controller;


import com.hugo.shop.biz.model.*;
import com.hugo.shop.biz.service.ProductService;
import com.hugo.shop.biz.service.TrolleyItemService;
import com.hugo.shop.biz.service.TrolleyService;
import com.hugo.shop.biz.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/trolley")
public class TrolleyController {

    @Autowired
    private TrolleyItemService trolleyItemService;

    @Autowired
    private TrolleyService trolleyService;

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;


    @GetMapping
    public String list(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        Trolley trolley = trolleyService.findByUser(user);
        if(trolley == null || trolley.getTrolleyItems() ==null) {
            trolley = new Trolley();
            trolley.setTrolleyItems(new ArrayList<>());
        }
        model.addAttribute("trolley", trolley);
//        List<TrolleyItem> items = trolleyItemService.findByUser(user);
//        model.addAttribute("items", items);
        return "/default/cart";
    }

    @ModelAttribute
    public Orders getOrders() {
        return  new Orders();
    }

    @GetMapping("/add/{pid}")
    public String add(@PathVariable("pid") Long pid, HttpSession session) {
        User user = (User) session.getAttribute("user");
        trolleyService.save(user, pid);
        return "redirect:/trolley";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
         trolleyService.deleteItem(id);
        return "redirect:/trolley";
    }
}
