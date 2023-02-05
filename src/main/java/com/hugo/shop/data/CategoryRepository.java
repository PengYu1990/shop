package com.hugo.shop.data;

import org.springframework.data.repository.CrudRepository;

import com.hugo.shop.biz.model.Category;
import org.springframework.stereotype.Repository;


@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {
}
