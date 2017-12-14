<%--
  Created by IntelliJ IDEA.
  User: c1yde3
  Date: 17-12-11
  Time: 下午8:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>登录</title>
  </head>
  <body>
  <form action="login.action" method="post">
    <input type="text" name="username" placeholder="输入用户名" />
    <br/>
    <input type="password" name="password" placeholder="输入密码" />
    <br />
    <input type="submit" value="登录">
    <input type="reset" value="重置">
    <div>
      <a href="register.jsp">还没有账号?点此注册</a>
    </div>
    <div>
      <a href="trainIndex.jsp">火车票</a>
    </div>
  </form>
  </body>
</html>
