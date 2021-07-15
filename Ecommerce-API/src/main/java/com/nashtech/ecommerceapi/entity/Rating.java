package com.nashtech.ecommerceapi.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "rating")
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rating_id", unique = true)
    private long rating_id;

    @OneToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @OneToOne
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    @Column(name = "date")
    private Date date;

    @Column(name = "star", nullable = false)
    private float star;

    public void setAccount(Account account) {
        this.account = account;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setRating_id(long rating_id) {
        this.rating_id = rating_id;
    }

    public void setStar(float star) {
        this.star = star;
    }

    public Date getDate() {
        return date;
    }

    public float getStar() {
        return star;
    }

    public Account getAccount() {
        return account;
    }

    public Product getProduct() {
        return product;
    }

    public long getRating_id() {
        return rating_id;
    }
}
