package com.nashtech.ecommerceapi.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "rating")
@Getter
@Setter
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ratingId", unique = true)
    private long rating_id;

    @OneToOne
    @JoinColumn(name = "productId", nullable = false)
    private Product product;

    @OneToOne
    @JoinColumn(name = "accountId", nullable = false)
    private Account account;

    @Column(name = "date")
    private Date date;

    @Column(name = "star", nullable = false)
    private float star;
}
