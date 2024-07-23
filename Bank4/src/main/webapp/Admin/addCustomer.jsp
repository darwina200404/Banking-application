<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add Customer</title>
</head>
<body>
    <h2>Add Customer</h2>
    <form action="AdminServlet" method="post">
        <input type="hidden" name="action" value="addCustomer">
        <label for="name">Name:</label>
        <input type="text" id="name" name="name" required><br><br>
        <label for="accountNumber">Account Number:</label>
        <input type="text" id="accountNumber" name="accountNumber" required><br><br>
        <input type="submit" value="Add Customer">
    </form>
</body>
</html>
