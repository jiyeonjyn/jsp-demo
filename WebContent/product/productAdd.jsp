<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/productAdd.css" />
    <meta charset="UTF-8" />
    <title>제품추가</title>
</head>
<body>
    <form action="/DEMO/productAdd.do" method="POST" enctype = "multipart/form-data" name="addProductForm">
        <h3>제품 추가하기</h3>
        <div><span>제품명</span><input type="text" placeholder="제품명을 입력하세요." name="name" required></div>
        <div><span>간단한 설명</span><textarea placeholder="설명을 입력하세요." name="brief" required></textarea></div>
        <div><span>대표 이미지</span><input type="file" name="imgfile" required></div>
        <div><span>상세 페이지 주소</span><input type="text" placeholder="클릭 시 이동할 페이지를 입력하세요." name="detailpage" required></div>
        <input type="button" value="추가하기" onclick="checkFileFormat()">
    </form>
    <script src="${pageContext.request.contextPath}/script/script.js"></script>
</body>
</html>