package com.hugo.shop;

import com.hugo.shop.data.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ProductRepositoryTest {


    @Autowired
    private ProductRepository productRepository;

    @Test
    public void testGetImageName1ById() {
        System.out.println(productRepository.findImagename1ById(19l));
    }
}
