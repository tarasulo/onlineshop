<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="users" scope="request" type="java.util.List<internet.shop.model.User>"/>
<jsp:useBean id="greeting" scope="request" type="java.lang.String"/>
<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 18.09.2019
  Time: 13:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>All users</title>
</head>
<body>
Hello, ${greeting}! Welcome to the all users page!
<p>Users</p>
<table border="2" bgcolor="#b3e6ff">
    <tr>
        <th>ID</th>
        <th>Login/th>
        <th>Name</th>
        <th>Surname</th>
        <th>Delete</th>
    </tr>
    <c:forEach var="user" items="${users}">
        <tr>
            <td>
                <c:out value="${user.id}" />
            </td>
            <td>
                <c:out value="${user.login}" />
            </td>
            <td>
                <c:out value="${user.name}" />
            </td>
            <td>
                <c:out value="${user.surname}" />
            </td>
            <td>
                <a href="/onlineshop_war_exploded/deleteUser?user_id=${user.id}">DELETE</a>
            </td>
        </tr>
    </c:forEach>
</table>
<a href="/onlineshop_war_exploded/registration">REGISTRATE NEW USER</a>
</body>
</html>
