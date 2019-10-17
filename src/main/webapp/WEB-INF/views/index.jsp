<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Index</title>
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
            <c:if test="${user == 'true'}">
                <a class="navbar-item" href="${pageContext.request.contextPath}/servlet/getAllItems">
                    Items
                </a>
                <a class="navbar-item" href="${pageContext.request.contextPath}/servlet/getAllOrders">
                    Orders
                </a>
                <a class="navbar-item"
                   href="${pageContext.request.contextPath}/servlet/bucket?user_id=${user_id}&item_id=${item.id}&bucket_id=${bucket_id}">
                    Bucket
                </a>
            </c:if>
            <c:if test="${admin == 'true'}">
                <a class="navbar-item" href="${pageContext.request.contextPath}/servlet/getAllUsers">
                    SHOW All Users
                </a>
                <a class="navbar-item" href="${pageContext.request.contextPath}/servlet/editItems">
                    Edit items
                </a>
            </c:if>
        </div>
        <div class="navbar-end">
            <c:choose>
                <c:when test="${login == 'true'}">
                    <div class="navbar-item">
                        <a class="button is-success is-outlined" href="${pageContext.request.contextPath}/logout">
                            Logout
                        </a>
                    </div>
                </c:when>
                <c:otherwise>
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
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</nav>
<section class="section">
    <div class="container">
        <h1 class="title">
            <c:choose>
                <c:when test="${login == 'true'}">
                    Hello, ${name}
                </c:when>
                <c:otherwise>
                    Hello, Guest
                </c:otherwise>
            </c:choose>
        </h1>
        <p class="subtitle">
            Welcome to our store!
        </p>
    </div>
</section>
</body>
</html>