<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 01.11.2016
  Time: 19:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Update Page</title>
</head>
<body>
<form action="/update" method="post">

    <input type="text" required placeholder="id" name="id"/>
    <input type="text" required placeholder="name" name="name"/>
    <input type="text" required placeholder="link" name="link"/>


</form>
<a href="<c:url value="/logout"/>">Logout</a>
<a href="<c:url value="/update"/>">Update</a>
</body>
</html>
