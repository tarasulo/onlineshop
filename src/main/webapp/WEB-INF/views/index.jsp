<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Online Store</title>
</head>
<body>
    <h1>Welcome to our store!</h1>
    <a href="${pageContext.request.contextPath}/registration">Registration</a>
    <a href="${pageContext.request.contextPath}/login">Login</a>
    <a href="${pageContext.request.contextPath}/servlet/getAllUsers">Users</a>
    <a href="${pageContext.request.contextPath}/servlet/getAllItems">Items</a>
    <a href="${pageContext.request.contextPath}/logout">Logout</a>
    <h2><img src="https://github.com/tarasulo/onlineshop/images/onlinestore.jpg" alt="Online-store"></h2>
</body>
</html>
