<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/signUpCheckId.css" />
    <title>아이디 중복 확인</title>
</head>
<body>
    <form action = "/DEMO/signUpCheckId.do" method="GET" name="checkIdForm">
        <input type = "text" placeholder="ID" value="${userid }" name = "userid">
        <input type = "button" value = "중복확인" onclick="isEmpty()">
    </form>
    <c:choose>
        <c:when test = "${result }">
            <div class="result">&quot;${userid }&quot;(은)는 사용 중 입니다.</div>
        </c:when>
        <c:otherwise>
            <div class="result">사용 가능한 아이디입니다.</div>
            <input class="close" type = "button" value = "사용하기" onclick = "checkIdFormClose('${userid }')">
        </c:otherwise>
    </c:choose>
    <script src="${pageContext.request.contextPath}/script/script.js"></script>
</body>
</html>