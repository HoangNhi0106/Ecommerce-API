package com.nashtech.ecommerceapi.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.time.LocalDateTime;

@Entity
@Table(name = "product",
        indexes = {
        @Index(name = "product_idx" , columnList = "product_id , pname"),
        @Index(name = "product_categoryId_idx" , columnList = "category_id")
    })
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id", unique = true)
    private long productId;

    @Column(name = "pname", nullable = false)
    private String pname;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @Min(value = 0)
    @Column(name = "amount")
    private int amount;

    @Min(value = 0)
    @Column(name = "sold")
    private int sold;

    @Min(value = 0)
    @Column(name = "price")
    private long price;

    @Min(value = 0, message = "The star must be a positive ingeter")
    @Max(value = 5, message = "Maximum star is 5")
    @Column(name = "rating_star")
    private Float rating;

    //nhieu hinh anh
    @OneToOne
    @JoinColumn(name = "image_id")
    private Image image;

    @Column(name = "created_in")
    private LocalDateTime createdIn;

    @Column(name = "updated_in")
    private LocalDateTime updatedIn;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "brand_id", nullable = false)
    private Brand brand;

    @Column(name = "volume")
    private String volume;

    @Column(name = "made_in")
    private String madeIn;

    @Column(name = "skin_type")
    private String skinType;
}
