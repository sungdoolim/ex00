<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="../include/admin_header.jsp" %>

<div id="aMain_cont">
 <div id="aBc_wrap">
  <h2 class="aBc_title">관리자 공지 내용</h2>
  <table id="aBc_t">
   <tr>
    <th>제목</th> <td>${g.gongji_title}</td>
   </tr>
   <tr>
    <th>내용</th> <td>${g_cont}</td>
   </tr>
   <tr>
    <th>조회수</th> <td>${g.gongji_hit}</td>
   </tr>
  </table>
  <div id="aBc_menu">
  <input type="button" value="목록"
  onclick="location='admin_gongji_list?page=${page}';" />  
  </div>
 </div>
</div>

<%@ include file="../include/admin_footer.jsp" %>








