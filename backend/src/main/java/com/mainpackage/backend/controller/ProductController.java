package com.mainpackage.backend.controller;

import com.mainpackage.backend.model.Product;
import com.mainpackage.backend.service.CsvService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping("/api")
public class ProductController {
    
    private final CsvService csvService = new CsvService();

    @GetMapping("/{catalog_id}") // Returns a list of products and specifies the URL where you can access this endpoint
    public List<Product> getProducts() throws IOException {
        return csvService.parseCsv(); // returns the object list of Product
        }
    
    }
