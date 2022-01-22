<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <div id="header">
            <div class="logo"><a href="/DEMO/index.jsp">logo</a></div>
            <ul class="menu">
                <li><a href="#">기업소개</a></li>
                <li><a href="/DEMO/productList.do">제품안내</a></li>
                <li><a href="#">매장안내</a></li>
                <li><a href="#">창업안내</a></li>
                <li><a href="/DEMO/boardList.do?page=1">고객서비스</a></li>
            </ul>
            <div class="icons">
	            <c:choose>
			        <c:when test = "${ sessionScope.currentName != null }">
                	<span>안녕하세요! ${sessionScope.currentName}님</span>
                	<a href="myPage.html"><i class="fas fa-user"></i></a>
                	<div class="icon-slide-down"><a href="/DEMO/logOut.do">Log Out</a></div>
			        </c:when>
			        <c:otherwise>
			        <a href="/DEMO/signin/signIn.jsp"><span>Sign In</span></a>
			        </c:otherwise>
			    </c:choose>
                <a href="#"><i class="fas fa-search"></i></a>
            </div>        
        </div>