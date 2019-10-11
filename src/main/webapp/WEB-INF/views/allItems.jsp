<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>all Items</title>
    <style>
        table {
            font-family: "Arial", sans-serif;
            border-collapse: collapse;
            width: 80%;
        }
        th {
            border: 2px solid black;
            text-align: center;
            padding: 7px;
        }
        td {
            border: 2px solid black;
            text-align: left;
            padding: 7px;
        }
        th {
            background-color: #d8fcae;
        }
    </style>
</head>
<body>
<p>Items:</p>
<hr>
<a href="${pageContext.request.contextPath}/servlet/bucket?user_id=${user_id}&item_id=${item.id}&bucket_id=${bucket_id}">GO TO BUCKET</a>
<table border="2" bgcolor="#b3e6ff">
    <tr>
        <th>ID</th>
        <th>Item</th>
        <th>Price</th>
        <th>BUY</th>
    </tr>
    <c:forEach var="item" items="${items}">
        <tr>
            <td>
                <c:out value="${item.id}"/>
            </td>
            <td>
                <c:out value="${item.name}"/>
            </td>
            <td>
                <c:out value="${item.price}"/>
            </td>
            <td>
                <a href="${pageContext.request.contextPath}/servlet/addToBucket?item_id=${item.id}">ADD ITEM TO BUCKET</a>
            </td>
        </tr>
    </c:forEach>
</table>
<a href="${pageContext.request.contextPath}/logout">LOG OUT</a>
</body>
</html>
