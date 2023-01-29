package com.hugo.shop.biz.service;

import com.hugo.shop.biz.model.Product;
import com.hugo.shop.data.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public Product addProduct(Product product) {
        if(product != null) {
            product.setCtime(LocalDateTime.now());
            product.setMtime(LocalDateTime.now());
        }
        return productRepository.save(product);
    }

    public Product updateViewTimes(Product product){
        product.setViewTimes(product.getViewTimes()+1);
        productRepository.save(product);
        return product;
    }

    public List<Product> getAllProduct() {
        return (List<Product>) productRepository.findAll();
    }

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }
}
