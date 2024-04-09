<%-- 
    Document   : consumers
    Created on : 2024年4月5日, 下午11:26:44
    Author     : danni
--%>
<%@page import="transferobject.FoodItemDTO"%>
<%@page import="transferobject.FoodItemDTO"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Consumer Page</title>
        <link rel="stylesheet" href="style.css">

    </head>

    <body>
        <div class="sidebar">
            <img src="pics/logo.jpg" alt="Your Logo" class="sidebar-logo">
            <a href="index.jsp">Home</a> 
            <a href="allfood.jsp">All Food</a>
            <a href="LogoutServlet">Logout</a>
        </div>
        <div class="content">
            <h2>All Food</h2>
            <table>
                <tr>
                    <th>Food Name</th>
                    <th>Quantity</th>
                    <th>Price</th> 
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
                    <td><%= food.getPrice() %></td> 
                    <td><%= food.getExpirationDate() %></td>
                    <td><%= food.getDiscountRate() %></td>
                </tr>
                <% 
                    }
                } else {
                %>
                <tr>
                    <td colspan="5" style="text-align:center;">No food is available</td>
                </tr>
                <% 
                }
                %>
            </table>
        </div>

    </body>
</html>
