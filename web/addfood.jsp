<%-- 
    Document   : addfood
    Created on : Apr 5, 2024, 6:59:11 PM
    Author     : phron
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Add Food Item</title>
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
            <h2>Add New Food Item</h2>
            <form action="AddFoodServlet" method="POST">
                <label for="name">Food Name:</label><br>
                <input type="text" id="name" name="name" required><br>

                <label for="quantity">Quantity:</label><br>
                <input type="number" id="quantity" name="quantity" required><br>

                <label for="price">Price:</label><br>
                <input type="text" id="price" name="price" required><br>

                <label for="expirationDate">Expiration Date:</label><br>
                <input type="date" id="expirationDate" name="expirationDate" required><br>

                <label for="discountRate">Discount Rate (%):</label><br>
                <input type="text" id="discountRate" name="discountRate"><br>

                <label for="isForDonation">Is For Donation:</label><br>
                <input type="checkbox" id="isForDonation" name="isForDonation"><br>

                <input type="submit" value="Add Food Item">
            </form>
        </div>

    </body>
</html>
