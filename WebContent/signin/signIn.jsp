<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css" integrity="sha384-50oBUHEmvpQ+1lW4y57PTFmhCaXp0ML5d60M1M7uH2+nqUivzIebhndOJK28anvf" crossorigin="anonymous">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/signIn.css" />
        <meta charset="UTF-8" />
        <title>로그인</title>
    </head>
    <body>
        <div id="header">
            <div class="logo"><a href="/DEMO/index.jsp">logo</a></div>   
        </div>
        <div id="contents">
            <div class="logInFormWrap">
                <h2>로그인</h2>
                <form action="/DEMO/signIn.do" method="POST">
                    <div>
                    <c:choose>
						<c:when test = "${ cookie.rememberId.value == null}">
							<input type="text" placeholder=" " id="id_input" name="id">
						</c:when>
						<c:otherwise>
							<input type = "text" name = "id_input" value = "${cookie.rememberId.value }">
						</c:otherwise>
					</c:choose>
                        <label for="id_input">ID</label>
                    </div>
                    <div>
                        <input type="password" placeholder=" " id="pw_input" name="pw">
                        <label for="pw_input">비밀번호</label>
                    </div>
                    <input type="checkbox" id="cb1" name="remember_me" value = "true">
                    <label for="cb1">ID 기억하기</label>
                    <div class="link">
                        <input type="submit" value="로그인">
                        <a href="/DEMO/signin/findId.jsp" target="_blank">ID 찾기 또는 비밀번호 변경</a><br><br>
                        <a href="/DEMO/signup/signUp.jsp" target="_blank">가입하기</a>   
                    </div>
                </form>
            </div>
        </div>
		<jsp:include page="/layout/footer2.jsp"/>
    </body>
</html>