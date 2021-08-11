<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>订单管理</title>
	<%--静态包含base标签，css样式，jQuery文件--%>
	<%@include file="/pages/common/header.jsp"%>
</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="../../static/img/logo.gif" >
			<span class="wel_word">订单管理系统</span>
		<%--静态包含后台管理菜单--%>
		<%@include file="/pages/common/manager_menu.jsp"%>
	</div>
	
	<div id="main">
		<table>
			<tr>
				<td>用户ID</td>
				<td>日期</td>
				<td>金额</td>
				<td>发货</td>
				<td>详情</td>
			</tr>
			<c:forEach items="${requestScope.orders}" var="order">
				<tr>
					<td>${order.userId}</td>
					<td>${order.dateTime}</td>
					<td>${order.price}</td>
					<td>
						<c:choose>
							<c:when test="${order.status==0}">
								<a href="orderServlet?action=sendOrder&orderId=${order.id}">点击发货</a>
							</c:when>
							<c:when test="${order.status==1}">
								已发货
							</c:when>
							<c:when test="${order.status==2}">
								已签收
							</c:when>
						</c:choose>
					</td>
					<td><a href="orderServlet?action=showOrderDetail&orderId=${order.id}">查看详情</a></td>
				</tr>
			</c:forEach>
		</table>
	</div>

	<%--静态包含页脚--%>
	<%@include file="/pages/common/footer.jsp"%>
</body>
</html>