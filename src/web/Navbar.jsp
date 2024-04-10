<%@ page import="javax.xml.registry.infomodel.User" %>
<%@ page import="java.transferobject.UserDTO" %>
<div class="top-nav-bar">



<div class="logo">
    <h2>FWRP</h2>
</div>


<div class="profile">

    <%
        UserDTO user = (UserDTO) session.getAttribute("user");

        if (user != null) {
            out.println("<div>" + user.getUsername() + " <a href=\"logout\">Logout</a></div>");
            out.println("<div>Balance: $" + user.getBalance() + "</div>");
            if (user.getIsSubscribe()) {
                out.println("<div><a href=\"unsubscribe\">Unsubscribe</a></div>");
            } else {
                out.println("<div><a href=\"subscribe\">Subscribe</a></div>");
            }
        } else {
    %>

    <a href="register.jsp">Sign up</a>
    <%
        }
    %>




</div>


</div>