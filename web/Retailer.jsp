<%-- 
    Document   : Retailer
    Created on : Apr 5, 2024, 1:46:11 PM
    Author     : phron
--%>

<%@page import="java.transferobject.FoodItemDTO"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Your Application's Title</title>
    <link rel="stylesheet" href="Retailer.css">
</head>
<body>

<div class="sidebar">
    <a href="index.jsp">Home</a> <!-- Assuming you have a home page -->
    <a href="register.jsp">Register</a>
    <a href="login.jsp">Login</a>
    <a href="logout.jsp">Logout</a>
    <a href="addfood.jsp"> Add Inventory</a>
</div>

<div class="content">
    <h2>Your Inventory</h2>
    <table>
        <tr>
            <th>Food Name</th>
            <th>Quantity</th>
            <th>Price</th> <!-- Assuming you wanted Price here -->
            <th>Expiration Date</th>
            <th>Discount Rate</th>
        </tr>
        <%	
        List<FoodItemDTO> foodList = (List<FoodItemDTO>) request.getAttribute("foodList");
        if(foodList != null && !foodList.isEmpty()) {
            for (FoodItemDTO food : foodList) { 
        %>
                <tr>
                    <td><%= food.getName() %></td>
                    <td><%= food.getQuantity() %></td>
                    <td><%= food.getPrice() %></td> <!-- Corrected from getExpirationDate() -->
                    <td><%= food.getExpirationDate() %></td>
                    <td><%= food.getDiscountRate() %></td>
                </tr>
        <% 
            }
        } else {
        %>
                <tr>
                    <td colspan="5" style="text-align:center;">You do not have any food in the inventory.</td>
                </tr>
        <% 
        }
        %>
    </table>
</div>

</body>
</html>
