<%--
  Created by IntelliJ IDEA.
  User: 童政英
  Date: 2018/10/27
  Time: 11:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="gd.po.Guidedog"%>
<%@page import="gd.po.User"%>
<%@page import="java.util.List"%>
<%@ page import="gd.dao.UserDAO" %>
<%@page import="java.net.URLEncoder"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>导盲犬查询结果页面</title>
    <link href="styles.css" rel="stylesheet"/>
</head>
<body>
<div>
    <div id="header"><%@include file="/inc/header.inc" %></div>
    <div id="main">
        <table border="1">
            <h4><%=request.getAttribute("msg") == null ? "" : request.getAttribute("msg")%></h4>
            <tr>
                <td colspan="2">导盲犬信息</td>
                <td>信息管理</td>
            </tr>
            <%
                List<Guidedog> guidedogs = (List<Guidedog>) request.getAttribute("guidedogs");
                for (Guidedog guidedog : guidedogs)
                {
                    User user = new UserDAO().getById(guidedog.getOwnerId());
            %>
            <tr>
                <td><img src="<%=guidedog.getPhoto()%>" width="80px" height="100px"></td>
                <td class="minWidth">姓名:<%=guidedog.getName()%><br/>生日:<%=guidedog.getBirthdate()%><br/>主人：<%=guidedog.getOwnerName()%><br/>电话：<%=user.getTel()%><br/>地址：<%=user.getAddress()%></td>
                <td class="minWidth">
                    <a href="CultivateServlet?mode=showInformationHistory&guidedogId=<%=guidedog.getId()%>">查看信息</a>|
                    <a href="CultivateServlet?mode=addInformationHistory&customerId=<%=user.getId()%>&guidedogId=<%=guidedog.getId()%>&guidedogName=<%=URLEncoder.encode(guidedog.getName(), "UTF-8")%>">添加信息</a></td>
            </tr>
            <%
                }
            %>
        </table>
    </div>
    <div id="footer">
        <%@ include  file="inc/footer.inc"%>
    </div>
</div>

</body>
</html>