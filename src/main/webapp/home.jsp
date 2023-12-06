<%@ page import="com.example.repair.Utente" %><%--
  Created by IntelliJ IDEA.
  User: giovanni
  Date: 17/10/23
  Time: 17:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="it">
  <head>
    <link rel="stylesheet" href="home.css">
    <meta charset="UTF-8">
    <title>Re-pair homepage</title>
    <% Utente utente = (Utente) session.getAttribute("utente"); %>

  </head>
  <body>
    <header>
      <div id="div0">
        <div class="div0_figlio">Ordini</div>
        <div class="div0_figlio">Archivio</div>
      </div>
      <div id="div1">
        <div id="logout_div"> Ciao <%=utente.getNome()%> </div>
      </div>
    </header>

    <form action="search">
      <input type="email" placeholder="MAIL" name="mail"/>
      <input type="submit" value="CERCA PER MAIL" />
    </form>
    <%
      Utente u = (Utente) session.getAttribute("utenteCercato");
      if (u == null) {
    %>
    <h2>Ancora nessun utente cercato """"""</h2>
    <%} else {%>

  <h2><%=u.getNome()%> <%=u.getCognome()%>, <%=u.getMail()%>, <%=u.getId()%></h2>
  <%}%>
  </body>
</html>
