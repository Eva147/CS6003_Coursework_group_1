package com.mainpackage.backend.service;

import com.mainpackage.backend.model.Product;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.io.IOException;

public class CsvService {
    public List<Product> parseCsv() throws IOException {
        try (Reader reader = Files.newBufferedReader(Paths.get("src/main/resources/products.csv"))) {
            CsvToBean<Product> csvToBean = new CsvToBeanBuilder<Product>(reader)
                .withType(Product.class)
                .withIgnoreLeadingWhiteSpace(true)
                .build();
            return csvToBean.parse();
        }
    }
}
