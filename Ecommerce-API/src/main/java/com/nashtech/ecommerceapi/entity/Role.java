package com.nashtech.ecommerceapi.entity;

import javax.persistence.*;

@Entity
@Table(name = "role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id", nullable = false)
    private long role_id;

    @Column(name = "rname", nullable = false, unique = true)
    private String rname;

    public long getRole_id() {
        return role_id;
    }

    public String getRname() {
        return rname;
    }

    public void setRname(String rname) {
        this.rname = rname;
    }

    public void setRole_id(long role_id) {
        this.role_id = role_id;
    }
}
