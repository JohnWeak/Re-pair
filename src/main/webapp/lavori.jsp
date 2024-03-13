<jsp:useBean id="utente" scope="session" type="pojo.Utente"/>
<%@ page import="java.util.ArrayList" %>
<%@ page import="pojo.Riparazione" %>
<%@ page import="dao.UtenteDAO" %>
<%@ page import="pojo.Utente" %>
<%--
  Created by IntelliJ IDEA.
  User: Giovanni Liguori
  Date: 07/03/24
  Time: 11:07
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Re-pair</title>
  </head>
  <body>
    <p>Ciao, ${utente.nome} ${utente.cognome}. <%if (utente.isAdmin()) {%> -- Sei un admin, me cojoni! <%}%></p>
    <table>
      <tr>
        <th>ID</th>
        <th>Marca</th>
        <th>Modello</th>
        <th>Costo</th>
        <th>Utente a cui è assegnato il lavoro</th>
      </tr>
    <%
        final ArrayList<Riparazione> lista = (ArrayList<Riparazione>) request.getServletContext().getAttribute("listaRiparazioni");
		if (lista != null)
		{
		    for (Riparazione r : lista)
			{
                final Utente assegnato = r.getAssegnato() <= 0 ? null : UtenteDAO.doRetrieveByID(r.getAssegnato());%>
      <tr>
        <td> <%=r.getId()%> </td>
        <td> <%=r.getMarca()%> </td>
        <td> <%=r.getModello()%> </td>
        <td> <%=r.getCosto()%>€ </td>
        <td> <% if (assegnato != null){%> <%=assegnato.getNome()%> <%=assegnato.getCognome()%> <%}%></td>
      </tr>
            <%}
        }
    %>
  </table>

  </body>
</html>
