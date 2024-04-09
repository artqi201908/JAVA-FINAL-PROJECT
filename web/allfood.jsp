<%-- 
    Document   : allfood
    Created on : Apr 9, 2024, 3:08:12 AM
    Author     : phron
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="transferobject.FoodItemDTO"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>All Food Items</title>
        <link rel="stylesheet" href="style.css">
    </head>
    <body>
        <div class="content">
            <h2>All Food Items</h2>
            <table border="1">
                <tr>
                    <th>Food Name</th>
                    <th>Quantity</th>
                    <th>Price</th>
                    <th>Expiration Date</th>
                    <th>Discount Rate</th>
                    <th>For Donation</th>
                </tr>
                <%
                    List<FoodItemDTO> foodList = (List<FoodItemDTO>) request.getAttribute("foodlist"); // Make sure attribute name matches what's set in servlet
                    if (foodList != null && !foodList.isEmpty()) {
                        for (FoodItemDTO item : foodList) {
                %>
                <tr>
                    <td><%= item.getName()%></td>
                    <td><%= item.getQuantity()%></td>
                    <td><%= item.getPrice()%></td>
                    <td><%= item.getExpirationDate().toString()%></td>
                    <td><%= item.getDiscountRate()%></td>
                    <td><%= item.isForDonation() ? "Yes" : "No"%></td>
                </tr>
                <%
                    }
                } else {
                %>
                <tr>
                    <td colspan="6">No food items found.</td>
                </tr>
                <%
                    }
                %>
            </table>
        </div>
    </body>
</html>
