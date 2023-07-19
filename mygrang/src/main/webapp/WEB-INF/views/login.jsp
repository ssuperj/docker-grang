<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> <%@ include
file="layout/linkHeader.jsp"%>
<link rel="stylesheet" href="/css/login.css" />
<%@ include file="layout/header.jsp"%>
<main>
    <div class="wrap">
        <form action="/auth/loginProc" method="post">
            <div class="loginbox">
                <div class="box1"><img src="/image/logo_2.png" /></div>
                <div class="box2">
                    <ul>
                        <li>
                            <input
                                type="text"
                                name="username"
                                placeholder="아이디"
                                class="login_username"
                                style="padding-left: 10px"
                            />
                        </li>
                    </ul>
                </div>
                <div class="box3">
                    <ul>
                        <li>
                            <input
                                type="password"
                                name="password"
                                placeholder="비밀번호"
                                class="login_password"
                                style="padding-left: 10px"
                            />
                        </li>
                    </ul>
                </div>
                <div class="box4">
                    <ul>
                        <li>
                            <button class="ordinaryLogin">로그인</button>
                        </li>
                    </ul>
                </div>
                <div class="box5">
                    <div class="horizonAndOrWrap">
                        <hr class="leftHr" />
                        <div class="or">또는</div>
                        <hr class="rightHr" />
                    </div>
                    <div class="box6">
                        <a
                            href="https://kauth.kakao.com/oauth/authorize?response_type=code&client_id=3c4f59e8f00dd3b8e07672a870968fbd&redirect_uri=http://localhost:80/auth/kakao/login&response_type=code"
                            class="noneunderline"
                        >
                            <img
                                class="kakaotalk_login"
                                src="/image/kakao_login_medium_wide.png"
                                alt="카카오톡으로 로그인"
                                class="kakaotalk_icon"
                            />
                        </a>
                    </div>
                    <div class="haveAccount">
                        <p>
                            계정이 없으신가요?
                            <a href="/auth/signup" class="singuplink">가입하기</a>
                        </p>
                    </div>
                </div>
            </div>
        </form>
    </div>
</main>
<script defer src="/js/login.js"></script>
<%@ include file="layout/footer.jsp"%>
