<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>书城首页</title>
    <%--静态包含base标签，css样式，jQuery文件--%>
    <%@include file="/pages/common/header.jsp"%>
    <script>
        $(function(){
            //根据页面号 跳转指定页面
            $("#searchPageBtn").click(function(){
                var pageNo = $("#pn_input").val();
                /*切记“location”的首字母是小写*/
                location.href="${pageScope.baseUrl}${requestScope.page.url}&pageNo="+pageNo;
            });
            //加入购物车按钮
            $("button.cart_btn").click(function(){
                var bookId = $(this).attr("bookId");
                $.getJSON("${pageScope.baseUrl}cartServlet","action=ajaxAddItem&id="+bookId,function(data){
                    $("#cart_totalCount").text("您的购物车中有"+data.totalCount+"件商品");
                    $("#cart_lastName").text("您刚刚将"+data.lastName+"加入购物车");
                });
            });
        })
    </script>
</head>
<body>

<div id="header">
    <img class="logo_img" alt="" src="static/img/logo.gif" >
    <span class="wel_word">网上书城</span>
    <c:if test="${not empty sessionScope.user}">
        <div>
            <span>欢迎<span class="um_span">${sessionScope.user.name}</span>光临尚硅谷书城</span>
            <a href="orderServlet?action=showMyOrder">我的订单</a>
            <a href="client/bookServlet?action=loginOut">注销</a>&nbsp;&nbsp;
            <a href="pages/cart/cart.jsp">购物车</a>
            <a href="pages/manager/manager.jsp">后台管理</a>
        </div>
    </c:if>
    <c:if test="${empty sessionScope.user}">
        <div >
            <a href="pages/user/login.jsp">登录</a> |
            <a href="pages/user/regist.jsp">注册</a> &nbsp;&nbsp;
            <a href="pages/cart/cart.jsp">购物车</a>
            <a href="pages/manager/manager.jsp">后台管理</a>
        </div>
    </c:if>
</div>
<div id="main">
    <div id="book">
        <div class="book_cond">
            <form action="client/bookServlet" method="get">
                <input type="hidden" name="action" value="pageByPrice">
                价格：<input id="min" type="text" name="min" value="${param.min}"> 元 -
                <input id="max" type="text" name="max" value="${param.max}"> 元
                <input type="submit" value="查询" />
            </form>
        </div>
        <c:if test="${empty sessionScope.cart.items}">
            <div style="text-align: center">
                <span id="cart_totalCount"></span><br>
                <div>
                    <span id="cart_lastName" style="color: red" >您还未将商品加入购物车</span>
                </div>
            </div>
        </c:if>
        <c:if test="${not empty sessionScope.cart.items}">
            <div style="text-align: center">
                <span id="cart_totalCount"></span><br>
                <div>
                    <span id="cart_lastName" style="color: red"></span>
                </div>
            </div>
        </c:if>

        <c:forEach items="${requestScope.page.items}" var="book">
            <div class="b_list">
                <div class="img_div">
                    <img class="book_img" alt="" src="static/img/default.jpg" />
                </div>
                <div class="book_info">
                    <div class="book_name">
                        <span class="sp1">书名:</span>
                        <span class="sp2">${book.name}</span>
                    </div>
                    <div class="book_author">
                        <span class="sp1">作者:</span>
                        <span class="sp2">${book.author}</span>
                    </div>
                    <div class="book_price">
                        <span class="sp1">价格:</span>
                        <span class="sp2">${book.price}</span>
                    </div>
                    <div class="book_sales">
                        <span class="sp1">销量:</span>
                        <span class="sp2">${book.sales}</span>
                    </div>
                    <div class="book_amount">
                        <span class="sp1">库存:</span>
                        <span class="sp2">${book.stock}</span>
                    </div>
                    <div class="book_add">
                        <button bookId="${book.id}"  class="cart_btn">加入购物车</button>
                    </div>
                </div>
            </div>
        </c:forEach>

    </div>
    <%--静态包含页面导航--%>
    <%@include file="/pages/common/page_nav.jsp"%>

</div>

<div id="bottom">
		<span>
			尚硅谷书城.Copyright &copy;2015
		</span>
</div>
</body>
</html>