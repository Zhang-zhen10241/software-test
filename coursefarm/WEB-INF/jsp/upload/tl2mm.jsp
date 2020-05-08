<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>TestLink2Freemind</title>
</head>
<body>
	选择要上传的文件（将TestLink的xml转换为Freemind的测试用例的*.mm文件）: 
	<form action="${pageContext.request.contextPath}/upload/submittl2mm" method="post" enctype="multipart/form-data">
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