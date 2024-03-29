package gd.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import gd.dao.UserDAO;
import gd.po.User;
import gd.utils.MD5Util;
@WebServlet( "/LoginServlet")
//Login Servlet
public class LoginServlet extends HttpServlet
{
    private static final long serialVersionUID = 1L;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        try{
            /* 0.取出表单中的用户提交数据 */
            String username=request.getParameter("username");
            System.out.println(username);
            String pwd=request.getParameter("pwd");
            String usercode=request.getParameter("usercode");//用户输入的验证码

            //1.验证验证码
            String realcode=(String) request.getSession(true).getAttribute("realcode");//session中的验证码
            if(!realcode.equalsIgnoreCase(usercode))//如果两个验证码不一致
//            if(false)
            {
                request.setAttribute("msg", "验证码输入错误");
                request.getRequestDispatcher("/index.jsp").forward(request, response);
            }
            else
            {
                User user = new UserDAO().getByName(username);
                System.out.println(user.getPwd().toString());
                if(null == user)
                {
                    request.setAttribute("msg", "用户名不存在");
                    request.getRequestDispatcher("/index.jsp").forward(request, response);
                }
//                else if(!user.getPwd().equals(pwd))
//                !user.getPwd().equals(MD5Util.MD5(pwd))
                else if(!user.getPwd().equals(pwd))
                {
                    request.setAttribute("msg", "密码输入错误");
                    request.getRequestDispatcher("/index.jsp").forward(request, response);
                }
                else
                {
                    request.getSession(true).setAttribute("user", user);
                    if(user.getRole().equals("customer"))
                    {
                        request.setAttribute("msg", "客户" + user.getName() + "登录成功");
                    }
                    else if(user.getRole().equals("admin"))
                    {
                        request.setAttribute("msg", "系统管理员" + user.getName() + "登录成功");
                    }
                    else
                    {
                        request.setAttribute("msg", user.getName() + "登录成功");
                    }
                    request.getRequestDispatcher("/institutionsearch.jsp").forward(request, response);
                }
            }
        }
        catch(Exception e)
        {
            request.setAttribute("msg", e.getMessage());
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        //do nothing
    }

    protected void doNothing()
    {

    }
}