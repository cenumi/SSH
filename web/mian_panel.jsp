<%--
  Created by IntelliJ IDEA.
  User: c1yde3
  Date: 2017/12/16
  Time: 14:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>欢迎使用12307</title>
    <link rel="stylesheet" href="layui/css/layui.css">

</head>
<body>
<%--<%!String data;%>--%>
<%
    //用户信息，列车次信息获取
//    if(request.getAttribute("user")!= null && !request.getAttribute("user").equals("")){
//        String username = (String) request.getAttribute("user");
//        out.print(username);//测试
//    }
//    if(request.getAttribute("data")!=null && !request.getAttribute("data").equals("")){
//        data = (String) request.getAttribute("data");
////        out.print(data);//测试
//    }
%>
    <div class="layui-layout-admin">

        <%--导航--%>
        <div class="layui-header">
            <div class="layui-logo">12307 购票网站</div>
            <ul class="layui-nav layui-layout-left">
                <li class="layui-nav-item layui-this"><a href="mian_panel.jsp">购票查询</a></li>
                <li class="layui-nav-item"><a href="">车次管理</a></li>
            </ul>
            <ul class="layui-nav layui-layout-right">
                <li class="layui-nav-item">
                    <a href="javascript:;">用户</a>
                    <dl class="layui-nav-child">
                        <dd><a href="">退出登陆</a></dd>
                    </dl>
                </li>
            </ul>

            <script src="layui/layui.js"></script>
            <script>
                //注意：导航 依赖 element 模块，否则无法进行功能性操作
                layui.use('element', function(){
                    var element = layui.element;

                    //…
                })
            </script>
        </div>

        <%--表单 --%>
        <div class="layui-field-box">
            <form class="layui-form" action="queryTrips.action" method="post">
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <div class="layui-form-label">出发站 <i class="layui-icon">&#xe715;</i></div>
                        <div class="layui-input-inline">
                            <input class="layui-input" type="text" name="startStation" required lay-verify="required" placeholder="汉字"/>

                        </div>
                    </div>
                    <div class="layui-inline">
                        <div class="layui-form-label">到达站 <i class="layui-icon">&#xe715;</i></div>
                        <div class="layui-input-inline">
                            <input class="layui-input" type="text" name="endStation" required lay-verify="required" placeholder="汉字" />
                        </div>
                    </div>
                    <div class="layui-inline">
                        <div class="layui-form-label">日期 <i class="layui-icon">&#xe637;</i></div>
                        <div class="layui-input-inline">
                            <input class="layui-input layui-inline" type="date" name="date">
                        </div>


                    </div>
                    <div class="layui-inline">
                        <div class="layui-input-inline">
                            <button class="layui-btn" lay-submit lay-filter="train_form">查询</button>
                            <button class="layui-btn" lay-filter="train_form">高级模式</button>
                        </div>
                    </div>
                    <%--高级选项--%>
                    <div class="layui-inline">
                        <div class="layui-input-inline">
                            <input class="layui-form-radio" name="advance" value="length" title="最短距离" >
                            <input class="layui-form-radio" name="advance" value="money" title="最少金额" >
                            <input class="layui-form-radio" name="advance" value="transfer" title="中转站" >
                        </div>
                    </div>
                </div>
            </form>

            <script>
                //Demo
                layui.use('form', function(){
                    var form = layui.form;

                    //监听提交
                    form.on('submit(train_form)', function(data){
                        layui.msg(JSON.stringify(data.field));
                    });
                });
            </script>
        </div>

        <%--表格 --%>
        <div class="layui-fluid">
            <table id="train_table"></table>
            <%--<script src="layui/layui.js"></script>--%>
            <script>
                var a = ${data}['data'];
                console.log(${data});
                layui.use('table',function () {
                    var table = layui.table;
                    table.render({
                        elem:'#train_table',
                        height:'400',
                        // url:'queryTrips.action',
                        data:a,
                        page:true,
                        cols:[[
                            {field: 'id', title: '车次', width:120, fixed: 'left'},
                            {field: 'departure_name', title: '出发站', width:140},
                            {field: 'arrive_name', title: '出发站', width:140},
                            {field: 'departure_time', title: '出发时间', width:120, sort: true},
                            {field: 'arrive_time', title: '到达时间', width:120,sort: true},
                            {field: 'spend', title: '历时', width: 140},
                            {field: 'class0', title: '商务座', width: 100},
                            {field: 'class1', title: '一等座', width: 100},
                            {field: 'class2', title: '二等座', width: 100},
                            {field: 'class3', title: '高级软卧', width: 100},
                            {field: 'class4', title: '软卧', width: 100},
                            {field: 'class5', title: '动卧', width: 100},
                            {field: 'class6', title: '硬卧', width: 100},
                            {field: 'class7', title: '软座', width: 100},
                            {field: 'class8', title: '硬座', width: 100},
                            {field: 'class9', title: '无座', width: 100}
                        ]]
                    })
                })



            </script>
        </div>
    </div>

</body>
</html>
