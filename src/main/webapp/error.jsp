<%--
  Created by IntelliJ IDEA.
  User: Giovanni Liguori
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<style><%@include file="WEB-INF/HTML-CSS/CSS/errorpage.css"%></style>
  <head>
    <title>Errore!</title>
  </head>
  <body bgcolor="708090">
    <p>Per favore, ritorna alla pagina precedente e riprova ad eseguire l'operazione.</p> <br>
    <h1><strong>ERRORE:<br> <%=request.getAttribute("errore")%></strong></h1> <br><br>
    <%if (session.getAttribute("utente")!=null) {
      %>
    <a href="lavori.jsp">Ritorna alla pagina delle riparazioni</a>
    <%}else{%>
    <a href="home.jsp">Ritorna alla Homepage</a>
  <%}%>
  </body>
</html>
