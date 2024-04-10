<%@ page import="java.transferobject.FoodItemDTO" %>
<html>
<head>
    <link rel="stylesheet" href="css/main.css?v=<%=System.currentTimeMillis()%>>">
</head>
<body>

<jsp:include page="views/topNavBar.jsp" flush="true"/>

<div class="main-panel">
    <div>
        <h3>Create Order</h3>
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
            <%
                FoodItemDTO item = (FoodItemDTO) request.getAttribute("item");
            %>
            <FORM ACTION="createOrder" METHOD="POST">
                <table>
                    <tr>
                        <td>Title:</td>
                        <td><%=item.getTitle()%>
                            <INPUT TYPE="HIDDEN" NAME="itemId" VALUE="<%=item.getId()%>"></td>
                    </tr>
                    <tr>
                        <td>Description:</td>
                        <td><%=item.getDescription()%></td>
                    </tr>
                    <tr>
                        <td>Expiration Date:</td>
                        <td><%=item.getExpirationDate()%></td>
                    </tr>
                    <tr>
                        <td>Quantity:</td>
                        <td><INPUT TYPE="TEXT" NAME="quantity" VALUE="<%=item.getQuantity()%>"></td>
                    </tr>
                    <tr>
                        <td>Address:</td>
                        <td><INPUT TYPE="TEXT" NAME="address" VALUE="mountain view"></td>
                    </tr>
                    <tr>
                        <td>Price:</td>
                        <td><%=item.getPrice()%> $</td>
                    </tr>
                    <tr>
                        <td>Discount:</td>
                        <td><%=item.getDiscount()%> %</td>
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


