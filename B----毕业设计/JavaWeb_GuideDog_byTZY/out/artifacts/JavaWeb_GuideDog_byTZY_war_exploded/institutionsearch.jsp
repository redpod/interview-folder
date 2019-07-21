<%--
  Created by IntelliJ IDEA.
  User: 童政英
  Date: 2018/10/27
  Time: 11:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>导盲犬培养基地查询页面</title>
    <link href="styles.css" rel="stylesheet"/>
</head>
<body>
<div>
    <div id="header">
        <%@ include  file="inc/header.inc"%>
    </div>
    <div id="main">
        <form action="InstitutionServlet" method="post">
            <input type="hidden" name="m" value="search">
            <!-- type="hidden"  隐藏表单控件  用来传递值  这个控件在前台页面不显示 -->
            <table>
                <tr>
                    <td>培养基地名称</td>
                    <td><input  name="vname"/></td>
                </tr>
                <tr>
                    <td>导盲犬种类</td>
                    <td><input  name="sname"/></td>
                </tr>
                <tr>
                    <td></td>
                    <td><input  type="submit" value="查询" />
                        <input type="reset" value="清空"/>
                        <br/>
                        <a href="InstitutionServlet?mode=newInstitution"  title="转到添加新导盲犬培养基地页面">添加新导盲犬培养基地</a>
                        <br/>
                        <a href="InstitutionServlet?mode=newDogt"  title="转到添加新导盲犬种类页面">添加新导盲犬种类</a></td>
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
