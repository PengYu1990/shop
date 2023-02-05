package com.hugo.shop.data;

import com.hugo.shop.biz.model.User;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

import java.time.LocalDate;
import java.util.List;

//@Component
public class UserDataLoader implements ApplicationRunner {
    private UserRepository userRepository;

    public UserDataLoader(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if(userRepository.count() == 0) {
            List<User> users = List.of(
//                    new User(null, "ypydd", "qqdfa", "Peng", "Yu", LocalDate.of(1989, 12, 5), "yp1990@gmail.com"),
//                    new User(null, "ppkd", "sdf", "Hugo", "Yu", LocalDate.of(1999, 12, 5), "ddsdf@dail.com")

            );
            userRepository.saveAll(users);
        }
    }
}
