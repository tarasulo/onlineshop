<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Access denied</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bulma/0.7.5/css/bulma.min.css">
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
</nav>
<section class="section">
    <div class="columns is-center">
        <div class="column"></div>
        <div class="column">
            <div class="notification is-danger">
                <h1 class="title">Sorry, requested page is denied for you :(</h1>
            </div>
        </div>
        <div class="column"></div>
    </div>
</section>
</body>
</html>