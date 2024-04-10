/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

<%@page import="java.util.List" %>



<%
    User user = (User) session.getAttribute("user");
%>


<% if (user != null && user.getTypeId() == UserType.RETAILER) { %>

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
    <% List<Item> items = (List<Item>) request.getAttribute("items");
        for (Item item : items) {%>
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