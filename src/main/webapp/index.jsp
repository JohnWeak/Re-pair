<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
  <head>
        <title>Accedi a Re-pair</title>
        <link rel="stylesheet" href="index.css">
  </head>

  <body>
    <%
        String utente = (String) session.getAttribute("utenteLoggato");
    %>
    <h2><%=utente%></h2>

    <form action="login" method="post">
        <div id="root_div_form">
            <div id="mail_div">
                <div id="div_label_mail">
                    <label for="mail">Enter your mail</label>
                </div>
                <div id="div_input_mail">
                    <input id="mail" type="email" name="mail" placeholder="mariorossi@libero.it"/>
                </div>
            </div>
            <div id="password_div">
                <div id="div_label_password">
                    <label for="password">Enter your password</label>
                </div>
                <div id="div_input_password">
                    <input id="password" type="password" name="password" placeholder="password123" />
                </div>
            </div>
            <input type="submit" id="submit_button">
        </div>
    </form>
  </body>
</html>
