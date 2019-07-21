<%--
  Created by IntelliJ IDEA.
  User: 童政英
  Date: 2018/10/27
  Time: 11:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="gd.po.Dogtype"%>
<%@page import="java.util.List"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>导盲犬培养基地添加页面</title>
    <link href="styles.css" rel="stylesheet"/>
</head>
<body>
<div>
    <!-- 将各个页面相同的公用代码保存成外部文件 使用静态导入的方式导入到当前页面 方便后期维护 -->
    <div id="header">
        <%@include file="inc/header.inc" %>
    </div>
    <div id="main">
        <form action=InstitutionServlet" method="post">
            <input type="hidden" name="m" value="addInstitution">
            <table>
                <tr>
                    <td>导盲犬培养基地名称</td>
                    <td><input  name="vname"/></td>
                </tr>
                <tr>
                    <td>导盲犬种类倾向</td>
                    <td>
                        <select name="sid" style="width: 152px;" multiple="multiple">
                            <option disabled="disabled">请至少选择一项</option>
                            <%
                                List<Dogtype>  dogts=(List<Dogtype>)request.getAttribute("dogts");
                                if(dogts!=null){
                                    for(Dogtype d:dogts){
                            %>
                            <option value="<%=d.getId()%>"><%=d.getName() %></option>
                            <%
                                    }
                                }
                            %>

                        </select>

                    </td>
                </tr>

                <tr>
                    <td></td>
                    <td><input  type="submit" value="保存" /> <input type="reset" value="清空"/><input value="取消" type="button" onclick="location.href='institutionsearch.jsp'" /></td>
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
