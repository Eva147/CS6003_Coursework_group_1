//package com.mainpackage.backend;
 /* 
import java.io.IOException;
import java.util.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mainpackage.backend.model.ProductRecord;

@RestController
@RequestMapping("/api")
public class ProductController {

    private final CSVService csvService;

    public ProductController(CSVService csvService) {
        this.csvService = csvService;
    }

    @GetMapping("/catalog/{catalogId}/products")
    public ResponseEntity<List<ProductRecord>> getProductsByCatalogId(@PathVariable String catalogId) {
        try {
            List<ProductRecord> products = csvService.getProductsByCatalogId(catalogId);
            return ResponseEntity.ok(products);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
*/