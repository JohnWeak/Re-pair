<%@ page import="pojo.Riparazione" %>
<%@ page import="java.util.ArrayList" %>
<%--
  Created by IntelliJ IDEA.
  User: Giovanni Liguori
  Date: 07/03/24
  Time: 11:42
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
      <%
          char lettera = 'i';
          final ArrayList<Riparazione> listaRiparazioni = (ArrayList<Riparazione>) request.getServletContext().getAttribute("listaRiparazioni");
		  final String ricerca = request.getParameter("search");
		  if (listaRiparazioni != null)
		  {
			  if (listaRiparazioni.size() == 1)
				  lettera = 'e';
            %><title><%=listaRiparazioni.size()%> Riparazion<%=lettera%></title>
          <%}
      %>
  </head>
  <body>
    <%
        if (listaRiparazioni != null)
        {
			for (Riparazione r : listaRiparazioni)
		    {
			    if (r.getId() == Integer.parseInt(ricerca))
                {
        %>
        <div>
            <%=r.getId()%> - <%=r.getMarca()%> <%=r.getModello()%>
        </div>
            <%}%>
        <%}
		}
    %>

<div class="logo">
    <a><img src='https://i.postimg.cc/qhMSGsGS/1709739159071stxt4cdl-removebg-preview.png' border='0' alt='1709739159071stxt4cdl-removebg-preview'/></a>
</div>

    <form action="cerca-riparazione">
<div class="search">
    <input type="search" placeholder="Ricerca la tua riparazione!!!" name="search" id="search-input">
</div>
    </form>

<div class="ricercaeffettuata">
    <p>Ricerca Effettuata</p>
</div>


  </body>
</html>
