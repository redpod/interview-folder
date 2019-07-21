<%--
  Created by IntelliJ IDEA.
  User: 童政英
  Date: 2018/10/27
  Time: 11:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="gd.po.Guidedog"%>
<%@page import="java.util.List"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>导盲犬查询结果页面</title>
    <link href="styles.css" rel="stylesheet"/>
    <SCRIPT type="text/javascript">
        function confirmDialog()
        {
            if(confirm("确定要删除该导盲犬信息吗?"))
            {
                return true;
            }
            else
            {
                return false;
            }
        }
    </SCRIPT>
</head>
<body>
<div>
    <div id="header"><%@include file="/inc/header.inc" %></div>
    <div id="main">
        <table>
            <tr>
                <td align="center">导盲犬姓名</td>
                <td align="center">导盲犬生日</td>
                <td align="center">导盲犬照片</td>
                <td align="center">导盲犬主人</td>
            </tr>
            <%
                List<Guidedog> guidedogs = (List<Guidedog>) request.getAttribute("guidedogs");
                for (Guidedog guidedog:guidedogs) {
            %>
            <tr class="result">
                <td><%=guidedog.getName()%></td>
                <td><%=guidedog.getBirthdate()%></td>
                <td><img src="<%=guidedog.getPhoto()%>" width="50px" height="50px"></td>
                <td><%=guidedog.getOwnerName()%></td>
                <td><a href="GuidedogServlet?m=deleteGuidedog&guidedogId=<%=guidedog.getId()%>&guidedogName=<%=guidedog.getName()%>" onclick=" return confirmDialog();">删除</a></td>
                <td><a href="GuidedogServlet?m=modifyGuidedog">修改</a></td>
            </tr>
            <%
                }
            %>
            <tr>
                <td></td>
                <td><input value="返回" type="button" onclick="location.href='guidedogSearch.jsp'" /></td>
            </tr>
        </table>

        <h4 align="center" style="color:red"><%=request.getAttribute("msg") == null ? "" : request.getAttribute("msg")%></h4>
    </div>
    <div id="footer">
        <%@ include  file="inc/footer.inc"%>
    </div>
</div>
</body>
</html>
