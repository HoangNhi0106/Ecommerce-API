package com.nashtech.ecommerceapi.entity;

import com.nashtech.ecommerceapi.constant.ERole;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "role")
@Getter
@Setter
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id", unique = true)
    private long role_id;

    @Enumerated(EnumType.STRING)
    @Column(name = "rname", nullable = false, unique = true)
    private ERole rname;
}
