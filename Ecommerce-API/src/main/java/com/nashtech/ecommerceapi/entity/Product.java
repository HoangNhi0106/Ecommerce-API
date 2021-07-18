package com.nashtech.ecommerceapi.entity;

import com.fasterxml.jackson.annotation.JsonGetter;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "product")
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

    @Column(name = "amount")
    private int amount;

    @Column(name = "sold")
    private int sold;

    @Column(name = "price")
    private long price;

    @Column(name = "rating_star")
    private Float rating;

    @OneToOne
    @JoinColumn(name = "image_id")
    private Image image;

    @Column(name = "created_in")
    private LocalDateTime createdIn;

    @Column(name = "updated_in")
    private LocalDateTime updatedIn;

    @Column(name = "description")
    private String description;
}
