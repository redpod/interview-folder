<%--
  Created by IntelliJ IDEA.
  User: 童政英
  Date: 2018/10/27
  Time: 10:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>领养人添加页面</title>
    <link href="styles.css" rel="stylesheet"/>
</head>
<body>
<div>
    <!-- 将各个页面相同的公用代码保存成外部文件 使用静态导入的方式导入到当前页面 方便后期维护 -->
    <div id="header"><%@include file="inc/header.inc" %></div>
    <div id="main">
        <form action="CustomerServlet" method="post">
            <input type="hidden" name="m" value="save">
            <table>
                <tr>
                    <td>领养人姓名</td>
                    <td><input name="name"/></td>
                </tr>
                <tr>
                    <td>领养人电话</td>
                    <td><input name="tel"/></td>
                </tr><tr>
                <td>领养人地址</td>
                <td><input name="address"/></td>
            </tr>
                <tr>
                    <td></td>
                    <td><input type="submit" value="保存" />
                        <input type="reset" value="清空"/>
                        <input value="取消" type="button" onclick="location.href='customersearch.jsp'" /></td>
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
