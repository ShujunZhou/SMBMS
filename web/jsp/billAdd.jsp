
<%--
  Created by IntelliJ IDEA.
  User: alren
  Date: 16-8-29
  Time: 下午8:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
﻿<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<div class="main">
    <div class="optitle clearfix">
        <div class="title">账单管理&gt;&gt;</div>
    </div>
    <form id="billForm" name="billForm" method="post" action="${pageContext.request.contextPath}/addBIll.do">
        <input type="hidden" name="method" value="add" />
        <div class="content">
            <table class="box">
                <tbody>
                <tr>
                    <td class="field">账单编号：</td>
                    <td>
                        <input type="text" name="billCode" class="text" id="billCode" value="" />
                        <font color="red"></font>
                    </td>
                </tr>
                <tr>
                    <td class="field">商品名称：</td>
                    <td>
                        <input type="text" name="productName" class="text" id="productName" value="" />
                        <font color="red"></font>
                    </td>
                </tr>
                <tr>
                    <td class="field">商品描述：</td>
                    <td>
                        <input type="text" name="productDesc" class="text" id="productDesc" value="" />
                        <font color="red"></font>
                    </td>
                </tr>
                <tr>
                    <td class="field">商品单位：</td>
                    <td>
                        <input type="text" name="productUnit" class="text" id="productUnit" value="" />
                        <font color="red"></font>
                    </td>
                </tr>
                <tr>
                    <td class="field">商品数量：</td>
                    <td>
                        <input type="text" name="productCount" class="text" id="productCount" value="" />
                        <font color="red"></font>
                    </td>
                </tr>
                <tr>
                    <td class="field">总额：</td>
                    <td>
                        <input type="text" name="totalPrice" id="totalPrice" class="text" value="" />
                        <font color="red"></font>
                    </td>
                </tr>
                <tr>
                    <td class="field">供应商：</td>
                    <td>
                        <select name="providerId" id="providerId">
                            <c:forEach var="provider" items="${providerList}" varStatus="status">
                                <c:choose>
                                    <c:when test="${status.count == 1}">
                                        <option value="0" selected="selected">请选择</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="${provider.id}">${provider.proName}</option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </select>
                        <font color="red"></font>
                    </td>
                </tr>
                <tr>
                    <td class="field">是否付款：</td>
                    <td>
                        <input type="radio" name="payed" value="1" />是
                        <input type="radio" name="payed" checked="checked" value="0" />否
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
        <div class="buttons">
            <input type="button" name="addBtn" id="addBtn" value="保存" class="input-button" />
            <input type="button" name="button" id="button" onclick="history.back(-1)" value="返回" class="input-button" />
        </div>
    </form>
    <input type="hidden" value="${pageContext.request.contextPath}" name="path" id="path"/>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/addBill.js"></script>
</body>
</html>
