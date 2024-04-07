<%-- 
    Document   : PurchaseItem.jsp
    Created on : 2024年4月7日, 下午2:01:48
    Author     : danni
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Purchase Item</title>
    <link rel="stylesheet" href="Consumer.css"> <!-- change it later -->
</head>
<body>

<div class="sidebar">
    <a href="index.jsp">Home</a> <!-- Assuming you have a home page -->
    <a href="register.jsp">Register</a>
    <a href="login.jsp">Login</a>
    <a href="logout.jsp">Logout</a>
    <a href="PurchaseItem.jsp">Purchase Item</a> <!-- Link to this page -->
</div>

<div class="content">
    <h2>Purchase Item</h2>
    <form action="ConsumerServlet" method="POST">
        <div class="form-group">
            <label for="userId">User ID:</label>
            <input type="text" id="userId" name="userId" required>
        </div>
        <div class="form-group">
            <label for="itemId">Item ID:</label>
            <input type="text" id="itemId" name="itemId" required value="<%= request.getParameter("itemId") != null ? request.getParameter("itemId") : "" %>">

        </div>
        <div class="form-group">
            <label for="quantity">Quantity:</label>
            <input type="number" id="quantity" name="quantity" required min="1">
        </div>
        <button type="submit">Submit</button>
    </form>
</div>

</body>
</html>
