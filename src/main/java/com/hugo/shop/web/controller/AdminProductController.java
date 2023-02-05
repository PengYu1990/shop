package com.hugo.shop.web.controller;


import com.hugo.shop.biz.model.Category;
import com.hugo.shop.biz.model.Product;
import com.hugo.shop.biz.service.CategoryService;
import com.hugo.shop.biz.service.ProductService;
import com.hugo.shop.data.FileStorageRepository;
import com.hugo.shop.web.dto.ProductDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Controller
@RequestMapping("/admin/product")
public class AdminProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private FileStorageRepository fileStorageRepository;

    @ModelAttribute
    public Product getProduct(){
        return new Product();
    }

    @GetMapping("/add")
    public String addProduct(Model model) {
        List<Category> categories = categoryService.findAll();
        model.addAttribute("categories", categories);
        return "/default/admin/product_add";
    }

    @PostMapping("/add")
    public String addProduct(@Valid Product product, Errors errors,
            @RequestParam MultipartFile image1,
                             @RequestParam MultipartFile image2,
                             @RequestParam MultipartFile image3,
                             @RequestParam MultipartFile image4
                             ) throws IOException {
        if (!errors.hasErrors()) {

            if(!image1.isEmpty()) {
                String fileName1 = fileStorageRepository.save(image1.getOriginalFilename(), image1.getInputStream());
                product.setImageName1(fileName1);
            }
            if(!image2.isEmpty()) {
                String fileName2 = fileStorageRepository.save(image2.getOriginalFilename(), image2.getInputStream());
                product.setImageName2(fileName2);
            }
            if(!image3.isEmpty()) {
                String fileName3 = fileStorageRepository.save(image3.getOriginalFilename(), image3.getInputStream());
                product.setImageName3(fileName3);
            }
            if(!image4.isEmpty()) {
                String fileName4 = fileStorageRepository.save(image4.getOriginalFilename(), image4.getInputStream());
                product.setImageName4(fileName4);
            }

            if(product.getCategory() != null && product.getCategory().contains("-")) {
                String[] categorySplit = product.getCategory().split("-");
                product.setCategoryId(Long.valueOf(categorySplit[0]));
                product.setCategory(categorySplit[1]);
            }

            Product savedProduct = productService.addProduct(product);
            // 此块代码报错，需要找原因
//        System.out.printf(savedProduct.toString());


            return "redirect:/admin/product/list";
        }
        System.out.printf(errors.toString());
        return "/default/admin/product_add";

    }



    @GetMapping("/edit/{id}")
    public String editProduct(@PathVariable("id") Long id, Model model) {
        try {
            Optional<Product> optionalProduct = productService.getProductById(id);
            Product product = optionalProduct.get();
            List<Category> categories = categoryService.findAll();
            model.addAttribute("product",product);
            model.addAttribute("categories", categories);
            return "default/admin/product_edit";
        } catch (NoSuchElementException noSuchElementException) {
            noSuchElementException.printStackTrace();
            return "default/404";
        }

    }

    @PostMapping("/update")
    public String updateProduct(@Valid ProductDTO productDTO, @RequestParam MultipartFile image1,
                                @RequestParam MultipartFile image2,
                                @RequestParam MultipartFile image3,
                                @RequestParam MultipartFile image4, Errors errors) {
        if (!errors.hasErrors()) {
            try {
                if(!image1.isEmpty()) {
                    String fileName1 = fileStorageRepository.save(image1.getOriginalFilename(), image1.getInputStream());
                    productDTO.setImageName1(fileName1);
                }
                if(!image2.isEmpty()) {
                    String fileName2 = fileStorageRepository.save(image2.getOriginalFilename(), image2.getInputStream());
                    productDTO.setImageName2(fileName2);
                }
                if(!image3.isEmpty()) {
                    String fileName3 = fileStorageRepository.save(image3.getOriginalFilename(), image3.getInputStream());
                    productDTO.setImageName3(fileName3);
                }
                if(!image4.isEmpty()) {
                    String fileName4 = fileStorageRepository.save(image4.getOriginalFilename(), image4.getInputStream());
                    productDTO.setImageName4(fileName4);
                }
            } catch (IOException exception){
                exception.printStackTrace();
            }

            if(productDTO.getCategory() != null && productDTO.getCategory().contains("-")) {
                String[] categorySplit = productDTO.getCategory().split("-");
                productDTO.setCategoryId(Long.valueOf(categorySplit[0]));
                productDTO.setCategory(categorySplit[1]);
            }

            productService.updateProduct(productDTO);
            return "redirect:/admin/product/list";
        }
        return "/default/admin/product_add";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable("id") Long id, Model model) {
        productService.deleteProduct(id);
        return "redirect:/admin/product/list";
    }


    @GetMapping("/list")
    public String listProduct(Model model) {
        List<Product> productList = productService.getAllProduct();
        model.addAttribute("productList", productList);
        return "default/admin/product_list";

    }
}
