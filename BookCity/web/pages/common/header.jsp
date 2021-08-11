<%--
  Created by IntelliJ IDEA.
  User: 86150
  Date: 2021/7/31
  Time: 12:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String baseUrl = request.getScheme()
            + "://"
            + request.getServerName()
            + ":"
            + request.getServerPort()
            + request.getContextPath()
            + "/";
    pageContext.setAttribute("baseUrl",baseUrl);
%>
<!--写base标签，永远固定相对路径跳转的结果-->
<base href="<%=baseUrl%>">
<link type="text/css" rel="stylesheet" href="static/css/style.css" >
<script type="text/javascript" src="static/script/jquery-1.7.2.js"></script>
