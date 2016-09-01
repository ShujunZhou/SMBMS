<%--
  Created by IntelliJ IDEA.
  User: alren
  Date: 16-8-28
  Time: 下午8:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>超市账单管理系统-账单管理</title>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<div class="menu">
    <table>
        <tbody>
        <tr>
            <td>
                <form method="post" action="${pageContext.request.contextPath}/bill.do">
                <input name="method" value="query" class="input-text" type="hidden" />
                商品名称：<input name="productName" class="input-text" type="text" value="" />
                    账单编号：<input name="billCode" class="input-text" type="text" value="" />
                <input value="查 询" type="submit" />
            </form>
            </td>
        </tr>
        </tbody>
    </table>
</div>
<div class="main">
    <div class="optitle clearfix">
        <em><input value="添加账单" class="input-button" onclick="window.location='${pageContext.request.contextPath}/findProvider.do'" type="button"></em>
        <div class="title">账单管理&gt;&gt;</div>
    </div>
    <div class="content">
        <table class="list">
            <tbody>
            <tr>
                <td width="70" height="29"><div class="STYLE1" align="center">编号</div></td>
                <td width="80"><div class="STYLE1" align="center">商品名称</div></td>
                <td width="80"><div class="STYLE1" align="center">供应商</div></td>
                <td width="100"><div class="STYLE1" align="center">TotalGoods</div></td>
                <td width="100"><div class="STYLE1" align="center">账单金额</div></td>
                <td width="100"><div class="STYLE1" align="center">是否付款</div></td>
                <td width="100"><div class="STYLE1" align="center">操作时间</div></td>
            </tr>
            <c:forEach var="bill" items="${billList}" varStatus="status">
                <tr>
                    <td height="23"><span class="STYLE1">${bill.billCode}</span></td>
                    <td><span class="STYLE1">${bill.productName}</span></td>
                    <td><span class="STYLE1">
                        <c:forEach var="provider" items="${providerList}" varStatus="status1">
                            <c:if test="${bill.providerId == provider.id}">${provider.proName}</c:if>
                        </c:forEach>
                    </span></td>
                    <td><span class="STYLE1">${bill.productCount}</span></td>
                    <td><span class="STYLE1">${bill.totalPrice}</span></td>
                    <td><span class="STYLE1">
                        <c:if test="${bill.isPayment == 1}">已付款</c:if>
                        <c:if test="${bill.isPayment == 0}">未付款</c:if>
                    </span></td>
                    <td><span class="STYLE1">${bill.creationDate}</span></td>
                </tr>
            </c:forEach>

            </tbody>
        </table>
    </div>
</div>

</body>
</html>
