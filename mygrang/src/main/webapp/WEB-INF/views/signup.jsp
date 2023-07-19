<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> <%@ include
file="layout/linkHeader.jsp"%>
<link rel="stylesheet" href="/css/signup.css" />
<script defer src="/js/isotope.pkgd.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.min.js"></script>
<%@ include file="layout/header.jsp"%>
<main>
    <div class="wrap">
        <div class="signupbox">
            <div class="box1"><img src = /image/logo_2.png></div>
            <div class="box2">
                <ul>
                    <li>
                        <input type="text" placeholder="아이디" class="username" style="padding-left: 10px" />
                    </li>
                </ul>
            </div>
            <div class="box3">
                <ul>
                    <li>
                        <input type="email" placeholder="이메일" class="email" style="padding-left: 10px" />
                    </li>
                </ul>
            </div>
            <div class="box4">
                <ul>
                    <li>
                        <input type="password" placeholder="비밀번호" id="password1" style="padding-left: 10px" />
                    </li>
                </ul>
            </div>
            <div class="box5">
                <ul>
                    <li>
                        <input type="password" placeholder="비밀번호 확인" id="password2" style="padding-left: 10px" />
                    </li>
                </ul>
            </div>
            <div class="box6">
                <ul>
                    <li>
                        <button type="submit" class="ordinarysignup">가입하기</button>
                    </li>
                </ul>
            </div>
            <div class="box7">
                <div class="horizonAndOrWrap">
                    <hr class="leftHr" />
                    <div class="or">또는</div>
                    <hr class="rightHr" />
                </div>
                <div class="box8">
                    <button class="kakaotalk_btn">
                        <a
                            href="https://kauth.kakao.com/oauth/authorize?response_type=code&client_id=3c4f59e8f00dd3b8e07672a870968fbd&redirect_uri=http://localhost:80/auth/kakao/login&response_type=code"
                            class="noneunderline"
                        >
                            <img src="/image/kakao_login_medium_wide.png" alt="카카오톡으로 로그인" class="" />
                        </a>
                    </button>
                </div>
                <div class="haveAccount">
                    <p>
                        계정이 있으신가요?
                        <a href="/auth/login" class="singuplink">로그인 하기</a>
                    </p>
                </div>
            </div>
        </div>
    </div>
    <script defer src="/js/signup.js"></script>
</main>
<%@ include file="layout/footer.jsp"%>
