<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8" />
        <title>제품안내</title>
    </head>
    <body>
    <c:choose>
        <c:when test = "${result }">
        	<c:if test="${state == '추가'}">
        	<script>
            	alert("항목이 ${state}되었습니다.");
            	opener.location.href='/DEMO/productList.do';
                location.href='/DEMO/product/productAdd.jsp';
            </script>
			</c:if>
			<c:if test="${state == '삭제'}">
            <script>
            	alert("항목이 ${state}되었습니다.");
                location.href='/DEMO/productList.do';
            </script>
            </c:if>
        </c:when>
        <c:otherwise>
            <script>
                alert("항목 ${state}에 실패하였습니다. 다시 시도해 주세요.");
                history.back();
            </script>
        </c:otherwise>
    </c:choose>
    </body>
</html>