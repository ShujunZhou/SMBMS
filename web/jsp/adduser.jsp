<%@page contentType="text/html;charset=UTF-8" language="java" %>
﻿<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" />
</head>
<body>
<div class="main">
    <div class="optitle clearfix">
        <div class="title">用户管理&gt;&gt;</div>
    </div>
    <form id="userform" name="userform" method="post" action="${pageContext.request.contextPath}/useradd.do">
        <input type="hidden" name="path" value="${pageContext.request.contextPath}" id="path">
        <div class="content">s
            <table class="box">
                <tbody>
                <tr>
                    <td class="field">用户账号：</td>
                    <td>
                        <input type="text" name="userCode" class="text" id="userCode" value="">
                        <font color="red"></font>
                    </td>
                </tr>
                <tr>
                    <td class="field">用户名：</td>
                    <td>
                        <input type="text" name="userName" class="text" id="userName" value="" />
                        <font color="red"></font>
                    </td>
                </tr>
                <tr>
                    <td class="field">用户密码：</td>
                    <td>
                        <input type="password" name="userPassword" class="text" id="userPassword" value="">
                        <font color="red"></font>
                    </td>
                </tr>
                <tr>
                    <td class="field">确认密码：</td>
                    <td>
                        <input type="password" name="ruserPassword" class="text" id="ruserPassword" value=""/>
                        <font color="red"></font>
                    </td>
                </tr>
                <tr>
                    <td class="field">用户性别：</td>
                    <td>
                        <select name="gender" id="gender">
                        <option value="1" checked="checked">男</option>
                        <option value="2">女</option>
                    </select>
                    </td>
                </tr>
                <tr>
                    <td class="field">出生日期：</td>
                    <td>
                        <input type="text" name="birthday" class="Wdate" id="birthday" value="" readonly="readonly" onclick="WdatePicker()"/>
                        <font color="red"></font>
                    </td>
                </tr>
                <tr>
                    <td class="field">用户电话：</td>
                    <td>
                        <input type="text" name="phone" class="text" id="phone" value=""/>
                        <font color="red"></font>
                    </td>
                </tr>
                <tr>
                    <td class="field">用户地址：</td>
                    <td>
                        <input name="address" id="address" class="text" value=""/>
                        <font color="red"></font>
                    </td>
                </tr>
                <tr>
                    <td class="field">用户权限：</td>
                    <td>
                        <input type="radio" name="userType" value="1">管理员
                        <input type="radio" name="userType" value="2" checked="true">经理
                        <input type="radio" name="userType" value="3" checked="checked">普通用户
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
        <div class="buttons">
            <input type="button" name="addBtn" id="addBtn" value="保存" class="input-button">
            <input type="button" name="button" id="button" onclick="history.back(-1)" value="返回" class="input-button">
        </div>
    </form>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/add.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/calendar/WdatePicker.js"></script>
</body>
</html>