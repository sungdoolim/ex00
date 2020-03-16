<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자료실 답변폼</title>
<link rel="stylesheet" type="text/css" 
href="/resources/css/bbs.css" />
<script src="/resources/js/jquery.js"></script>
<script src="/resources/js/bbs.js"></script>
</head>
<body>
 <div id="bsW_wrap">
  <h2 class="bsW_title">자료실 답변글</h2>
  <form method="post" action="bbs_reply_ok"
  onsubmit="return write_check();"  >
  <%--답변글 히든값 --%>
  <input type="hidden" name="bbs_ref" value="${b.bbs_ref}" />
  <input type="hidden" name="bbs_step" value="${b.bbs_step}"/>
  <input type="hidden" name="bbs_level" 
  value="${b.bbs_level}" />
  <%--페이징 쪽번호 히든값 --%>
  <input type="hidden" name="page" value="${page}" />
  <table id="bsW_t">
   <tr>
    <th>이름</th>
    <td><input name="bbs_name" id="bbs_name" size="14" /></td>
   </tr>
   <tr>
    <th>제목</th>
    <td><input name="bbs_title" id="bbs_title" size="33" 
    value="Re:${b.bbs_title}"/></td>
   </tr>
   <tr>
    <th>비밀번호</th>
    <td><input type="password" name="bbs_pwd" id="bbs_pwd"
    size="14" /></td>   
   </tr>
   <tr>
    <th>글내용</th>
    <td><textarea name="bbs_cont" id="bbs_cont" rows="8"
    cols="34"></textarea></td>
   </tr>  
  </table>
  <div id="bsW_menu">
   <input type="submit" value="답변" />
   <input type="reset" value="취소" 
   onclick="$('#bbs_name').focus();" >
   <input type="button" value="목록" 
   onclick="location='bbs_list?page=${page}';" >
  </div>
  </form>
 </div>
</body>
</html>



















