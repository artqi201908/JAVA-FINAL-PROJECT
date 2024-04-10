
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="style.css?v=<%=System.currentTimeMillis()%>">
        <title>main page</title>

    </head>
    <body>
    <jsp:include page="Navbar.jsp" flush="true"/>

    <%
        String username = (String) session.getAttribute("username");

        if (username != null) {
    %>

    <jsp:include page="main.jsp" flush="true"/>

    <%
    } else {
    %>

    <jsp:include page="login.jsp" flush="true"/>

    <%
        }
    %>



    </body>
</html>
