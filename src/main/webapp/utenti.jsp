<%@ page import="java.util.ArrayList" %>
<%@ page import="pojo.Utente" %>
<% final ArrayList<Utente> lista = (ArrayList<Utente>) request.getServletContext().getAttribute("listaUtenti");%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<style><%@include file="WEB-INF/HTML-CSS/CSS/utenti.css"%></style>

<head>
    <title>Lista Utenti</title>
</head>


<body bgcolor="708090">
<div class="listautenti">
    <a href="lavori.jsp"><img src='https://i.postimg.cc/9RdjQBTs/list.png' border='0' alt='list' width="60px" height="60px"/></a>
</div>
<table>
    <tr>
        <h2>Lista Utenti</h2>
    </tr>
    <tr>
        <th>ID</th>
        <th>Nome</th>
        <th>Cognome</th>
        <th>Mail</th>
        <th>Admin?</th>
        <th>Modifica</th>
        <th>Cancella</th>
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
                <input type="submit" name="" value="Cancella Utente"/>
            </form>
        </td>
    </tr>
    <%}%>
    <%}%>
</table>

<table>
    <tr>
        <h2>Registra Un Nuovo Utente</h2>
    </tr>
    <tr>
        <th>Nome</th>
        <th>Cognome</th>
        <th>Mail</th>
        <th>Password</th>
        <th>Admin?</th>
        <th>Aggiungi</th>
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
