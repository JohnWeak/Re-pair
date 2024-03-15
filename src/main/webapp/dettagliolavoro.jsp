<%@ page import="pojo.Utente" %>
<%@ page import="pojo.Cliente" %>
<%@ page import="dao.ClienteDAO" %>
<jsp:useBean id="riparazione" scope="request" type="pojo.Riparazione" />
<%
    final Cliente c = ClienteDAO.doCercaClienteByMail(riparazione.getMailCliente());
    final Utente utenteAssegnato = (Utente) request.getAttribute("assegnato");
    if (utenteAssegnato != null){%>
    <jsp:useBean id="assegnato" scope="request" type="pojo.Utente" />
<%}%>
<%--
  Created by IntelliJ IDEA.
  User: Cristina
  Date: 13/03/2024
  Time: 18:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<style><%@include file="WEB-INF/HTML-CSS/CSS/dettagliolavoro.css"%></style>
<head>
    <title>Dettaglio Lavoro</title>
</head>
<body bgcolor="#708090">

<div class="logo">
    <a href="/"><img src='https://i.postimg.cc/qhMSGsGS/1709739159071stxt4cdl-removebg-preview.png' border='0' alt='1709739159071stxt4cdl-removebg-preview'/></a>
</div>
<a href="lavori.jsp">&lt; Lista Riparazioni</a>
<div class="titlelavoro">
    <h2>Nome Lavoro</h2>
</div>

<div class="table-dettagli">
    <div class="dettagli">
        <form action="gestione-riparazione" method="post">
        <ul>
          <il>・ID: ${riparazione.id}</il><br>
          <il>・Marca: <input type="text" name="marca" value="${riparazione.marca}"/></il><br>
          <il>・Modello: <input type="text" name="modello" value="${riparazione.modello}"/></il><br>
          <il>・Cliente: <%if (c != null){%><%=c.getNome()%><%}%></il><br>
          <il>・Status:
            <select name="status">
              <option value="0" <%if (riparazione.getStatus() == 0) {%>selected<%}%>>Riparazione in corso</option>
              <option value="1" <%if (riparazione.getStatus() == 1) {%>selected<%}%>>Riparazione conclusa</option>
              <option value="2" <%if (riparazione.getStatus() == 2)  {%>selected<%}%>>Prodotto ritirato dal cliente</option>
            </select>
          </il><br>
          <il>・Costo: <input type="number" name="costo" value="${riparazione.costo}" />€</il><br>
          <il>・Utente a cui è assegnato: <%=utenteAssegnato == null ? "Nessuno" : utenteAssegnato.getNome() +" "+ utenteAssegnato.getCognome()%></il>
        </ul>
           <textarea id="nota" name="nota" rows="4" cols="50" placeholder="Scrivi qui la tua nota...">${riparazione.nota}</textarea>
            <input type="hidden" name="id" value="${riparazione.id}" />
            <input type="hidden" name="tipo" value="modifica" />
            <input type="hidden" name="assegnato" value="${assegnato == null ? -1 : assegnato.id}" />
            <input type="hidden" name="mailCliente" value="<%= c == null ? "" : c.getMail() %>" />
            <input type="submit" name="" value="Conferma Modifiche" />
        </form>
    </div>
</div>

</body>
</html>
