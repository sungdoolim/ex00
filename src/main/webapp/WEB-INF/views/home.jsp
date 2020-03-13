<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Hello world!  
</h1>
<form action="doC" method="post">
<input type="Text" name="msg">
<input type="submit">

</form>
<P>  The time on the server is ${serverTime}. </P>
</body>
</html>
