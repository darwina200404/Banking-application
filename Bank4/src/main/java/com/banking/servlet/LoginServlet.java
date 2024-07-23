package com.banking.servlet;

import com.banking.model.Admin;
import com.banking.model.Customer;
import com.banking.service.AdminService;
import com.banking.service.CustomerService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(LoginServlet.class.getName());
    private AdminService adminService = new AdminService();
    private CustomerService customerService = new CustomerService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String role = request.getParameter("role");

        if ("admin".equals(role)) {
            handleAdminLogin(request, response);
        } else if ("customer".equals(role)) {
            handleCustomerLogin(request, response);
        } else {
            response.getWriter().println("Invalid role specified");
        }
    }

    private void handleAdminLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        Admin admin = adminService.authenticateAdmin(username, password);
        if (admin != null) {
            request.getSession().setAttribute("admin", admin);
            response.sendRedirect("admin/adminHome.jsp");
        } else {
            response.getWriter().println("Invalid admin credentials");
        }
    }

    private void handleCustomerLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            int customerId = Integer.parseInt(request.getParameter("customerId"));
            Customer customer = customerService.getCustomerById(customerId);
            if (customer != null) {
                request.getSession().setAttribute("customer", customer);
                response.sendRedirect("customer/customerHome.jsp");
            } else {
                response.getWriter().println("Invalid customer ID");
            }
        } catch (NumberFormatException e) {
            logger.log(Level.SEVERE, "Invalid customer ID format", e);
            response.getWriter().println("Invalid customer ID format");
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "SQL error during customer authentication", e);
            response.getWriter().println("An error occurred while processing your request. Please try again later.");
        }
    }
}
