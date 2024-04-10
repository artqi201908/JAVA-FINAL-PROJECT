<%@ page import="java.transferobject.UserDTO" %>
<%@ page import="java.transferobject.OrderDTO" %>
<%@ page import="java.util.List" %>
<%@ page import="java.constant.UserType" %>
<%@ page import="java.constant.orderStatus" %>
<%
    UserDTO user = (UserDTO) session.getAttribute("user");
%>
<html>
<head>
    <link rel="stylesheet" href="css/main.css?v=<%=System.currentTimeMillis()%>">
</head>
<body>

<jsp:include page="topNavBar.jsp" flush="true"/>

<% if (user != null && user.getTypeId() == UserType.RETAILER) { %>
<div class="main-panel">
    <div class="left-nav-bar">
        <ul>
            <li>
                <a href="listItem">List Item</a>
            </li>
            <li>
                <a href="createItem">Create Item</a>
            </li>
            <li>
                <a href="listSurplusItem">Surplus List</a>
            </li>
            <li>
                <a href="listOrder">Order list</a>
            </li>
        </ul>
    </div>
    <div class="content">
        <table border="1">
            <thead>
            <tr>
                <th>Id</th>
                <th>Item Id</th>
                <th>Item title</th>
                <th>Quantity</th>
                <th>Address</th>
                <th>Status</th>
                <th>Action</th>
            </tr>
            </thead>
            <tbody>
            <% List<OrderDTO> orders = (List<OrderDTO>) request.getAttribute("orders");
                for (OrderDTO order : orders) {%>
            <tr>
                <td><%= order.getId()%></td>
                <td><%= order.getItemId()%></td>
                <td><%= order.getItemTitle()%></td>
                <td><%= order.getQuantity()%></td>
                <td><%= order.getAddress()%></td>
                <td>
                    <%
                        if (order.getStatusId() == orderStatus.PENDING_APPROVE) {
                            out.print("Pending approve");
                        }else if (order.getStatusId() == orderStatus.APPROVED) {
                            out.print("Approved");
                        }
                    %>
                </td>
                <td>
                    <a href="approveOrder?orderId=<%=order.getId()%>">Approve</a>
                </td>
            </tr>
            <% }%>
            </tbody>
        </table>
        <div>
            <%
                if (orders == null || orders.size() == 0) {
                    out.println("No results.");
                }
            %>
        </div>
    </div>
</div>
<% }%>

<% if (user != null && user.getTypeId() == UserType.CONSUMER) { %>
<div class="main-panel">
    <div class="left-nav-bar">
        <ul>
            <li>
                <a href="listItem">Purchase</a>
            </li>
            <li>
                <a href="listOrder">My Item</a>
            </li>
        </ul>
    </div>
    <div class="content">
        <table border="1">
            <thead>
            <tr>
                <th>Id</th>
                <th>Item Id</th>
                <th>Item title</th>
                <th>Quantity</th>
                <th>Address</th>
                <th>Status</th>
            </tr>
            </thead>
            <tbody>
            <% List<OrderDTO> orders = (List<OrderDTO>) request.getAttribute("orders");
                for (OrderDTO order : orders) {%>
            <tr>
                <td><%= order.getId()%></td>
                <td><%= order.getItemId()%></td>
                <td><%= order.getItemTitle()%></td>
                <td><%= order.getQuantity()%></td>
                <td><%= order.getAddress()%></td>
                <td>
                    <%
                        if (order.getStatusId() == orderStatus.PENDING_APPROVE) {
                            out.print("Pending approve");
                        }else if (order.getStatusId() == orderStatus.APPROVED) {
                            out.print("Approved");
                        }
                    %>
                </td>
            </tr>
            <% }%>
            </tbody>
        </table>
        <div>
            <%
                if (orders == null || orders.size() == 0) {
                    out.println("No results.");
                }
            %>
        </div>
    </div>
</div>
<% }%>


<% if (user != null && user.getTypeId() == UserType.CHARITABLE_ORGANIZATION) { %>
<div class="main-panel">
    <div class="left-nav-bar">
        <ul>
            <li>
                <a href="listItem">Claim</a>
            </li>
            <li>
                <a href="listOrder">My Item</a>
            </li>
        </ul>
    </div>
    <div class="content">
        <table border="1">
            <thead>
            <tr>
                <th>Id</th>
                <th>Item Id</th>
                <th>Item title</th>
                <th>Quantity</th>
                <th>Address</th>
                <th>Status</th>
            </tr>
            </thead>
            <tbody>
            <% List<OrderDTO> orders = (List<OrderDTO>) request.getAttribute("orders");
                for (OrderDTO order : orders) {%>
            <tr>
                <td><%= order.getId()%></td>
                <td><%= order.getItemId()%></td>
                <td><%= order.getItemTitle()%></td>
                <td><%= order.getQuantity()%></td>
                <td><%= order.getAddress()%></td>
                <td>
                    <%
                        if (order.getStatusId() == orderStatus.PENDING_APPROVE) {
                            out.print("Pending approve");
                        }else if (order.getStatusId() == orderStatus.APPROVED) {
                            out.print("Approved");
                        }
                    %>
                </td>
            </tr>
            <% }%>
            </tbody>
        </table>
        <div>
            <%
                if (orders == null || orders.size() == 0) {
                    out.println("No results.");
                }
            %>
        </div>
    </div>
</div>
<% }%>

</body>
</html>