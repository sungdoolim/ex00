<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 로그인</title>
<link rel="stylesheet" type="text/css" href="./resources/css/admin.css"/>
<script src="./resources/js/jquery.js"></script><!--  현 폴더는 webapp으로 인식하는 것 같아...view가 아니고..? -->
<script type="text/javascript">
	function admin_check(){
		if($.trim($("#admin_id").val())==""){//trim은 jquery함수
			alert('관리자 아이디를 입력하세요!');
			$('#admin_id').val('').focus();// 입력값을 초기화하고 포커스를 이동
			return false;
			
		}
		if($.trim($('#admin_pwd').val())==''){
			alert('관리자 비번을 입력하세요 !');
			$('#admin_pwd').val('').focus();
			return false;
		}
	}

</script>
</head>
<body>
	<div id="aLogin_wrap">
		<h2 class="aLogin_title">관리자 로그인</h2>
		<form method="post" action="admin_login_ok" onsubmit="return admin_check();">
			<table id="aLogin_t">
				<tr>
					<th>관리자 아이디 </th>
					<td>
					<input type="text" name="admin_id" id="admin_id" size="14" tabindex="1"/>
					<%--type속성을 생략하면 기본 text , tabindex="1" : 키보드 tab을 눌렀을때 포커스를 가지게 되는 것 --%>
					</td>
					<th rowspan="2">
						<input type="submit" value="로그인"/>
					</th>
				</tr>
				<tr>
					<th>관리자 비번</th>
					<td>
						<input type="password" name="admin_pwd" id="admin_pwd" size="14" tabindex="2"/><%--tabindex="2"로 설정하면 탭키를 눌렀을때 2번째로 포커스를 가짐 --%>
					</td>
				</tr>
			</table>
		</form>
	</div>

</body>
</html>