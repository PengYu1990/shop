package com.hugo.shop.biz.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDateTime;
import java.util.List;


@Entity
@Data
@DynamicUpdate
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
//    private Long uid;

    // = id + uid + ctime
    private String code;

    // 0 : Ordered; 1 : paid; 2 : posted; 3 : delivered; 4 : applied refund; 5 : refunded
    private int status;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Product> products;
    private LocalDateTime ctime;
    private float price;
    private float discount;
    private String postAddress;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name="user_id")
    private User user;


}
