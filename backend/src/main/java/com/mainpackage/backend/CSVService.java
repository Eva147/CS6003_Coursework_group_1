/* 
package com.mainpackage.backend;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;

import com.mainpackage.backend.model.CategoryRecord;
import com.mainpackage.backend.model.ProductRecord;

import com.mainpackage.backend.model.UserRecord;


@Service
public class CSVService {

    private static final String PRODUCT_CSV_FILE_PATH = "../data/products.csv";
    private static final String CATEGORY_CSV_FILE_PATH = "../data/categories.csv";
    private static final String USER_CSV_FILE_PATH = "../data/users.csv";

    public List<ProductRecord> getProducts() throws IOException {
        List<ProductRecord> products = new ArrayList<>();

        try (Reader reader = Files.newBufferedReader(Paths.get(PRODUCT_CSV_FILE_PATH));
            CSVParser csvParser = CSVFormat.Builder.create()
                .setHeader("id", "name", "description", "price", "quantity", "catalogId", "image")
                .setSkipHeaderRecord(true)
                .build()
                .parse(reader)) {

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
                products.add(product);
            }
        }
        return products;
    }

    public List<CategoryRecord> getCategories() throws IOException {
        List<CategoryRecord> categories = new ArrayList<CategoryRecord>();

        try (Reader reader = Files.newBufferedReader(Paths.get(CATEGORY_CSV_FILE_PATH));
            CSVParser csvParser = CSVFormat.Builder.create()
                .setHeader("category", "id", "image")
                .setSkipHeaderRecord(true)
                .build()
                .parse(reader)) {

            for (CSVRecord csvRecord : csvParser) {
                CategoryRecord category = new CategoryRecord(
                    csvRecord.get("category"),
                    csvRecord.get("id"),
                    csvRecord.get("image")
                );
                categories.add(category);
            }
        } catch (IOException e) {
                System.err.println("Error reading categories CSV file: " + e.getMessage());
                throw e;
        }
        return categories;
    }

    public List<UserRecord> getUsers() throws IOException {
        List<UserRecord> users = new ArrayList<UserRecord>();

        try (Reader reader = Files.newBufferedReader(Paths.get(USER_CSV_FILE_PATH));
                CSVParser csvParser = CSVFormat.Builder.create()
                        .setHeader("email", "password", "name")
                        .setSkipHeaderRecord(true)
                        .build()
                        .parse(reader)) {
            for (CSVRecord csvRecord : csvParser) {
                UserRecord user = new UserRecord(
                        csvRecord.get("email"),
                        csvRecord.get("password"),
                        csvRecord.get("name"));
                users.add(user);
            }
        }

        return users;
    }

    public List<ProductRecord> getProductsByCatalogId(String catalogId) throws IOException {
        List<ProductRecord> allProducts = getProducts();

        List<ProductRecord> filteredProducts = allProducts.stream()
            .filter(product -> catalogId.equals(product.getCatalogId()))
            .collect(Collectors.toList());

        return filteredProducts;
    }
}
*/

