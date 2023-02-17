package com.hugo.shop.biz.service;

import com.hugo.shop.biz.model.Product;
import com.hugo.shop.biz.model.Trolley;
import com.hugo.shop.biz.model.TrolleyItem;
import com.hugo.shop.biz.model.User;
import com.hugo.shop.data.TrolleyItemRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
public class TrolleyItemServiceImpl implements TrolleyItemService{

    @Autowired
    private TrolleyItemRespository itemRespository;

    @Override
    public TrolleyItem add(Product product, Trolley trolley) {
        TrolleyItem trolleyItem = new TrolleyItem();
        trolleyItem.setPid(product.getId());
        trolleyItem.setTrolley(trolley);
        trolleyItem.setCtime(LocalDateTime.now());
        trolleyItem.setPrice(product.getCurrentPrice());
        trolleyItem.setTitle(product.getTitle());
        trolleyItem.setImageName(product.getImageName1());
        trolleyItem.setNumber(1); // Default 1
        return itemRespository.save(trolleyItem);
    }

    @Override
    public void delete(Long id) {
        itemRespository.deleteById(id);
    }

    @Override
    public List<TrolleyItem> findByTrolley(Trolley trolley) {
        return itemRespository.findAllByTrolley(trolley);
    }

    @Override
    public TrolleyItem findById(Long id) {
        return itemRespository.findById(id).get();
    }
}
