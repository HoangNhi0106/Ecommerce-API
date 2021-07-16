package com.nashtech.ecommerceapi.entity;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "category")
@Getter
@Setter
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "categoryId", unique = true)
    private long categoryId;

    @Column(name = "cname", nullable = false)
    private String cname;

    @OneToMany(targetEntity = Product.class, fetch = FetchType.LAZY, mappedBy = "category")
    private List<Product> products;

}
