package com.hugo.shop.biz.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Tittle should not be empty")
    private String title;

    @NotEmpty(message = "please select a category")
    private String category;

    @NotNull(message = "please set a original price")
    private float price;

    private float discount = 10;

    @Lob
    @Column( length = 1000000000 )
    @NotEmpty(message = "Summary should not be empty")
    private String summary;
    @Lob
    @Column( length = 1000000000 )
    @NotEmpty(message = "description should not be empty")
    private String description;


    private String imageUrl;

    @NotEmpty(message = "Weight should not be empty")
    private String weight;

    @NotEmpty(message = "Dimensions should not be empty")
    private String dimensions;
    @NotEmpty(message = "Materials should not be empty")
    private String materials;
    private String otherInfo;

    private LocalDateTime ctime;

    private LocalDateTime mtime;


    private int viewTimes = 0;
    private int addToCarTimes = 0;
    private int volume = 0;
    private int avgRating = 5;

    private String imageUrl1;
    private String imageUrl2;
    private String imageUrl3;

    public float getCurrentPrice() {
        return this.price * this.discount/10;
    }



}
