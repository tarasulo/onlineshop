<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>all Items</title>
</head>
<body>
<p>Items:</p>
<hr>
<a href="/onlineshop_war_exploded/servlet/bucket?user_id=${user_id}&item_id=${item.id}&bucket_id=${bucket_id}">GO TO BUCKET</a>
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
                <a href="/onlineshop_war_exploded/servlet/addToBucket?item_id=${item.id}">ADD ITEM TO BUCKET</a>
            </td>
        </tr>
    </c:forEach>
</table>
<a href="/onlineshop_war_exploded/logout">LOG OUT</a>
</body>
</html>
