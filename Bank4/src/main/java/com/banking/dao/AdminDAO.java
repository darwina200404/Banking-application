package com.banking.dao;

import com.banking.model.Admin;
import com.banking.model.Customer;
import com.banking.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AdminDAO {
    private static final Logger logger = Logger.getLogger(AdminDAO.class.getName());

    public Admin authenticateAdmin(String username, String password) {
        String query = "SELECT * FROM Admin WHERE username = ? AND password = ?";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement pst = con.prepareStatement(query)) {
            pst.setString(1, username);
            pst.setString(2, password); // Note: Ensure that passwords are stored securely (e.g., hashed)
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    return new Admin(rs.getInt("id"), rs.getString("username"), rs.getString("password"));
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error authenticating admin", e);
        }
        return null;
    }

    public boolean canDeleteCustomer(int customerId) throws SQLException {
        String query = "SELECT COUNT(*) FROM Customer WHERE id = ?";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement pst = con.prepareStatement(query)) {
            pst.setInt(1, customerId);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error checking if customer can be deleted", e);
            throw e;
        }
        return false;
    }

    public boolean deleteCustomer(int customerId) throws SQLException {
        String query = "DELETE FROM Customer WHERE id = ?";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement pst = con.prepareStatement(query)) {
            pst.setInt(1, customerId);
            int rowsAffected = pst.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error deleting customer", e);
            throw e;
        }
    }

    public Customer getCustomerById(int customerId) throws SQLException {
        String query = "SELECT * FROM Customer WHERE id = ?";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement pst = con.prepareStatement(query)) {
            pst.setInt(1, customerId);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    // Updated to match new Customer class constructor
                    return new Customer(rs.getInt("id"), rs.getString("name"), rs.getDouble("balance"));
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error retrieving customer by ID", e);
            throw e;
        }
        return null;
    }

	
}
