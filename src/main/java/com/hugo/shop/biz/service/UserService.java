package com.hugo.shop.biz.service;

import com.hugo.shop.biz.model.User;
import com.hugo.shop.data.UserRepository;
import com.hugo.shop.utils.JpaUtil;
import org.apache.tomcat.util.security.MD5Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public void register(User user){
        user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
        userRepository.save(user);
    }

    public List<User> findAll() {
        return (List<User>) userRepository.findAll();
    }

    public Page<User> findAll(Pageable pageable) {
        Page<User> userPage =  userRepository.findAll(pageable);
        if(userPage == null) {
            userPage = Page.empty();
        }
        return userPage;
    }

    public User login(String username, String password) {
        User user;
        if(password != null && password != null) {
            password = DigestUtils.md5DigestAsHex(password.getBytes());
            user = userRepository.findByUsernameAndPassword(username, password);
        } else {
            user = null;
        }
        return user;
    }

    public User update(User user) {
        User oldUser = userRepository.findById(user.getId()).get();
        JpaUtil.copyNotNullProperties(user, oldUser);
        return userRepository.save(oldUser);
    }

    public User findById(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if(optionalUser.isPresent()) {
            return optionalUser.get();
        }
        return null;
    }
}
