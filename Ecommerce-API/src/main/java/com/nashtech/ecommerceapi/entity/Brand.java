package com.nashtech.ecommerceapi.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Table(name = "brand")
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "brand_id", unique = true)
    private long brandId;

    @Column(name = "bname", nullable = false, unique = true)
    private String bname;

    @Column(name = "made_in")
    private String madeIn;

    @Column(name = "created_in")
    private LocalDateTime createdIn;

    @Column(name = "updated_in")
    private LocalDateTime updatedIn;

}
