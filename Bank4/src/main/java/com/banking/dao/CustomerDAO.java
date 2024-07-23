package com.banking.dao;

import com.banking.model.Customer;
import com.banking.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CustomerDAO {
    private static final Logger logger = Logger.getLogger(CustomerDAO.class.getName());

    public Customer getCustomerById(int customerId) {
        String query = "SELECT * FROM Customer WHERE id = ?";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement pst = con.prepareStatement(query)) {
            pst.setInt(1, customerId);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                // Assuming the columns in your database match these names
                return new Customer(rs.getInt("id"), rs.getString("name"), rs.getDouble("balance"));
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error getting customer by ID", e);
        }
        return null;
    }

    public void updateCustomerBalance(int customerId, double newBalance) {
        String query = "UPDATE Customer SET balance = ? WHERE id = ?";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement pst = con.prepareStatement(query)) {
            pst.setDouble(1, newBalance);
            pst.setInt(2, customerId);
            pst.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error updating customer balance", e);
        }
    }
  

    public void deposit(int customerId, double amount) {
        Customer customer = getCustomerById(customerId);
        if (customer != null) {
            double newBalance = customer.getBalance() + amount;
            updateCustomerBalance(customerId, newBalance);
        }
    }

    public void withdraw(int customerId, double amount) throws SQLException {
        Customer customer = getCustomerById(customerId);
        if (customer != null && customer.getBalance() >= amount) {
            double newBalance = customer.getBalance() - amount;
            updateCustomerBalance(customerId, newBalance);
        } else {
            throw new SQLException("Insufficient balance.");
        }
    }

    public void transfer(int fromCustomerId, int toCustomerId, double amount) throws SQLException {
        Connection connection = null;
        try {
            connection = DBUtil.getConnection();
            connection.setAutoCommit(false);

            Customer fromCustomer = getCustomerById(fromCustomerId);
            Customer toCustomer = getCustomerById(toCustomerId);

            if (fromCustomer != null && toCustomer != null && fromCustomer.getBalance() >= amount) {
                double newFromBalance = fromCustomer.getBalance() - amount;
                double newToBalance = toCustomer.getBalance() + amount;

                updateCustomerBalance(fromCustomerId, newFromBalance);
                updateCustomerBalance(toCustomerId, newToBalance);

                connection.commit();
            } else {
                throw new SQLException("Transfer failed due to insufficient balance or invalid customer details.");
            }
        } catch (SQLException e) {
            if (connection != null) {
                connection.rollback();
            }
            logger.log(Level.SEVERE, "Error transferring money", e);
            throw e;
        } finally {
            if (connection != null) {
                connection.setAutoCommit(true);
                connection.close();
            }
        }
    }
}
