<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css" integrity="sha384-50oBUHEmvpQ+1lW4y57PTFmhCaXp0ML5d60M1M7uH2+nqUivzIebhndOJK28anvf" crossorigin="anonymous">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" />
        <meta charset="UTF-8" />
        <title>메인 페이지</title>
    </head>
    <body>
        <jsp:include page="/layout/header.jsp" />
        <div id="visual">
            <img
                src="https://images.samsung.com/kdp/cms_contents/104190/052c510d-e878-4400-b97a-f29ee2467f43.jpg?$ORIGIN_JPG$"
                alt=""
            />
        </div>
        <div id="contents">
            <div class="con1">
                <h2>Contents 1</h2>
                <div>
                    <div class="img-wrap">
                        <div class="img1"></div>
                    </div>
                    <h3>Here comes sub title</h3>
                    <p>
                        Lorem ipsum dolor sit amet consectetur adipisicing elit.
                    </p>
                </div>
                <div>
                    <div class="img-wrap">
                        <div class="img2"></div>
                    </div>
                    <h3>Here comes sub title</h3>
                    <p>
                        Lorem ipsum dolor sit amet consectetur adipisicing elit.
                    </p>
                </div>
                <div>
                    <div class="img-wrap">
                        <div class="img3"></div>
                    </div>
                    <h3>Here comes sub title</h3>
                    <p>
                        Lorem ipsum dolor sit amet consectetur adipisicing elit.
                    </p>
                </div>
                <div>
                    <div class="img-wrap">
                        <div class="img4"></div>
                    </div>
                    <h3>Here comes sub title</h3>
                    <p>
                        Lorem ipsum dolor sit amet consectetur adipisicing elit.
                    </p>
                </div>
                <a href="#">View more</a>
            </div>
            <div class="con2">
                <div class="box1"></div>
                <div class="box2">
                    <h2>Contents 2</h2>
                    <div class="box3">
                        <h3>Lorem</h3>
                        <p>
                            Lorem ipsum dolor sit amet consectetur adipisicing
                            elit.
                        </p>
                        <ul>
                            <li>first</li>
                            <li>second</li>
                            <li>third</li>
                        </ul>
                    </div>
                    <span><a href="#">VIEW ALL</a></span>
                </div>
            </div>
            <div class="con3">
                <h2>Contents 3</h2>
                <ul>
                    <li>
                        <div class="img1"></div>
                        <h3>Here comes sub title</h3>
                        <p>
                            Lorem ipsum, dolor sit amet consectetur adipisicing
                            elit. Asperiores esse illo tempora aut animi eveniet
                            nostrum sequi optio!
                        </p>
                    </li>
                    <li>
                        <div class="img2"></div>
                        <h3>Here comes sub title</h3>
                        <p>
                            Lorem ipsum dolor sit amet, consectetur adipisicing
                            elit. A facere, voluptatum aspernatur harum
                            eligendi, quos eum fugiat.
                        </p>
                    </li>
                    <li>
                        <div class="img3"></div>
                        <h3>Here comes sub title</h3>
                        <p>
                            Lorem ipsum dolor sit amet consectetur adipisicing
                            elit. Quis, harum omnis odio voluptatum veniam,
                            assumenda est provident soluta culpa.
                        </p>
                    </li>
                    <li>
                        <div class="img4"></div>
                        <h3>Here comes sub title</h3>
                        <p>
                            Lorem ipsum dolor sit amet consectetur adipisicing
                            elit. Rem ducimus cum quo maiores quas perspiciatis
                            aut reprehenderit sed!
                        </p>
                    </li>
                    <li>
                        <div class="img5"></div>
                        <h3>Here comes sub title</h3>
                        <p>
                            Lorem ipsum dolor sit amet, consectetur adipisicing
                            elit. Ratione sed ad ipsum odio quis earum sapiente
                            reprehenderit rerum.
                        </p>
                    </li>
                    <li>
                        <div class="img6"></div>
                        <h3>Here comes sub title</h3>
                        <p>
                            Lorem ipsum dolor, sit amet consectetur adipisicing
                            elit. Tempore enim esse nihil ipsum, atque
                            repellendus provident quos neque..
                        </p>
                    </li>
                </ul>
            </div>
        </div>
        <jsp:include page="/layout/footer.jsp" />
    </body>
</html>
