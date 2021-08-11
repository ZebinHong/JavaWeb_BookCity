<%--
  Created by IntelliJ IDEA.
  User: 86150
  Date: 2021/7/31
  Time: 12:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="header">
    <img class="logo_img" alt="" src="static/img/logo.gif">
    <div>
        <span>欢迎<span class="um_span">${sessionScope.user.name}</span>光临尚硅谷书城</span>
        <a href="orderServlet?action=showMyOrder">我的订单</a>
        <a href="client/bookServlet?action=loginOut">注销</a>&nbsp;&nbsp;
        <a href="index.jsp">返回</a>
    </div>
</div>
