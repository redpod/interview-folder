package gd.servlet;

import gd.dao.GuidedogDAO;
import gd.dao.UserDAO;
import gd.po.Guidedog;
import gd.po.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

//@WebServlet(name = "CustomerServlet")
@WebServlet("/CustomerServlet")
public class CustomerServlet extends HttpServlet
{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String m = request.getParameter("m");
        if("search".equals(m))//查询已有客户，来自于customersearch.jsp提交的表单
        {
            searchCustomer(request, response);
        }
        else if("save".equals(m))//保存新的客户，来自于customeradd.jsp提交的表单
        {
            saveCustomer(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String mode = request.getParameter("mode");
        if("delete".equals(mode))//如果mode的值等于"delete"，说明请求是来自customerserarch_result.jsp的“删除客户”链接.add
        {
            deleteCustomer(request, response);
        }
        else if("detail".equals(mode))//如果mode的值等于"detail"，说明请求是来自customerserarch_result.jsp的“查看”链接.add by
        {
            showDetail(request, response);
        }
    }

    private void searchCustomer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        try
        {
//            List<User> users = new UserDAO().searchCustomer(request.getParameter("cname"));
            String cname = request.getParameter("cname");
            UserDAO userDAO = new UserDAO();
            List<User> users = userDAO.searchCustomer(cname);

            if (0 == users.size())
            {
                request.setAttribute("msg", "没有找到客户信息");
                request.getRequestDispatcher("/customersearch.jsp").forward(request, response);

            }
            else
            {
                request.setAttribute("users", users);
                request.getRequestDispatcher("/customersearch_result.jsp").forward(request, response);
            }
        }
        catch (Exception e)
        {
            request.setAttribute("msg", e.getMessage());
            request.getRequestDispatcher("/customersearch.jsp").forward(request, response);
        }
    }

    private void deleteCustomer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        try
        {
//            int id = Integer.parseInt(request.getParameter("cid"));
            String strId = request.getParameter("cid");
            int id = Integer.parseInt(strId);
            UserDAO userDAO = new UserDAO();
            userDAO.delete(id);
            request.setAttribute("msg", "删除客户成功");
            request.getRequestDispatcher("/customersearch.jsp").forward(request, response);
        }
        catch (Exception e)
        {
            request.setAttribute("msg", e.getMessage());
            request.getRequestDispatcher("/customersearch.jsp").forward(request, response);
        }
    }

    private void saveCustomer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String name = request.getParameter("name");
        if("".equals(name))//客户姓名不能为空
        {
            request.setAttribute("msg", "请输入客户姓名");
            request.getRequestDispatcher("/customeradd.jsp").forward(request, response);//这里可以直接转发到 customeradd.jsp
            return;// 调整到customeradd.jsp后，函数直接返回，add by hlzhang, 20180122
        }

        User user=new User();
        user.setName(request.getParameter("name"));
        user.setAddress(request.getParameter("address"));
        user.setTel(request.getParameter("tel"));
        user.setRole("customer");
        user.setPwd("123456");

        try
        {
            new UserDAO().save(user);
            request.setAttribute("msg", "添加客户成功");
            request.getRequestDispatcher("/customersearch.jsp").forward(request, response);
        }
        catch (Exception e)
        {
            request.setAttribute("msg", e.getMessage());
            request.getRequestDispatcher("/customersearch.jsp").forward(request, response);
        }
    }

    private void showDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        try
        {
            UserDAO userDAO = new UserDAO();
            GuidedogDAO guidedogDAO = new GuidedogDAO();
            int ownerId = Integer.valueOf(request.getParameter("id"));//得到customersearch_resultl.jsp的“查看明细”链接发送来的参数客户id
            User user = userDAO.getById(ownerId);
            List<Guidedog> guidedogs = guidedogDAO.getGuidedogsByOwnerId(ownerId);
            user.setGuidedogs(guidedogs);
            request.setAttribute("user", user);
            request.getRequestDispatcher("/customerdetail.jsp").forward(request, response);
        }
        catch (Exception e)
        {
            request.setAttribute("msg", e.getMessage());
            request.getRequestDispatcher("/customersearch.jsp").forward(request, response);
        }
    }
}
