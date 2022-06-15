
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Update Page</title>
</head>
<body>
<form method="post" action="">

    <input type="text" required placeholder="name" name="name"/>
    <input type="text" required placeholder="link" name="link"/>
    <input type="text" required placeholder="company" name="company"/>
    <input type="text" required placeholder="training_area" name="training_area"/>
    <input type="text" required placeholder="type" name="type"/>
    <input type="text" required placeholder="reason" name="reason"/>
    <input type="text" required placeholder="format" name="format"/>
    <input type="date" required placeholder="start_date" name="start_date"/>
    <input type="text" required placeholder="duration" name="duration"/>
    <input type="text" required placeholder="price" name="price"/>
    <input type="text" required placeholder="link_review" name="link_review"/>
    <input type="text" required placeholder="comment" name="comment"/>

    <input class="button" type="submit" value="Создать">

</form>
</body>
</html>
