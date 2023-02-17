package com.hugo.shop.biz.service;


import com.hugo.shop.biz.model.Product;
import com.hugo.shop.biz.model.Trolley;
import com.hugo.shop.biz.model.TrolleyItem;
import com.hugo.shop.biz.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TrolleyItemService {
    public TrolleyItem add(Product product, Trolley trolley);
    public void delete(Long id);

    List<TrolleyItem> findByTrolley(Trolley trolley);

    TrolleyItem findById(Long id);
}
