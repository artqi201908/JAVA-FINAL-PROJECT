<%@ page import="javax.xml.registry.infomodel.User" %>


<%
    User user = (User) session.getAttribute("user");
%>



<% if (user != null && user.getTypeId() == UserType.RETAILER) { %>

<% }%>



<% if (user != null && user.getTypeId() == UserType.CONSUMER) { %>

<% }%>


<% if (user != null && user.getTypeId() == UserType.CHARITABLE_ORGANIZATION) { %>
<% }%>



