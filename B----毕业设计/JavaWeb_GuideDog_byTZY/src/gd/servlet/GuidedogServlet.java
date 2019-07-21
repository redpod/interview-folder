package gd.servlet;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import java.util.List;
import gd.dao.GuidedogDAO;
import gd.po.Guidedog;
import gd.dao.UserDAO;
import gd.po.User;

@WebServlet("/GuidedogServlet")
@MultipartConfig


public class GuidedogServlet extends HttpServlet
{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String m = request.getParameter("m");
        if("search".equals(m))
        {
            searchGuidedog(request, response);
        }
        else if("newGuidedogAdd".equals(m))
        {
            newGuidedogAdd_doPost(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String m = request.getParameter("m");
        if("deleteGuidedog".equals(m))
        {
            deleteGuidedog(request, response);
        }
        else if("newGuidedogAdd".equals(m))
        {
            newGuidedogAdd_doGet(request, response);
        }
    }

    private void searchGuidedog(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException
    {
        String guidedogName = request.getParameter("guidedogName");
        String ownerName = request.getParameter("ownerName");
        try
        {
            List<Guidedog> guidedogs = new GuidedogDAO().search(guidedogName, ownerName);
            if(0==guidedogs.size())
            {
                request.setAttribute("msg", "没有查到导盲犬信息");
                request.getRequestDispatcher("/guidedogSearch.jsp").forward(request, response);
            }
            else
            {
                request.setAttribute("guidedogs", guidedogs);
                request.getRequestDispatcher("/guidedogSearchResult.jsp").forward(request, response);
            }
        }
        catch (Exception e)
        {
            request.setAttribute("msg", e.getMessage());
            request.getRequestDispatcher("/guidedogSearch.jsp").forward(request, response);
        }
    }


    private void deleteGuidedog(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        try
        {
            String strGuidedogId = request.getParameter("guidedogId");
            int guidedogID = Integer.parseInt(strGuidedogId);
            GuidedogDAO guidedogDAO = new GuidedogDAO();
            guidedogDAO.delete(guidedogID);

            request.setAttribute("msg", "成功删除导盲犬：" + request.getParameter("guidedogName"));
            request.getRequestDispatcher("/guidedogSearch.jsp").forward(request, response);
        }
        catch (Exception e)
        {
            request.setAttribute("msg", e.getMessage());
            request.getRequestDispatcher("/guidedogSearch.jsp").forward(request, response);
        }
    }

    private void newGuidedogAdd_doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        try
        {
            UserDAO userDAO = new UserDAO();
            List<User> users = userDAO.getAllCustomer();
            request.setAttribute("users", users);
            request.getRequestDispatcher("/newGuidedogAdd.jsp").forward(request, response);
        }
        catch (Exception e)
        {
            request.setAttribute("msg", e.getMessage());
            request.getRequestDispatcher("/guidedogSearch.jsp").forward(request, response);
        }
    }

    private void newGuidedogAdd_doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String name = request.getParameter("name");
        if("".equals(name))//导盲犬姓名不能为空
        {
            try
            {
                UserDAO userDAO = new UserDAO();
                List<User> users = userDAO.getAllCustomer();
                request.setAttribute("users", users);
                request.setAttribute("msg", "请输入导盲犬姓名");
                request.getRequestDispatcher("/newGuidedogAdd.jsp").forward(request, response);
            }
            catch (Exception e)
            {
                request.setAttribute("msg", e.getMessage());
                request.getRequestDispatcher("/guidedogSearch.jsp").forward(request, response);
            }
            return;
        }
        Part p = request.getPart("photo");
        String filename = getFileName(p);
        //这里需要上传文件，就需要得到上传文件的目标路径，这里的文件保存路径不能是任意路径，只能放到当前应用根目录及其子目录下，才能够通过浏览器访问，如何得到这个当前应用根目录，一般情况下不能写死路径，应该通过代码动态得到路径
        String photo = "photo/default.jpg";
        if(!filename.equals(""))//如果旧文件名不为空表示用户上传了照片，则需要保存照片，否则可以省略这个步骤
        {
            String type = filename.substring(filename.lastIndexOf("."));//xxxx.xx
            String newname = System.currentTimeMillis() + type;
            //System.out.println(getRuntimePath());//路径有了差文件名，如果使用原文件名，可能出现重名文件，若非要使用原文件名，则可以分文件夹或将文件名改为时间毫秒数
            String saveFile = getRuntimePath() + newname;
            photo = "photo/" + newname;
            p.write(saveFile);//上传文件
        }

        Guidedog guidedog = new Guidedog();
        guidedog.setName(request.getParameter("name"));
        guidedog.setBirthdate(request.getParameter("birthdate"));
        guidedog.setOwnerId(Integer.parseInt(request.getParameter("userId")));
        guidedog.setPhoto(photo);
        try
        {
            new GuidedogDAO().save(guidedog);
            request.setAttribute("msg", "添加成功");
            request.getRequestDispatcher("/guidedogSearch.jsp").forward(request, response);
        }
        catch (Exception e)
        {
            request.setAttribute("msg",e.getMessage());
            request.getRequestDispatcher("/guidedogSearch.jsp").forward(request, response);
        }
    }

    private String getFileName(Part part)
    {
        String filename = "";
        String contentDec = part.getHeader("content-disposition");// 获取header信息中的content-disposition，如果为文件，则可以从其中提取出文件名

        Pattern p = Pattern.compile("filename=\".+\"");//  filename="任意个字符"   .任意字符   +表示数量不固定
        Matcher m = p.matcher(contentDec);
        if(m.find())
        {
            String temp=m.group();
            filename=temp.substring(10,temp.length()-1);
        }
        return filename;
    }

    private String getRuntimePath()
    {
        String path = "";
        path = this.getServletContext().getRealPath("/photo");
        path += "\\";
        return path;
    }
}