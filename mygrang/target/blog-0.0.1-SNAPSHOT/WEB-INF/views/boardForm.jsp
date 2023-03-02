<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="layout/linkHeader.jsp"%>
<script src="https://code.jquery.com/jquery-3.6.2.min.js"></script>
<link rel="stylesheet" href="css/boardForm.css" />
<script defer src="/js/saveBoardForm.js"></script>
<%@ include file="layout/header.jsp"%>
<div class="board_wrap">
    <form class="board" onsubmit="upload(event)">
        <div class="b_info">
            <h4>게시글 등록</h4>
            <input
                    type="text"
                    placeholder="제목을 입력해주세요."
                    class="b_title"
                    id="title"
                    style="padding: 2px;margin: 2px;border: 1px solid #eee; "

            />
            <div id="image_preview">
                <h4>사진</h4>
                <div id="att_zone">

                </div>
                <div id="att_zone2">
                    <span class="upload-name">"+" 버튼을 눌러 사진을 추가하세요.</span>
                    <label for="btnAtt">+</label>
                    <input type="file" id="btnAtt" class="files" name="uploadFile" multiple="multiple"/>
                </div>
            </div>
            <div class="introduce">
                <label class="b_label">소개</label>
                <textarea
                        name="content"
                        id="content"
                        cols="30"
                        rows="10"
                        class="b_info_area"
                        placeholder="여기에 내용을 소개해 주세요."
                ></textarea>
                <button type="submit" class="b_btn-save" id="uploadBtn">게시</button>
            </div>
        </div>
    </form>
</div>

<%@ include file="layout/footer.jsp"%>