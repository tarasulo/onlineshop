<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="items" scope="request" type="java.util.List<internet.shop.model.Item>"/>
<html>
<head>
    <title>My Bucket ${user.login}</title>
</head>
<body>
<table  border="1" bgcolor="#f0e68c">
    Here's your bucket with all items:
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Price</th>
        <th>Delete From Bucket</th>
    </tr>
    <c:forEach var="item" items="${items}">
        <tr>
            <td>
                <c:out value="${item.id}" />
            </td>
            <td>
                <c:out value="${item.name}" />
            </td>
            <td>
                <c:out value="${item.price}" />
            </td>
            <td>
                <a href="${pageContext.request.contextPath}/servlet/DeleteBucketItem?bucket_id=${bucket.id}&item_id=${item.id}">delete</a>
            </td>
        </tr>
    </c:forEach>
</table>
<a href="${pageContext.request.contextPath}/servlet/completeOrder">COMPLETE ORDER</a>
<a href="${pageContext.request.contextPath}/logout">LOG OUT</a>
</body>
</html>
