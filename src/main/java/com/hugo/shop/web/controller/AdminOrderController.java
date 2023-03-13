package com.hugo.shop.web.controller;


import com.hugo.shop.biz.model.Orders;
import com.hugo.shop.biz.model.Product;
import com.hugo.shop.biz.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin/order")
public class AdminOrderController {

    @Autowired
    private OrderService orderService;


    @GetMapping("/list")
    public String list(@PageableDefault(size = 10) Pageable pageable, Model model){
        Page<Orders> orders = orderService.findAll(pageable);
        model.addAttribute("orders", orders);
        return "default/admin/order_list";

    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id){
        orderService.delete(id);
        return "redirect:/admin/order/list";
    }
}
