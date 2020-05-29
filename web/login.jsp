<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录</title>
</head>

<link href="images/search.gif" type="img/x-ico" rel="icon" />
<link href="css/login.css" type="text/css" rel="stylesheet">
<script type="text/javascript" src="<%=path%>/js/jquery-3.3.1.min.js"></script>
<%--在同一个form里面分别为登录和注册创建2个点击事件，所以创建2个函数。--%>
<script type="text/javascript">
	function a() {
		var form = document.getElementById("twoButton");
		form.action = "/login";
		form.submit();
	}
	function b() {
		var form = document.getElementById("twoButton");
		form.action = "/register";
		form.submit();
	}
</script>

<body>
	<div id="top"></div>

	<div id="main">
		<img src="images/login.jpg" id="main_bg"/>
		<div id="login_block">

			<form <%--action="/login"--%> method="post" id="twoButton">
				<table border="0">
					<tr>
						<td class="title">用户名:</td>
						<td class="input">
							<%--key就是input 里面的name属性的值。--%>
							<input type="text" name="username" id="username" class="login_input"/>
						</td>
					</tr>
					<tr>
						<td class="title">密码:</td>
						<td class="input">
							<%--key就是input 里面的name属性的值。--%>
							<input type="password" name="password" id="password" class="login_input"/>
						</td>
					</tr>
					<tr>
						<td class="title">用户类型:</td>
						<td class="input">
							<input type="radio" name="type" value="reader" checked="checked"/>&nbsp;&nbsp;读者&nbsp;&nbsp;
							<input type="radio" name="type" value="admin"/>&nbsp;&nbsp;管理员
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<input class="btn" type="submit" value="登录" onclick="a()"/>  <%--点击事件函数a()--%>
							<input class="btn" type="submit" value="注册" onclick="b()"/>  <%--点击事件函数b()--%>
							<%--<div class="btn" id="reset">注&nbsp;&nbsp;册</div>--%>
						</td>
					</tr>
				</table>
			</form>

		</div>
	</div>

	<div id="footer">
		<div class="foot_content">
			CopyRight &copy; 2020 www.htl.com 淮安市阿里巴巴有限公司
		</div>
		<div class="foot_content">
			版权所有 Library V2.0
		</div>
	</div>

</body>
</html>