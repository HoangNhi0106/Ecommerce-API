package com.nashtech.ecommerceapi.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id", nullable = false)
    private long category_id;

    @Column(name = "cname", nullable = false)
    private String name;

    @OneToMany(targetEntity = Product.class, fetch = FetchType.LAZY, mappedBy = "category")
    private List<Product> products;

    public long getCategory_id() { return category_id; }

    public String getName() { return name; }

    public List<Product> getProducts() { return products; }

    public void setCategory_id(long category_id) { this.category_id = category_id; }

    public void setName(String name) { this.name = name; }

    public void setProducts(List<Product> products) { this.products = products; }
}
