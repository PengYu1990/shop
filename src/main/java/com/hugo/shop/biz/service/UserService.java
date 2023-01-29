package com.hugo.shop.biz.service;

import com.hugo.shop.biz.model.User;
import com.hugo.shop.data.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public void register(User user){
        userRepository.save(user);
    }

    public List<User> findAll() {
        return (List<User>) userRepository.findAll();
    }

    public User login(User user) {
        if(user != null && user.getUsername() != null && user.getPassword() != null) {
            return userRepository.findByUsername(user.getUsername());
        }
        return null;
    }
}
