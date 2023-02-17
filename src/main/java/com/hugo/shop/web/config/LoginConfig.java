package com.hugo.shop.web.config;


import com.hugo.shop.web.interceptor.AdminLoginInterceptor;
import com.hugo.shop.web.interceptor.UserLoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class LoginConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {


        //Admin Config
        InterceptorRegistration adminReg = registry.addInterceptor(new AdminLoginInterceptor());
        adminReg.addPathPatterns("/admin/**");
        adminReg.excludePathPatterns("/admin/login");
        adminReg.excludePathPatterns("/**/*.html");
        adminReg.excludePathPatterns("/**/*.css");
        adminReg.excludePathPatterns("/**/*.js");

        // User Config
        InterceptorRegistration userReg = registry.addInterceptor(new UserLoginInterceptor());
        userReg.addPathPatterns("/order/**");
        userReg.addPathPatterns("/trolley/**");
        userReg.addPathPatterns("/user/**");

        userReg.excludePathPatterns("/user/login");
        userReg.excludePathPatterns("/user/logout");
        userReg.excludePathPatterns("/user/register");
        userReg.excludePathPatterns("/*.html");
        userReg.excludePathPatterns("/*.css");
        userReg.excludePathPatterns("/*.js");

    }
}
