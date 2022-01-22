<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css" integrity="sha384-50oBUHEmvpQ+1lW4y57PTFmhCaXp0ML5d60M1M7uH2+nqUivzIebhndOJK28anvf" crossorigin="anonymous">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/productList.css" />
        <meta charset="UTF-8" />
        <title>제품안내</title>
    </head>
    <body>
        <jsp:include page="/layout/header.jsp"/>
        <div id="contents">
            <h2>제품안내</h2>
            <c:if test="${sessionScope.currentId == 'admin' }">            
            <form action="/DEMO/productDelete.do" method="POST">
            <input type="checkbox" id="select_all" onchange="selectAll(this)"><label for="select_all">모두 선택</label>
            </c:if>
            <ul class="product-container">
            	<c:if test="${sessionScope.currentId == 'admin' }">
            	<c:forEach var = "product" items = "${ requestScope.productList }">
                <a href="${product.detailpage }" class="item">
                	<c:if test="${sessionScope.currentId == 'admin' }">
                	<input type="checkbox" value="${product.num}" name="delete_num">
                	</c:if>
                    <div class="img-wrap">
                        <img
                            src="/DEMO/productThumbnail.do?fileName=${product.imgfile }"
                        />
                    </div>
                    <h3>${product.name }</h3>
                    <p>${product.brief }</p>
                </a>
              	</c:forEach>
              	</c:if>
            </ul>
            <c:choose>
            <c:when test="${sessionScope.currentId == 'admin' }">
            	<div class="btn-wrap">
	                <div class="pagination">
	                    <c:if test="${currentPage == totalPages && totalPages > 5}">
                    		<a href="/DEMO/productList.do?page=${currentPage-5}" class="y-prev"></a>
                    		<a href="/DEMO/productList.do?page=${currentPage-4}">${currentPage-4}</a>
                    		<a href="/DEMO/productList.do?page=${currentPage-3}">${currentPage-3}</a>
                    	</c:if>
                    	<c:if test="${currentPage == totalPages-1 && totalPages > 5}">
                    		<a href="/DEMO/productList.do?page=${currentPage-4}" class="y-prev"></a>
                    		<a href="/DEMO/productList.do?page=${currentPage-3}">${currentPage-3}</a>
                    	</c:if>
                    	<c:if test="${currentPage > 3 && currentPage < totalPages-1 && totalPages > 5}">
                    		<a href="/DEMO/productList.do?page=${currentPage-3}" class="y-prev"></a>
                    	</c:if>
                        <c:if test = "${currentPage > 2}">	
                        	<a href="/DEMO/productList.do?page=${currentPage-2}">${currentPage-2}</a>
                        </c:if>
                        <c:if test = "${currentPage > 1}">	
                        	<a href="/DEMO/productList.do?page=${currentPage-1}">${currentPage-1}</a>
                        </c:if>
                        	<span class="current-num">${currentPage}</span>
                        <c:if test = "${currentPage < totalPages}">
                        	<a href="/DEMO/productList.do?page=${currentPage+1}">${currentPage+1}</a>
                        </c:if>
                        <c:if test = "${currentPage+1 < totalPages}">
                        	<a href="/DEMO/productList.do?page=${currentPage+2}">${currentPage+2}</a>
                        </c:if>
                        <c:if test = "${currentPage+2 < totalPages && currentPage > 2}">
                        	<a href="/DEMO/productList.do?page=${currentPage+3}" class="y-next"></a>
                        </c:if>
                        <c:if test = "${currentPage+3 < totalPages && currentPage == 2}">
                        	<a href="/DEMO/productList.do?page=${currentPage+3}">${currentPage+3}</a>
                        	<a href="/DEMO/productList.do?page=${currentPage+4}" class="y-next"></a>
                        </c:if>
                        <c:if test = "${currentPage+5 < totalPages && currentPage == 1}">
                        	<a href="/DEMO/productList.do?page=${currentPage+3}">${currentPage+3}</a>
                        	<a href="/DEMO/productList.do?page=${currentPage+4}">${currentPage+4}</a>
                        	<a href="/DEMO/productList.do?page=${currentPage+5}" class="y-next"></a>
                        </c:if>
	                </div>
	                <input type="button" class="add-btn" value="추가" onclick="window.open('/DEMO/product/productAdd.jsp','','width=800 height=660 left=200 top=10')">
	                <input type="submit"class="delete-btn" value="삭제" onclick="if (!confirm('정말 삭제하시겠습니까?')) {return false}">
				</div>		                
			</form>	        
            </c:when>
            <c:otherwise>
	            <c:if test="${productList != null }">
	           	<input type="hidden" value="${totalPages }" name="totalPages">
                <input type="hidden" value="${currentPage }" name="currentPage">
	            <button class="view-more" onclick="getProducts()">더보기</button>
	            </c:if> 
            </c:otherwise>
            </c:choose>
        </div>
		<jsp:include page="/layout/footer.jsp"/>
    </body>
</html>
