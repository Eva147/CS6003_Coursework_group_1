package com.mainpackage.backend.model;

public class CategoryRecord {
    private String category;
    private String id;
    private String image; 

    public CategoryRecord () {}

    public CategoryRecord(String category, String id, String image) {
        this.category = category;
        this.id = id; 
        this.image = image;
    }

    // Getters & Setters
      public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

     @Override
    public String toString() {
        return "Category Record{" +
                "id='" + id + '\'' +
                ", category='" + category + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}