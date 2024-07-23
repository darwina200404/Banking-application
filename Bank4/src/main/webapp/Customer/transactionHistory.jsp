<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Transaction History</title>
</head>
<body>
    <h2>Transaction History</h2>
    <form action="TransactionServlet" method="post">
        <label for="customerId">Customer ID:</label>
        <input type="number" id="customerId" name="customerId" required><br><br>
        <input type="submit" value="View History">
    </form>
    <br>
    <div>
        <h3>History:</h3>
        <%
            List<String> transactions = (List<String>) request.getAttribute("transactions");
            if (transactions != null) {
                for (String transaction : transactions) {
                    out.println("<p>" + transaction + "</p>");
                }
            } else {
                out.println("<p>No transactions found.</p>");
            }
        %>
    </div>
</body>
</html>
