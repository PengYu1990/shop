package com.hugo.shop.biz.service;

import com.hugo.shop.biz.model.Product;
import com.hugo.shop.biz.model.Trolley;
import com.hugo.shop.biz.model.TrolleyItem;
import com.hugo.shop.biz.model.User;
import com.hugo.shop.data.TrolleyRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TrolleyService {
    @Autowired
    private TrolleyRespository trolleyRespository;


    @Autowired
    private TrolleyItemService trolleyItemService;


    @Autowired
    private ProductService productService;

    public Trolley find(Long id) {
        return trolleyRespository.findById(id).get();
    }


    public Trolley findByUser(User user) {
        return trolleyRespository.findByUser(user);
    }

    public void save(Trolley trolley) {
        trolleyRespository.save(trolley);
    }

    public void save(User user, Long pid) {
        Trolley trolley = findByUser(user);
        if(trolley == null) {
            trolley = new Trolley();
        }
        trolley.setUser(user);
        Product product = productService.getProductById(pid).get();
        TrolleyItem trolleyItem = trolleyItemService.add(product, trolley);
        trolley.addItem(trolleyItem);
        trolley.setDiscount(product.getDiscount());
//        trolley.addDiscount(trolleyItem.getDi);
        trolleyRespository.save(trolley);
    }

    public void deleteItem(Long id) {
        TrolleyItem item = trolleyItemService.findById(id);
        Trolley trolley = item.getTrolley();
        trolley.deleteItem(item);
        trolleyRespository.save(trolley);
        trolleyItemService.delete(item.getId());
    }
}
