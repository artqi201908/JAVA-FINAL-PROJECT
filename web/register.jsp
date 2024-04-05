<%-- 
    Document   : register
    Created on : Apr 3, 2024, 10:30:52 PM
    Author     : phron
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
    <center><h2>Registration Form</h2>
    
    <form action="RegisterServlet" method="post">
        <div>
            <label for="name">Name:</label>
            <input type="text" id="name" name="name" required>
        </div>
        <div>
            <label for="email">Email:</label>
            <input type="email" id="email" name="email" required>
        </div>
        <div>
            <label for="password">Password:</label>
            <input type="password" id="password" name="password" required>
        </div>
        <div>
            <label for="userType">User Type:</label>
            <select id="userType" name="userType">
                <option value="retailer">Retailer</option>
                <option value="consumer">Consumer</option>
                <option value="charitableOrganization">Charitable Organization</option>
            </select>
        </div>
        <div>
            <input type="submit" value="Register">
        </div>
    </form>
        </center>

</body>
</html>