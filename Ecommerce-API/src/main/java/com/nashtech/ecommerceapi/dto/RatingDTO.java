package com.nashtech.ecommerceapi.dto;

import java.util.Date;

public class RatingDTO {
    private long rating_id;
    private long product_id;
    private long account_id;
    private Date date;
    private float star;

    public void setStar(float star) {
        this.star = star;
    }

    public void setRating_id(long rating_id) {
        this.rating_id = rating_id;
    }

    public void setProduct_id(long product_id) {
        this.product_id = product_id;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setAccount_id(long account_id) {
        this.account_id = account_id;
    }

    public long getRating_id() {
        return rating_id;
    }

    public long getProduct_id() {
        return product_id;
    }

    public float getStar() {
        return star;
    }

    public Date getDate() {
        return date;
    }

    public long getAccount_id() {
        return account_id;
    }
}
