<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Freemind2TestLink</title>
</head>
<body>
	选择要上传的文件（将Freemind转换为TestLink的测试用例的xml）: 
	<form action="${pageContext.request.contextPath}/upload/submitmm2tl" method="post" enctype="multipart/form-data">
		<input type="file" name="image" /><br/>
		<input type="submit" /><br/>
	</form>
	<hr/>
	<!--  屏蔽掉多文件上传
	<form action="${pageContext.request.contextPath}/upload/submits" method="post" enctype="multipart/form-data">
		<input type="file" name="images" /><br/>
		<input type="file" name="images" /><br/>
		<input type="submit" /><br/>
	</form>
	 -->
</body>
</html>