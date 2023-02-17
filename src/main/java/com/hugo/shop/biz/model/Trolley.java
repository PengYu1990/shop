package com.hugo.shop.biz.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@DynamicUpdate
public class Trolley {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
//    private Long uid;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name ="trolley_id")
    private List<TrolleyItem> trolleyItems = new ArrayList<TrolleyItem>();
    private float price = 0f;
    private float discount;

    @OneToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name="user_id")
    private User user;

    public void addItem(TrolleyItem item) {
        this.trolleyItems.add(item);
        this.price += item.getPrice();
    }

    public void deleteItem(TrolleyItem item) {
        this.trolleyItems.remove(item);
        this.price = this.price - item.getPrice();
    }

}
