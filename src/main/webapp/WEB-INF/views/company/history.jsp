<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="../include/header.jsp" %>
<%-- 상단부분 외부 포함파일 불러오는 형식 --%>

<%--서브 메인 이미지 --%>
<div id="sub_img"></div>

<%--서브메뉴 --%>
<div id="sub_menu">
 <ul>
  <li><a href="welcome.jsp">welcome</a></li>
  <li><a href="history.jsp">History</a></li>
  <li><a href="#">Newsroom</a></li>
  <li><a href="#">Public Policy</a></li>
 </ul>
</div>

<%--본문내용 --%>
<div id="wel_cont">
  <%-- copy begin --%>
  <h1 class="h_title">History</h1>
    <div class="y2017"> 
    <h3> 2017 </h3>
    <dl>
        <dt>may</dt>
        <dd>Lorem ipsum dolor sit amet, consectetur adipiscing elit</dd>
        <dd>Duis eu ipsum nisl. Duis posuere fringilla nunc quis </dd>
        <dd>Donec mollis dapibus risus volutpat mattis. </dd>
     </dl>
     <dl>  
        <dt>apr</dt>
        <dd>Cras felis lectus, gravida ac tincidunt eget</dd>
        <dd>Duis eu ipsum nisl. Duis posuere fringilla nunc quis </dd>
        <dd>Donec mollis dapibus risus volutpat mattis. </dd>
     </dl>
     <dl class="dot_none">   
        <dt>jan</dt>
        <dd>Fusce scelerisque dictum magna eget viverra.</dd>
    </dl>
    
    <div class="clear"></div>
    </div>
    
   <div class="y2016"> 
    <h3> 2016 </h3>
    <dl>
        <dt>dec</dt>
        <dd>Lorem ipsum dolor sit amet, consectetur adipiscing elit</dd>
        <dd>Duis eu ipsum nisl. Duis posuere fringilla nunc quis </dd>
        <dd>Donec mollis dapibus risus volutpat mattis. </dd>
     </dl>
     <dl>  
        <dt>nov</dt>
        <dd>Cras felis lectus, gravida ac tincidunt eget</dd>
        <dd>Duis eu ipsum nisl. Duis posuere fringilla nunc quis </dd>
        <dd>Donec mollis dapibus risus volutpat mattis. </dd>
     </dl>
     <dl class="dot_none">   
        <dt>jan</dt>
        <dd>Fusce scelerisque dictum magna eget viverra.</dd>
    </dl>
    
    <div class="clear"></div>
    </div>
    
  <div class="y2015"> 
    <h3> 2015 </h3>
    <dl>
        <dt>Jul</dt>
        <dd>Lorem ipsum dolor sit amet, consectetur adipiscing elit</dd>
        <dd>Duis eu ipsum nisl. Duis posuere fringilla nunc quis </dd>
        <dd>Donec mollis dapibus risus volutpat mattis. </dd>
     </dl>
     <dl>  
        <dt>jun</dt>
        <dd>Cras felis lectus, gravida ac tincidunt eget</dd>
        <dd>Duis eu ipsum nisl. Duis posuere fringilla nunc quis </dd>
        <dd>Donec mollis dapibus risus volutpat mattis. </dd>
     </dl>
     <dl class="dot_none">   
        <dt>jan</dt>
        <dd>Fusce scelerisque dictum magna eget viverra.</dd>
    </dl>
   
    <div class="clear"></div>
    </div>    
  <%-- copy end --%>
</div>

<jsp:include page="../include/footer.jsp" />














