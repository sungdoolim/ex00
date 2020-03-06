<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시물 내용보기</title>
</head>
<body>
<table border="1">
<tr>
	<th colspan="2">
	스프링 게시판 내용보기
	</th>
</tr>
<tr>
	<th>제목</th> <td>${b.title }</td><%--boardcontroller cont에서 넘어오는 b객체를 사용 --%>
</tr>
<tr>
	<th>글내용</th><td>${b.content }</td>
</tr>
<tr>
	<th>조회수</th><td>${b.viewcnt }</td>
</tr>
<tr>
	<th colspan="2">
		<input type="button" value="수정"
		onclick="location='/controller/board/board_edit?bno=${b.bno}&page=${page }';"/>
		<%--get방식으로 전달 --%>
		<input type="button" value="삭제"
		onclick="location='/controller/board/board_del?bno=${b.bno}';"/>
		<input type="button" value="목록"
		onclick="location='/controller/board/board_list?page=${page}';"/><!-- sessionScope.page도 됨 -->
		
	</th>
</tr>


</table>

</body>
</html>