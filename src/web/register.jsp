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
        <title>register page</title>

        <link rel="stylesheet" href="registration.css">
    </head>
    <body>

        <form action="RegisterServlet" method="post">

            <div class="textfield">
                <label for="email">Email Address</label>
                <input type="text" name="email" id="email" placeholder="Email">
            </div>

            <div class="textfield">
                <label for="login">User Name</label>
                <input type="text" name="login" id="login" placeholder="User name"> 

            </div>

            <div class="textfield">
                <label for="pass">Password</label>
                <input type="password" name="pass" id="pass" placeholder="Password">
            </div>

            <div class="textfield">
                <label for="pass2">Re-type Password</label>
                <input type="password" name="pass2" id="pass2" placeholder="Password">
            </div>


            <div class="textfield">
                <label for="userType">User Type:</label>
                <select id="userType" name="userType">
                    <option value="retailer">Retailer</option>
                    <option value="consumer">Consumer</option>
                    <option value="charitableOrganization">Charitable Organization</option>
                </select>
            </div>
            <% if (request.getAttribute("error") != null) {%>
            <p style="color: red;"><%= request.getAttribute("error")%></p>
            <% }%>

            <button type="submit">Sign Up</button>
            <button type="reset">Reset</button>
            <p>Already a user? Sign in <a href="login.jsp">here</a></p>

        </form>

    </body>
</html>