package com.banking.model;

public class Customer {
    private int customerId;
    private String name;
    private double balance;

    // Constructor
    public Customer(int customerId, String name, double balance) {
        this.customerId = customerId;
        this.name = name;
        this.balance = balance;
    }

    // Default constructor
    public Customer() {
    }

    // Getters and Setters
    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
