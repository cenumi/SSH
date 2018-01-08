<%--
  Created by IntelliJ IDEA.
  User: c1yde3
  Date: 2018/1/8
  Time: 18:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>车次管理</title>
    <link rel="stylesheet" href="layui/css/layui.css">
    <script src="layui/layui.js"></script>
</head>
<body>

<div class="layui-layout-admin">

    <%--导航--%>
    <div class="layui-header">
        <div class="layui-logo">12307 购票网站</div>
        <ul class="layui-nav layui-layout-left">
            <li class="layui-nav-item"><a href="mian_panel.jsp">购票查询</a></li>
            <li class="layui-nav-item  layui-this"><a href="getAllTrips.action">车次管理</a></li>
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

    <%--表格 --%>
    <div class="layui-fluid" >
        <table id="train_table" lay-filter="detail"></table>
    </div>
    <%--表单--%>
        <form name="update" method="post" action="updateOneTrip.action">
            <input type="hidden"  name="id" id="id"/><br>
            <input type="hidden"  name="arrival" id="arrival"/><br>
            <input type="hidden"  name="date" id="date"/><br>
            <input type="hidden"  name="depature" id="depature"/><br>
            <input type="hidden"  name="number" id="number"/><br>
            <input type="hidden"  name="passby" id="passby"/><br>
            <input type="hidden"  name="ticket" id="ticket"/><br>
        </form>

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

    layui.use('table',function () {
        var table = layui.table;
        table.render({
            elem:'#train_table',
            height:'400',
            data:${data}['data'],
            page:true,
            cols:[[
                {field: 'number', title: '车次', width:120, fixed: 'left',event: 'showDetail', style:'cursor: pointer;'},
                {field: 'depature', title: '出发时间', width:120, sort: true,edit:'text'},
                {field: 'arrival', title: '到达时间', width:120,sort: true,edit:'text'},
                {field: 'day', title: '日期', width:120,edit:'text'},
                {field: 'passby', title: '经过站',edit:'text'},
                {field: 'ticket', title: '余票',width:400,edit:'text'}
            ]]
        });
        table.on('edit(detail)',function (obj) {

            var d = obj.data;
            update(d['id'],d['arrival'],d['day'],d['depature'],d['number'],d['passby'],d['ticket']);
            
        })

    });

    function update(id,arrival,date,depature,number,passby,ticket) {
        document.getElementById('id').value = id;
        document.getElementById('arrival').value = arrival;
        document.getElementById('date').value = date;
        document.getElementById('depature').value = depature;
        document.getElementById('number').value = number;
        document.getElementById('passby').value = passby;
        document.getElementById('ticket').value = ticket;

        var f = document.forms[0];
        f.action = 'updateOneTrip.action';
        f.submit();
        alert('修改完成！')
        // f.action = 'updateOneTrip.action?id='+id+'arrival'+arrival+'date'+date+'depature'+depature+'number'+number+'passby'+passby+'ticket'+ticket;
    }

</script>



</body>
</html>
