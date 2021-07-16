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
    @Column(name = "productId", unique = true)
    private long product_id;

    @Column(name = "pname", nullable = false)
    private String pname;

    @ManyToOne
    @JoinColumn(name = "categoryId", nullable = false)
    private Category category;

    @Column(name = "amount")
    private int amount;

    @Column(name = "sold")
    private int sold;

    @Column(name = "price")
    private long price;

    @Column(name = "ratingStar")
    private Float rating;

    @Column(name = "image")
    private String image;

    @Column(name = "createdIn")
    private String created_in;

    @Column(name = "updatedIn")
    private String updated_in;

    @Column(name = "description")
    private String description;

}
