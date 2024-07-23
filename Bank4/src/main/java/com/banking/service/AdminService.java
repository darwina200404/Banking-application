package com.banking.service;

import com.banking.dao.AdminDAO;
import com.banking.model.Admin;
import com.banking.model.Customer;

import java.sql.SQLException;

public class AdminService {
//	public Admin authenticateAdmin(String username, String password) {
//		// TODO Auto-generated method stub
//		return adminDAO.authenticateAdmin(username,password);
//		
//	}
    private static AdminDAO adminDAO = new AdminDAO();

    public boolean canDeleteCustomer(int customerId) throws SQLException {
        return adminDAO.canDeleteCustomer(customerId);
    }
   

    public boolean deleteCustomer(int customerId) throws SQLException {
        if (canDeleteCustomer(customerId)) {
            return adminDAO.deleteCustomer(customerId);
        }
        return false;
    }

    public Customer getCustomerById(int customerId) throws SQLException {
        return adminDAO.getCustomerById(customerId);
    }


	public Admin authenticateAdmin(String username, String password) {
		// TODO Auto-generated method stub
		return adminDAO.authenticateAdmin(username,password);
	}

   
    // Additional service methods for admin operations
}
