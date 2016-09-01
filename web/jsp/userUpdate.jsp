<%--
  Created by IntelliJ IDEA.
  User: alren
  Date: 16-8-23
  Time: 下午2:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
﻿<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<div class="main">
    <div class="optitle clearfix">
        <div class="title">修改信息&gt;&gt;</div>
    </div>
    <form id="userForm" name="userForm" method="get" action="${pageContext.request.contextPath}/modify.do">
        <input type = "hidden" name="userId" value="${user.id}" id="userId"/>
        <input type="hidden" name="path" value="${pageContext.request.contextPath}" id="path"/>
        <div class="content">
            <font color="red"></font>
            <table class="box">
            <tbody>
            <tr>
                <td class="field">用户名：</td>
                <td>
                    <input type="text" name="userName" class="text" id="userName" value="${user.userName}"/>
                    <font color="red"></font>
                </td>
            </tr>
            <tr>
                <td class="field">用户性别：</td>
                <td>
                    <select name="gender" id="gender">
                        <c:choose>
                        <c:when test="${user.gender == 1}">
                            <option value="1" selected ="checked">男</option>
                            <option value="2">女</option>
                        </c:when>
                        <c:otherwise>
                            <option value="1">男</option>
                            <option value="2" selected="checked">女</option>
                        </c:otherwise>
                        </c:choose>
                    </select>
                </td>
            </tr>
            <tr>
                <td class="field">出生日期：</td>
                <td>
                    <input type="text" name="birthday" class="Wdate" id="birthday" value="${user.birthday}" readonly="readonly" onclick="WdatePicker()"/>
                    <font color="red"></font>
                </td>
            </tr>
            <tr>
                <td class="field">用户电话：</td>
                <td>
                    <input type="text" name="phone" class="text" id="phone" value="${user.phone}"/>
                    <font color="red"></font>
                </td>
            </tr>
            <tr>
                <td class="field">用户地址：</td>
                <td>
                    <input name="address" id="address" class="text" value="${user.address}"/>
                    <font color="red"></font>
                </td>
            </tr>
            <tr>
                <td class="field">用户权限：</td>
                <td>
                    <input type="radio" name="userType" value="1" <c:if test="${user.userType == 1}">checked="checked"</c:if>/>管理员
                    <input type="radio" name="userType" value="2" <c:if test="${user.userType == 2}">checked="checked"</c:if>/>经理
                    <input type="radio" name="userType" value="3" <c:if test="${user.userType == 3}">checked="checked"</c:if>/>普通用户
                </td>
            </tr>
            </tbody>
        </table>
        </div>
        <div class="buttons">
            <input type="button" name="update" id="update" value="修改" class="input-button">
            <input type="button" name="button" id="button" onclick="history.back(-1)" value="返回" class="input-button">
        </div>
    </form>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/update.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/calendar/WdatePicker.js"></script>
</body>
</html>
