<%@ page language="java" pageEncoding="UTF-8"%>  
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  

<form action="<%=request.getContextPath()%>/tools/mm2tl" method="POST" enctype="multipart/form-data">  
<!--     username: <input type="text" name="username"/><br/>  
    nickname: <input type="text" name="nickname"/><br/>  
    password: <input type="password" name="password"/><br/>  
    yourmail: <input type="text" name="email"/><br/>  
     -->
    选择要上传的文件: <input type="file" name="myfiles"/><br/>  
    <input type="submit" value="将【freemind思维导图】转成【testlink导入的用例】"/>  
</form>  

<br/>

<c:forEach items="${mm2tlList}" var="mm2tlBean">  
    ${mm2tlBean.value.fileName}----${mm2tlBean.value.fileUrl}-----${mm2tlBean.value.lastModified}
        <a href="<%=request.getContextPath()%>/upload/mm2tlList/${user.value.fileName}">下载</a>  
     <!--    
     	<a href="<%=request.getContextPath()%>/user/${user.value.username}/update">编辑</a>  
        <a href="<%=request.getContextPath()%>/user/${user.value.username}/delete">删除</a>  
         -->
    <br/>  
</c:forEach>  
