package com.mainpackage.backend;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;

import com.mainpackage.backend.model.CategoryRecord;
import com.mainpackage.backend.model.ProductRecord;

@Service
public class CSVService {

    private static final String PRODUCT_CSV_FILE_PATH = "data/products.csv";
    private static final String CATEGORY_CSV_FILE_PATH = "data/categories.csv";

    public List<ProductRecord> getProducts() throws IOException {
        List<ProductRecord> products = new ArrayList<>();
        
        try (Reader reader = Files.newBufferedReader(Paths.get(PRODUCT_CSV_FILE_PATH));
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT)) {
             
            for (CSVRecord csvRecord : csvParser) {
                ProductRecord product = new ProductRecord(
                    csvRecord.get("product_id"),
                    csvRecord.get("name"),
                    csvRecord.get("description"),
                    csvRecord.get("price"),
                    Integer.parseInt(csvRecord.get("quantity")),
                    csvRecord.get("catalog_id"),
                    csvRecord.get("image")
                );
                products.add(product);
            }
        }
        
        return products;
    }

    public List<CategoryRecord> getCategories() throws IOException {
        List<CategoryRecord> categories = new ArrayList<>();
        
        try (Reader reader = Files.newBufferedReader(Paths.get(CATEGORY_CSV_FILE_PATH));
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT)) {
             
            for (CSVRecord csvRecord : csvParser) {
                CategoryRecord category = new CategoryRecord(
                    csvRecord.get("category"),
                    csvRecord.get("id"),
                    csvRecord.get("image")
                );
                categories.add(category);
            }
        }
        
        return categories;
    }
}
