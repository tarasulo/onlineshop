<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Orders</title>
</head>
<body>
<p>Orders: </p>
<table border="1" bgcolor="#b3e6ff">
    <tr>
        <th>ID</th>
        <th>UserId</th>
        <th>Items</th>
        <th>Delete</th>
    </tr>
    <c:forEach var="orders" items="${orders}">
        <tr>
            <td>
                <c:out value="${orders.id}" />
            </td>
            <td>
                <c:out value="${orders.userId}" />
            </td>
            <td>
                <c:out value="${orders.items}" />
            </td>
            <td>
                <a href="/onlineshop_war_exploded/deleteOrder?orders_id=${orders.id}">DELETE</a>
            </td>
        </tr>
    </c:forEach>
</table>
<a href="/onlineshop_war_exploded/bucket">BUCKET</a>
<hr>
<a href="/onlineshop_war_exploded/getAllItems">GO TO THE GOODS</a>
</body>
</html>