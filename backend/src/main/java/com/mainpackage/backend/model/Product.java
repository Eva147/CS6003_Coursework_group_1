package com.mainpackage.backend.model;

public class Product {
    private int id; 
    private String name;
    private String description;
    private double price;
    private int quantity;
    private String cathegory;
    private String image;

    // the getters and setters 
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }   
    public void setName(String name){
        this.name = name;
    }
    
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getCathegory() {
        return cathegory;
    }
    public void setCathegory(String cathegory) {
        this.cathegory = cathegory;
    }

    public String getImage() {
        return image;
    }   

    public void setImage(String image) {
        this.image = image;
    }

}
