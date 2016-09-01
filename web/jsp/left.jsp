<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>超市账单管理系统</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link type="text/css" rel="stylesheet" href="../css/style.css" />
<script type="text/javascript">
	function logout() {
		top.location = "${pageContext.request.contextPath}/logout.do";
	}
</script>
</head>
<body class="frame-bd">
	<ul class="left-menu">
		<li><a href="${pageContext.request.contextPath}/bill.do" target="mainFrame">
			<img src="${pageContext.request.contextPath}/images/btn_bill.gif" />
		</a>
		</li>
		<li><a href="${pageContext.request.contextPath}/provider.do" target="mainFrame">
			<img src="${pageContext.request.contextPath}/images/btn_suppliers.gif" />
		</a>
		</li>
		<li><a href="${pageContext.request.contextPath}/user.do" target="mainFrame">
			<img src="${pageContext.request.contextPath}/images/btn_users.gif" />
		</a>
		</li>	
		<li><a href="${pageContext.request.contextPath}/jsp/updatePwd.jsp" target="mainFrame">
			<img src="${pageContext.request.contextPath}/images/btn_password.gif" />
		</a>
		</li>
		<li><a href="#" onClick="logout();">
			<img src="../images/btn_exit.gif" />
		</a>
		</li>
	</ul>
</body>
</html>
