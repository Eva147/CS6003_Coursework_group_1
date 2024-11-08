package com.mainpackage.backend;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mainpackage.backend.model.CategoryRecord;
import com.mainpackage.backend.model.ProductRecord;

@RestController
@RequestMapping("/api")
public class DatabaseController {

    @Autowired
    private CSVService csvService;

    @GetMapping("/products")
    public List<ProductRecord> getProducts() throws IOException {
        return csvService.getProducts();
    }

    @GetMapping("/categories")
    public List<CategoryRecord> getCategories() throws IOException {
        return csvService.getCategories();
    }
}
