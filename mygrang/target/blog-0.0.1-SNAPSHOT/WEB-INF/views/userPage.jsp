<%@page import="org.springframework.data.domain.Page"%>
<%@page import="com.grang.model.board"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="layout/linkHeader.jsp"%>
<link rel="stylesheet" href="/css/index.css" />
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/Swiper/4.5.1/css/swiper.min.css">
<script defer src="/js/isotope.pkgd.min.js"></script>
<script defer src="/js/main.js"></script>
<script defer src="/js/main2.js"></script>
<script defer src="https://cdn.jsdelivr.net/npm/swiper@8/swiper-bundle.min.js"></script>
<%@ include file="layout/header.jsp"%>
<main>
	<div class="user-info">
		<c:choose>
			<c:when test="${user.profileImage == '/image/normal.jpg'}">
				<img class="user-info__img" width="50" height="50" src="/image/normal.jpg">
			</c:when>
			<c:otherwise>
				<img class="user-info__img" width="50" height="50" src="/profile/${user.profileImage}"/>
			</c:otherwise>
		</c:choose>
		<span class="user-info__username">${user.username}</span>
	</div>
	<ul>
		<li class="on"><a href="*">ALL</a></li>
		<li><a href=".odd">ODD</a></li>
		<li><a href=".even">EVEN</a></li>
	</ul>
 	<section>
		<c:forEach var="boards" items="${board.content }">
		<c:if test="${boards.user.id==userId }">
			<c:choose>
				<c:when test="${(boards.id % 2)==0}">
					<article class="odd">
						<div>
							<c:set var="ss" value="${boards.storyImages }"></c:set>
				 			<%
				 				String s = (String)pageContext.getAttribute("ss");
				 				String[] arrStr = s.split("/");
				 				for(String str : arrStr) {
				 					out.print("<img src='/upload/"+ str + "' class='pront_img'>");
				 				}
				 			%>
				 			<span>${boards.user.username}</span>
							<h2>${boards.title}</h2>
							<p>${boards.content}</p>
							<c:if test="${principal.user.id==boards.user.id}">
							<a href="/updateBoardForm/${boards.id}">
									<i class="fa-solid fa-pen"></i>
									<span>수정,삭제</span>
							</a>
							</c:if>
						</div>
					</article>
				</c:when>
				<c:otherwise>
					<article class="even">
						<div>
							<c:set var="ss" value="${boards.storyImages }"></c:set>
				 			<%
					 			String s = (String)pageContext.getAttribute("ss");
				 				String[] arrStr = s.split("/");
				 				for(String str : arrStr) {
				 					out.print("<img src='/upload/"+ str + "' class='pront_img'>");
				 				}
				 			%>
				 			<span>${boards.user.username}</span>
							<h2>${boards.title}</h2>
							<p>${boards.content}</p>
							<c:if test="${principal.user.id==boards.user.id}">
								<a href="/updateBoardForm/${boards.id}">
									<i class="fa-solid fa-pen"></i>
									<span>수정,삭제</span>
								</a>
							</c:if>
						</div>
					</article>
				</c:otherwise>
			</c:choose>
		</c:if>
		</c:forEach>
	</section>
</main>
<%@ include file="layout/footer.jsp"%>