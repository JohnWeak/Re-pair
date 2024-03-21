<jsp:useBean id="utente" scope="session" type="pojo.Utente"/>
<%@ page import="java.util.ArrayList" %>
<%@ page import="pojo.Riparazione" %>
<%@ page import="dao.UtenteDAO" %>
<%@ page import="pojo.Utente" %>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%final String[] status = {"Riparazione in corso", "Riparazione conclusa", "Ritirato dal cliente", "Archiviato"};%>
<html>
<style><%@include file="WEB-INF/HTML-CSS/CSS/lavori.css"%></style>
<style><%@include file="WEB-INF/HTML-CSS/CSS/footer.css"%></style>

<head>
    <title>Re-pair</title>
</head>
<body bgcolor="708090">

<div class="logo">
    <a><img src='https://i.postimg.cc/qhMSGsGS/1709739159071stxt4cdl-removebg-preview.png' border='0' alt='1709739159071stxt4cdl-removebg-preview'/></a>
</div>

<div class="gestioneutenti">
  <%if (utente.isAdmin())
  {%>
  <a href="utenti.jsp"><img src='https://i.postimg.cc/Hc1Sn9sD/user.png' border='0' alt='user' width="60px" height="60px"/></a>
  <%}%>
</div>

<div class="logout">
  <a href="home.jsp"><img src='https://i.postimg.cc/FdJ45ZWM/opened-door-aperture.png' border='0' alt='opened-door-aperture' width="60px" height="60px"/></a>
</div>

<%
  final ArrayList<Riparazione> lista = (ArrayList<Riparazione>) request.getServletContext().getAttribute("listaRiparazioni");
  if (lista != null)
  {
    for (Riparazione r : lista)
    {
      final Utente assegnato = r.getAssegnato() <= 0 ? null : UtenteDAO.doRetrieveByID(r.getAssegnato());%>

<div class="container">
  <div class="card" style="--clr: #292929">
    <div class="img-box">
      <a><img src='https://i.postimg.cc/YG41NLDr/support.png'/></a>
    </div>
  <div class="content">
  <h2><%=r.getModello()%></h2>
      <p> <br>
        ID: <%=r.getId()%> <br>
        MARCA: <%=r.getMarca()%> <br>
        COSTO: <%=r.getCosto()%>€ <br>
        STATUS: <%=status[r.getStatus()]%> <br>
        ASSEGNATO: <% if (assegnato != null){%> <%=assegnato.getNome()%> <%=assegnato.getCognome()%> <%}else{%>DISPONIBILE
        <%}%>
      </p>

    <form action="dettaglio-riparazione">
      <input type="hidden" name="tipo" value="visualizza">
      <input type="hidden" name="id" value="<%=r.getId()%>">
      <button>LEGGI DI PIU'</button>
    </form>


    <%if (assegnato == null)
    {%>
    <form action="gestione-riparazione">
      <input type="hidden" name="tipo" value="assegna" />
      <input type="hidden" name="id" value="<%=r.getId()%>" />
      <input type="hidden" name="status" value="<%=r.getStatus()%>" />
      <input type="hidden" name="idUtente" value="${utente.id}" />
      <button>ASSEGNATI LA RIPARAZIONE</button>
    </form>
    <%}%>

    </div>
  </div>
</div>
<%}%>

<%}%>


<footer>
    <h3>AGGIUNGI UNA RIPARAZIONE</h3>

  <form action="gestione-riparazione">

      <input type="hidden" name="id" value="-1" />
      <input type="hidden" name="tipo" value="crea" />
      <input type="text" name="marca" placeholder="Marca">
      <input type="text" name="modello" placeholder="Modello">
      <input type="number" name="costo" value="100" placeholder="Costo" min="10" max="900">€

        <select name="status">
          <option value="0" selected>Riparazione in corso</option>
          <option value="1">Riparazione conclusa</option>
          <option value="2">Prodotto ritirato dal cliente</option>
        </select>

      <input type="email" name="mailCliente" placeholder="Mail Cliente">

        <textarea id="nota" name="nota" rows="4" cols="30" placeholder="Scrivi qui la tua nota..."></textarea>

      <input type="submit" value="Aggiungi Riparazione">
  </form>
</footer>


  </body>
</html>