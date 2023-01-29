package com.hugo.shop.web.controller;


import com.hugo.shop.biz.model.Product;
import com.hugo.shop.biz.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/product")
public class AdminProductController {

    @Autowired
    private ProductService productService;

    @ModelAttribute
    public Product getProduct(){
        return new Product();
    }

    @GetMapping("/add")
    public String addProduct() {
        return "/default/admin/product_add";
    }

    @PostMapping("/add")
    public String addProduct(@Valid Product product, Errors errors){
        if (!errors.hasErrors()) {
            Product savedProduct = productService.addProduct(product);
            // 此块代码报错，需要找原因
//        System.out.printf(savedProduct.toString());
            return "redirect:/admin/product/add";
        }
        return "/default/admin/product_add";

    }
}
