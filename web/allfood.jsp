<%-- 
    Document   : allfood
    Created on : 2024年4月6日, 下午12:32:14
    Author     : danni
--%>

<%@page import="transferobject.FoodItemDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="java.transferobject.FoodItemDTO"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>All Food Items</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>

<div class="sidebar">
    <a href="index.jsp">Home</a>
    <a href="PurchaseItem.jsp">Purchase Item</a>
    <!-- other links -->
</div>

<div class="content">
    <h2>All Food Items</h2>
    <table>
        <tr>
            <th>Item ID</th>
            <th>Food Name</th>
            <th>Price</th>
            <th>Action</th>
        </tr>
        <%
        List<FoodItemDTO> foodItems = (List<FoodItemDTO>)request.getAttribute("foodItems");
        for(FoodItemDTO item : foodItems) {
        %>
            <tr>
                <td><%= item.getId() %></td>
                <td><%= item.getName() %></td>
                <td><%= item.getPrice() %></td>
                <td>
                    <a href="PurchaseItem.jsp?itemId=<%= item.getId() %>">Purchase</a>
                </td>
            </tr>
        <%
        }
        %>
    </table>
</div>

</body>
</html>
