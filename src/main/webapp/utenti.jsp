<%@ page import="java.util.ArrayList" %>
<%@ page import="pojo.Utente" %><%--
  Created by IntelliJ IDEA.
  User: Giovanni Liguori
  Date: 14/03/24
  Time: 18:01
  To change this template use File | Settings | File Templates.
--%>
<% final ArrayList<Utente> lista = (ArrayList<Utente>) request.getServletContext().getAttribute("listaUtenti");%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Lista Utenti</title>
  </head>
  <body>
  <a href="lavori.jsp">&lt; Torna alla pagina dei lavori</a>
  <table>
    <tr>
      <th>ID</th>
      <th>Nome</th>
      <th>Cognome</th>
      <th>Mail</th>
      <th>Admin?</th>
    </tr>
      <%if (lista != null)
	  {
		  for (Utente u : lista)
		  {%>
              <tr>
                  <td><%=u.getId()%></td>
                  <td><%=u.getNome()%></td>
                  <td><%=u.getCognome()%></td>
                  <td><%=u.getMail()%></td>
                  <td><%=u.isAdmin()%></td>
                  <td>
                    <form action="dettaglio-utente">
                      <input type="hidden" name="idUtente" value="<%=u.getId()%>">
                      <input type="hidden" name="tipo" value="modifica">
                      <input type="submit" name="" value="Modifica Utente">
                    </form>
                  </td>
                  <td>
                    <form action="gestione-utente">
                      <input type="hidden" name="idUtente" value="<%=u.getId()%>">
                      <input type="hidden" name="tipo" value="cancella">
                      <input type="submit" name="" value="CANCELLA Utente"/>
                    </form>
                  </td>
              </tr>
          <%}%>
      <%}%>
      <tr>
        <td>--- Registra un nuovo utente ---</td>
      </tr>
      <tr>
        <td>Nome</td>
        <td>Cognome</td>
        <td>Mail</td>
        <td>Password</td>
        <td>Admin?</td>
      </tr>
      <tr>
        <form action="gestione-utente" method="post">
          <input type="hidden" name="tipo" value="crea" />
          <input type="hidden" name="idUtente" value="${sessionScope.get("utente").getId()}" />
          <td><input type="text" name="nome" placeholder="Nome nuovo Utente"></td>
          <td><input type="text" name="cognome" placeholder="Cognome nuovo Utente"></td>
          <td><input type="email" name="mail" placeholder="Mail nuovo utente"></td>
          <td><input type="password" name="password" placeholder="Password nuovo utente"></td>
          <td><input type="checkbox" name="admin"></td>
          <td><input type="submit" value="Aggiungi Nuovo Utente"></td>
        </form>
      </tr>
    </table>
  </body>
</html>
