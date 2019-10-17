<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Users</title>
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
                <a class="navbar-item" href="${pageContext.request.contextPath}/servlet/getAllItems">
                    Items
                </a>
                <a class="navbar-item" href="${pageContext.request.contextPath}/servlet/getAllOrders">
                    Orders
                </a>
                <a class="navbar-item"
                   href="${pageContext.request.contextPath}/servlet/bucket?user_id=${user_id}&item_id=${item.id}&bucket_id=${bucket_id}">
                    Go to Bucket
                </a>
            </div>
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
    <div class="columns is-centered">
        <div class="column is-half">
            <h1 class="title">Orders</h1>
            <table class="table is-striped is-fullwidth">
                <thead>
                <tr>
                    <th>ID</th>
                    <th>User ID</th>
                    <th>Items</th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="orders" items="${orders}">
                    <tr>
                        <td><c:out value="${orders.id}"/></td>
                        <td><c:out value="${orders.userId}"/></td>
                        <td><c:out value="${orders.items}"/></td>
                        <td>
                            <a class="button is-warning"
                               href="${pageContext.request.contextPath}/servlet/deleteOrder?orders_id=${orders.id}">
                               Delete Order</a></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</section>
</body>
</html>
