<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css" integrity="sha384-50oBUHEmvpQ+1lW4y57PTFmhCaXp0ML5d60M1M7uH2+nqUivzIebhndOJK28anvf" crossorigin="anonymous">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/signUp.css" />
        <meta charset="UTF-8" />
        <title>가입하기</title>
    </head>
    <body>
        <div id="header">
            <div class="logo"><a href="/DEMO/index.jsp">logo</a></div>   
        </div>
        <div id="contents">
            <div class="signUpFormWrap">
                <h2>가입하기</h2>
                <form action="/DEMO/signUp.do" method="POST" name="joinForm">
					<div>
                        <input type="text" placeholder=" " id="id_input" name="userid">
                        <label for="id_input">ID</label>
                        <button type="button" onclick="checkId()">중복확인</button>
                    </div>
                    <div>
                        <input type="password" placeholder=" " id="pw_input" name="userpw" oninput="checkPassword()">
                        <label for="pw_input">비밀번호</label>
                    </div>
                    <div>
                        <input type="password" placeholder=" " id="re_pw_input" name="re_userpw" oninput="checkPassword()">
                        <label for="re_pw_input">비밀번호 재확인</label>
                        <span id="pw-guide"></span>
                    </div>
                    <div>
                        <input type="text" placeholder=" " id="name_input" name="name">
                        <label for="name_input">닉네임</label>
                    </div>
                    <div>
                        <input type="text" placeholder=" " id="mail_input" name="mail">
                        <label for="mail_input">이메일</label>
                    </div>
                    <div class="err_msg">모든 항목을 빠짐없이 입력하세요.</div>
                    <input type="button" value="가입하기" onclick="checkInputValue()">
                </form>
            </div>
        </div>
		<jsp:include page="/layout/footer2.jsp"/>
    </body>
</html>
