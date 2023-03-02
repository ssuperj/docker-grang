<%--
  Created by IntelliJ IDEA.
  User: poqwer95
  Date: 2022/12/22
  Time: 12:26 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<sec:authorize access="isAuthenticated()">
    <sec:authentication property="principal" var="principal"/>
</sec:authorize>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="/css/welcome.css">
</head>
<body>
<div class="welcome-container">
    <h1 class="welcome-container__h1">Hello </h1>
    <h3 class="welcome-container__h3">${principal.user.username}</h3>
    <h3 class="welcome-container__h3">have a good day</h3>
</div>
</body>
</html>
