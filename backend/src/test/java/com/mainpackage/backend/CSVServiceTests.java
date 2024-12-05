package com.mainpackage.backend;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.boot.test.context.SpringBootTest;

import com.mainpackage.backend.model.ProductRecord;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

@SpringBootTest
public class CSVServiceTests {
    CSVService csvServiceUnderTest = new CSVService();

    @Test
    public void testGetProductsByCatalogId() throws IOException
    {
        String catalogId = "2";
        List<ProductRecord> productsToSet = new ArrayList<>();
        List<ProductRecord> expectedProducts = new ArrayList<>();

        // raw data
        String testData = "id,name,description,price,quantity,catalogId,image\n" +
            "1,product1,description1,1.0,1,1,image1\n" +
            "2,product2,description2,2.0,2,1,image2\n" +
            "3,product3,description3,3.0,3,2,image3\n" +
            "4,product4,description4,4.0,4,3,image4\n";

        // expected data
        String expectedData = "id,name,description,price,quantity,catalogId,image\n" +
            "3,product3,description3,3.0,3,2,image3\n";

        // preparing the mock object
        CSVParser csvParser = null;
        try {
            Reader reader = new java.io.StringReader(testData);
            csvParser = CSVFormat.Builder.create()
                .setHeader("id", "name", "description", "price", "quantity", "catalogId", "image")
                .setSkipHeaderRecord(true)
                .build()
                .parse(reader);
        } catch (Exception e) {
            System.err.println("Error parsing CSV: " + e.getMessage());
            e.printStackTrace();
        }
        for (CSVRecord csvRecord : csvParser) {
            ProductRecord product = new ProductRecord(
                csvRecord.get("id"),
                csvRecord.get("name"),
                csvRecord.get("description"),
                Float.parseFloat(csvRecord.get("price")),
                Integer.parseInt(csvRecord.get("quantity")),
                csvRecord.get("catalogId"),
                csvRecord.get("image")
            );
            productsToSet.add(product);
        }
        CSVService.setProducts(productsToSet);

        // preparing the expected object
        csvParser = null;
        try {
            Reader reader = new java.io.StringReader(expectedData);
            csvParser = CSVFormat.Builder.create()
                .setHeader("id", "name", "description", "price", "quantity", "catalogId", "image")
                .setSkipHeaderRecord(true)
                .build()
                .parse(reader);
        } catch (Exception e) {
            System.err.println("Error parsing CSV: " + e.getMessage());
            e.printStackTrace();
        }
        for (CSVRecord csvRecord : csvParser) {
            ProductRecord product = new ProductRecord(
                csvRecord.get("id"),
                csvRecord.get("name"),
                csvRecord.get("description"),
                Float.parseFloat(csvRecord.get("price")),
                Integer.parseInt(csvRecord.get("quantity")),
                csvRecord.get("catalogId"),
                csvRecord.get("image")
            );
            expectedProducts.add(product);
        }

        // asserting that the method does not throw any exceptions
        Assertions.assertDoesNotThrow(() -> csvServiceUnderTest.getProductsByCatalogId(catalogId));
        // getting products by catalog ID
        List<ProductRecord> actualProducts = csvServiceUnderTest.getProductsByCatalogId(catalogId);
        // asserting that the actual products are equal to the expected products
        Assertions.assertEquals(expectedProducts.toArray().length, actualProducts.toArray().length);
        for (int i = 0; i < expectedProducts.toArray().length; i++) {
            Assertions.assertEquals(expectedProducts.get(i).getName(), actualProducts.get(i).getName());
            Assertions.assertEquals(expectedProducts.get(i).getDescription(), actualProducts.get(i).getDescription());
            Assertions.assertEquals(expectedProducts.get(i).getPrice(), actualProducts.get(i).getPrice());
            Assertions.assertEquals(expectedProducts.get(i).getQuantity(), actualProducts.get(i).getQuantity());
            Assertions.assertEquals(expectedProducts.get(i).getCatalogId(), actualProducts.get(i).getCatalogId());
            Assertions.assertEquals(expectedProducts.get(i).getImage(), actualProducts.get(i).getImage());
        }
    }
}
