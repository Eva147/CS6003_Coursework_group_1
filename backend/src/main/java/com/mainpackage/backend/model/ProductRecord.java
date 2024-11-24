package com.mainpackage.backend.model;

public class ProductRecord {
    private String id;
    private String name;
    private String description;
    private float price;
    private int quantity;
    private String catalogId;
    private String image;

    // Default constructor
    public ProductRecord() {}

    // Constructor with all parameters
    public ProductRecord(String id, String name, String description, float price, int quantity, String catalogId, String image) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.catalogId = catalogId;
        this.image = image;
    }

    // Getters and Setters
    public String getProductId() {
        return id;
    }

    public void setProductId(String productId) {
        this.id = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(String catalogId) {
        this.catalogId = catalogId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
