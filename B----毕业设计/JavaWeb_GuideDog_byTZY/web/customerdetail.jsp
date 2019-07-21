<%--
  Created by IntelliJ IDEA.
  User: 童政英
  Date: 2018/10/27
  Time: 10:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.net.URLEncoder"%>
<%@page import="gd.po.Guidedog"%>
<%@page import="gd.po.User"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>领养人详细信息页面</title>
    <link href="styles.css" rel="stylesheet" />
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
    <div id="header">
        <%@ include file="inc/header.inc"%>
    </div>
    <div id="main">
        <%
            User user = (User) request.getAttribute("user");
        %>
        <table>
            <tr>
                <td>领养人姓名</td>
                <td><input disabled="disabled" value="<%=user.getName()%>" /></td>
            </tr>
            <tr>
                <td>联系电话</td>
                <td><input disabled="disabled" value="<%=user.getTel()%>" /></td>
            </tr>
            <tr>
                <td>家庭地址</td>
                <td><input disabled="disabled" value="<%=user.getAddress()%>" /></td>
            </tr>
            <tr>
                <td></td>
                <td>
                    <input value="返回" type="button" onclick="location.href='customersearch.jsp'"/>

                </td>
            </tr>
        </table>
        <%--比较request.getAttribute()和request.getParameter()的区别--%>
        <h4 align="center" style="color:red"><%=request.getAttribute("msg") == null ? "" : request.getAttribute("msg")%><%=request.getParameter("msg") == null ? "" : request.getParameter("msg")%></h4>
        <hr>
        <table>
            <tr>
                <td colspan="2">导盲犬信息</td>
                <%--<td>操作</td>--%>
            </tr>

            <%
                for (Guidedog guidedog : user.getGuidedogs())
                {
            %>
            <tr>
                <td><img src="<%=guidedog.getPhoto()%>" width="48px" height="48px"></td>
                <td class="minWidth">姓名:<%=guidedog.getName()%><br/>生日:<%=guidedog.getBirthdate()%>
                </td>
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
