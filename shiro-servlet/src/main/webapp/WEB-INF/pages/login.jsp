<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/1/2 0002
  Time: 10:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>login</title>
</head>
<body>

<form action="${pageContext.request.contextPath}/login" method="post">
    <input type="text" id="username" name="username" /><br/>
    <input type="password" id="password" name="password" /><br/>
    <button type="submit" id="login">login</button>
</form>


</body>
</html>
