<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">

    <link href="css/index.css" type="text/css" rel="stylesheet">
    <link href="css/top.css" type="text/css" rel="stylesheet">
    <link href="css/footer.css" type="text/css" rel="stylesheet">

    <script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>
</head>
<body>
<%@ include file="top.jsp" %>


<div id="main">
    <div  style="width:100%;text-align:center">
        <h1 style="text-align: center">欢迎来到注册页面！！</h1>
        <br/>
        <form action="/register?method=UserRegister" method="post">
            <input type="text" name="username" placeholder="用户名" style="width: 300px;height: 36px;text-indent:6px"><br/>
            性别：<input type="radio" name="gender" value="1" checked="checked"/>男
            <input type="radio" name="gender" value="0"/>女<br/>
            <input type="text" name="name" placeholder="真实姓名" style="width: 300px;height: 36px;text-indent:6px"><br/>
            <input type="text" name="telephone" placeholder="电话" style="width: 300px;height: 36px;text-indent:6px"><br/>
            <input type="text" name="cardid" placeholder="卡号" style="width: 300px;height: 36px;text-indent:6px"><br/>
            <input type="password" name="password" placeholder="用户密码" style="width: 300px;height: 36px;text-indent:6px"><br/>
            <input type="password" name="confirm" placeholder="确认密码" style="width: 300px;height: 36px;text-indent:6px"><br/>
            <input type="submit" value="注册" style="width: 300px;height: 36px">
        </form>
    </div>
</div>


<%@ include file="footer.jsp" %>
</body>
</html>


