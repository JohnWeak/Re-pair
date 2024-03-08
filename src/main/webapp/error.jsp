<%--
  Created by IntelliJ IDEA.
  User: Giovanni Liguori
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Errore!</title>
</head>
<body>
<p>Per favore, ritorna alla pagina precedente e riprova ad eseguire l'operazione.</p>
<p><strong>ERRORE: <%=request.getAttribute("errore")%></strong></p>
</body>
</html>
