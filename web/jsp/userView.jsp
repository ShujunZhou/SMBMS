<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
	<div class="main">
		<div class="optitle clearfix">
			<div class="title">用户信息&gt;&gt;</div>
		</div>
			<div class="content">
				<table class="box"> 
					<tbody>
					<tr>
						<td class="field">用户账号：</td>
						<td>${user.userCode}</td>
					</tr>
					<tr>
						<td class="field">用户名：</td>
						<td>${user.userName}</td>
					</tr>
					<tr>
						<td class="field">用户性别：</td>
							<td>
								<c:if test = "${user.gender == 1}">男</c:if>
								<c:if test = "${user.gender == 2}">女</c:if>
							</td>
					</tr>
					<tr>
						<td class="field">出生日期：</td>
						<td>${user.birthday}</td>
					</tr>
					<tr>
						<td class="field">用户电话：</td>
						<td>${user.phone}</td>
					</tr>
					<tr>
						<td class="field">用户地址：</td>
						<td>${user.address}</td>
					</tr>
					<tr>
						<td class="field">用户权限：</td>
						<td>
							<c:if test="${user.userType == 1}">系统管理员</c:if>
							<c:if test="${user.userType == 2}">经理</c:if>
							<c:if test="${user.userType == 3}">普通用户</c:if>
						</td>
					</tr>
				</tbody>
				</table>
				<input type="button" value="<<返回" onclick="javascript:history.back()"/>
			</div>
	</div>
</body>
</html>