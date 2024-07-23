package com.banking.service;

import com.banking.dao.CustomerDAO;
import com.banking.model.Customer;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CustomerService {
    private static final Logger logger = Logger.getLogger(CustomerService.class.getName());
    private CustomerDAO customerDAO;

    // Constructor for dependency injection
    public CustomerService(CustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
    }

    // Default constructor (if needed)
    public CustomerService() {
        this.customerDAO = new CustomerDAO();
    }

    public void updateCustomerBalance(int customerId, double newBalance) throws SQLException {
        customerDAO.updateCustomerBalance(customerId, newBalance);
    }

    public void deposit(int customerId, double amount) throws SQLException {
        customerDAO.deposit(customerId, amount);
    }

    public void withdraw(int customerId, double amount) throws SQLException {
        try {
            customerDAO.withdraw(customerId, amount);
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error withdrawing " + amount + " for customer ID: " + customerId, e);
            throw e; // Re-throw exception to indicate failure to the caller
        }
    }

    public void transfer(int fromCustomerId, int toCustomerId, double amount) throws SQLException {
        try {
            customerDAO.transfer(fromCustomerId, toCustomerId, amount);
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error transferring " + amount + " from customer ID: " + fromCustomerId + " to customer ID: " + toCustomerId, e);
            throw e; // Re-throw exception to indicate failure to the caller
        }
    }

    public Customer getCustomerById(int customerId) throws SQLException {
        return customerDAO.getCustomerById(customerId);
    }

	
}
