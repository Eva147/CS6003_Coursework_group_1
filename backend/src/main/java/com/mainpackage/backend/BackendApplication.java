package com.mainpackage.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BackendApplication {

	public static void main(String[] args) {
		// preparing the database
		// reading categories from CSV
		try {
			CSVService.readCategoriesFromCSV();
		} catch (Exception e) {
			System.err.println("Error reading categories from CSV: " + e.getMessage());
			e.printStackTrace();
		}
		// reading products from CSV
		try {
			CSVService.readProductsFromCSV();
		} catch (Exception e) {
			System.err.println("Error reading products from CSV: " + e.getMessage());
			e.printStackTrace();
		}
		// reading users from CSV
		try {
			CSVService.readUsersFromCSV();
		} catch (Exception e) {
			System.err.println("Error reading users from CSV: " + e.getMessage());
			e.printStackTrace();
		}
		SpringApplication.run(BackendApplication.class, args);
	}

}
