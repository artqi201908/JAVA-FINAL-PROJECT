<%-- 
    Document   : CharitableOrganizationFoodHistory
    Created on : Apr 6, 2024, 12:57:15 PM
    Author     : artqi
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <title>Food History</title>
    <!-- Bootstrap CSS for styling -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        /* Custom styles */
        .table { margin-top: 20px; }
    </style>
</head>
<body>

<div class="container">
    <h2>Food History</h2>

    <!-- Adding a table to display food history with Bootstrap styling -->
    <table class="table table-striped">
        <thead>
            <tr>
                <th>ID</th>
                <th>Item Name</th>
                <th>Quantity</th>
                <th>Received Date</th>
                <th>Address</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="order" items="${foodOrders}">
                <tr>
                    <td>${order.id}</td>
                    <td>${order.itemName}</td> <!-- Assuming OrderDTO has an itemName attribute -->
                    <td>${order.quantity}</td>
                    <td><fmt:formatDate value="${order.receivedDate}" pattern="yyyy-MM-dd"/></td> <!-- Assuming OrderDTO has a receivedDate attribute -->
                    <td>${order.address}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>

</body>
</html>