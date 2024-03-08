<%@ page import="java.util.ArrayList" %>
<%@ page import="pojo.Riparazione" %>
<%@ page import="dao.UtenteDAO" %>
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
                final String assegnato = r.getAssegnato() <= 0 ? "Nessuno" : UtenteDAO.doRetrieveByID(r.getAssegnato()).getNome();%>
      <tr>
        <td> <%=r.getId()%> </td>
        <td> <%=r.getMarca()%> </td>
        <td> <%=r.getModello()%> </td>
        <td> <%=r.getCosto()%>€ </td>
        <td> <%=assegnato%> </td>
      </tr>
            <%}
        }
    %>
  </table>

  </body>
</html>
