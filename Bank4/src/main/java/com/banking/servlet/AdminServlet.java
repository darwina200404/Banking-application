//package com.banking.servlet;
//
//import com.banking.service.AdminService;
//import com.banking.service.CustomerService;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.sql.SQLException;
//
//@WebServlet("/AdminServlet")
//public class AdminServlet extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//
//    private CustomerService customerService = new CustomerService();
//    private AdminService adminService = new AdminService();
//
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String action = request.getParameter("action");
//
//        try {
//            if ("addCustomer".equals(action)) {
//                String name = request.getParameter("name");
//                String accountNumber = request.getParameter("accountNumber");
//
//                if (name == null || name.trim().isEmpty() || accountNumber == null || accountNumber.trim().isEmpty()) {
//                    response.getWriter().println("Name and account number are required.");
//                    return;
//                }
//
//                // Assuming you want to initialize balance to 0 or some other default value
//              
//            } else if ("deleteCustomer".equals(action)) {
//                int customerId = Integer.parseInt(request.getParameter("customerId"));
//
//                if (adminService.deleteCustomer(customerId)) {
//                    response.getWriter().println("Customer deleted successfully");
//                } else {
//                    response.getWriter().println("Customer cannot be deleted (might have a non-zero balance or does not exist)");
//                }
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//            response.getWriter().println("An error occurred: " + e.getMessage());
//        }
//    }
//}
