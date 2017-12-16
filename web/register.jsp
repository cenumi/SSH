<%--
  Created by IntelliJ IDEA.
  User: c1yde3
  Date: 17-12-11
  Time: 下午9:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>register</title>
    <link rel="stylesheet" href="layui/css/layui.css">
    <link rel="stylesheet" href="layui/css/ssh.css">
</head>
<body>
    <div class="layui-container center">
        <form class="layui-form" action="register.action" method="post">
            <div class="layui-form-item">
                <div class="layui-input-inline">
                    <input class="layui-input" type="text" name="username" required lay-verify="required" placeholder="输入用户名" />
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-input-inline">
                    <input class="layui-input" type="password" name="password" required lay-verify="required" placeholder="请输入密码" />
                </div>
            </div>

            <div class="layui-form-item">
                <div class="layui-input-inline">
                    <input class="layui-input" type="password" name="password_again" required lay-verify="required" placeholder="请再次输入密码" />
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-input-inline">
                    <button class="layui-btn" lay-submit lay-filter="register_form">注册</button>
                    <button type="reset" class="layui-btn layui-btn-primary">重置</button>
                </div>
            </div>
        </form>
        <script src="layui/layui.js"></script>
        <script>
            //Demo
            layui.use('form', function(){
                var form = layui.form;

                //监听提交
                form.on('submit(register_form)', function(data){
                    var rs = JSON.stringify(data.field);
                    return rs['password'] === rs['password_again'];
                });
            });
        </script>
    </div>

</body>
</html>
