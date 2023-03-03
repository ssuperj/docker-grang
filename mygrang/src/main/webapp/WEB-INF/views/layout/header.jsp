<%@ page import="java.net.InetAddress" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
</head>
<body>
<header>
    <div class="h_logo">
        <a href="/">
            <c:choose>
                <c:when test="${empty story}">
                    <img src="/image/logo_2.png"/>
                </c:when>
                <c:otherwise>
                    <img src="/image/logo_white.png"/>
                </c:otherwise>
            </c:choose>
        </a>
    </div>
    <div class="h_m_nav">
        <ul class="h_m_list">
            <li>
                <a href="/chat/${principal.user.id}">
                    <i class="fa-regular fa-paper-plane"></i>
                </a>
            </li>
            <li>
                <a class="drop-link">
                    <i class="fa-solid fa-magnifying-glass drop-icon" id="drop1"></i>
                    <ul class="drop-down remove">
                        <input type="text" class="drop-down__input"/>
                    </ul>
                </a>
            </li>
        </ul>
    </div>
    <div class="h_nav">
        <ul class="h_nav_list">
            <li>
                <a href="/">
                    <i class="fa-solid fa-house"></i>
                </a>
            </li>
            <li>
                <a href="/chat/${principal.user.id}">
                    <i class="fa-regular fa-paper-plane"></i>
                </a>
            </li>
            <li>
                <a href="/boardForm">
                    <i class="fa-solid fa-pen-to-square"></i>
                </a>
            </li>
            <li style="margin-top: 32px">
                <button class="playmusic m_btn">
                    <i class="fa-solid fa-play"></i>
                </button>
                <button class="stopmusic m_btn">
                    <i class="fa-solid fa-pause"></i>
                </button>
            </li>
            <li>
                <a class="drop-link">
                    <i class="fa-solid fa-magnifying-glass drop-icon" id="drop2"></i>
                    <ul class="drop-down remove">
                        <input type="text" class="drop-down__input" onke/>
                    </ul>
                </a>
            </li>
            <li>
                <a class="drop-link">
                    <i class="fa-solid fa-user"></i>
                    <ul class="drop-down drop-down-sm remove">
                        <c:choose>
                            <c:when test="${empty principal}">
                                <li class="userOption slow">
                                    <span onclick="location.href='/auth/login'">로그인</span>
                                </li>
                                <li class="userOption slow">
                                    <span onclick="location.href='/auth/signup'">회원가입</span>
                                </li>
                            </c:when>
                            <c:otherwise>
                                <li class="userOption slow">
                                    <span onclick="location.href='/logout'">로그아웃</span>
                                </li>
                                <c:if test="${principal.user.auth == 'GENERAL'}">
                                    <li class="userOption slow">
                                        <span onclick="location.href='/updateUserForm'">회원수정</span>
                                    </li>
                                </c:if>
                                <li class="userOption slow">
                                    <span onclick="location.href='/userPage/${principal.user.id}'">유저페이지</span>
                                </li>
                            </c:otherwise>
                        </c:choose>
                    </ul>
                </a>
            </li>
        </ul>
    </div>
</header>