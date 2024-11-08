package com.mainpackage.backend.service;

import com.mainpackage.backend.model.Catalog;
import com.mainpackage.backend.model.Product;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.List;

@Service
public class CatalogService {
    private final CsvService csvService = new CsvService();

    // Method to create and return a Catalog with products from CSV
    public Catalog getCatalog(String catalogName) throws IOException {
        List<Product> products = csvService.parseCsv(); // Parse CSV into Product list
        return new Catalog(catalogName, products);
    }
}
