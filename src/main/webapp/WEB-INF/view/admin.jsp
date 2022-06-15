<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>USER</title>

</head>
<body>


<h1>Hello Admin!</h1>
<a href="<c:url value="/logout"/>">Logout</a>
<a href="<c:url value="/download"/>">Download</a>
<a href="<c:url value="/update"/>">Update</a>
<a href="<c:url value="/processing"/>">Look</a>
<a href="<c:url value="/create"/>">Create</a>


<br><br>
</body>
</html>