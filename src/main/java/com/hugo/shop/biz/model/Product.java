package com.hugo.shop.biz.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@DynamicUpdate
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Tittle should not be empty")
    private String title;

    private Long categoryId;

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


    private String imageName1;
    private String imageName2;
    private String imageName3;
    private String imageName4;

    @NotEmpty(message = "Weight should not be empty")
    private String weight;

    @NotEmpty(message = "Dimensions should not be empty")
    private String dimensions;
    @NotEmpty(message = "Materials should not be empty")
    private String materials;
    private String otherInfo;

    private LocalDateTime ctime;

    private LocalDateTime mtime;


    private int viewTimes;
    private int addToCarTimes;
    private int volume;
    private int avgRating;


    public float getCurrentPrice() {
        return this.price * this.discount/10;
    }



}
