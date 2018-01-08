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
    <script src="layui/layui.js"></script>
</head>
<body>

    <div class="layui-layout-admin">

        <%--导航--%>
        <div class="layui-header">
            <div class="layui-logo">12307 购票网站</div>
            <ul class="layui-nav layui-layout-left">
                <li class="layui-nav-item layui-this"><a href="mian_panel.jsp">购票查询</a></li>
                <li class="layui-nav-item"><a href="getAllTrips.action">车次管理</a></li>
            </ul>
            <ul class="layui-nav layui-layout-right">
                <li class="layui-nav-item">
                    <a href="javascript:;">用户</a>
                    <dl class="layui-nav-child">
                        <dd><a href="">退出登陆</a></dd>
                    </dl>
                </li>
            </ul>
        </div>

        <%--表单 --%>
        <div class="layui-field-box">
            <form class="layui-form"  method="post">
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
                            <button class="layui-btn"  lay-filter="train_form" onclick="normal()">查询</button>
                            <button class="layui-btn" lay-filter="train_form" onclick="advanced()">高级查询</button>
                        </div>
                    </div>

                </div>
            </form>
        </div>

        <%--表格 --%>
        <div class="layui-fluid" lay-filter="detail">
            <table id="train_table"></table>
        </div>
    </div>
<script>
    var layer;
    layui.use('element', function(){
        var element = layui.element;

        //…
    });
    layui.use('layer', function(){
        layer = layui.layer;

    });

    layui.use('form', function(){
        var form = layui.form;

        //监听提交
        form.on('submit(train_form)', function(data){
            layui.msg(JSON.stringify(data.field));
        });
    });

    layui.use('table',function () {
        var table = layui.table;
        table.render({
            elem:'#train_table',
            height:'400',
            data:${data}['data'],
            page:true,
            cols:[[
                {field: 'id', title: '车次', width:120, fixed: 'left',event: 'showDetail', style:'cursor: pointer;'},
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
        });
    });
    function normal() {
        var f = document.forms[0];
        f.action = 'queryTrips.action';
        f.submit();
    }
    function advanced() {
        var f = document.forms[0];
        f.action = 'getIndirectTrip.action';
        f.submit();
    }


</script>


</body>
</html>
