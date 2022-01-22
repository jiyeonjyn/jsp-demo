<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8" />
        <title>로그인</title>
    </head>
    <body>
    <c:choose>
        <c:when test = "${ sessionScope.currentName != null }">
            <script>
            	sessionStorage.setItem('currentId', '${sessionScope.currentId}');
            	sessionStorage.setItem('currentName', '${sessionScope.currentName}');
                location.href='/DEMO/index.jsp';
            </script>
        </c:when>
        <c:otherwise>
            <script>
                alert("아이디 또는 패스워드를 확인해 주세요.");
                history.back();
            </script>
        </c:otherwise>
    </c:choose>
    </body>
</html>
