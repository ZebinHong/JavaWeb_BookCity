<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>我的订单</title>
	<%--静态包含base标签，css样式，jQuery文件--%>
	<%@include file="/pages/common/header.jsp"%>
<style type="text/css">
	h1 {
		text-align: center;
		margin-top: 200px;
	}
</style>
</head>
<body>

<div id="header">
	<img class="logo_img" alt="" src="static/img/logo.gif">
	<div>
		<span>欢迎<span class="um_span">${sessionScope.user.name}</span>光临尚硅谷书城</span>
		<a href="client/bookServlet?action=loginOut">注销</a>&nbsp;&nbsp;
		<a href="index.jsp">返回</a>
	</div>
</div>
	
	<div id="main">
		
		<table>
			<tr>
				<td>日期</td>
				<td>金额</td>
				<td>状态</td>
				<td>详情</td>
			</tr>
			<c:forEach items="${requestScope.orders}" var="order">
				<tr>
					<td>${order.dateTime}</td>
					<td>${order.price}</td>
					<td>
						<c:choose>
							<c:when test="${order.status==0}">
								未发货
							</c:when>
							<c:when test="${order.status==1}">
								<a href="orderServlet?action=receiveOrder&orderId=${order.id}">签收</a>
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