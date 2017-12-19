<%--
  Created by IntelliJ IDEA.
  User: wangyonghao8
  Date: 2017/12/12
  Time: 19:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>首页</title>
</head>
<body>
<%
    if(request.getAttribute("user") != null && !request.getAttribute("user").equals("")){
        out.print("<h2>欢迎你，"+ request.getAttribute("user")+"登陆</h2>");
    }else{
        out.print("<h2>欢迎你，陌生人</h2>");
    }
%>
<form action="queryTrips.action" method="post">
    <input type="text" name="startStation" placeholder="出发地"/>
    <br/>
    <input type="text" name="endStation" placeholder="目的地"/>
    <br/>
    <input type="text" name="date" placeholder="时间"/>
    <br/>
    <input type="submit" value="查询">
    <input type="reset" value="重置">
    <div>
        <a href="index.jsp">返回index去注册</a>
    </div>
</form>
</body>
</html>
