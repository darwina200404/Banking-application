package com.banking.servlet;

import com.banking.model.Customer;
import com.banking.service.CustomerService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/CustomerServlet")
public class CustomerServlet extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CustomerService customerService = new CustomerService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        try {
            if ("deposit".equals(action)) {
                int customerId = Integer.parseInt(request.getParameter("customerId"));
                double amount = Double.parseDouble(request.getParameter("amount"));
                Customer customer = customerService.getCustomerById(customerId);
                if (customer != null) {
                    customerService.updateCustomerBalance(customerId, customer.getBalance() + amount);
                    response.getWriter().println("Deposit successful");
                } else {
                    response.getWriter().println("Customer not found");
                }
            } else if ("withdraw".equals(action)) {
                int customerId = Integer.parseInt(request.getParameter("customerId"));
                double amount = Double.parseDouble(request.getParameter("amount"));
                Customer customer = customerService.getCustomerById(customerId);
                if (customer != null) {
                    if (customer.getBalance() >= amount) {
                        customerService.updateCustomerBalance(customerId, customer.getBalance() - amount);
                        response.getWriter().println("Withdrawal successful");
                    } else {
                        response.getWriter().println("Insufficient balance");
                    }
                } else {
                    response.getWriter().println("Customer not found");
                }
            } else if ("transfer".equals(action)) {
                int senderId = Integer.parseInt(request.getParameter("senderId"));
                int receiverId = Integer.parseInt(request.getParameter("receiverId"));
                double amount = Double.parseDouble(request.getParameter("amount"));
                Customer sender = customerService.getCustomerById(senderId);
                Customer receiver = customerService.getCustomerById(receiverId);
                if (sender != null && receiver != null) {
                    if (sender.getBalance() >= amount) {
                        customerService.updateCustomerBalance(senderId, sender.getBalance() - amount);
                        customerService.updateCustomerBalance(receiverId, receiver.getBalance() + amount);
                        response.getWriter().println("Transfer successful");
                    } else {
                        response.getWriter().println("Insufficient balance for transfer");
                    }
                } else {
                    response.getWriter().println("Sender or receiver not found");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
