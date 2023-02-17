package com.hugo.shop.data;

import com.hugo.shop.biz.model.Trolley;
import com.hugo.shop.biz.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrolleyRespository extends JpaRepository<Trolley, Long> {
    Trolley findByUser(User user);
}
