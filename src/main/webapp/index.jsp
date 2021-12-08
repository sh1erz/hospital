<%@ page import="controller.command.CommandList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
</head>
<body>
<form method="post" action="controller">
    <input type="hidden" name="command" value="<%=CommandList.AUTHORISATION%>">
    <p>Account type:</p>
    <label>
        <input type="radio" name="accountType" value="admin">
    </label>Admin<br>
    <label>
        <input type="radio" name="accountType" value="doctor">
    </label>Doctor<br>

    <label>Login:
        <input type="text" name="login">
    </label>
    <label>Password:
        <input type="password" name="password">
    </label>
    <input type="submit" name="button" >
</form>
</body>
</html>
