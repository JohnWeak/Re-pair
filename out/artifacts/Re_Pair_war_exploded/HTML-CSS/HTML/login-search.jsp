<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 29/02/2024
  Time: 10:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

    <link rel="stylesheet" href="./WEB-INF/HTML-CSS/CSS/login-search.css" type="text/css">
    <title>Login/Search</title>
</head>
<body>

<div class="container">
    <div class="box"></div>
    <div class="cotainer-forms">
        <div class="container-info">
            <div class="info-item">
                <div class="table">
                    <div class="table-cell">
                        <p>Sei un'utente?</p>
                        <div class="btn">
                            Log-In
                        </div>
                    </div>
                </div>
            </div>
            <div class="info-item">
                <div class="table">
                    <div class="table-cell">
                        <p>Sono un cliente!</p>
                        <div class="btn">
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
                        <input name="Password" placeholder="Password" type="password" >
                        <div class="btn">
                            Log-In
                        </div>
                    </div>
                </div>
            </div>
            <div class="form-item search">
                <div class="table">
                    <div class="table-cell">
                        <input name="search" placeholder="Search..." type="text" >
                        <button type="submit"></button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>
