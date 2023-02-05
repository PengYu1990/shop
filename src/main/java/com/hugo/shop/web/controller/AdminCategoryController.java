package com.hugo.shop.web.controller;

import com.hugo.shop.biz.model.Category;
import com.hugo.shop.biz.service.CategoryService;
import com.hugo.shop.data.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/category")
public class AdminCategoryController {

    @Autowired
    private CategoryService categoryService;

    @ModelAttribute
    public Category getCategory() {
        return new Category();
    }


    @GetMapping
    public String list(Model model) {
        List<Category> categories = categoryService.findAll();
        model.addAttribute("categories", categories);
        return "default/admin/category_list";
    }

    @GetMapping(params = "action=add")
    public String showAdd() {
        return "default/admin/category_add";
    }

    @GetMapping(params = "action=edit")
    public String showEdit(@RequestParam Long id, Model model) {
        Category category = categoryService.findById(id);
        model.addAttribute("category", category);
        return "default/admin/category_add";
    }

    @PostMapping
    public String save(Category category) {
        categoryService.save(category);
        return "redirect:/admin/category";
    }

    @GetMapping(params = "action=delete")
    public String delete(@RequestParam Long id) {
        categoryService.delete(id);
        return "redirect:/admin/category";
    }


}
