package com.hugo.shop.web.config;


import com.hugo.shop.web.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//@Configuration
public class LoginConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration registration = registry.addInterceptor(new LoginInterceptor());
        registration.addPathPatterns("/admin/**");
        registration.excludePathPatterns("/admin/login");
        registration.excludePathPatterns("/**/*.html");
        registration.excludePathPatterns("/**/*.css");
        registration.excludePathPatterns("/**/*.js");
    }
}
