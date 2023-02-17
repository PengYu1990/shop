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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.NoSuchElementException;
import java.util.Optional;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @ModelAttribute
    public Product getProduct(){
        return new Product();
    }

    @GetMapping("/{id}")
    public String view(@PathVariable("id") Long id, Model model) {
        Optional<Product> optionalProduct = productService.getProductById(id);
        try {
            Product product = optionalProduct.get();
            product = productService.updateViewTimes(product);
            model.addAttribute("thisProduct", product);

        } catch (NoSuchElementException exception) {
            return "redirect:/404";
        }

        return "default/single-product";
    }

    @GetMapping
    public String list(Model model, @PageableDefault(size = 12) Pageable pageable) {
        Page<Product> productPage = productService.getAllProduct(pageable);
        model.addAttribute("productPage", productPage);
        return "default/product_list";
    }

}
