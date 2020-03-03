/**
 * 자바스크립트 한줄이상 주석문 기호.
 * board.js
 */
function check(){
	if($.trim($("#writer").val())==""){
		// trim : 양쪽 공백 제거 -> writer의 value를 공백 제거했더니 ""라면!
		alert("글쓴이를 입력하세요!");
		$("#writer").val('').focus();//val('')입력박스값을 초기화 ,  focus는 입력박스로 포커스 이동
		return false;
		
	}
	if($.trim($('#title').val())==''){
		alert('제목을 입력 !');
		$('#title').val('').focus();
		return false;
	}
	if($.trim($('#content').val())==''){
		alert('글내용을 입력하세요!');
		$('#content').val('').focus();
		return false;
	}
	
}