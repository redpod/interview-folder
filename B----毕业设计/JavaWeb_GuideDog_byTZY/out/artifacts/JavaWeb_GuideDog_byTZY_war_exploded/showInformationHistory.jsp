<%--
  Created by IntelliJ IDEA.
  User: 童政英
  Date: 2018/10/27
  Time: 11:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="gd.po.Cultivate"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <base href="<%=basePath%>">
    <link rel="stylesheet" href="styles.css">
    <title>信息查询结果</title>
</head>
<body>
<div id="container">
    <div id="header">
        <%@ include file="inc/header.inc"%>
    </div>
    <div id="content">
        <table class="wide">
            <thead>
            <tr>
                <td width="120">所属导盲犬培养基地</td>
                <td width="120">领养时间</td>
                <td width="200">信息描述</td>
                <td width="200">信息档案</td>
            </tr>
            </thead>
            <%
                List<Cultivate> cultivates = (List<Cultivate>)request.getAttribute("cultivates");
                for (Cultivate c : cultivates) {
            %>
            <tr class="result">
                <td width="120">
                    <%=c.getInstitutionName() %>
                </td>
                <td width="120">
                    <%=c.getCultivatedate() %>
                </td>
                <td>
                    <%=c.getDescription() %>
                </td>
                <td>
                    <%=c.getTreatment() %>
                </td>
            </tr>
            <%
                }
            %>

            <tr class="cols4">
                <td colspan="4"><input type="button" value="返回" onclick="history.back(-1);" /></td>
            </tr>
            <tr class="cols4">
                <td colspan="4" class="info"><%=request.getAttribute("msg")==null?"":request.getAttribute("msg") %></td>
            </tr>
        </table>
    </div>
    <div id="footer">
        <%@ include  file="inc/footer.inc"%>
    </div>
</div>
</body>
</html>