<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var = "board" value = "${ requestScope.brdVO }"/>
<c:remove var = "brdVO" scope = "request"/>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css" integrity="sha384-50oBUHEmvpQ+1lW4y57PTFmhCaXp0ML5d60M1M7uH2+nqUivzIebhndOJK28anvf" crossorigin="anonymous">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/boardRead.css" />
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
                <div class="board-header">
                    <h4>${board.title }</h4>
                    <div class="board-info">
                        <span class="writer">${board.name }</span>
                        <span>조회 ${board.views }</span>
                        <span>${board.regdate }</span>
                    </div>
                </div>
                <div class="board-con">
                ${board.content }
                </div>
                <div class="comment-box">
                    <div class="comment-tab">
                        <h4>댓글</h4>
                        <span class="cmt-count"></span>
                    </div>
                    <ul class="comment-list">
                    </ul>
                    <c:if test = "${currentId != null }">
                    <div class="comment-write">
                        <p>${sessionScope.currentName }</p>
                        <textarea placeholder="댓글을 남겨보세요." name="content" required></textarea>
                    	<input type="hidden" name="parentnum" value="${board.num }">
						<input type="button" value="등록" onclick="writeComment()">
                    </div>
                    </c:if>
                </div>
                <div class="btn-wrap">
                    <a href="/DEMO/boardList.do?page=1" class="board-list-btn">목록으로</a>
                    <span>
                    <c:if test = "${sessionScope.currentId == board.userid }">
                    <form action="/DEMO/boardRead.do" method="POST">
	                    <input type = "hidden" name = "brdNo" value = "${board.num }">
	                    <input type = "hidden" name = "modify" value = "true">
	                    <input type="submit" value="수정" class="board-modify-btn">
                    </form>
                    <form action="/DEMO/boardDelete.do" method="POST">
	                    <input type = "hidden" name = "brdNo" value = "${board.num }">
	                    <input type="submit" value="삭제" class="board-delete-btn"
	                    	onclick="if (!confirm('정말 삭제하시겠습니까?')) {return false;}">
                    </form>
                    </c:if>
                    </span>
                </div>
            </section>
        </div>
        <jsp:include page="/layout/footer.jsp"/>
    </body>
</html>