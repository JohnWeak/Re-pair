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

<head>
  <title>Login/Search</title>
</head>
<body bgcolor="708090">

<div class="logo">
  <a><img src='https://i.postimg.cc/qhMSGsGS/1709739159071stxt4cdl-removebg-preview.png' border='0' alt='1709739159071stxt4cdl-removebg-preview'/></a>
</div>

<div class="container" id="container">
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
            <form action="cerca-riparazione" method="post">
            <input type="search" placeholder="Ricerca la tua riparazione!!!" name="search" id="search-input">
              <div class="btn">
                <button class="bottonericerca">Ricerca Qui</button>
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>
    <div class="container-form">
      <div class="form-item log-in">
        <div class="table">
          <div class="table-cell">
            <form action="login" method="get">
            <input name="mail" placeholder="Mail" type="email" required />
            <input name="password" placeholder="Password" type="password" required/>
            <div class="btn">
              <button class="bottonericerca">Login</button>
            </div>
            </form>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>
</body>
</html>

