package com.nashtech.ecommerceapi.dto;

public class ProductDTO {
    private String name;

    private String description;

    private String price;

    public void setPrice(String price) { this.price = price; }

    public void setName(String name) { this.name = name; }

    public void setDescription(String description) { this.description = description; }

    public String getName() { return name; }

    public String getDescription() { return description; }

    public String getPrice() { return price; }
}
