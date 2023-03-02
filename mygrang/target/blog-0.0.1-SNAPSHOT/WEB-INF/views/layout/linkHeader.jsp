<%@ page import="com.grang.config.auth.PrincipalDetail" %>
<%@ page import="org.springframework.beans.factory.annotation.Value" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<sec:authorize access="isAuthenticated()">
    <sec:authentication property="principal" var="principal"/>
</sec:authorize>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="content-type" content="text/html;charset=utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=Edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <script src="https://code.jquery.com/jquery-3.6.2.min.js"></script>
    <link rel="icon" href="/image/favicon.png" type="image/x-icon"/>
    <link rel="stylesheet" href="/css/header.css"/>
    <c:if test="${empty story}">
    <link rel="stylesheet" href="/css/footer.css"/>
    </c:if>
    <link rel="stylesheet" href="/css/various.css"/>
    <script defer src="https://kit.fontawesome.com/75427af088.js"
            crossorigin="anonymous"></script>
    <script defer src="/js/header.js"></script>
    <title>Grang</title>