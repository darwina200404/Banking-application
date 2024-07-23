<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Withdraw Money</title>
</head>
<body>
    <h2>Withdraw Money</h2>
    <form action="CustomerServlet" method="post">
        <input type="hidden" name="action" value="withdraw">
        <label for="customerId">Customer ID:</label>
        <input type="number" id="customerId" name="customerId" required><br><br>
        <label for="amount">Amount:</label>
        <input type="number" id="amount" name="amount" step="0.01" required><br><br>
        <input type="submit" value="Withdraw">
    </form>
</body>
</html>
