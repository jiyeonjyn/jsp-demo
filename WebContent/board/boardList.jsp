<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css" integrity="sha384-50oBUHEmvpQ+1lW4y57PTFmhCaXp0ML5d60M1M7uH2+nqUivzIebhndOJK28anvf" crossorigin="anonymous">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/boardList.css" />
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
                <table class="board-list">
                    <thead>
                        <tr>
                            <th class="td-num">글번호</th>
                            <th class="td-title">제목</th>
                            <th class="td-writer">작성자</th>
                            <th class="td-regdate">작성일</th>
                            <th class="td-views">조회수</th>
                        </tr>
                    </thead>
                    <tbody>
                    <c:choose>
                    	<c:when test = "${ requestScope.boardList == null}">
                    	<tr>
                            <td colspan="5" class="td-empty">게시글이 없습니다.</td>
                        </tr>
                        </c:when>
                        <c:otherwise>
                        <c:forEach var = "board" items = "${ requestScope.boardList }">
                        <tr>
                            <td class="td-num">${board.num }</td>
                            <td class="td-title">
                            	<a href="boardRead.do?brdNo=${board.num }">${board.title }</a>
                            	<c:if test = "${board.commentnum != 0 }">
                            	<span class="commentNum">[${board.commentnum }]</span>
                            	</c:if>
                            </td>
                            <td class="td-writer">${board.name }</td>
                            <td class="td-regdate">${board.regdate }</td>
                            <td class="td-views">${board.views }</td>
                        </tr>
						</c:forEach>
						</c:otherwise>
					</c:choose>
                    </tbody>
                </table>
                <div class="btn-wrap">
                    <div class="pagination">
                    	<input type="hidden" value="${totalPages }" name="totalPages">
                    	<input type="hidden" value="${currentPage }" name="currentPage">
                    	<c:if test="${currentPage == totalPages && totalPages > 5}">
                    		<a href="/DEMO/boardList.do?page=${currentPage-5}" class="y-prev"></a>
                    		<a href="/DEMO/boardList.do?page=${currentPage-4}">${currentPage-4}</a>
                    		<a href="/DEMO/boardList.do?page=${currentPage-3}">${currentPage-3}</a>
                    	</c:if>
                    	<c:if test="${currentPage == totalPages-1 && totalPages > 5}">
                    		<a href="/DEMO/boardList.do?page=${currentPage-4}" class="y-prev"></a>
                    		<a href="/DEMO/boardList.do?page=${currentPage-3}">${currentPage-3}</a>
                    	</c:if>
                    	<c:if test="${currentPage > 3 && currentPage < totalPages-1 && totalPages > 5}">
                    		<a href="/DEMO/boardList.do?page=${currentPage-3}" class="y-prev"></a>
                    	</c:if>
                        <c:if test = "${currentPage > 2}">	
                        	<a href="/DEMO/boardList.do?page=${currentPage-2}">${currentPage-2}</a>
                        </c:if>
                        <c:if test = "${currentPage > 1}">	
                        	<a href="/DEMO/boardList.do?page=${currentPage-1}">${currentPage-1}</a>
                        </c:if>
                        	<span class="current-num">${currentPage}</span>
                        <c:if test = "${currentPage < totalPages}">
                        	<a href="/DEMO/boardList.do?page=${currentPage+1}">${currentPage+1}</a>
                        </c:if>
                        <c:if test = "${currentPage+1 < totalPages}">
                        	<a href="/DEMO/boardList.do?page=${currentPage+2}">${currentPage+2}</a>
                        </c:if>
                        <c:if test = "${currentPage+2 < totalPages && currentPage > 2}">
                        	<a href="/DEMO/boardList.do?page=${currentPage+3}" class="y-next"></a>
                        </c:if>
                        <c:if test = "${currentPage+3 < totalPages && currentPage == 2}">
                        	<a href="/DEMO/boardList.do?page=${currentPage+3}">${currentPage+3}</a>
                        	<a href="/DEMO/boardList.do?page=${currentPage+4}" class="y-next"></a>
                        </c:if>
                        <c:if test = "${currentPage+4 < totalPages && currentPage == 1}">
                        	<a href="/DEMO/boardList.do?page=${currentPage+3}">${currentPage+3}</a>
                        	<a href="/DEMO/boardList.do?page=${currentPage+4}">${currentPage+4}</a>
                        	<a href="/DEMO/boardList.do?page=${currentPage+5}" class="y-next"></a>
                        </c:if>
                    </div>
                    <c:if test = "${sessionScope.currentId != null }">
                    <a href="/DEMO/board/boardWrite.jsp" class="board-write-btn">글쓰기</a>
                    </c:if>
                </div>
            </section>
        </div>
		<jsp:include page="/layout/footer.jsp"/>
    </body>
</html>