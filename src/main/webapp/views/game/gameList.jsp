<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
   
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
 <script src="https://code.jquery.com/jquery-3.7.1.js"></script>
 <script src="https://code.jquery.com/jquery-3.7.1.js"></script>
    <!-- 부트스트랩 -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <!-- 아이콘 -->
    <script src="https://kit.fontawesome.com/1ee4acc8d4.js" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
    integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
    crossorigin="anonymous"></script>
    
	 <!-- Project local -->
	<link rel="stylesheet" href="/css/game.css">
</head>
<body>
<!-- container -->
    <div class="container-fluid d-flex flex-column">
        <!-- nav -->
        <div class="container text-center flex-grow-1 d-flex flex-column">
            <img src="img/bg1.png" alt="" class="img_bg">
            <img src="img/bg3.png" alt="" class="img_bg bgs">
            <img src="img/bg3.png" alt="" class="img_bg bgs">
            <img src="img/bg3.png" alt="" class="img_bg bgs">
            <img src="img/bg3.png" alt="" class="img_bg bgs">
            <img src="img/bg3.png" alt="" class="img_bg bgs">
            <img src="img/bg3.png" alt="" class="img_bg bgs">


            <!-- nav -->
 			<%@ include file="/views/include/header.jsp" %>

            <main class="main container flex-grow-1">
			    <section class="row">
			        <c:forEach var='l' items='${list}'>
			            <div class="col-lg-4 col-md-6 mb-4">
			                <div class="card" style="width: 18rem;">
			                	<div>
			                    	<img src="${l.thumbnail}" class="card-img-top" alt="...">
			                    </div>
			                    <button class='btn btn-primary' data-seq="${l.seq}">${l.title}</button>
			                </div>
			            </div>
			        </c:forEach>
			    </section>
			</main>
        </div>
        <!-- mode -->
        <div id="mode">
            <i class="fa-regular fa-lightbulb" style="display: none;"></i>
            <i class="fa-solid fa-lightbulb"></i>
        </div>
    </div>


<script>
$(document).ready(function() {
    $('.btn').click(function() {
        var seq = $(this).data('seq'); // 클릭된 버튼의 시퀀스를 가져옴
        sendSequenceToServer(seq); // 시퀀스를 서버로 전송
    });
});

// 시퀀스를 서버로 전송
function sendSequenceToServer(seq) {
    $.ajax({
        type: "post", 
        url: "/detail.game", 
        data: { seq: seq }, 
    }).done(function(response){
    	var obj = JSON.parse(response);
    	//console.log(obj)
        var seq = obj.seq;
        //console.log("seq: " + seq);
        window.location.href = "/views/game/gameDetail.jsp?seq="+seq;
    });
}
</script>
    <script src="js/main.js"></script>
</body>
</html>