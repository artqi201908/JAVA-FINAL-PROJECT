<%-- 
    Document   : CharitableOrganizationFoodItemManagement
    Created on : Apr 6, 2024, 12:38:27 PM
    Author     : artqi
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <title>Food Item Management</title>
    <!-- Bootstrap CSS for styling -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        /* Custom styles */
        .table { margin-top: 20px; }
    </style>
</head>
<body>

<div class="container">
    <h2>Food Item Management</h2>

    <!-- Adding a table to display food items with Bootstrap styling -->
    <table class="table table-striped">
        <thead>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Quantity</th>
                <th>Expiration Date</th>
                <th>Price</th>
                <th>Discount Rate</th>
                <th>For Donation</th>
                <th>Category</th>
                <th>Description</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="item" items="${foodItems}">
                <tr>
                    <td>${item.itemId}</td>
                    <td>${item.name}</td>
                    <td>${item.quantity}</td>
                    <td><fmt:formatDate value="${item.expirationDate}" pattern="yyyy-MM-dd"/></td>
                    <td>${item.price}</td>
                    <td>${item.discountRate}%</td>
                    <td><c:out value="${item.forDonation ? 'Yes' : 'No'}"/></td>
                    <td>${item.category}</td>
                    <td>${item.description}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>

</body>
</html>