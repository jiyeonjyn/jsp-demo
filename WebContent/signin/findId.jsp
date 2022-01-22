<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css" integrity="sha384-50oBUHEmvpQ+1lW4y57PTFmhCaXp0ML5d60M1M7uH2+nqUivzIebhndOJK28anvf" crossorigin="anonymous">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/findId.css" />
        <meta charset="UTF-8" />
        <title>아이디 찾기</title>
    </head>
    <body>
        <div id="header">
            <div class="logo"><a href="/DEMO/index.jsp">logo</a></div>   
        </div>
        <div id="contents">
            <div class="findIdFormWrap">
                <ul>
                    <li><a href="/DEMO/signin/findId.jsp">ID 찾기</a></li>
                    <li><a href="#">비밀번호 재설정</a></li>
                </ul>
                <p>ID를 잊으셨나요?<br>아래의 정보를 입력해 주세요.</p>
                <div class="form">
                    <div>
                        <input type="text" placeholder=" " id="mail_input" name="mail" required>
                        <label for="mail_input">이메일</label>
                    </div>
                    <div id="result"></div>
                    <input type="button" value="ID 찾기" onclick="findId()">
                </div>
            </div>
        </div>
        <jsp:include page="/layout/footer2.jsp"/>
    </body>
</html>
