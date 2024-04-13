<html>
<head>
    <link rel="stylesheet" href="css/main.css?v=<%=System.currentTimeMillis()%>">
</head>
<body>

<jsp:include page="views/topNavBar.jsp" flush="true"/>

<%
    String username = (String) session.getAttribute("username");

    if (username != null) {
%>

<jsp:include page="views/main.jsp" flush="true"/>

<%
    } else {
%>

<jsp:include page="views/login.jsp" flush="true"/>

<%
    }
%>

</body>
</html>
