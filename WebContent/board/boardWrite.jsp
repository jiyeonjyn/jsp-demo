<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css" integrity="sha384-50oBUHEmvpQ+1lW4y57PTFmhCaXp0ML5d60M1M7uH2+nqUivzIebhndOJK28anvf" crossorigin="anonymous">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/boardWrite.css" />
        <meta charset="UTF-8" />
        <title>고객서비스</title>
    </head>
    <body>
        <jsp:include page="/layout/header.jsp"/>
        <div id="main">
            <nav>
                <h3>고객서비스</h3>
                <ul class="board-title">
                    <li><a href="#">고객마당</a></li>
                    <ul class="board-menu">
                        <li><a href="/DEMO/boardList.do?page=1">자유게시판</a></li>
                    </ul>
                </ul>
            </nav>
            <section>
                <h3>자유게시판</h3>
                <form action="/DEMO/boardWrite.do" method="POST" name="boardWriteForm">
                    <input type="text" class="title-input" placeholder="제목을 입력하세요." name="title"
                    		onkeydown="if (event.keyCode === 13) {event.preventDefault();}" required/>
                    <textarea class="content-input" name="content" required>게시글 내용입니다.</textarea>
                    <div class="btn-wrap">
                        <input type="submit" value="글 등록하기" class="go-write-btn">
                        <input type="reset" value="초기화" class="reset-btn">
                    </div>
                </form>
            </section>
        </div>
		<jsp:include page="/layout/footer.jsp"/>
    </body>
</html>
