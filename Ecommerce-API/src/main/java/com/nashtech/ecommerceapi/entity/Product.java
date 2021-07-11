package com.nashtech.ecommerceapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id", nullable = false)
    private long product_id;

    @Column(name = "pname", nullable = false)
    private String name;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @Column(name = "amount")
    private int amount;

    @Column(name = "sold")
    private int sold;

    @Column(name = "price")
    private long price;

    @Column(name = "rating")
    private float rating;

    @Column(name = "image")
    private String image;

    @Column(name = "created_in")
    private String created_in;

    @Column(name = "updated_in")
    private String updated_in;

    @Column(name = "description")
    private String description;

    public String getDescription() { return description; }

    public long getProduct_id() { return product_id; }

    public String getName() { return name; }

    public float getRating() { return rating; }

    public int getAmount() { return amount; }

    public int getSold() { return sold; }

    public Category getCategory() { return category; }

    public long getPrice() { return price; }

    public String getImage() { return image; }

    public String getCreated_in() { return created_in; }

    public String getUpdated_in() { return updated_in; }

    public void setAmount(int amount) { this.amount = amount; }

    public void setCategory(Category category) { this.category = category; }

    public void setProduct_id(long product_id) { this.product_id = product_id; }

    public void setCreated_in(String created_in) { this.created_in = created_in; }

    public void setName(String name) { this.name = name; }

    public void setDescription(String description) { this.description = description; }

    public void setImage(String image) { this.image = image; }

    public void setPrice(long price) { this.price = price; }

    public void setRating(float rating) { this.rating = rating; }

    public void setSold(int sold) { this.sold = sold; }

    public void setUpdated_in(String updated_in) { this.updated_in = updated_in; }
}
