<%@ page import="java.net.InetAddress" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Document</title>
    <link rel="stylesheet" href="/css/detail.css" />
</head>
<body>
<input type="hidden" id="sendUser" value="${sendUsers}">
<input type="hidden" id="recvUser" value="${recvUsers}">
<div class="container">
    <div class="msg-box">
    </div>
</div>

<script src="/js/detail.js">
</script>
</body>
</html>
