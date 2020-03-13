<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><%--jstl 코어 태그라이브러리  추가 --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>스프링 MVC 게시판 목록</title>
</head>
<body>

 <table border="1">
  <tr>
   <th colspan="5">스프링 mvc게시판 목록</th>
  </tr>
  <tr>
  <td colspan="5" align="right"> 총 게시물수 : <b>${totalCount }</b>개
  </tr>
  <tr>
	<th>번호</th>	<th>제목</th>	<th>이름</th>	<th>조회수</th><th>등록날짜</th>
  </tr>
  <c:if test="${!empty blist }">
   <c:forEach var= "b" items="${blist }">
   <tr>
    <th>${b.bno }</th>
  <th> 
   <a href="/controller/board/board_cont?bno=${b.bno }&page=${page}">${b.title }</a>
    </th>
    <th>${b.writer }</th>
    <th>${b.viewcnt }</th>
    <th>${b.regdate }</th>
  </tr></c:forEach></c:if>
  <c:if test="${empty blist }">
  <tr>
   <th colspan="5">게시판 목록이 없습니다!</th></tr></c:if>
   
   
   <%--페이징 추가 --%>
   
   <tr>
   		<th colspan="5">
   			<c:if test="${page<=1 }">
   				[이전]&nbsp;
   			</c:if>
   			<c:if test="${page>1 }">
   				<a href="/controller/board/board_list?page=${page-1 }">[이전]</a>&nbsp;
   			</c:if>
   			
   			<%--현재쪽 번호 출력 --%>
   			<c:forEach var="a" begin="${startpage }" end="${endpage }" step="1">
   				<c:if test="${a==page }"><%--현재 쪽번호가 선택된 경우는 링크 없어도됨 --%>
   					 <${a }>
   				</c:if>
   				<c:if test="${a!=page}">
   					<a href="/controller/board/board_list?page=${a }">[${a }]</a>&nbsp;
   					
   				</c:if>
   			</c:forEach>
   			<c:if test="${page>=endpage }"><!-- maxpage =>endpage로바꾸면..? -->
   			[다음]
   			</c:if>
   			<c:if test="${page<endpage }">
   			<a href="/controller/board/board_list?page=${page+1 }">[다음]</a>
   			</c:if>
   			
   		</th>
   </tr>
   
   
   <tr>
    <td colspan="5" align="right">
     <input type="button" value="글쓰기"
     onclick="location='/controller/board/board_write?page=${page}';"/><%--js를 통한 이동 --%>
     <%-- 책갈피 기능 구현을 위해 page를 전달   --- 나는 session만들어서 쓸것 같은데.... --%>
    </td>
   </tr>
 </table>
 <script>
 var msg='${msg}'; //s에서 el로 값을 받아 올수 있음  - board_write_ok에서 전달 할껄? , 문자열을 처리하게 됨 , jquery가 아님!!!! jquery였다면 $msg='${msg}'     좌값이 jquery
		 if(msg=="SUCCESS"){// ==    이 가능하네.?			 js에서는 ' "  구분이 없음                                                    jquery였다면 $msg=="SUCCESS"
			 alert('게시물 처리에 성공했습니다');  // 수정해도 이거 뜬다!
		 }
 </script>


</body>
</html>