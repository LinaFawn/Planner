<%--
  Created by IntelliJ IDEA.
  User: tcipk
  Date: 27.05.2022
  Time: 2:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin Requests</title>
</head>
<body>

<h2>Создание новой заявки</h2><br />

<form method="post" action="<c:url value='/add_user'/>">

    <label><input type="text" name="name"></label>Имя<br>

    <label><input type="number" name="age"></label>Возраст<br>

    <input type="submit" value="Ok" name="Ok"><br>
</form>

</body>
</html>
