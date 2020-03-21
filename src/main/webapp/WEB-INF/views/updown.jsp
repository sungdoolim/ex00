<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><%--jstl 코어 태그라이브러리  추가 --%>
<html>
<head>
	<title>io</title>
</head>
<c:forEach var="a" items="${io }">
<a href="/controller/downloadFile?name=${a }">${a }</a><br>

</c:forEach>



</body>
</html>
