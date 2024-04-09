<%-- 
    Document   : addfood
    Created on : Apr 5, 2024, 6:59:11 PM
    Author     : phron
--%>

<%@page import="dataaccesslayer.FoodItemDAOImpl"%>
<%@page import="java.util.List"%>
<%@page import="transferobject.FoodItemDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Your Retailer Inventory</title>
        <link rel="stylesheet" href="style.css">
    </head>
    <body>

        <div class="sidebar">
            <img src="pics/logo.jpg" alt="Your Logo" class="sidebar-logo">
            <a href="index.jsp">Home</a>
            <a href="allfood.jsp">All Food</a>
            <a href="addfood.jsp">Add Inventory</a>
            <a href="LogoutServlet">Logout</a>
        </div>

        <div class="content">
            <h2>Your Inventory</h2>
            <%
                Integer userID = (Integer) session.getAttribute("userID");
                if (userID != null) {
                    FoodItemDAOImpl inventoryDAO = new FoodItemDAOImpl();
                    List<FoodItemDTO> inventoryList = inventoryDAO.getAllFoodItemsByUserId(userID);
                    
                    if (inventoryList != null && !inventoryList.isEmpty()) {
            %>
                        <table>
                            <tr>
                                <th>Food Name</th>
                                <th>Quantity</th>
                                <th>Price</th>
                                <th>Expiration Date</th>
                                <th>Discount Rate</th>
                                <th>For Donation</th>
                            </tr>
                            <%
                                for (FoodItemDTO food : inventoryList) {
                            %>
                            <tr>
                                <td><%= food.getName() %></td>
                                <td><%= food.getQuantity() %></td>
                                <td><%= food.getPrice() %></td>
                                <td><%= food.getExpirationDate() %></td>
                                <td><%= food.getDiscountRate() %></td>
                                <td><%= food.isForDonation() ? "Yes" : "No" %></td>
                            </tr>
                            <%
                                }
                            %>
                        </table>
            <%
                    } else {
            %>
                        <p>No food items found.</p>
            <%
                    }
                } else {
                    // If userID is not found in session, redirect to login or handle accordingly
                    response.sendRedirect("login.jsp");
                }
            %>
        </div>

    </body>
</html>
