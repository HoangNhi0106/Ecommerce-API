package com.nashtech.ecommerceapi.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Table(name = "image")
@Getter
@Setter
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "image_id")
    private long imageId;

    @Lob
    @Type(type="org.hibernate.type.BinaryType")
    @Column(name = "data", length = Integer.MAX_VALUE)
    private byte[] data;
}