/* 
package com.mainpackage.backend;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;

import com.mainpackage.backend.model.CategoryRecord;
import com.mainpackage.backend.model.ProductRecord;
import com.mainpackage.backend.model.UserRecord;

@Service
public class CSVService {

    private static final String PRODUCT_CSV_FILE_PATH = "../data/products.csv";
    private static final String CATEGORY_CSV_FILE_PATH = "../data/categories.csv";
    private static final String USER_CSV_FILE_PATH = "../data/users.csv";

    private List<ProductRecord> products;
    private List<ProductRecord> productsSortedByName;
    private List<ProductRecord> productsSortedByPrice;

    @PostConstruct
    public void loadAndSortProducts() throws IOException {
        products = loadProductsFromCSV();

        // Sort by name
        productsSortedByName = products.stream()
            .sorted(Comparator.comparing(ProductRecord::getName))
            .collect(Collectors.toList());

        // Sort by price
        productsSortedByPrice = products.stream()
            .sorted(Comparator.comparingDouble(ProductRecord::getPrice))
            .collect(Collectors.toList());
    }

    private List<ProductRecord> loadProductsFromCSV() throws IOException {
        List<ProductRecord> products = new ArrayList<>();

        try (Reader reader = Files.newBufferedReader(Paths.get(PRODUCT_CSV_FILE_PATH));
             CSVParser csvParser = CSVFormat.Builder.create()
                     .setHeader("id", "name", "description", "price", "quantity", "catalogId", "image")
                     .setSkipHeaderRecord(true)
                     .build()
                     .parse(reader)) {

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
                products.add(product);
            }
        }
        return products;
    }

    public List<ProductRecord> getProducts() {
        return products;
    }

    public List<ProductRecord> getProductsSortedByName() {
        return productsSortedByName;
    }

    public List<ProductRecord> getProductsSortedByPrice() {
        return productsSortedByPrice;
    }

    public List<ProductRecord> getProductsByCatalogId(String catalogId) throws IOException {
        return products.stream()
            .filter(product -> catalogId.equals(product.getCatalogId()))
            .collect(Collectors.toList());
    }

    public List<CategoryRecord> getCategories() throws IOException {
        List<CategoryRecord> categories = new ArrayList<CategoryRecord>();

        try (Reader reader = Files.newBufferedReader(Paths.get(CATEGORY_CSV_FILE_PATH));
             CSVParser csvParser = CSVFormat.Builder.create()
                     .setHeader("category", "id", "image")
                     .setSkipHeaderRecord(true)
                     .build()
                     .parse(reader)) {

            for (CSVRecord csvRecord : csvParser) {
                CategoryRecord category = new CategoryRecord(
                        csvRecord.get("category"),
                        csvRecord.get("id"),
                        csvRecord.get("image")
                );
                categories.add(category);
            }
        } catch (IOException e) {
            System.err.println("Error reading categories CSV file: " + e.getMessage());
            throw e;
        }
        return categories;
    }

    public List<UserRecord> getUsers() throws IOException {
        List<UserRecord> users = new ArrayList<UserRecord>();

        try (Reader reader = Files.newBufferedReader(Paths.get(USER_CSV_FILE_PATH));
             CSVParser csvParser = CSVFormat.Builder.create()
                     .setHeader("email", "password", "name")
                     .setSkipHeaderRecord(true)
                     .build()
                     .parse(reader)) {
            for (CSVRecord csvRecord : csvParser) {
                UserRecord user = new UserRecord(
                        csvRecord.get("email"),
                        csvRecord.get("password"),
                        csvRecord.get("name"));
                users.add(user);
            }
        }

        return users;
    }
}

*/

package com.mainpackage.backend;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;
import java.util.stream.Collectors;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;

import com.mainpackage.backend.model.CategoryRecord;
import com.mainpackage.backend.model.ProductRecord;

import com.mainpackage.backend.model.UserRecord;


@Service
public class CSVService {

    private static final String PRODUCT_CSV_FILE_PATH = "../data/products.csv";
    private static final String CATEGORY_CSV_FILE_PATH = "../data/categories.csv";
    private static final String USER_CSV_FILE_PATH = "../data/users.csv";

    public List<ProductRecord> getProducts() throws IOException {
        List<ProductRecord> products = new ArrayList<>();

        try (Reader reader = Files.newBufferedReader(Paths.get(PRODUCT_CSV_FILE_PATH));
            CSVParser csvParser = CSVFormat.Builder.create()
                .setHeader("id", "name", "description", "price", "quantity", "catalogId", "image")
                .setSkipHeaderRecord(true)
                .build()
                .parse(reader)) {

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
                products.add(product);
            }
        }
        products.sort(Comparator.comparing(ProductRecord::getName)
                                .thenComparingDouble(ProductRecord::getPrice));

        return products;
    }

    public List<CategoryRecord> getCategories() throws IOException {
        List<CategoryRecord> categories = new ArrayList<>();

        try (Reader reader = Files.newBufferedReader(Paths.get(CATEGORY_CSV_FILE_PATH));
            CSVParser csvParser = CSVFormat.Builder.create()
                .setHeader("category", "id", "image")
                .setSkipHeaderRecord(true)
                .build()
                .parse(reader)) {

            for (CSVRecord csvRecord : csvParser) {
                CategoryRecord category = new CategoryRecord(
                    csvRecord.get("category"),
                    csvRecord.get("id"),
                    csvRecord.get("image")
                );
                categories.add(category);
            }
        } catch (IOException e) {
                System.err.println("Error reading categories CSV file: " + e.getMessage());
                throw e;
        }
        return categories;
    }

    public List<UserRecord> getUsers() throws IOException {
        List<UserRecord> users = new ArrayList<>();

        try (Reader reader = Files.newBufferedReader(Paths.get(USER_CSV_FILE_PATH));
                CSVParser csvParser = CSVFormat.Builder.create()
                        .setHeader("email", "password", "name")
                        .setSkipHeaderRecord(true)
                        .build()
                        .parse(reader)) {
            for (CSVRecord csvRecord : csvParser) {
                UserRecord user = new UserRecord(
                        csvRecord.get("email"),
                        csvRecord.get("password"),
                        csvRecord.get("name"));
                users.add(user);
            }
        }

        return users;
    }

    public List<ProductRecord> getProductsByCatalogId(String catalogId) throws IOException {
        List<ProductRecord> allProducts = getProducts();

        List<ProductRecord> filteredProducts = allProducts.stream()
            .filter(product -> catalogId.equals(product.getCatalogId()))
            .collect(Collectors.toList());

        return filteredProducts;
    }
}