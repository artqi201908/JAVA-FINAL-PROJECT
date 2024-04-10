<%@ page import="javax.xml.registry.infomodel.User" %>
<%@ page import="java.transferobject.UserDTO" %>
<%@ page import="java.transferobject.UserType" %>
<%@page import="java.transferobject.FoodItemDTO" %>
<%@ page import="java.util.List" %>


<%
    UserDTO user = (UserDTO) session.getAttribute("user");
%>



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
                <th>id</th>
                <th>title</th>
                <th>quantity</th>
                <th>expirationDate</th>
                <th>isDonate</th>
                <th>price</th>
                <th>disCount</th>
                <th>isSurplus</th>
                <th>Action</th>
            </tr>
            </thead>
            <tbody>
            <% List<FoodItemDTO> items = (List<FoodItemDTO>) request.getAttribute("items");
                for (FoodItemDTO item : items) {%>
            <tr>
                <td><%= item.getId()%></td>
                <td><%= item.getTitle()%></td>
                <td><%= item.getQuantity()%></td>
                <td <% if(DateUtil.isExpiredInNextWeek(item.getExpirationDate())) { out.print("style='color: red;'"); } %>>
                    <%= item.getExpirationDate()%>
                </td>
                <td><%= item.getIsDonate()%></td>
                <td><%= item.getPrice()%></td>
                <td><%= item.getDiscount()%></td>
                <td><%= item.getIsSurplus()%></td>
                <td>
                    <a href="updateItem?itemId=<%=item.getId()%>">Edit</a>
                    <a href="deleteItem?itemId=<%=item.getId()%>">Delete</a>
                </td>
            </tr>
            <% }%>
            </tbody>
        </table>
        <div>
            <%
                if (items == null || items.size() == 0) {
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
                <th>id</th>
                <th>title</th>
                <th>quantity</th>
                <th>expirationDate</th>
                <th>price</th>
                <th>disCount</th>
                <th>Action</th>
            </tr>
            </thead>
            <tbody>
            <% List<FoodItemDTO> items = (List<FoodItemDTO>) request.getAttribute("items");
                for (FoodItemDTO item : items) {%>
            <tr>
                <td><%= item.getId()%></td>
                <td><%= item.getTitle()%></td>
                <td><%= item.getQuantity()%></td>
                <td><%= item.getExpirationDate()%></td>
                <td><%= item.getPrice()%></td>
                <td><%= item.getDiscount()%></td>
                <td>
                    <a href="createOrder?itemId=<%=item.getId()%>">buy</a>
                </td>
            </tr>
            <% }%>
            </tbody>
        </table>
        <div>
            <%
                if (items == null || items.size() == 0) {
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
                <th>id</th>
                <th>title</th>
                <th>quantity</th>
                <th>expirationDate</th>
                <th>price</th>
                <th>disCount</th>
                <th>Action</th>
            </tr>
            </thead>
            <tbody>
            <% List<FoodItemDTO> items = (List<FoodItemDTO) request.getAttribute("items");
                for (FoodItemDTO item : items) {%>
            <tr>
                <td><%= item.getId()%></td>
                <td><%= item.getTitle()%></td>
                <td><%= item.getQuantity()%></td>
                <td><%= item.getExpirationDate()%></td>
                <td><%= item.getPrice()%></td>
                <td><%= item.getDiscount()%></td>
                <td>
                    <a href="createOrder?itemId=<%=item.getId()%>">Claim</a>
                </td>
            </tr>
            <% }%>
            </tbody>
        </table>
        <div>
            <%
                if (items == null || items.size() == 0) {
                    out.println("No results.");
                }
            %>
        </div>
    </div>
</div>
<% }%>



