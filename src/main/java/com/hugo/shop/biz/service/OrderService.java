package com.hugo.shop.biz.service;

import com.hugo.shop.biz.model.*;
import com.hugo.shop.data.OrderRepository;
import com.hugo.shop.data.ProductRepository;
import com.hugo.shop.data.TrolleyItemRespository;
import com.hugo.shop.data.TrolleyRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private TrolleyItemRespository trolleyItemRespository;
    @Autowired
    private TrolleyRespository trolleyRespository;

    public Orders add(Trolley trolley, User user, String postAddress) {
        Orders order = new Orders();
        order.setCtime(LocalDateTime.now());
        order.setPrice(trolley.getPrice());
        order.setUser(user);
        order.setDiscount(trolley.getDiscount());
        order.setStatus(0);
        order.setPostAddress(postAddress);
        order.setCode(UUID.randomUUID().toString());
        List<TrolleyItem> trolleyItems = trolley.getTrolleyItems();
        List<Long> pids = new ArrayList<Long>();
        Float discountSum = 10f;
        for(TrolleyItem trolleyItem : trolleyItems) {
            pids.add(trolleyItem.getPid());
            discountSum += trolleyItem.getDiscount();
        }
        Float avgDiscount = discountSum / trolleyItems.size();
        order.setDiscount(avgDiscount);
        List<Product> products = productRepository.findAllById(pids);
        order.setProducts(products);

        Orders orders = orderRepository.save(order);
        trolleyRespository.delete(trolley);
        return orders;
    }

    public Page<Orders> findAll(Pageable pageable, User user) {
        if(user == null) {
            return Page.empty();
        }
        Page<Orders> page = orderRepository.findByUser(pageable, user);
        if(page == null){
            page = Page.empty();
        }
        return page;
    }

    public Page<Orders> findAll(Pageable pageable) {
        Page<Orders> orders = orderRepository.findAll(pageable);
        if(orders == null){
            orders = Page.empty();
        }
       return orders;
    }

    public void delete(Long id) {
        Orders order = orderRepository.findById(id).get();
        orderRepository.delete(order);
    }
}
