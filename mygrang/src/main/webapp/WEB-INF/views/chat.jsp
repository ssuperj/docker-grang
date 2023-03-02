<%@ page import="java.time.LocalDateTime" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.Calendar" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set value="notFooter" var="notFooter"/>
<%@ include file="layout/linkHeader.jsp" %>
<script defer src="/js/chat.js"></script>
<link rel="stylesheet" href="/css/chat.css"/>
<%@ include file="layout/header.jsp" %>
<input id="myId" type="hidden" value="${principal.user.id}">
<main>
    <div class="msg">
        <div class="msg__main">
            <div class="msg__main__user">
                <c:forEach var="room" items="${rooms}">
                    <div class="msg__main__content slow" data-id="${room.recvUser.id}">
                        <div class="msg__main__content__image">
                            <c:choose>
                                <c:when test="${room.recvUser.profileImage == '/image/normal.jpg'}">
                                    <img width="50" height="50" src="/image/normal.jpg">
                                </c:when>
                                <c:otherwise>
                                    <img src="/profile/${room.recvUser.profileImage}" width="50" height="50">
                                </c:otherwise>
                            </c:choose>
                        </div>
                        <div class="msg__main__content__name"><span>${room.recvUser.username}</span>
                            <span>
                            <c:set value="${room.recvUser.loginTime}" var="date"/>
                            <%
                                Date date = (Date) pageContext.getAttribute("date");
                                long now = Calendar.getInstance().getTimeInMillis() - date.getTime();
                                long minute = now / (1000 * 60);
                                long hour = now / (1000 * 60 * 60);
                                long day = now / (1000 * 60 * 60 * 24);
                                if (day >= 1) {
                                    out.print(day + "일전");
                                } else if (hour >= 1) {
                                    out.print(hour + "시간전");
                                } else {
                                    out.print(minute + "분전");
                                }
                            %>
                        </span>
                        </div>
                        <span class="msg__main__content__btn" onclick="index1.deleteChatUser(event)">
                            <i class="fa-solid fa-xmark"></i>
                        </span>
                    </div>
                </c:forEach>
            </div>
            <div class="msg__main__plus">
                <i id="findUserToggleBtn" class="fa-solid fa-plus fa-2x"></i>
            </div>
        </div>
        <div class="findUser-box remove">
            <div class="findUser-box__search">
                <input id="search-input" placeholder="...search">
                <div class="findUser-box__search__inner">
                </div>
            </div>
            <div class="findUser-box__user">
                <div class="findUser-box__user__column">
                </div>
            </div>
        </div>

        <div class="msg__sub">
            <div class="img-box">
                <img src="/image/chatting.png" alt=""/>
            </div>
            <div class="img-box__container">
                <iframe id="iframe" src="/detail/${rooms[0].recvUser.id}" width="100%" height="80%"></iframe>
                <div class="send-box">
                    <input class="send-box__content"/>
                    <button class="send-box__btn" id="send-box__btn">보내기</button>
                </div>
            </div>
        </div>
    </div>
</main>
<%@ include file="layout/footer.jsp" %>