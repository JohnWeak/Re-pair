<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: giovanni
  Date: 13/03/24
  Time: 15:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>cwds</title>
  </head>
  <body>
    <p><%=request.getAttribute("cwd")%></p>
    <%for (String s : (ArrayList<String>) request.getAttribute("files"))
	{%>
        <p><%=s%></p>
    <%}%>
  </body>
</html>
