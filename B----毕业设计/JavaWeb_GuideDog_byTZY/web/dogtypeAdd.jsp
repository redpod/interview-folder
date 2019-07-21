<%--
  Created by IntelliJ IDEA.
  User: 童政英
  Date: 2018/10/27
  Time: 11:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <title>导盲犬种类添加页面</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="styles.css" rel="stylesheet"/>
</head>
<body>
<div>
    <div id="header"><%@include file="inc/header.inc" %></div>
    <div id="main">
        <form action="InstitutionServlet" method="post">
            <input type="hidden" name="m" value="addDogt">
            <table>
                <tr>
                    <td>导盲犬种类名称</td>
                    <td><input  name="dogtName"/></td>
                </tr>
                <tr>
                    <td>导盲犬种类描述</td>
                    <td><input  name="dogtDesc"/></td>
                </tr>
                <tr>
                    <td></td>
                    <td><input  type="submit" value="保存" />
                        <input type="reset" value="清空"/>
                        <input value="取消" type="button" onclick="location.href='institutionsearch.jsp'" /></td>
                </tr>
            </table>

        </form>
        <h4 align="center" style="color:red"><%=request.getAttribute("msg")==null?"":request.getAttribute("msg") %></h4>
    </div>
    <div id="footer">
        <%@ include  file="inc/footer.inc"%>
    </div>
</div>
</body>
</html>
