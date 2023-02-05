package com.hugo.shop.web.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ImageConfig implements WebMvcConfigurer {
    // @Value可以将配置文件的内容自动注入到属性内
    @Value("${user.upload.images.product.path}")
    private String imagePath;
    @Value("${user.upload.images.product.mapper-url}")
    private String imageMapperPath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(imageMapperPath + "**").addResourceLocations("file:" + imagePath);
    }
}
