package com.hugo.shop.biz.service;

import com.hugo.shop.biz.model.Product;
import com.hugo.shop.data.FileStorageRepository;
import com.hugo.shop.data.ProductRepository;
import com.hugo.shop.utils.JpaUtil;
import com.hugo.shop.web.dto.ProductDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private FileStorageRepository fileStorageRepository;

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

    public void updateProduct(ProductDTO productDTO) {
        if(productDTO.getId() != null) {

            Product oldProduct = productRepository.findById(productDTO.getId()).orElseThrow(() -> new EntityNotFoundException());

            //Delete old images
            if(productDTO.getImageName1() != null) {
                fileStorageRepository.delete(oldProduct.getImageName1());
            }
            if(productDTO.getImageName2() != null) {
                fileStorageRepository.delete(oldProduct.getImageName2());
            }
            if(productDTO.getImageName3() != null) {
                fileStorageRepository.delete(oldProduct.getImageName3());
            }
            if(productDTO.getImageName4() != null) {
                fileStorageRepository.delete(oldProduct.getImageName4());
            }
            JpaUtil.copyNotNullProperties(productDTO, oldProduct);
            productRepository.save(oldProduct);
        }
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
