<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 17.10.2016
  Time: 11:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>List Requests</title>
</head>
<body>
<h2>List of Own Requests</h2>
<table border="2">
    <tr>
        <td>Id</td>
        <td>Login</td>
        <td>email</td>
    </tr>
    <c:forEach var="request" items="${requestsList}">
        <tr>
            <td>${request.getId}</td>
            <td>${request.getName}</td>
        </tr>
    </c:forEach>
</table>
<a href="/">To main</a>
</body>
</html>