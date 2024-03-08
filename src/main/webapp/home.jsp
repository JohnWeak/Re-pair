<%--
  Created by IntelliJ IDEA.
  User: Cristina
  Date: 05/03/2024
  Time: 14:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<style><%@include file="WEB-INF/HTML-CSS/CSS/login-search.css"%></style>
<script><%@include file="WEB-INF/HTML-CSS/JS/login-search.js"%></script>
<head>
  <title>Login/Search</title>
</head>
<body>
      <form action="cerca-riparazione">
        <input type="text" name="cerca-riparazione" placeholder="Ricerca riparazione...">
      </form>

      <div class="container" id="container">
        <div class="box"></div>
        <div class="container-forms">
          <div class="container-info">
            <div class="info-item">
              <div class="table">
                <div class="table-cell">
                  <p>Sei un'utente?</p>
                  <div class="btn" id="login">
                    Log-In
                  </div>
                </div>
              </div>
            </div>
            <div class="info-item">
              <div class="table">
                <div class="table-cell">
                  <p>Sono un cliente!</p>
                  <div class="btn" id="cerca">
                    Ricerca Qui!
                  </div>
                </div>
              </div>
            </div>
          </div>
          <div class="container-form">
            <div class="form-item log-in">
              <div class="table">
                <div class="table-cell">
                  <form action="login" method="post">
                    <input name="mail" placeholder="Mail" type="email" >
                    <input name="password" placeholder="Password" type="password">
                    <input type="submit" class="btn" id="loginButton" value="Login">
                  </form>
                </div>
              </div>
            </div>
            <div class="form-item search">
              <div class="table">
                <div class="table-cell">
                  <form action="cerca-riparazione">
                    <input name="cerca-riparazione" placeholder="Cerca riparazione" type="text" >
                    <input type="submit" class="btn" id="searchButton" value="Cerca">
                  </form>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
</body>
</html>

