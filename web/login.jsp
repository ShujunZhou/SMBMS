<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>系统登录 - 超市账单管理系统</title>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" /><%--通过EL表达式获取绝对路径--%>
<script type="text/javascript">
  function validate(){
		<%--window.location.href="${pageContext.request.contextPath}/jsp/frame.jsp";--%>
	  //验证
	  var userCode = document.getElementById("userCode").value;
	  var userPassword = document.getElementById("userPassword").value;
	  var loginNameSpan = document.getElementById("loginNameSpan");
	  var passwordSpan = document.getElementById("passwordSpan");

	  var flag = true;
	  if (userCode == "" || userCode == null) {
		  loginNameSpan.innerHTML = "请输入姓名";
		  loginNameSpan.style.color = "red";
		  flag = false;
	  }
	  if (userPassword == "" || userPassword == null) {
		  passwordSpan.innerHTML = "请输入密码";
		  flag = false;
	  }
	  //提交
	  if (flag) {
		  var actionForm = document.getElementById("actionForm");
		  actionForm.submit();
	  }
	}
  </script>
</head>
<body class="blue-style">
<div id="login">
	<div class="icon"></div>
	<div class="login-box">
		<form  action="${pageContext.request.contextPath}/login.do"  name="actionForm" id="actionForm"  method="post" >
			<dl>
				<dt>用户名：</dt>
				<dd><input type="text" class="input-text" name="userCode" id="userCode" value=""/> <span id="loginNameSpan"></span></dd>
				<dt>密&nbsp;码：</dt>
				<dd><input type="password"  class="input-text" name="userPassword" id="userPassword" value=""/><span id="passwordSpan"></span></dd>
			</dl>
			<div class="buttons">
				<span color="red">${error}</span>
				<input type="button"   value="登录系统" class="input-button" onclick="validate();" />
				<input type="reset"  value="重　　填" class="input-button" />
			</div>
		</form>
	</div>
</div>
</body>
</html>
