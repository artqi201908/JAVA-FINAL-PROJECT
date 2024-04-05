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
    </head>
    <body>
    <center><form action="LoginServlet" method="post">
                    <table>
                        <tr>
                            <th>UserName:</th><th><input type="text" name="username"</th>
                        </tr>
                        <tr>
                            <th>Password:</th><th><input type="password" name="password"</th>
                        </tr>
                        <th></th>
                        <th><input type="submit" value="Login">
                    <input type="reset" value="Reset"></th>
                    </table>
 
        </form>
        <a href="register.jsp" class="register-button">Register</a>
    </center>
    </body>
</html>
