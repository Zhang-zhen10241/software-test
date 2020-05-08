<%@ page language="java" pageEncoding="UTF-8"%>  
<form action="<%=request.getContextPath()%>/tools/tl2mm" method="POST" enctype="multipart/form-data">  
    username: <input type="text" name="username"/><br/>  
    nickname: <input type="text" name="nickname"/><br/>  
    password: <input type="password" name="password"/><br/>  
    yourmail: <input type="text" name="email"/><br/>  
    选择要上传的文件: <input type="file" name="myfiles"/><br/>  
    <input type="submit" value="将【testlink导出的用例】转成【freemind思维导图】"/>  
</form>  

<br/>
<!-- 
<c:forEach items="${users}" var="user">  
    ${user.value.username}----${user.value.nickname}----${user.value.password}----${user.value.email}  
        <a href="<%=request.getContextPath()%>/user/${user.value.username}">查看</a>  
        <a href="<%=request.getContextPath()%>/user/${user.value.username}/update">编辑</a>  
        <a href="<%=request.getContextPath()%>/user/${user.value.username}/delete">删除</a>  
    <br/>  
</c:forEach>  
 -->