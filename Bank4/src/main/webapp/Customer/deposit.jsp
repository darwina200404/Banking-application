<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Deposit Money</title>
</head>
<body>
    <h2>Deposit Money</h2>
    <form action="CustomerServlet" method="post">
        <input type="hidden" name="action" value="deposit">
        <label for="customerId">Customer ID:</label>
        <input type="number" id="customerId" name="customerId" required><br><br>
        <label for="amount">Amount:</label>
        <input type="number" id="amount" name="amount" step="0.01" required><br><br>
        <input type="submit" value="Deposit">
    </form>
</body>
</html>
