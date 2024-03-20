<%@ page import="java.util.ArrayList" %>
<%@ page import="pojo.Riparazione" %>
<%@ page import="dao.UtenteDAO" %>
<%@ page import="pojo.Utente" %>
<%@ page import="pojo.Riparazione" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%final String[] status = {"Riparazione in corso", "Riparazione conclusa", "Ritirato dal cliente"};%>
<html>

<style><%@include file="WEB-INF/HTML-CSS/CSS/lavori.css"%></style>

<head>
    <body bgcolor="708090">

<div class="logo">
    <a><img src='https://i.postimg.cc/qhMSGsGS/1709739159071stxt4cdl-removebg-preview.png' border='0' alt='1709739159071stxt4cdl-removebg-preview'/></a>
</div>

      <%
          final ArrayList<Riparazione> lista = (ArrayList<Riparazione>) request.getAttribute("riparazioni");
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
                      ASSEGNATO: <% if (assegnato != null){%> <%=assegnato.getNome()%> <%=assegnato.getCognome()%> <%}else{%>NON ASSEGNATO
                      <%}%>
                  </p>

              </div>
          </div>
      </div>
      <%}%>

      <%}%>

    </body>
  </head>
</html>
