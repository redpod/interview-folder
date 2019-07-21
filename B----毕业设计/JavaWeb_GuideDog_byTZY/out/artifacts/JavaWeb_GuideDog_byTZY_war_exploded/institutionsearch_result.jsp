<%--
  Created by IntelliJ IDEA.
  User: 童政英
  Date: 2018/10/27
  Time: 11:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="gd.po.Dogtype"%>
<%@page import="gd.po.Institution"%>
<%@page import="java.util.List"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>导盲犬培养基地查询页面</title>
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
        <%@include file="inc/header.inc" %>
    </div>
    <div id="main">
        <table>
            <tr>
                <td>导盲犬培养基地名称</td>
                <td>导盲犬种类倾向</td>
            </tr>

            <%
                List<Institution> institutions = (List<Institution>) request.getAttribute("institutions");
                for (Institution institution : institutions) {
            %>
            <tr class="result">
                <td><%=institution.getName()%></td>
                <td>
                    <%
                        for(Dogtype dogt:institution.getDogts()){
                    %>
                    <span><%=dogt.getName()%>;</span>
                    <%
                        }
                    %>
                </td>
                <td><a href="InstitutionServlet?mode=deleteInstitution&institutionId=<%=institution.getId()%>&institutionName=<%=institution.getName()%>" onclick=" return confirmDialog();">删除</a></td>
            </tr>
            <%
                }
            %>

            <tr>
                <td></td>
                <%--<td><input value="返回" type="button" onclick="history.back(-1)" /></td>--%>
                <td><input value="返回" type="button" onclick="location.href='institutionsearch.jsp'" /></td>
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
