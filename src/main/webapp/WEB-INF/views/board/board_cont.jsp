<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시물 내용보기</title>
<script src="../resources/js/jquery.js"></script><!-- jquery써야지!, 현 폴더가 views인건가...? -->
<style>
#modDiv{/*댓글 ui 그리기*/
	width:300px;
	height:100px;
	background-color:gray;
	position:absolute; /*절대 위치 */
	top:35%;
	left:30%;
	padding:10px;/*상하좌우 안 여백을 동시에 10px 지정*/
	z-index:1000; /*position 속성을 absolute 나 fixed로 성정된 곳에서 사용하며, 요소가 겹쳐지느 순서 제어 --- 먼저 표시되는 우선순위 설정이지... 값이 작은게 먼저 표시되어 있는 것*/
	
	
}


</style>

</head>
<body>
<table border="1">
<tr>
	<th colspan="2">
	스프링 게시판 내용보기
	</th>
</tr>
<tr>
	<th>글쓴이</th> <td>${b.writer }</td><%--boardcontroller cont에서 넘어오는 b객체를 사용 --%>
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
<br><hr><br>

<%--댓글 수정 화면 --%>
<div id="modDiv" style="display:none;">
	<div class="modal-title"></div><%--댓글 번호 --%>
	<div><%--div : 가로줄 전체를 차지한느 블록 요소 태그이고 레이아웃 그릴때 많이 사용 --%>
		<textarea rows="3" cols="30" id="replytext"></textarea>
	</div>
	<div>
		<button type="button" id="replyModBtn">댓글 수정</button>
		<button type="button" id="replyDelBtn">댓글 삭제</button>
		<button type="button" id="closeBtn" onclick="modDivClose();">닫기</button>
	</div>
</div>


<%--댓글 등록 화면 --%>
<h2>ajax 댓글 등록</h2>
<div>
	<div>
		댓글 작성자 : <input type="text" name="replyer" id="newReplyWriter"/>
	</div>
	<br>
	<div>
		댓글 내용 : <textarea rows="5" cols="30" name="replytext" id="newReplyText"></textarea>
	</div>
	<br>
	<button id="replyAddBtn">댓글 등록</button>
</div>
	
<br><hr>

[댓글 개수: <b>${b.replycnt }</b> 개]
<br>


	<%--댓글 목록 --%>
<ul id="replies"></ul>

<script>
	var bno=${b.bno};//js에서는 jstl/el을 사용할수 있다
	
	getAllList();
	function getAllList(){
		$.getJSON("/controller/replies/all/"+bno,function(data){//얘도 많이 사용함!!!!!
			//get방식으로 json데이터를 얻어올때 사용하는 jquery ajax함수, 받아오는 것이 성공하면 data에 값이 저장되고, $는jquery라는 뜻이고 
			var str="";
			console.log(data);
			// 웹에서 페이지 검사 -> 콘솔    0: {rno: 8, bno: 29, replyer: "이순신", replytext: "댓글내용", regdate: "2020-03-14 15:26:27.0", …}
			//						1: {rno: 2, bno: 29, replyer: "댓글작성자", replytext: "댓글내용", regdate: "2020-03-14 14:42:07.0", …}

			$(data).each(function(){// jquery의 each함수는 댓글 목록을 반복해주는 것// forEach느낌이....
				str += "<li data-rno='"+this.rno+"' class='replyLi'>"
				+ this.rno+ " : <span class='com' style='color:blue;font-weight:bold;'>"+ this.replytext+"</span>"
				+" <button>댓글수정</button></li><br/>";		
			});
			$('#replies').html(str);//jquery html함수로 str을 replies에 뿌리는 것임
		});
	}
	// 댓글 추가
	
	
	
	$('#replyAddBtn').on('click',function(){
		var replyer =$('#newReplyWriter').val();//value를 가져오는 것
		var replytext=$('#newReplyText').val();
		
		
		$.ajax({// ajax를 사용해보자 - get/post방식을 처리 , 많이 사용함!!!!! getjson보다 훨씬 많이 쓰나봐용....
			type:'post', // 서버를 자료를 보내는 형식
			url:'/controller/replies',
			headers:{
				"Content-Type":"application/json",
				"X-HTTP-Method-Override":"POST"
			},
			dataType:"text",
			data:JSON.stringify({
				bno:bno,//key:value쌍의 json데이터로 전송    지금 bno는 29라고 위에 지정해놨음
				replyer:replyer,
				replytext:replytext
			}),
			success:function(result){//ajax로 받아오는 것이 성공되면 호출되는 콜백 함수, 받아온 자료는 result 매게변수에 저장
				if(result=='success'){
					alert('댓글이 등록되었습니다!');
					location.reload();// 자바스크립트 location의 reload메서드는 새로고침 기능을 가짐 - 댓글 수 증가에 필요 - MODIV를 새로고침!
					getAllList();
				}
			}
		});		
	});
	
	//댓글 수정화면
	$('#replies').on('click',".replyLi button",function(){//replies내에 클래스를 눌렀을때????
		var reply=$(this).parent();
		var rno=reply.attr("data-rno");//attr("data-rno") : data-rno속성값을 가져온다 = 댓글 번호를 가져온다 // li태그내에 좀 봐바바ㅏ바바
		var replytext=reply.text();//댓글 내용  
		$('.modal-title').html(rno);//댓글 번호를 변경 적용\
		$('#replytext').val(replytext);// 댓글 내용
		$('#modDiv').show('slow');//jquery show()함수로 천천히 해당 영역을 보이게함 / 지금은 none
	});
	
	// 댓글 수정 화면 닫기
	function modDivClose(){
		$('#modDiv').hide('slow');
		
	}
	
	
	//댓글 수정 완료
	$('#replyModBtn').on('click',function(){
		var rno=$('.modal-title').html();// 댓글 번호 가져오기
		var replytext=$('#replytext').val();
		$.ajax({
			type:'put',
			url: '/controller/replies/'+rno,
			headers:{
				"Content-Type":"application/json",
				"X-HTTP-Method-Override":"PUT"
			},
			data:JSON.stringify({
				replytext:replytext,
			}),
			dataType:'text',
			success:function(result){
				if(result=='success'){
					alert('댓글이 수정되었습니다');
					$('#modDiv').hide('slow');
					getAllList();
				}
			}
		});
	});
	
	
	//댓글 삭제 완료
	$('#replyDelBtn').on('click',function(){
		var rno=$('.modal-title').html();
		$.ajax({
			type:'delete',
			url:'/controller/replies/'+rno,
			headers:{
				"Content-Type":"application/json",
				"X-HTTP-Method-Overriede":"DELETE"
			},
			dataType:"text",
			success:function(result){
				if(result=="SUCCESS"){
				alert('댓글 삭제에 성공했습니다!');
				
				$('#modDiv').hide('slow');
				location.reload();
				getAllList();}
			}
		});
	});
	

</script>




	
	

</body>
</html>