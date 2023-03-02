<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<footer>
  <div class="f_nav">
    <ul class="f_nav_list">
      <li>
        <a href="/">
          <i class="fa-solid fa-house"></i>
        </a>
      </li>
      <li>
        <a href="/boardForm">
          <i class="fa-solid fa-pen-to-square"></i>
        </a>
      </li>
      <li style="padding-top: 5px;">
        <button class="playmusic m_btn">
          <i class="fa-solid fa-play"></i>
        </button>
        <button class="stopmusic m_btn">
          <i class="fa-solid fa-pause"></i>
        </button>
      </li>
      <li>
        <a href="/<c:if test="${not empty principal}">userPage/${principal.user.id}</c:if>">
          <i class="fa-solid fa-user"></i>
        </a>
      </li>
    </ul>
  </div>
</footer>
<c:if test="${empty notFooter}">
  <div class="footer">
    <p>Copyright © 2022 grang All rights reserved.</p>
    <address>Contact webmaster for more information. 070-1234-5678</address>
  </div>
</c:if>
</body>
</html>