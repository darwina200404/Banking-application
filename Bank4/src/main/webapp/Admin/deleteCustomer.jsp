<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Delete Customer</title>
</head>
<body>
    <h2>Delete Customer</h2>
    <form action="AdminServlet" method="post">
        <input type="hidden" name="action" value="deleteCustomer">
        <label for="customerId">Customer ID:</label>
        <input type="number" id="customerId" name="customerId" required><br><br>
        <input type="submit" value="Delete Customer">
    </form>
</body>
</html>
