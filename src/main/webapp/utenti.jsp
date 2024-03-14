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
                      <input type="submit" name="" value="Modifica Utente">
                    </form>
                  </td>
              </tr>
          <%}%>
      <%}%>
  </table>
  </body>
</html>
