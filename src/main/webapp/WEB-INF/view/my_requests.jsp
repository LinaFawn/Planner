<%--
  Created by IntelliJ IDEA.
  User: tcipk
  Date: 27.05.2022
  Time: 2:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Заявки пользователя</title>
</head>
<body>

<h2>Создание новой задачи</h2>
<form method="post" action="<c:url value='/add_task'/>">
    <label>Название <input type="text" name="title"></label><br>
    <label>Описание <input type="text" name="description"></label><br>
    <input type="submit" value="Ok" name="Ok"><br>
</form>
<h2>Получить JSON задач</h2>
<form method="get" action="<c:url value='/request'/>">
    <input type="submit" value="Получить данные задачи" name="Ok"><br>
</form>
<br><br>
<a href="<c:url value="/logout"/>"></a>

</body>
</html>
