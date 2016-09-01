<%--
  Created by IntelliJ IDEA.
  User: alren
  Date: 16-8-25
  Time: 下午3:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>超市账单管理系统-供应商管理</title>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>

<input type="hidden" id="path" value="${pageContext.request.contextPath}" name="path"/>

<body>
<div class="menu">
    <table>
        <tbody>
        <tr>
            <td>
                <form method="post" action="${pageContext.request.contextPath}/provider.do">
                <input name="queryProvider" value="proName" class="input-text" type="hidden" id="queryProdiver"/> 供应商名称：
                <input name="queryProviders" class="input-text" type="text" value=""  id="queryProviders"/>&nbsp;&nbsp;&nbsp;&nbsp;
                <input value="查 询" type="submit" name="find" id="quertBtn"/>
            </form>
            </td>
        </tr>
        </tbody>
    </table>
</div>
<div class="main">
    <div class="optitle clearfix">
        <em><input value="添加供应商" class="input-button" onclick="window.location='${pageContext.request.contextPath}/jsp/providerAdd.jsp'" type="button"></em>
        <div class="title">供应商管理&gt;&gt;</div>
    </div>
    <div class="content">
        <table class="list">
            <tbody>
            <tr>
                <td width="100" height="29"><div class="STYLE1" align="center">编号</div></td>
                <td width="500"><div class="STYLE1" align="center">供应商名称</div></td>
                <td width="500"><div class="STYLE1" align="center">商品描述</div></td>
                <td width="150"><div class="STYLE1" align="center">联系人</div></td>
                <td width="150"><div class="STYLE1" align="center">联系电话</div></td>
                <td width="150"><div class="STYLE1" align="center">传真</div></td>
                <td width="500"><div class="STYLE1" align="center">供应商地址</div></td>
                <td width="300"><div class="STYLE1" align="center">操作</div></td>
            </tr>
            <c:forEach var="provider" items="${providerList}" varStatus="status">
                <tr>
                    <td height="23"><span class="STYLE1">${provider.proCode}</span></td>
                    <td><span class="STYLE1">
                        <a href="${pageContext.request.contextPath}/provider.do?queryProvider=id&queryProviders=${provider.id}"
                                                style="color:red">${provider.proName}</a></span></td>
                    <td><span class="STYLE1">${provider.proDesc}</span></td>
                    <td><span class="STYLE1">${provider.proContact}</span></td>
                    <td><span class="STYLE1">${provider.proPhone}</span></td>
                    <td><span class="STYLE1">${provider.proFax}</span></td>
                    <td><span class="STYLE1">${provider.proAddress}</span></td>
                    <td>
                        <span><a href="javascript:;" providerId="${provider.id}" providerName="${provider.proName}" class="modifyProvider">修改</a></span>
                        <span><a href="javascript:;" providerId="${provider.id}" providerName="${provider.proName}" class="deleteProvider">删除</a></span>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <input type="hidden" value="queryId" name="queryId" id="queryId"/>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/providerList.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/addProvider.js"></script>
</body>
</html>
