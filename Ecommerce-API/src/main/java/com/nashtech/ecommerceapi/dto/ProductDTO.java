package com.nashtech.ecommerceapi.dto;

public class ProductDTO {
    private long product_id;

    private String name;

    private String category_name;

    private String description;

    private Float rating;

    private long price;

    public void setProduct_id(long product_id) { this.product_id = product_id; }

    public void setPrice(long price) { this.price = price; }

    public void setName(String name) { this.name = name; }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public void setDescription(String description) { this.description = description; }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public long getProduct_id() { return product_id; }

    public String getName() { return name; }

    public String getDescription() { return description; }

    public long getPrice() { return price; }

    public Float getRating() {
        return rating;
    }

    public String getCategory_name() {
        return category_name;
    }
}
