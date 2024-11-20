package com.mainpackage.backend.model;

public class UserRecord {
    private String email;
    private String password;
    private String name;

    public UserRecord() {
    }

    public UserRecord(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }

    // Getters & Setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User Record{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}