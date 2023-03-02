<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="layout/linkHeader.jsp"%>
<script src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
<link rel="stylesheet" href="/css/boardForm.css" />
<script defer src="/js/saveBoardForm.js"></script>
<%@ include file="layout/header.jsp"%>
<div class="board_wrap">
      <form class="board">
      <input type="hidden" id="id" value="${board.id}">
      <input type="hidden" id="userid" value="${board.user.id}">
        <div class="b_info">
          <h4>게시글 수정</h4>
          <!--원래 타이틀 불러오기  -->
          <input
            type="text"
            class="b_title"
            id="title"
            value="${board.title}"
            style="padding: 2px"
            style="margin: 2px"
          />
          <div id="image_preview" on>
            <h4>사진</h4>
            <div id="att_zone">
              <c:set var="ss" value="${board.storyImages }"></c:set>
	 			<%
		 			String s = (String)pageContext.getAttribute("ss");
	 				String[] arrStr = s.split("/");
	 				for(String str : arrStr) {
	 					out.print("<img src='/upload/"+ str + "' class='pront_img'");
	 					out.print(" style='display:inline-block;position:relative;width:150px;height:120px;margin:5px;z-index:1;'>");
	 				}
	 			%>
            </div>
          </div>
          <div class="introduce">
            <label class="b_label">소개</label>
            <!-- 원래 소개글 불러오기 -->
            <textarea
              name="content"
              id="content"
              cols="30"
              rows="10"
              class="b_info_area"
              placeholder="여기에 내용을 소개해 주세요."
            >${board.content}</textarea>
          </div>
        </div>
        <button type="button" class="b_btn-save" id="updateBtn" onclick="update()" style="margin-right:10px; z-index:50;">수정</button>
        <button type="button" class="b_btn-save" id="deleteBtn" onclick="return deleteById()" style="z-index:50;">삭제</button>
      </form>
    </div>

<%@ include file="layout/footer.jsp"%>