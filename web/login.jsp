<%-- 
    Document   : loginjsp
    Created on : Apr 2, 2024, 10:02:43 PM
    Author     : phron
--%>

<%@page import="java.util.List"%>
<%@page import="transferobject.UserDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
        <link rel="stylesheet" href="registration.css">
    </head>
    <body>
    <center>
        <form action="LoginServlet" method="post">
                   <div class="textfield">
                <label for="email">Email Address</label>
                <input type="text" name="email" id="email" placeholder="Email">
                <span class="error" id="emailError">Email address should be non-empty</span>
            </div>

            <div class="textfield">
                <label for="pass">Password</label>
                <input type="password" name="pass" id="pass" placeholder="Password">
                <span class="error" id="passError">Password should be non-empty</span>
            </div>

            <button type="submit">Sign In</button>
 
        </form>
        <p>Don't have an account? Sign up <a href="register.jsp">here</a></p>
       
    </center>
    </body>
</html>
