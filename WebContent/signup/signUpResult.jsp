<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css" integrity="sha384-50oBUHEmvpQ+1lW4y57PTFmhCaXp0ML5d60M1M7uH2+nqUivzIebhndOJK28anvf" crossorigin="anonymous">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/signUpResult.css" />
        <meta charset="UTF-8" />
        <title>가입완료</title>
    </head>
    <body>
        <div id="header">
            <div class="logo"><a href="/DEMO/index.jsp">logo</a></div>   
        </div>
        <div id="contents">
            <div class="signUpFormWrap">
            <c:choose>
				<c:when test="${result}">
					<h2><p>${name} 님,</p>가입이 완료되었습니다.</h2>
					<a href="/DEMO/signin/signIn.jsp">로그인하러 가기</a>
				</c:when>
				<c:otherwise>
					<h2>회원가입에 실패했습니다.</h2>
					<a href="#" onclick="history.back()">뒤로가기</a>
				</c:otherwise>
			</c:choose>
            </div>
        </div>
        <div id="footer">
            <div class="f_logo">
                <div>logo</div>
                <span>Copyright © Lee All Rights Reserved.</span>
            </div>
            <ul class="f_menu">
                <li><a href="#">이용약관</a></li>
                <li><a href="#">개인정보 처리방침</a></li>
                <li><a href="#">공지사항</a></li>
                <li><a href="#">문의하기</a></li>
            </ul>       
        </div>
	    <script src="${pageContext.request.contextPath}/script/script.js"></script>
    </body>
</html>