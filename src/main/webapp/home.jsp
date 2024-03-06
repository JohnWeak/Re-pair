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
                  <input name="Username" placeholder="Username" type="text" >
                  <input name="Password" placeholder="Password" type="password">
                  <div class="btn" id="loginButton">
                    Log-In
                  </div>
                </div>
              </div>
            </div>
            <div class="form-item search">
              <div class="table">
                <div class="table-cell">
                  <input name="search" placeholder="Search..." type="text" >
                  <div class="btn" id="searchButton">
                    Cerca
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
</body>
</html>

