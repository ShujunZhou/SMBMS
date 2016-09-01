<%--
  Created by IntelliJ IDEA.
  User: alren
  Date: 16-8-26
  Time: 上午9:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
﻿<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>Insert title here</title>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<div class="main">
    <div class="optitle clearfix">
        <div class="title">供应商管理&gt;&gt;</div>
    </div>
    <div class="content">
        <table class="box" border="1px red solid">
            <tbody>
            <tr>
                <td class="field">供应商编号：</td>
                <td>${providerList[0].proCode}</td>
            </tr>
            <tr>
                <td class="field">供应商名称：</td>
                <td>${providerList[0].proName}</td>
            </tr>
            <tr>
                <td class="field">联系人：</td>
                <td>${providerList[0].proContact}</td>
            </tr>
            <tr>
                <td class="field">联系电话：</td>
                <td>${providerList[0].proPhone}</td>
            </tr>
            <tr>
                <td class="field">联系地址：</td>
                <td>${providerList[0].proAddress}</td>
            </tr>
            <tr>
                <td class="field">传真：</td>
                <td>${providerList[0].proFax}</td>
            </tr>
            </tbody>
        </table>
        <input type="button" value="<<返回" name="button" onclick="javascript:history.back();"/>
    </div>
</div>
</body>
</html>
