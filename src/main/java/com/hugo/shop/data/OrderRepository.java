package com.hugo.shop.data;

import com.hugo.shop.biz.model.Orders;
import com.hugo.shop.biz.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Orders,Long> {
    Page<Orders> findByUser(Pageable pageable, User user);
}
