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
                    csvRecord.get("price"),
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

    // File paths for CSV data
    private static final String PRODUCT_CSV_FILE_PATH = "../data/products.csv";
    private static final String CATEGORY_CSV_FILE_PATH = "../data/categories.csv";
    private static final String USER_CSV_FILE_PATH = "../data/users.csv";

    // Lists to hold products and their sorted versions
    private List<ProductRecord> products;
    private List<ProductRecord> productsSortedByName;
    private List<ProductRecord> productsSortedByPrice;

    /**
     * This method is called after the service is initialized.
     * It loads products from the CSV file and sorts them by name and price.
     */
    @PostConstruct
    public void loadAndSortProducts() throws IOException {
        // Load products from the CSV file
        products = loadProductsFromCSV();

        // Sort the products by name and store the sorted list
        productsSortedByName = products.stream()
            .sorted(Comparator.comparing(ProductRecord::getName))  // Sort by name alphabetically
            .collect(Collectors.toList());

        // Sort the products by price (ascending) and store the sorted list
        productsSortedByPrice = products.stream()
            .sorted(Comparator.comparingDouble(ProductRecord::getPrice))  // Sort by price
            .collect(Collectors.toList());
    }

    /**
     * This method loads product data from the CSV file and converts it into a list of ProductRecord objects.
     */
    private List<ProductRecord> loadProductsFromCSV() throws IOException {
        List<ProductRecord> products = new ArrayList<>();

        // Read the CSV file and parse it
        try (Reader reader = Files.newBufferedReader(Paths.get(PRODUCT_CSV_FILE_PATH));
             CSVParser csvParser = CSVFormat.Builder.create()
                     .setHeader("id", "name", "description", "price", "quantity", "catalogId", "image")
                     .setSkipHeaderRecord(true)  // Skip the header row
                     .build()
                     .parse(reader)) {

            // Iterate through each CSV record and create ProductRecord objects
            for (CSVRecord csvRecord : csvParser) {
                ProductRecord product = new ProductRecord(
                        csvRecord.get("id"),
                        csvRecord.get("name"),
                        csvRecord.get("description"),
                        Float.parseFloat(csvRecord.get("price")),  // Convert price to float
                        Integer.parseInt(csvRecord.get("quantity")),  // Convert quantity to integer
                        csvRecord.get("catalogId"),
                        csvRecord.get("image")
                );
                products.add(product);
            }
        }
        return products;
    }

    // Method to return all products
    public List<ProductRecord> getProducts() {
        return products;
    }

    // Method to return products sorted by name
    public List<ProductRecord> getProductsSortedByName() {
        return productsSortedByName;
    }

    // Method to return products sorted by price
    public List<ProductRecord> getProductsSortedByPrice() {
        return productsSortedByPrice;
    }

    /**
     * This method filters products by their catalog ID.
     * 
     * @param catalogId The catalog ID to filter by.
     * @return A list of products belonging to the specified catalog.
     */
    public List<ProductRecord> getProductsByCatalogId(String catalogId) throws IOException {
        // Filter the list of products based on the catalog ID
        return products.stream()
            .filter(product -> catalogId.equals(product.getCatalogId()))  // Compare catalog IDs
            .collect(Collectors.toList());  // Collect the filtered results into a list
    }

    /**
     * This method loads categories from the categories CSV file.
     */
    public List<CategoryRecord> getCategories() throws IOException {
        List<CategoryRecord> categories = new ArrayList<CategoryRecord>();

        // Read and parse the CSV file
        try (Reader reader = Files.newBufferedReader(Paths.get(CATEGORY_CSV_FILE_PATH));
             CSVParser csvParser = CSVFormat.Builder.create()
                     .setHeader("category", "id", "image")
                     .setSkipHeaderRecord(true)
                     .build()
                     .parse(reader)) {

            // Iterate through each CSV record and create CategoryRecord objects
            for (CSVRecord csvRecord : csvParser) {
                CategoryRecord category = new CategoryRecord(
                        csvRecord.get("category"),
                        csvRecord.get("id"),
                        csvRecord.get("image")
                );
                categories.add(category);
            }
        } catch (IOException e) {
            // Print an error message and rethrow the exception if reading the categories fails
            System.err.println("Error reading categories CSV file: " + e.getMessage());
            throw e;
        }
        return categories;
    }

    /**
     * This method loads user data from the users CSV file.
     */
    public List<UserRecord> getUsers() throws IOException {
        List<UserRecord> users = new ArrayList<UserRecord>();

        // Read and parse the users CSV file
        try (Reader reader = Files.newBufferedReader(Paths.get(USER_CSV_FILE_PATH));
             CSVParser csvParser = CSVFormat.Builder.create()
                     .setHeader("email", "password", "name")
                     .setSkipHeaderRecord(true)
                     .build()
                     .parse(reader)) {

            // Iterate through each CSV record and create UserRecord objects
            for (CSVRecord csvRecord : csvParser) {
                UserRecord user = new UserRecord(
                        csvRecord.get("email"),
                        csvRecord.get("password"),
                        csvRecord.get("name")
                );
                users.add(user);
            }
        }

        return users;
    }
}
