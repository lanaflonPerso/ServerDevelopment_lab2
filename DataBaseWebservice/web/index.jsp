<%--
  Created by IntelliJ IDEA.
  User: o_0
  Date: 2016-11-14
  Time: 12:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>$Title$</title>
</head>
<body>
<ul>
    <a href="/services/userservice">UserService</a>
    <a href="/services/userservice/users">UserService/Users</a>
    <a href="/services/userservice/login?email=email2&password=pass2">UserService/Login</a>
    <a href="/services/userservice/userById?id=1">UserService/UserById</a>

    <p> Profile </p>

    <a href="/services/profileservice">ProfileService</a>
    <a href="/services/profileservice/getProfile">ProfileService/getProfile</a>
</ul>
</body>
</html>
