<%-- 
    Document   : allfood.jsp
    Created on : 2024年4月7日, 下午2:01:48
    Author     : danni
--%>

<%@page import="transferobject.FoodItemDTO"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Purchase Item</title>
        <link rel="stylesheet" href="style.css"> <!-- change it later -->
    </head>
    <body>

        <div class="sidebar">
            <img src="pics/logo.jpg" alt="Your Logo" class="sidebar-logo">
            <a href="index.jsp">Home</a> <!-- Assuming you have a home page -->
            <a href="allfood.jsp">All Food</a> <!-- Link to this page -->
            <a href="LogoutServlet">Logout</a>

        </div>

        <div class="content">
            <h2>ALL Food</h2>
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