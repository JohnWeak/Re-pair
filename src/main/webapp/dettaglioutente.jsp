<%@ page import="pojo.Utente" %><%--
  Created by IntelliJ IDEA.
  User: Giovanni Liguori
  Date: 14/03/24
  Time: 18:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="utente" scope="request" type="pojo.Utente" />
<html>
  <head>
    <title> ${utente.admin == true ? "Admin" : "Utente"} ${utente.nome} ${utente.cognome}</title>
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
            <td>${utente.id}</td>
            <td>${utente.nome}</td>
            <td>${utente.cognome}</td>
            <td>${utente.mail}</td>
            <td>${utente.admin}</td>
        </tr>
        <tr>
            <form action="gestione-utente" method="post">
              <td><input readonly type="number" placeholder=${utente.id} /></td>
              <td><input type="text" name="nuovoNome" placeholder=${utente.nome} /></td>
              <td><input type="text" name="nuovoCognome" placeholder=${utente.cognome} /></td>
              <td><input type="text" name="nuovaMail" placeholder=${utente.mail} /></td>
              <td><input type="checkbox" name="nuovoAdmin" <%if (utente.isAdmin()){%>checked<%}%> /></td>
              <input type="hidden" name="password" value="${utente.password}" />
              <input type="hidden" name="idUtente" value="${utente.id}" />
              <input type="hidden" name="tipo" value="modifica">
              <td><input type="submit" value="Conferma Modifiche" /></td>
            </form>
        </tr>
    </table>
  </body>
</html>
