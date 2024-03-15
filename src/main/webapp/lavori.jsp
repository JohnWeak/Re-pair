<jsp:useBean id="utente" scope="session" type="pojo.Utente"/>
<%@ page import="java.util.ArrayList" %>
<%@ page import="pojo.Riparazione" %>
<%@ page import="dao.UtenteDAO" %>
<%@ page import="pojo.Utente" %>
<%@ page import="pojo.Status" %>
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
    <p>Ciao, ${utente.nome} ${utente.cognome}. <%if (utente.isAdmin()) {%> -- Sei un admin, complimenti! <%}%></p><a href="logout">Logout</a>
    <table>
      <tr>
        <th>ID</th>
        <th>Marca</th>
        <th>Modello</th>
        <th>Costo</th>
        <th>Status</th>
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
        <form action="dettaglio-riparazione">
          <td> <%=r.getId()%> </td>
          <td> <%=r.getMarca()%> </td>
          <td> <%=r.getModello()%> </td>
          <td> <%=r.getCosto()%>€ </td>
          <td> <%=r.getStatus()%> </td>
          <td> <% if (assegnato != null){%> <%=assegnato.getNome()%> <%=assegnato.getCognome()%> <%}%></td>
          <td>
            <input type="hidden" name="id" value="<%=r.getId()%>">
            <input type="submit" name="" value="Modifica Riparazione">
          </td>
        </form>
      </tr>
            <%
            }
        }
            %>
      <table/><br/><br/>
      <table>
      <tr><td>--- Aggiungi una riparazione ---</td></tr>
      <tr>
        <td>Marca</td>
        <td>Modello</td>
        <td>Costo</td>
        <td>Status</td>
        <td>Mail Cliente</td>
        <td>Nota</td>
      </tr>
      <form action="gestione-riparazione">
      <tr>
        <input type="hidden" name="id" value="-1" />
        <td> <input type="text" name="marca" placeholder="Marca"> </td>
        <td> <input type="text" name="modello" placeholder="Modello"> </td>
        <td> <input type="number" name="costo" value="100" placeholder="Costo" min="10" max="900">€ </td>
        <td>
          <select name="status">
            <option value="<%=Status.RIPARAZIONE_IN_CORSO.toString()%>">Riparazione in corso</option>
            <option value="<%=Status.RIPARAZIONE_CONCLUSA.toString()%>">Riparazione conclusa</option>
            <option value="<%=Status.PRODOTTO_RITIRATO_DAL_CLIENTE.toString()%>">Prodotto ritirato dal cliente</option>
          </select>
        </td>
        <td><input type="email" name="mailCliente" placeholder="Mail Cliente"></td>
        <td>
          <textarea id="nota" name="nota" rows="4" cols="30" placeholder="Scrivi qui la tua nota..."></textarea>
        </td>
        <td><input type="submit" value="Aggiungi Riparazione"></td>
      </tr>

      </form>
  </table>

  <%if (utente.isAdmin())
  {%>
    <a href="utenti.jsp">Lista degli utenti</a>
  <%}%>
  </body>
</html>
