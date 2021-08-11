<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>订单详情</title>
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
            // $("#back_a").click(function(){
            //     window.history.go(-1);
            // });
        });
    </script>
</head>
<body>

<div id="header">
    <img class="logo_img" alt="" src="static/img/logo.gif" >
    <span class="wel_word">订单详情</span>
    <div>
        <span>欢迎<span class="um_span">${sessionScope.user.name}</span>光临尚硅谷书城</span>
        <a href="orderServlet?action=showMyOrder">我的订单</a>
<%--        返回标签跳回上一个页面--%>
        <a href="javascript:history.back()">返回</a>
<%--        <input type="button" id="back_a">返回</input>--%>
    </div>
</div>

<div id="main">

    <table>
        <tr>
            <td>商品名称</td>
            <td>数量</td>
            <td>单价</td>
            <td>金额</td>
        </tr>
        <c:forEach items="${requestScope.orderItems}" var="item">
            <tr>
                <td>${item.name}</td>
                <td>${item.count}</td>
                <td>${item.price}</td>
                <td>${item.totalPrice}</td>
            </tr>
        </c:forEach>

    </table>
    <div class="cart_info">
        <span class="cart_span">共有<span class="b_count">${param.totalCount}</span>件商品</span>
        <span class="cart_span">总金额<span class="b_price">${param.totalPrice}</span>元</span>
    </div>

</div>

<%--静态包含页脚--%>
<%@include file="/pages/common/footer.jsp"%>
</body>
</html>