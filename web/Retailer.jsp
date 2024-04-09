<%-- 
    Document   : Retailer
    Created on : Apr 5, 2024, 1:46:11 PM
    Author     : phron
--%>

<%@page import="java.time.temporal.ChronoUnit"%>
<%@page import="java.time.ZoneId"%>
<%@page import="java.util.Date"%>
<%@page import="java.time.LocalDate"%>
<%-- 
    Document   : Retailer
    Created on : Apr 5, 2024, 1:46:11 PM
    Author     : phron
--%>

<%@page import="transferobject.FoodItemDTO"%>
<%@page import="transferobject.FoodItemDTO"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Your Application's Title</title>
        <link rel="stylesheet" href="style.css">
    </head>
    <body>

        <div class="sidebar">
            <img src="pics/logo.jpg" alt="Your Logo" class="sidebar-logo">
            <a href="index.jsp">Home</a> <!-- Assuming you have a home page -->
            <a href="allfood.jsp">All Food</a>
            <a href="addfood.jsp"> Add Inventory</a>
            <a href="LogoutServlet">Logout</a>

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
                    <th>Surplus Flag</th> <!-- Additional column for surplus flag -->
                </tr>
                <%
                    List<FoodItemDTO> foodList = (List<FoodItemDTO>) request.getAttribute("foodList");
                    LocalDate today = LocalDate.now();

                    if (foodList != null && !foodList.isEmpty()) {
                        for (FoodItemDTO food : foodList) {
                            Date expiration = food.getExpirationDate();
                            LocalDate expirationDate = expiration.toInstant()
                                    .atZone(ZoneId.systemDefault())
                                    .toLocalDate();
                            long daysUntilExpiration = ChronoUnit.DAYS.between(today, expirationDate);
                            boolean isSurplus = daysUntilExpiration <= 7;
                %>
                <tr>
                    <td><%= food.getName()%></td>
                    <td><%= food.getQuantity()%></td>
                    <td><%= food.getPrice()%></td>
                    <td><%= food.getExpirationDate()%></td>
                    <td><%= food.getDiscountRate()%></td>
                    <td><%= isSurplus ? "Surplus" : ""%></td> <!-- Display Surplus if within 1 week -->
                </tr>
                <%
                    }
                } else {
                %>
                <tr>
                    <td colspan="6" style="text-align:center;">You do not have any food in the inventory.</td>
                </tr>
                <%
                    }
                %>
            </table>
        </div>

    </body>
</html>
