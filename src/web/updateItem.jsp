<%@ page import="java.transferobject.FoodItemDTO" %>
<html>
<head>
    <link rel="stylesheet" href="css/main.css?v=<%=System.currentTimeMillis()%>>">
</head>
<body>

<jsp:include page="topNavBar.jsp" flush="true"/>

<div class="main-panel">
    <div>
        <h3>Edit Item</h3>
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
            <FORM ACTION="updateItem" METHOD="POST">
                <table>
                    <tr>
                        <td>Title:</td>
                        <td><INPUT TYPE="TEXT" NAME="title" VALUE="<%=item.getTitle()%>">
                            <INPUT TYPE="HIDDEN" NAME="itemId" VALUE="<%=item.getId()%>"></td>
                    </tr>
                    <tr>
                        <td>Description:</td>
                        <td><INPUT TYPE="TEXT" NAME="description" VALUE="<%=item.getDescription()%>"></td>
                    </tr>
                    <tr>
                        <td>Expiration Date:</td>
                        <td><INPUT TYPE="TEXT" NAME="expirationDate" VALUE="<%=item.getExpirationDate()%>"> Ex:2024-03-04</td>
                    </tr>
                    <tr>
                        <td>Quantity:</td>
                        <td><INPUT TYPE="TEXT" NAME="quantity" VALUE="<%=item.getQuantity()%>"></td>
                    </tr>
                    <tr>
                        <td>Price:</td>
                        <td><INPUT TYPE="TEXT" NAME="price" VALUE="<%=item.getPrice()%>"> $</td>
                    </tr>
                    <tr>
                        <td>Discount:</td>
                        <td><INPUT TYPE="TEXT" NAME="discount" VALUE="<%=item.getDiscount()%>"> %</td>
                    </tr>
                    <tr>
                        <td></td>
                        <td><INPUT TYPE="checkbox" NAME="isDonate" <% if (item.getIsDonate()) {out.print("checked"); } %>> isDonate</td>
                    </tr>
                    <tr>
                        <td></td>
                        <td><INPUT TYPE="checkbox" NAME="isSurplus" <% if (item.getIsSurplus()) {out.print("checked"); } %>> isSurplus</td>
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