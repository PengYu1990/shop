package com.hugo.shop.web.controller;

import com.hugo.shop.biz.model.Orders;
import com.hugo.shop.biz.model.Trolley;
import com.hugo.shop.biz.model.User;
import com.hugo.shop.biz.service.OrderService;
import com.hugo.shop.biz.service.TrolleyItemService;
import com.hugo.shop.biz.service.TrolleyService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private TrolleyItemService trolleyItemService;

    @Autowired
    private TrolleyService trolleyService;


    @ModelAttribute
    public Orders getOrders() {
        return  new Orders();
    }


    @GetMapping
    public String list(Model model, @PageableDefault Pageable pageable, HttpSession session) {
        User user = (User) session.getAttribute("user");
        Page<Orders> orders = orderService.findAll(pageable, user);
        model.addAttribute("ordersPage", orders);
        return "default/my_order";
    }

    @PostMapping("/add")
    public String add(@RequestParam Long tid, @RequestParam String postAddress, HttpSession session) {
        User user = (User) session.getAttribute("user");
        Trolley trolley = trolleyService.find(tid);
        orderService.add(trolley, user, postAddress);
        return "redirect:/order";
    }

}
