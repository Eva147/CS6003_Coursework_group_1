package com.mainpackage.backend;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
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

    private static List<ProductRecord> products = null;
    private static List<CategoryRecord> categories = null;
    private static List<UserRecord> users = null;

    public List<ProductRecord> getProducts() throws IOException {
        return CSVService.products;
    }

    public static void setProducts(List<ProductRecord> products) {
        CSVService.products = products;
    }

    public List<CategoryRecord> getCategories() throws IOException {
        return CSVService.categories;
    }

    public static void setCategories(List<CategoryRecord> categories) {
        CSVService.categories = categories;
    }

    public List<UserRecord> getUsers() throws IOException {
        return CSVService.users;
    }

    public static void setUsers(List<UserRecord> users) {
        CSVService.users = users;
    }

    public static void readProductsFromCSV() throws IOException {
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
        CSVService.setProducts(products);
        return;
    }

    public static void readCategoriesFromCSV() throws IOException {
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
        CSVService.setCategories(categories);
        return;
    }

    public static void readUsersFromCSV() throws IOException {
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
        CSVService.setUsers(users);
        return;
    }

    public List<ProductRecord> getProductsByCatalogId(String catalogId) throws IOException {
        List<ProductRecord> allProducts = this.getProducts();

        List<ProductRecord> filteredProducts = allProducts.stream()
            .filter(product -> catalogId.equals(product.getCatalogId()))
            .collect(Collectors.toList());

        return filteredProducts;
    }

    public List<ProductRecord> getProductRecordsSortedByPrice(String catalogId) throws IOException {
        List<ProductRecord> allProducts = getProductsByCatalogId(catalogId);

        allProducts.sort((a, b) -> a.getPrice().compareTo(b.getPrice()));

        return allProducts;
    }

    public List<ProductRecord> getProductRecordsSortedByName(String catalogId) throws IOException {
        List<ProductRecord> allProducts = getProductsByCatalogId(catalogId);

        allProducts.sort((a, b) -> a.getName().compareTo(b.getName()));

        return allProducts;
    }
}
