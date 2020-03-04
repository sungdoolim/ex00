<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><%--jstl 코어 태그라이브러리  추가 --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>스프링 게시판 글쓰기 폼</title>
<script type="text/javascript" src="../resources/js/jquery.js"></script>
<%-- type은 생략 가능= default=js , 외부 라이브러리 읽어오기 --%>
<script src="../resources/js/board.js"></script>

</head>
<body>
<form method="post" action="board_write_ok" 
onsubmit="return check();"><!-- 이벤트 핸들러 : 데이터가 서버로 전송된 것을 처리 -> check()메서드를 호출! JS에서 정의한 함수 -->
	<table border="1"><!-- border가 커지면 두꺼워짐 -->
		<tr>
			<th><!-- td와 다르게 중앙정렬하고 진하게 출력 -->
				글쓴이
			</th>
			<td>
				<input type="text" name="writer" id="writer" size="14"/>
				<!-- 입력된 값은 name에 저장되어 서버로 전달되고, size는 입력 박스 길이,  -->
			</td>
		</tr>
		<tr>
			<th>제목</th>
			<td><input name="title" id="title" size="36"/></td>
			<!-- type default는 text -->
		</tr>
		<tr>
			<th>글내용</th>
			<td><textarea name="content" id="content" rows="10" cols="36"></textarea></td>					
		</tr>
		<tr>
			<th colspan="2"><!--  위에 보면 tr내에 두개 태그가 있는데 하나로 합쳐버리자! -->
				<input type="submit" value="저장"/>
				<input type="reset" value="취소"/>
				<input type="button" value="목록" onclick="location='/controller/board/board_list';"/>
				<!-- 클릭시에 location이동  -->
			</th>
			
		</tr>
		
		
	</table>

</form>


</body>
</html>