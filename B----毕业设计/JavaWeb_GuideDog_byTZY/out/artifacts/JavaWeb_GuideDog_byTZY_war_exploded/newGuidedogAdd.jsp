<%--
  Created by IntelliJ IDEA.
  User: 童政英
  Date: 2018/10/27
  Time: 10:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="gd.po.User"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>新领养人添加页面</title>
    <link href="styles.css" rel="stylesheet" />
    <script type="text/javascript">
        function preview(source)//上传图片到服务器之前，先实现图片预览
        {
            var file = source.files[0];
            if(window.FileReader)
            {
                var fr = new FileReader();
                var portrait = document.getElementById('portrait');
                fr.onloadend = function(e){ portrait.src = e.target.result; };
                fr.readAsDataURL(file);
                portrait.style.display = 'block';
            }
        }
    </script>
</head>
<body>
<div>
    <div id="header">
        <%@include file="inc/header.inc"%>  <!-- 将各个页面相同的公用代码保存成外部文件 使用静态导入的方式导入到当前页面 方便后期维护 -->
    </div>
    <div id="main">
        <form action="GuidedogServlet" method="post" enctype="multipart/form-data"> <!-- 文件上传  要修改表单的enctype属性 -->
            <input type="hidden" name="m" value="newGuidedogAdd">
            <table>
                <tr>
                    <td>领养人主人</td>
                    <td>
                        <select name="userId" style="width: 152px;">
                            <%
                                List<User> users = (List<User>) request.getAttribute("users");
                                if (users != null)
                                {
                                    for (User user : users)
                                    {
                            %>
                            <option value="<%=user.getId()%>"><%=user.getName()%></option>
                            <%
                                    }
                                }
                            %>
                        </select>
                    </td>
                <tr>
                    <td>导盲犬名字</td>
                    <td><input name="name" /></td>
                </tr>
                <tr>
                    <td>导盲犬生日</td>
                    <td><input name="birthdate" /></td>
                </tr>
                <tr>
                    <td>导盲犬照片</td>
                    <td><input name="photo" type="file" accept="image/*" onchange="preview(this)"/></td>
                </tr>
                <tr>
                    <td>照片预览</td>
                    <td><img id="portrait" src="" style="display:none;" width="200px" height="200px"/></td>
                </tr>
                <tr>
                    <td></td>
                    <td><input type="submit" value="保存"/> <input type="reset" value="清空" onclick="document.getElementById('portrait').style.display = 'none';"/><input value="取消" type="button" onclick="location.href='guidedogSearch.jsp'"/></td>
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
