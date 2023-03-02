<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="layout/linkHeader.jsp"%>
<link rel="stylesheet" href="/css/updateUserForm.css" />
<%@ include file="layout/header.jsp"%><div class="wrap">
    <div class="signupbox">
        <h2 class="u_title">회원 정보 수정</h2>
        <input type="hidden" value="${principal.user.id }" id="pId">
        <div class="box1">
            <label for="change_img">
                <c:choose>
                    <c:when test="${principal.user.profileImage == '/image/normal.jpg'}">
                        <img width="150" height="150" src="/image/normal.jpg" id="preview">
                    </c:when>
                    <c:otherwise>
                        <img width="150" height="150" src="/profile/${principal.user.profileImage}" id="preview">
                    </c:otherwise>
                </c:choose>
            </label>
            <input type="file" id="change_img" style="display:none;" onchange="readURL(this);">
        </div>

        <div class="box2">
            <ul>
                <li>
                    <input type="text" class="id sign_input" value="${principal.user.username }" style="padding-left: 10px" id="uId" readonly/>
                </li>
            </ul>
        </div>
        <div class="box2">
            <ul>
                <li>
                    <input type="email" class="id sign_input" placeholder="이메일" value="${principal.user.email }" style="padding-left: 10px" id="uEmail"/>
                </li>
            </ul>
        </div>
        <div class="box4">
            <ul>
                <li>
                    <input type="password" placeholder="비밀번호" class="password sign_input" id="pwd" style="padding-left: 10px" />
                </li>
            </ul>
        </div>
        <div class="box5">
            <ul>
                <li>
                    <input
                            type="password"
                            placeholder="비밀번호 확인"
                            class="password sign_input"
                            id="pwdc"
                            style="padding-left: 10px"
                    />
                </li>
            </ul>
        </div>
        <div class="box6">
            <ul>
                <li>
                    <button class="ordinarysignup unactivatedsignupColor" id="updateUser">수정하기</button>
                </li>
            </ul>
        </div>
    </div>
    <script src="/js/updateUser.js"></script>
</div>

<%@ include file="layout/footer.jsp"%>