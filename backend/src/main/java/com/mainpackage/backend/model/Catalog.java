package com.mainpackage.backend.model;

import java.util.List;

public class Catalog {
    private String catalogName;
    private List<Product> products;

    //creating Constructor
    public Catalog(String catalogName, List<Product> products) {
        this.catalogName = catalogName;
        this.products = products;
    }

    //creating getters and setters
    public String getCatalogName() {
        return catalogName;
    }
    public void setCatalogName(String catalogName) {
        this.catalogName = catalogName;
    }

    public List<Product> getProducts() {
        return products;
    }
    public void setProducts(List<Product> products) {
        this.products = products;
    }
    
}
