<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Login</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bulma/0.7.5/css/bulma.min.css">
    <script defer src="https://use.fontawesome.com/releases/v5.3.1/js/all.js"></script>
</head>
<body>
<nav class="navbar is-light" role="navigation" aria-label="main navigation">
    <div id="navbarBasicExample" class="navbar-menu">
        <div class="navbar-start">
            <a class="navbar-item" href="${pageContext.request.contextPath}/index">
                Home
            </a>
        </div>
    </div>
    <div class="navbar-end">
        <div class="navbar-item">
            <div class="buttons">
                <a class="button is-success" href="${pageContext.request.contextPath}/registration">
                    <strong>Sign up</strong>
                </a>
                <a class="button is-success is-outlined" href="${pageContext.request.contextPath}/login">
                    Log in
                </a>
            </div>
        </div>
    </div>
</nav>
<section class="section is-small">
    <div class="columns">
        <div class="column"></div>
        <div class="column">
            <c:if test="${error == 'true'}">
                <div class="notification is-warning">
                        ${errorMsg}
                </div>
            </c:if>
            <form action="${pageContext.request.contextPath}/login" method="post">
                <div class="container">
                    <h1 class="title">Login</h1>
                    <div class="field">
                        <p class="control has-icons-left">
                            <input class="input" type="text" placeholder="Login" name="login" required>
                            <span class="icon is-small is-left">
                                        <i class="fas fa-user"></i>
                                    </span>
                        </p>
                    </div>
                    <div class="field">
                        <p class="control has-icons-left">
                            <input class="input" type="password" placeholder="Password" name="psw" required>
                            <span class="icon is-small is-left">
                                        <i class="fas fa-lock"></i>
                                    </span>
                        </p>
                    </div>

                    <div class="field">
                        <p class="control">
                            <button class="button is-success" type="submit">
                                Login
                            </button>
                        </p>

                    </div>
                    <div class="field has-icons-right">
                        <p class="buttons">
                            <a class="button is-success" href="${pageContext.request.contextPath}/registration">
                                <strong>Registration</strong>
                            </a>
                        </p>
                    </div>
                </div>
            </form>
        </div>
        <div class="column"></div>
    </div>
</section>
</body>
</html>
