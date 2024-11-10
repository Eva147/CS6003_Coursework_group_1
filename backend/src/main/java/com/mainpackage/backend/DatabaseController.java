package com.mainpackage.backend;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mainpackage.backend.model.CategoryRecord;
import com.mainpackage.backend.model.ProductRecord;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/")
public class DatabaseController {
    @Autowired
    private CSVService csvService;

    public DatabaseController(CSVService csvService) {
        this.csvService = csvService;
    }

    @GetMapping("/")
    public List<CategoryRecord> getCategories() throws IOException {
        try {
            return csvService.getCategories();
        } catch (Exception e) {
            System.err.println("Error fetching categories: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }

    @GetMapping("/{catalogId}")
    public List<ProductRecord> getProductsByCatalogId(@PathVariable String catalogId) throws IOException {
        try {
            return csvService.getProductsByCatalogId(catalogId);
        } catch (Exception e) {
            System.err.println("Error fetching products for catalog ID " + catalogId + ": " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }
}