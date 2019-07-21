<%--
  Created by IntelliJ IDEA.
  User: 童政英
  Date: 2018/10/27
  Time: 9:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="gd.po.Institution"%>
<%@page import="gd.po.Dogtype"%>
<%@page import="java.util.List"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>导盲犬领养信息添加页面</title>
    <link href="styles.css" rel="stylesheet" />
</head>
<body>
<div>
    <!-- 将各个页面相同的公用代码保存成外部文件 使用静态导入的方式导入到当前页面 方便后期维护 -->
    <div id="header">
        <%@include file="inc/header.inc"%>
    </div>
    <div id="main">
        <form action="CultivateServlet" method="post">
            <input type="hidden" name="mode" value="saveInformationHistory">
            <table>
                <tr>
                    <td>导盲犬姓名</td>
                    <td>
                        <input name="pname" value="<%=request.getParameter("guidedogName")%>"  disabled="disabled"/>
                        <input type="hidden" name="guidedogName" value="<%=request.getParameter("guidedogName")%>" />
                        <input type="hidden" name="cid" value="<%=request.getParameter("customerId")%>" />
                        <input type="hidden" name="pid" value="<%=request.getParameter("guidedogId")%>" />
                    </td>
                </tr>
                <tr>
                    <td>所属导盲犬培养基地</td>
                    <td>
                        <select name="vid" style="width: 152px;">
                            <%
                                List<Institution> institutions = (List<Institution>) request.getAttribute("vets");
                                if (institutions != null)
                                {
                                    for (Institution institution : institutions)
                                    {
                            %>
                            <option value="<%=institution.getId()%>"><%=institution.getName()%></option>
                            <%
                                    }
                                }
                            %>
                        </select>
                    </td>
                </tr>

                <tr>
                    <td>信息描述</td>
                    <td><textarea name="description"></textarea></td>
                </tr>
                <tr>
                    <td>领养档案</td>
                    <td><textarea name="treatment"></textarea></td>
                </tr>

                <tr>
                    <td></td>
                    <td>
                        <input type="submit" value="保存" />
                        <input type="reset" value="清空" />
                        <input value="取消" type="button" onclick="location.href='cultivateSearch.jsp'" />
                    </td>
                </tr>
            </table>

        </form>
        <h4><%=request.getAttribute("msg") == null ? "" : request.getAttribute("msg")%></h4>
    </div>
    <div id="footer">
        <%@ include  file="inc/footer.inc"%>
    </div>
</div>
</body>
</html>
