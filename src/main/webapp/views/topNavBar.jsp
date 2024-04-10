<%@ page import="algonquin.dto.User" %>
<div class="top-nav-bar">
    <div class="logo">
        <h2>FWRP</h2>
    </div>
    <div class="profile">
        <%
            User user = (User) session.getAttribute("user");

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

                <a href="signup">Sign up</a>
        <%
            }
        %>

    </div>
</div>