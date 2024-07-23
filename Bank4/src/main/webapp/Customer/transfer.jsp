<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Transfer Money</title>
</head>
<body>
    <h2>Transfer Money</h2>
    <form action="CustomerServlet" method="post">
        <input type="hidden" name="action" value="transfer">
        <label for="senderId">Sender ID:</label>
        <input type="number" id="senderId" name="senderId" required><br><br>
        <label for="receiverId">Receiver ID:</label>
        <input type="number" id="receiverId" name="receiverId" required><br><br>
        <label for="amount">Amount:</label>
        <input type="number" id="amount" name="amount" step="0.01" required><br><br>
        <input type="submit" value="Transfer">
    </form>
</body>
</html>
