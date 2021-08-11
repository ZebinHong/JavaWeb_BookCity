<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>购物车</title>
	<%--静态包含base标签，css样式，jQuery文件--%>
	<%@include file="/pages/common/header.jsp"%>
	<script>
		$(function(){
			$(".delete_a").click(function(){
				return confirm("您确定删除【"+ $(this).parent().parent().find("td:first").text()+"】吗？");
			});
			//绑定onchange事件，在jquery中为change事件。
			$("input.count_text").change(function(){
				var id = $(this).attr("bookId");
				var count = this.value;
				if(confirm("您确定修改【"+ $(this).parent().parent().find("td:first").text()+"】的数量为"+count+"吗？")){
					location.href="cartServlet?action=updateCount&id="+id+"&count="+count;
				}else{
					this.value=this.defaultValue;
				}
			});
		});
	</script>
</head>
<body>
			<%@include file="/pages/common/login_success_menu.jsp"%>
	
	<div id="main">
	
		<table>
			<tr>
				<td>商品名称</td>
				<td>数量</td>
				<td>单价</td>
				<td>金额</td>
				<td>操作</td>
			</tr>
			<c:if test="${empty sessionScope.cart.items}">
				<tr>
					<td colspan="5"><a href="index.jsp">亲，当前购物车为空！快跟小伙伴们去浏览商品吧！！！</a>
					</td>
				</tr>
			</c:if>
			<c:if test="${not empty sessionScope.cart.items}">
				<c:forEach items="${sessionScope.cart.items}" var="item">
					<tr>
						<td>${item.value.name}</td>
						<td>
							<input bookId="${item.value.id}" style="width: 35px" type="text" class="count_text"
								   value="${item.value.count}">
						</td>
						<td>${item.value.price}</td>
						<td>${item.value.totalPrice}</td>
						<td><a  class="delete_a" href="cartServlet?action=deleteItem&id=${item.value.id}">删除</a></td>
					</tr>
				</c:forEach>
			</c:if>

		</table>
		<c:if test="${not empty sessionScope.cart.items}">
			<div class="cart_info">
				<span class="cart_span">购物车中共有<span class="b_count">${sessionScope.cart.totalCount}</span>件商品</span>
				<span class="cart_span">总金额<span class="b_price">${sessionScope.cart.totalPrice}</span>元</span>
				<span class="cart_span"><a href="cartServlet?action=clear">清空购物车</a></span>
				<span class="cart_span"><a href="orderServlet?action=createOrder">去结账</a></span>
			</div>
		</c:if>

	</div>

	<%--静态包含页脚--%>
	<%@include file="/pages/common/footer.jsp"%>
</body>
</html>