<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: wangyonghao8
  Date: 2017/12/12
  Time: 19:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>所有车次展示</title>
</head>
<body>
<table align="center" border="1px" cellspacing="0" bordercolor="#333333">
    <tr style="background-color: #1D81C5">
        <th>车次</th>
        <th>出发站到达站</th>
        <th>出发、到达时间</th>
        <th>历时</th>
        <th>商务特等座</th>
        <th>一等座</th>
        <th>二等座</th>
        <th>高级软卧</th>
        <th>软卧</th>
        <th>动卧</th>
        <th>硬卧</th>
        <th>软座</th>
        <th>硬座</th>
        <th>无座</th>
    </tr>
    <%
        List<String[]> lists = (List<String[]>) request.getAttribute("tripLists");
        if (lists != null) {
            //行处理
            int i = 0;
            for (String[] trip : lists) {
                //样式
                if (i % 2 == 0) {
                    out.print("<tr style=\"background-color:#EEF2F8\">");
                } else {
                    out.print("<tr>");
                }
                i++;
                //表格处理
                for (String string : trip) {
                    out.print("<td>" + string + "</td>");
                }
                out.print("</tr>");
            }
        }
    %>
</table>

<%
    if (lists == null) {
        out.print("sorry! What a pity!");
    }
%>

<form action="getIndirectTrip" method="post">

    <input name="startStation" value="嘉兴" type="text">
    <input name="endStation" value="北京" type="text">
    <input name="date" value="2017/10/12" type="text">
    <input type="submit" value="提交">
</form>
</body>
</html>
