<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set value="story" var="story"/>
<c:set value="notFooter" var="notFooter"/>
<%@ include file="layout/linkHeader.jsp" %>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@8/swiper-bundle.min.css"/>
<link rel="stylesheet" href="/css/story.css"/>
<script src="https://cdn.jsdelivr.net/npm/swiper@8/swiper-bundle.min.js"></script>
<script defer src="/js/replyForm.js"></script>
<script defer src="/js/reply.js"></script>
<%@ include file="layout/header.jsp" %>
<div class="wrap">
    <div class="swiper-wrapper">
        <c:forEach var="boards" items="${board.content}">
            <div class="swiper-slide">
                <div class="inner">
                    <form class="replyform">
                        <div class="r_title">
	      		          <span>
							<c:choose>
                                <c:when test="${boards.user.profileImage == '/image/normal.jpg'}">
                                    <img width="30" height="30" src="/image/normal.jpg">
                                </c:when>
                                <c:otherwise>
                                    <img src="/profile/${boards.user.profileImage}" width="30" height="30"/>
                                </c:otherwise>
                            </c:choose>
							</span>
                            <span>${boards.user.username}</span>
                        </div>


                        <div class="r_img">
                            <div class="img_zone">
                                <c:set var="ss" value="${boards.storyImages}"></c:set>
                                <%
                                    String s = (String) pageContext.getAttribute("ss");
                                    String[] arrStr = s.split("/");
                                    for (String str : arrStr) {
                                        out.print("<img src='/upload/" + str + "'>");
                                    }
                                %>
                            </div>
                            <div class="r_img__heart">
                                <i class="fa-regular fa-heart" style="padding-top:5px;"></i>
                                <input type="text" value="${boards.likeCount}" class="heart_cnt" readonly style="background: none; margin-left: -10px;">
                                <input type="hidden" value="${boards.id}" class="boardId">
                                <input type="hidden" value="${boards.user.username}">
                            </div>
                        </div>

                        <div class="r_info">
                            <label class="r_info_label">
                                <div class="r_info_title"
                                     style="width : 400px; margin :auto; margin-top : -10px; margin-left :-5px; overflow : auto; height : 60px;">
                                    <h3 style="margin-bottom : 5px; font-weight:bolder;">${boards.title}</h3>
                                    <p style="display:initial; font-size : 14px;">${boards.content}</p>
                                </div>
                            </label>

                            <div class="reply_wrap">
                                <c:forEach var="reply" items="${replys}">
                                    <c:if test="${reply.board.id==boards.id}">
                                        <div class="r_reply">

                                            <div class="r_profile">
                                                <div class="r_p_img">
                                                    <c:choose>
                                                        <c:when test="${reply.user.profileImage == '/image/normal.jpg'}">
                                                            <img width="30" height="30" src="/image/normal.jpg">
                                                        </c:when>
                                                        <c:otherwise>
                                                            <img src="/profile/${reply.user.profileImage}" width="30" height="30"/>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </div>
                                                <div class="r_p_name" style="margin-left:5px;">
                                                        ${reply.user.username}
                                                </div>
                                            </div>

                                            <div class="r_content">
                                                <div class="r_c_content">
                                                    <textarea class="c_content" readonly>${reply.content}</textarea>
                                                    <button class="r_c_more">
                                                        더보기
                                                    </button>
                                                    <button class="r_c_more_cancle" style="display: none;">
                                                        취소
                                                    </button>
                                                    <span class="edit_zone" style="display: none;">
			                        		<input type="hidden" value="${reply.id}">
			                        		<input type="text" placeholder="입력..." value="">
			                        		<button class="reply_edit_button">게시</button>
			                        	</span>
                                                </div>

                                            </div>


                                            <c:if test="${reply.user.id==principal.user.id}">
                                                <div class="r_btn">
                                                    <input type="hidden" value="${reply.id}">
                                                    <button class="reply_edit" style="font-size: 12px">
                                                        <i class="fa-solid fa-pen"></i>
                                                    </button>
                                                    <button class="reply_cancle" style="font-size: 12px; display: none">
                                                        <i class="fa-solid fa-x"></i>
                                                    </button>
                                                    <button class="reply_delete" style="font-size: 12px">
                                                        <i class="fa-solid fa-trash"></i>
                                                    </button>
                                                </div>
                                            </c:if>
                                            <c:if test="${reply.user.id!=principal.user.id}">
                                                <div class="reply_control"></div>
                                            </c:if>

                                        </div>
                                    </c:if>
                                </c:forEach>
                            </div>

                            <div class="r_reply_input">
                                <input type="hidden" value="${boards.id}">
                                <input type="hidden" value="${principal.user.id }">
                                <input type="text" placeholder="댓글 추가" class="add_reply" autofocus>
                                <button type="submit" class="r_btn-save" onclick="save()">게시</button>
                            </div>
                        </div>

                    </form>
                </div>
            </div>
        </c:forEach>
    </div>
    <div class="swiper-button-next"></div>
    <div class="swiper-button-prev"></div>
</div>
<%@ include file="layout/footer.jsp" %>