package com.hugo.shop.biz.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDateTime;


@Data
@Entity
@DynamicUpdate
public class TrolleyItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
//    private Long uid;
    private Long pid;

//    private Long tid;
    private String title;
    private int number;
    private LocalDateTime ctime;
    private float price;
    private String imageName;

    private float discount;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "trolley_id")
    private Trolley trolley;

}
