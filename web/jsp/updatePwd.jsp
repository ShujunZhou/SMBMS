<%--
  Created by IntelliJ IDEA.
  User: alren
  Date: 16-8-24
  Time: 下午4:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
﻿<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Insert title here</title>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<div class="main">
    <div class="optitle clearfix">
        <div class="title">密码修改&gt;&gt;</div>
    </div>
        <input type="hidden" id="path" name="hidden" value="${pageContext.request.contextPath}"/>
        <div class="content">
        <table class="box">
            <tr>
                <td class="field">旧密码：</td>
                <td>
                    <input type="password" name="oldPwd" class="text" id="oldPwd"  />
                    <font color="red"></font>
                </td>
            </tr>
            <tr>
                <td class="field">新密码：</td>
                <td>
                    <input type="password" name="newPwd" class="text" id="newPwd"  />
                    <font color="red"></font>
                </td>
            </tr>
            <tr>
                <td class="field">重复密码：</td>
                <td>
                    <input type="password" name="rnewPwd" class="text" id="rnewPwd" />
                    <font color="red"></font>
                </td>
            </tr>
        </table>
    </div>
    <div class="buttons">
        <input type="button" id="updBtn" value="修改" class="input-button" />
    </div>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/modifyPsw.js"></script>
</body>
</html>
