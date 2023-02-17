package com.hugo.shop.web.controller;


import com.hugo.shop.biz.model.Product;
import com.hugo.shop.biz.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
public class IndexController {

    @Autowired
    private ProductService productService;

    @ModelAttribute
    public Product getProduct(){
        return new Product();
    }

    @GetMapping
    public String index(Model model, @PageableDefault(size=8) Pageable pageable){
        Page<Product> latestProducts = productService.getAllProduct(pageable);
        List<Product> productsOnSale = productService.getOnSaleProduct();
        model.addAttribute("latestProducts",latestProducts);
        model.addAttribute("productsOnSale",productsOnSale);
        return "default/index";
    }

    @GetMapping("/404")
    public String pageNotFound() {
        return "default/404";
    }
}
