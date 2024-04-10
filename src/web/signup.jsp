<%--
    Created on : Apr 2, 2024
    Author     : fengqi
--%>
<html>
<head>
    <link rel="stylesheet" href="css/main.css?v=<%=System.currentTimeMillis()%>>">
</head>
<body>

<jsp:include page="views/topNavBar.jsp" flush="true"/>

<div class="main-panel">
    <div>
        <h3>Sign Up</h3>
    </div>
    <div>
        <div>
            <%
                String errorMsg = (String) request.getAttribute("errorMsg");
                if (errorMsg != null) {
            %>
            <h6 style="color: red"><%=errorMsg%></h6>
            <%
                }
            %>
        </div>
        <div>
            <FORM ACTION="signup" METHOD="POST">
                <table>
                    <tr>
                        <td>Username:</td>
                        <td><INPUT TYPE="TEXT" NAME="username" VALUE=""></td>
                    </tr>
                    <tr>
                        <td>Password:</td>
                        <td><INPUT TYPE="password" NAME="password" VALUE=""></td>
                    </tr>
                    <tr>
                        <td>Confirmed Password:</td>
                        <td><INPUT TYPE="password" NAME="confirmedPassword" VALUE=""></td>
                    </tr>
                    <tr>
                        <td>Email:</td>
                        <td><INPUT TYPE="TEXT" NAME="email" VALUE=""></td>
                    </tr>
                    <tr>
                        <td>Phone:</td>
                        <td><INPUT TYPE="TEXT" NAME="phone" VALUE=""></td>
                    </tr>
                    <tr>
                        <td>Type:</td>
                        <td>
                            <select name="type">
                                <option value="1">Retailer</option>
                                <option value="2">Charitable organization</option>
                                <option value="3">Consumer</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>Balance:</td>
                        <td><INPUT TYPE="TEXT" NAME="balance" VALUE="100000"></td>
                    </tr>
                    <tr>
                        <td></td>
                        <td><INPUT TYPE="checkbox" NAME="isSubscribe"> isSubscribe</td>
                    </tr>
                    <tr>
                        <td></td>
                        <td><button>Submit</button>&nbsp;<a href="index.jsp">Cancel</a></td>
                    </tr>
                </table>
            </FORM>
        </div>
    </div>

</div>

</body>
</html>


