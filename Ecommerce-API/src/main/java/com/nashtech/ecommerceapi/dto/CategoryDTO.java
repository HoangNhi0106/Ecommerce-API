package com.nashtech.ecommerceapi.dto;

public class CategoryDTO {
    private long category_id;

    private String cname;

    public void setCategory_id(long category_id) {
        this.category_id = category_id;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public long getCategory_id() {
        return category_id;
    }

    public String getCname() {
        return cname;
    }
}
