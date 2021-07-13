package com.nashtech.ecommerceapi.entity;

import com.nashtech.ecommerceapi.constant.ERole;

import javax.persistence.*;

@Entity
@Table(name = "role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id", unique = true)
    private long role_id;

    @Enumerated(EnumType.STRING)
    @Column(name = "rname", nullable = false, unique = true)
    private ERole rname;

    public long getRole_id() {
        return role_id;
    }

    public ERole getRname() {
        return rname;
    }

    public void setRname(ERole rname) {
        this.rname = rname;
    }

    public void setRole_id(long role_id) {
        this.role_id = role_id;
    }
}
