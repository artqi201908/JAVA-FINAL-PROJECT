<div class="main-panel">
    <div class="left-panel">
        <img src="pics/logo.jpg" title="">
    </div>
    <div class="right-panel">
        <div>
            <h3>Welcome to FWRP</h3>
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
            <FORM ACTION="signin" METHOD="POST">
                <table>
                    <tr>
                        <td>Username:</td>
                        <td><INPUT TYPE="TEXT" NAME="username" VALUE="retailer1"></td>
                    </tr>
                    <tr>
                        <td>Password:</td>
                        <td><INPUT TYPE="password" NAME="password" VALUE="retailer1"></td>
                    </tr>
                    <tr>
                        <td></td>
                        <td><button>login</button></td>
                    </tr>
                </table>
            </FORM>
        </div>

    </div>
</div>