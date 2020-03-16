<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Welcome to FunWeb</title>
<link rel="stylesheet" type="text/css" 
href="/resources/css/main.css" />
<%-- jsp주석문형태, jsp주석문은 웹브라우저 소스보기에서
안보인다. 외부 스타일(css)을 불러오는 방법. --%>
<link rel="stylesheet" type="text/css" 
href="/resources/css/welcome.css" />
<link rel="stylesheet" type="text/css" 
href="/resources/css/notice.css" />
<link rel="stylesheet" type="text/css" 
href="/resources/css/member.css" />
<link rel="stylesheet" type="text/css" 
href="/resources/css/bbs.css" />
<script src="/resources/js/jquery.js"></script>
<script src="/resources/js/bbs.js"></script>
</head>
<body>
 <div id="wrap">
  <header><%--header는 html5에 추가된 태그로 머릿상단영
  영역을 지정할 때 사용한다. --%>
  <div id="login">
   <a href="member_login">login</a> | <a href="member_join">Join</a>
  </div>
  <div class="clear"></div>
  <%--회사로고--%>
  <div id="logo">
  <a href="main">
   <img src="/resources/images/logo.gif" 
   width="265" height="62"
   alt="Fun Web" /></a>   
  </div>
  <nav><%--nav는 메뉴구성할 때 사용하는 html5에서 추가된
  태그명 --%>
   <ul>
    <li><a href="main">HOME</a></li>
    <li><a href="#">COMPANY</a></li>
    <li><a href="#">SOLUTION</a></li>
    <li><a href="bbs_write">CUSTOMER CENTER</a></li>
    <li><a href="#">CONTACT US</a></li>
   </ul>
  </nav>
  </header>
  
  <div class="clear"></div>
  
  
  
  
  