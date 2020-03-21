<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>io</title>
</head>
<body>

<form name="uploadForm" method="post" action="uploadFile" enctype="multipart/form-data">
    <input type="file" name="imgFile">
    <input type="submit" value="등록">
</form>

<form name="downloadForm" method="post" action="downloadFile" enctype="multipart/form-data">

    <input type="submit" value="등록">
</form>


</body>
</html>
