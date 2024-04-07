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
    <h2>Registration Form</h2>
    
    <form action="RegisterServlet" method="post">
    
        
        <div class="textfield">
                <label for="email">Email Address</label>
                <input type="text" name="email" id="email" placeholder="Email">
                <span class="error" id="emailError">Email address should be non-empty 
                    with the format xyz@xyz.xyz.</span>
            </div>

            <div class="textfield">
                <label for="login">User Name</label>
                <input type="text" name="login" id="login" placeholder="User name">  <!-- Not use required tag！-->
                <span class="error" id="loginError">User should be non-empty, 
                    and within 20 characters long.</span>
            </div>

            <div class="textfield">
                <label for="pass">Password</label>
                <input type="password" name="pass" id="pass" placeholder="Password">
                <span class="error" id="passError">Pssword should be at least 8 characters:
                    1 uppercase, 1 lowercase.</span>
            </div>
        
            <div class="textfield">
                <label for="pass2">Re-type Password</label>
                <input type="password" id="pass2" placeholder="Password">
                <span class="error" id="pass2Error">Please retype password.</span>
            </div>
        
        
        <div class="textfield">
            <label for="userType">User Type:</label>
            <select id="userType" name="userType">
                <option value="retailer">Retailer</option>
                <option value="consumer">Consumer</option>
                <option value="charitableOrganization">Charitable Organization</option>
            </select>
        </div>
        
        

            <div class="checkbox">
                <input type="checkbox" name="terms" id="terms">
                <label for="terms">I agree to the terms and conditions</label>
                <span class="error" id="termsError">Please accept the terms and conditions.</span>
            </div>

            <button type="submit">Sign Up</button>
            <button type="reset">Reset</button>
        
        
    </form>
    
        

</body>
</html>