package com.nashtech.ecommerceapi.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int category_id;

    @Column(name = "cname")
    private String name;

    @OneToMany(mappedBy = "category")
    private List<Product> products;

    public int getCategory_id() { return category_id; }

    public String getName() { return name; }

    public List<Product> getProducts() { return products; }

    public void setCategory_id(int category_id) { this.category_id = category_id; }

    public void setName(String name) { this.name = name; }

    public void setProducts(List<Product> products) { this.products = products; }
}
