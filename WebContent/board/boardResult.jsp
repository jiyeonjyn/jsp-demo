<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8" />
        <title>고객서비스</title>
    </head>
    <body>
    <c:choose>
        <c:when test = "${result }">
            <script>
            	alert("글이 ${state}되었습니다.");
                location.href='/DEMO/boardList.do?page=1';
            </script>
        </c:when>
        <c:otherwise>
            <script>
                alert("글${state}에 실패하였습니다. 다시 시도해 주세요.");
                history.back();
            </script>
        </c:otherwise>
    </c:choose>
    </body>
</html>