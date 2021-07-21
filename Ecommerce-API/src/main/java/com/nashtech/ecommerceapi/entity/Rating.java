package com.nashtech.ecommerceapi.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.time.LocalDateTime;

@Entity
@Table(name = "rating",
        indexes = {
                @Index(name = "rating_account_idx" , columnList = "account_id"),
                @Index(name = "rating_product_idx" , columnList = "product_id")
        })
@Getter
@Setter
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rating_id", unique = true)
    private long ratingId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    @Column(name = "date")
    private LocalDateTime date;

    @Min(value = 0, message = "The star must be a positive ingeter")
    @Max(value = 5, message = "Maximum star is 5")
    @Column(name = "star", nullable = false)
    private int star;

    @Column(name = "comment")
    private String comment;
}
