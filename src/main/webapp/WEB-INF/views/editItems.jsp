<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Items</title>
    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/bulma/0.7.5/css/bulma.min.css">
</head>
<body>
<nav class="navbar is-light" role="navigation" aria-label="main navigation">
    <div id="navbarBasicExample" class="navbar-menu">
        <div class="navbar-start">
            <a class="navbar-item" href="${pageContext.request.contextPath}/index">
                Home
            </a>
            <div class="navbar-item is-hoverable">
                <a class="navbar-item" href="${pageContext.request.contextPath}/servlet/editItems">
                    Edit items
                </a>
                <a class="navbar-item" href="${pageContext.request.contextPath}/servlet/getAllUsers">
                    SHOW All Users
                </a>
            </div>
        </div>
        <div class="navbar-end">
            <c:choose>
                <c:when test="${login == 'true'}">
                    <div class="navbar-item">
                        <a class="button is-primary is-outlined" href="${pageContext.request.contextPath}/logout">
                            Logout
                        </a>
                    </div>
                </c:when>
                <c:otherwise>
                    <div class="navbar-item">
                        <div class="buttons">
                            <a class="button is-primary" href="${pageContext.request.contextPath}/registration">
                                <strong>Sign up</strong>
                            </a>
                            <a class="button is-primary is-outlined" href="${pageContext.request.contextPath}/login">
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
    <div class="columns is-centered">
        <div class="column is-half">
            <h1 class="title">Items</h1>
            <table class="table is-striped is-fullwidth">
                <thead>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Price</th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="item" items="${items}">
                    <tr>
                        <td><c:out value="${item.id}"/></td>
                        <td><c:out value="${item.name}"/></td>
                        <td><c:out value="${item.price}"/></td>
                        <td><a class="button is-warning"
                               href="${pageContext.request.contextPath}/servlet/deleteItem?item_id=${item.id}">
                            Delete</a></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <form action="${pageContext.request.contextPath}/servlet/createItem" method="post">
                <label class="label">Create item</label>
                <div class="field is-horizontal">
                    <div class="field-body">
                        <div class="field">
                            <p class="control is-expanded">
                                <input class="input" type="text" placeholder="Name" name="item_name" required>
                            </p>
                        </div>
                        <div class="field">
                            <p class="control is-expanded">
                                <input class="input" type="text" placeholder="Price" name="item_price" required>
                            </p>
                        </div>
                    </div>
                    <button class="button is-success" type="submit">
                        Create
                    </button>
                </div>
            </form>
        </div>
    </div>
</section>
</body>
</html>