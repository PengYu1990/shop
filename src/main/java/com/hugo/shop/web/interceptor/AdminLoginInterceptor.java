package com.hugo.shop.web.interceptor;

import com.hugo.shop.biz.model.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AdminLoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        User user = (User) request.getSession().getAttribute("admin_user");
        if(user != null && user.getType() == 0) {
            return true;
        }
        response.sendRedirect(request.getContextPath() + "/admin/login");
        return false;
    }
}
