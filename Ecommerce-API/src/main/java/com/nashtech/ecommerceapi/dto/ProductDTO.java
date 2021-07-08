package com.nashtech.ecommerceapi.dto;

public class ProductDTO {
    private int product_id;

    private String name;

    private String description;

    private String price;

    public void setProduct_id(int product_id) { this.product_id = product_id; }

    public void setPrice(String price) { this.price = price; }

    public void setName(String name) { this.name = name; }

    public void setDescription(String description) { this.description = description; }

    public int getProduct_id() { return product_id; }

    public String getName() { return name; }

    public String getDescription() { return description; }

    public String getPrice() { return price; }
}
