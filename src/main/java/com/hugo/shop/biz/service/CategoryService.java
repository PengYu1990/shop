package com.hugo.shop.biz.service;

import com.hugo.shop.biz.model.Category;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface CategoryService {

    public Category save(Category category);

    public List<Category> findAll();

    public void delete(Long id);

    Category findById(Long id);
}
