package com.mainpackage.backend;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mainpackage.backend.model.CategoryRecord;
import com.mainpackage.backend.model.ProductRecord;
import com.mainpackage.backend.model.UserRecord;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/")
public class DatabaseController {
    @Autowired
    private CSVService csvService;

    public DatabaseController(CSVService csvService) {
        this.csvService = csvService;
    }

    @GetMapping("/")
    public List<CategoryRecord> getCategories() throws IOException {
        try {
            return csvService.getCategories();
        } catch (Exception e) {
            System.err.println("Error fetching categories: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) throws IOException {
        System.out.println("Login request received: " + loginRequest.getEmail());
        System.out.println("Login request received: " + loginRequest.getPassword());
        try {
            List<UserRecord> users = csvService.getUsers();
            boolean loggedIn = false;
            UserRecord loggedInUser = null;
            for (UserRecord user : users) {
                System.out.println("User email: " + user.getEmail());
                System.out.println("User password: " + user.getPassword());
                boolean emailMatch = user.getEmail().equals(loginRequest.getEmail());
                boolean passwordMatch = user.getPassword().equals(loginRequest.getPassword());
                if (emailMatch && passwordMatch) {
                    System.out.println("Match found");
                    loggedInUser = user;
                    loggedIn = true;
                    break;
                }
            }
            System.out.println("Logged in: " + loggedIn);
            if (loggedIn && loggedInUser != null) {
                String responseString = loggedInUser.toString();
                return ResponseEntity.ok(responseString);
            } else {
                return ResponseEntity.status(401).body("Invalid credentials");
            }
        } catch (Exception e) {
            System.err.println("Error fetching users: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }

    @GetMapping("/{catalogId}")
    public List<ProductRecord> getProductsByCatalogId(@PathVariable String catalogId) throws IOException {
        try {
            return csvService.getProductsByCatalogId(catalogId);
        } catch (Exception e) {
            System.err.println("Error fetching products for catalog ID " + catalogId + ": " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }

    @GetMapping("/{catalogId}/{sortBy}")
    public List<ProductRecord> getProductsByCatalogIdSorted(@PathVariable String catalogId, @PathVariable String sortBy) throws IOException {
        try {
            switch (sortBy) {
                case "price":
                    return csvService.getProductRecordsSortedByPrice(catalogId);
                case "name":
                    return csvService.getProductRecordsSortedByName(catalogId);
                default:
                    return csvService.getProductsByCatalogId(catalogId);
            }
        } catch (Exception e) {
            System.err.println("Error fetching products for catalog ID " + catalogId + ": " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }
}