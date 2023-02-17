package com.hugo.shop.data;

import com.hugo.shop.biz.model.Trolley;
import com.hugo.shop.biz.model.TrolleyItem;
import com.hugo.shop.biz.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrolleyItemRespository extends CrudRepository<TrolleyItem, Long> {
    List<TrolleyItem> findAllByTrolley(Trolley trolley);
}
