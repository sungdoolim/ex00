<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 메인화면</title>
<link rel="stylesheet" href="./resources/css/admin.css"/>
</head>
<body>
	<div id="aMain_wrap">
		<%--관리자 메인상당 --%>
		<div id="aMain_header">
			<div id="aMain_logo">
				<a href="admin_main">
					<img src="./resources/images/admin/admin_logo.png"/><%--img src="그림파일 경로" --%>
				</a>
			</div>
			
			<!-- 관리자 상단 메뉴 -->
			<div id="aMain_menu">
				<ul>
				<li><a href="#">공지사항</a></li>
				<li><a href="#">게시판</a></li>
				<li><a href="#">자료실</a></li>
				<li><a href="#">회원 관리</a></li>
				
				</ul>
			</div>
			
			<%--관리자 메인 우측메뉴 --%>
			<div id="aMain_right">
			<form action="admin_logout" method="post">
			<h3 class="aRight_title">${admin_name }님 로그인을 환영합니다
			<input type="submit" value="로그아웃"/></h3>
			
			</form>
			</div>
		</div>
		<div class="clear"></div>
		
		<%--관리자 메인 본문 --%>
		<div id="aMain_cont">
			<h2 class="aMainCont_title">관리자 메인 화면입니다.</h2>
		</div>
		
		<div class="clear"></div>
		
		<%--관리자 메인 하단 --%>
		<div id="aMain_footer">
			<h2 class="aMainFooter_title">경기도 김포시 김포대로 staris TEL)010-1234-5678</h2>
		</div>
	</div>

</body>
</html>