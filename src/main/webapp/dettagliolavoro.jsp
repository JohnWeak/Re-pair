<%--
  Created by IntelliJ IDEA.
  User: Cristina
  Date: 13/03/2024
  Time: 18:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<style><%@include file="WEB-INF/HTML-CSS/CSS/dettagliolavoro.css"%></style>
<head>
    <title>Dettaglio Lavoro</title>
</head>
<body bgcolor="#708090">

<div class="logo">
    <a href="/"><img src='https://i.postimg.cc/qhMSGsGS/1709739159071stxt4cdl-removebg-preview.png' border='0' alt='1709739159071stxt4cdl-removebg-preview'/></a>
</div>

<div class="titlelavoro">
    <h2>Nome Lavoro</h2>
</div>

<div class="table-dettagli">
    <div class="dettagli">
       <ul>
           <il>・ID:</il><br>
           <il>・Marca:</il><br>
           <il>・Modello:</il><br>
           <il>・Costo:</il><br>
           <il>・Utente a cui è assegnato:</il>
       </ul>
           <textarea id="nota" name="nota" rows="4" cols="50" placeholder="Scrivi qui la tua nota..."></textarea>
    </div>
</div>

</body>
</html>
