<%@ page import="pojo.Utente" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="utente" scope="request" type="pojo.Utente" />
<html>
<style><%@include file="WEB-INF/HTML-CSS/CSS/dettagliolavoro.css"%></style>
<style><%@include file="WEB-INF/HTML-CSS/CSS/dettaglioutenti.css"%></style>
  <head>
    <title> ${utente.admin == true ? "Admin" : "Utente"} ${utente.nome} ${utente.cognome}</title>
  </head>
  <body bgcolor="708090">

  <div class="listalavori">
      <a href="utenti.jsp"><img src='https://i.postimg.cc/9RdjQBTs/list.png' border='0' alt='list' width="60px" height="60px"/></a>
  </div>
    <table>
        <tr>
            <h2>Gestione Utente</h2>
        </tr>
        <tr>
            <th>ID</th>
            <th>Nome</th>
            <th>Cognome</th>
            <th>Mail</th>
            <th>Admin?</th>
            <th>Conferma</th>
        </tr>
        <tr>
            <td>${utente.id}</td>
            <td>${utente.nome}</td>
            <td>${utente.cognome}</td>
            <td>${utente.mail}</td>
            <td>${utente.admin}</td>
            <td></td>
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
