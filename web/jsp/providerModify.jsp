<%--
  Created by IntelliJ IDEA.
  User: alren
  Date: 16-8-26
  Time: 上午11:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
﻿<html><head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<input type="hidden" id="path" value="${pageContext.request.contextPath}" name="path"/>
<body>
<div class="main">
    <div class="optitle clearfix">
        <div class="title">供应商管理&gt;&gt;</div>
    </div>
    <form id="providerForm" name="providerForm" method="post" action="${pageContext.request.contextPath}/modifyProvider.do">
        <div class="content">
            <table class="box">
                <tbody>
                    <tr>
                        <td class="field">供应商名称：</td>
                        <td>
                            <input type="text" name="proName" id="proName" class="text" value="${providerList[0].proName}">
                            <font color="red"></font>
                        </td>
                    </tr>
                    <tr>
                        <td class="field">联系人：</td>
                        <td>
                            <input type="text" name="proContact" class="text" id="proContact" value="${providerList[0].proContact}"/>
                            <font color="red"></font>
                        </td>
                    </tr>
                    <tr>
                        <td class="field">联系电话：</td>
                        <td>
                            <input type="text" name="proPhone" class="text" id="proPhone" value="${providerList[0].proPhone}"/>
                            <font color="red"></font>
                        </td>
                    </tr>
                    <tr>
                        <td class="field">联系地址：</td>
                        <td>
                            <input name="proAddress" id="proAddress" class="text" value="${providerList[0].proAddress}"/>
                            <font color="red"></font>
                        </td>
                    </tr>
                    <tr>
                        <td class="field">传真：</td>
                        <td>
                            <input type="text" name="proFax" class="text" id="proFax" value="${providerList[0].proFax}"/>
                            <font color="red"></font>
                        </td>
                    </tr>
                    <tr>
                            <td class="field">描述：</td>
                            <td>
                                <input type="text" name="proDesc" class="text" id="proDesc" value="${providerList[0].proDesc}"/>
                                <font color="red"></font>
                            </td>
                    </tr>
                </tbody>
            </table>
        </div>
        <input type="hidden" name="proId" value="${providerList[0].id}" id="proId"/>
        <input type="button" name="modifyBtn" value="修改" id="modifyBtn"/>
    </form>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/modifyProvider.js"></script>
</body>
</html>
