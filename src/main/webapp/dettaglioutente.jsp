<%@ page import="pojo.Utente" %><%--
  Created by IntelliJ IDEA.
  User: Giovanni Liguori
  Date: 14/03/24
  Time: 18:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%final Utente u = (Utente) request.getAttribute("utente");%>
<html>
  <head>
    <title><%=u.getNome()%> <%=u.getCognome()%></title>
  </head>
  <body>
  <a href="utenti.jsp">&lt; Torna alla lista degli utenti</a>
    <table>
        <tr>
            <th>ID</th>
            <th>Nome</th>
            <th>Cognome</th>
            <th>Mail</th>
            <th>Admin?</th>
        </tr>
        <tr>
            <td><%=u.getId()%></td>
            <td><%=u.getNome()%></td>
            <td><%=u.getCognome()%></td>
            <td><%=u.getMail()%></td>
            <td><%=u.isAdmin()%></td>
        </tr>
    </table>
  </body>
</html>
