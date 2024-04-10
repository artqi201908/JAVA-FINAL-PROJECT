<html>
<head>
    <link rel="stylesheet" href="css/main.css?v=<%=System.currentTimeMillis()%>>">
</head>
<body>

<jsp:include page="views/topNavBar.jsp" flush="true"/>

<div class="main-panel">
    <div>
        <h3>Create Item</h3>
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
            <FORM ACTION="createItem" METHOD="POST">
                <table>
                    <tr>
                        <td>Title:</td>
                        <td><INPUT TYPE="TEXT" NAME="title" VALUE="apple"></td>
                    </tr>
                    <tr>
                        <td>Description:</td>
                        <td><INPUT TYPE="TEXT" NAME="description" VALUE="apple"></td>
                    </tr>
                    <tr>
                        <td>Expiration Date:</td>
                        <td><INPUT TYPE="TEXT" NAME="expirationDate" VALUE="2024-03-29"> Ex:2024-03-04</td>
                    </tr>
                    <tr>
                        <td>Quantity:</td>
                        <td><INPUT TYPE="TEXT" NAME="quantity" VALUE="100"></td>
                    </tr>
                    <tr>
                        <td>Price:</td>
                        <td><INPUT TYPE="TEXT" NAME="price" VALUE="5.88"> $</td>
                    </tr>
                    <tr>
                        <td>Discount:</td>
                        <td><INPUT TYPE="TEXT" NAME="discount" VALUE="2"> %</td>
                    </tr>
                    <tr>
                        <td></td>
                        <td><INPUT TYPE="checkbox" NAME="isDonate"> isDonate</td>
                    </tr>
                    <tr>
                        <td></td>
                        <td><button>Submit</button>&nbsp;<a href="listItem">Cancel</a></td>
                    </tr>
                </table>
            </FORM>
        </div>
    </div>

</div>

</body>
</html>


