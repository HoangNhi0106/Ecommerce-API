package com.nashtech.ecommerceapi.entity;

import com.fasterxml.jackson.annotation.JsonGetter;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "product")
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id", unique = true)
    private long product_id;

    @Column(name = "pname", nullable = false)
    private String name;

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

    @Column(name = "image")
    private String image;

    @Column(name = "created_in")
    private String created_in;

    @Column(name = "updated_in")
    private String updated_in;

    @Column(name = "description")
    private String description;

}
