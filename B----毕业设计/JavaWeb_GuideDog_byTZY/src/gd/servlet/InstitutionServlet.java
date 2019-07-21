package gd.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import java.util.List;
import gd.dao.DogtypeDAO;
import gd.dao.InstitutionDAO;
import gd.po.Dogtype;
import gd.po.Institution;


@WebServlet( "/InstitutionServlet")
public class InstitutionServlet extends HttpServlet
{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String m = request.getParameter("m");
        if ("search".equals(m))
        {
            search(request, response);
        }
        else if ("addDogt".equals(m))
        {
            addDogt(request, response);
        }
        else if ("addInstitution".equals(m))
        {
            addInstitution(request, response);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        try
        {
            String mode = request.getParameter("mode");
            if ("deleteInstitution".equals(mode))
            {
                String strInstitutionID = request.getParameter("institutionId");
                int institutionID = Integer.parseInt(strInstitutionID);
                InstitutionDAO institutionDAO = new InstitutionDAO();
                institutionDAO.delete(institutionID);
                request.setAttribute("msg", "成功删除导盲犬基地：" + request.getParameter("institutionName"));
                request.getRequestDispatcher("/institutionsearch.jsp").forward(request, response);
            }
            else if ("newDogt".equals(mode))
            {
                request.getRequestDispatcher("/dogtypeAdd.jsp").forward(request, response);
            } else if ("newInstitution".equals(mode))
            {
                request.setAttribute("dogts", new DogtypeDAO().getAll());
                request.getRequestDispatcher("/institutionadd.jsp").forward(request, response);
            } else
            {
                request.getRequestDispatcher("/institutionsearch.jsp").forward(request, response);
            }
        }
        catch (Exception e)
        {
            request.setAttribute("msg", e.getMessage());
            request.getRequestDispatcher("/institutionsearch.jsp").forward(request, response);
        }
    }

    private void search(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String vname = request.getParameter("vname");
        String sname = request.getParameter("sname");
        try
        {
            List<Institution> institutions = new InstitutionDAO().search(vname, sname);
            if (0 == institutions.size())
            {
                request.setAttribute("msg", "没有相关导盲犬培养基地信息");
                request.getRequestDispatcher("/institutionsearch.jsp").forward(request, response);
            } else
            {
                request.setAttribute("institutions", institutions);
                request.getRequestDispatcher("/institutionsearch_result.jsp").forward(request, response);
            }
        }
        catch (Exception e)
        {
            request.setAttribute("msg", e.getMessage());
            request.getRequestDispatcher("/institutionsearch.jsp").forward(request, response);
        }
    }

    private void addDogt(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        Dogtype dogt = new Dogtype();
        String dogtName = request.getParameter("dogtName");
        String dogtDesc = request.getParameter("dogtDesc");
        if ("".equals(dogtName))
        {
            request.setAttribute("msg", "请输入导盲犬种类名称");
            request.getRequestDispatcher("/dogtypeAdd.jsp").forward(request, response);
        }
        else
        {
            dogt.setName(dogtName);
            dogt.setDesc(dogtDesc);
            try
            {
                new DogtypeDAO().save(dogt);
                request.setAttribute("msg", "添加新导盲犬种类成功:" + dogt.getName());
                request.getRequestDispatcher("/institutionsearch.jsp").forward(request, response);
            }
            catch (Exception e)
            {
                request.setAttribute("msg", e.getMessage());
                doGet(request, response);
            }
        }
    }

    private void addInstitution(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        try
        {
            Institution institution = new Institution();
            String[] sids = request.getParameterValues("sid");
            String vname = request.getParameter("vname");
            if ("".equals(vname))
            {
                request.setAttribute("msg", "请输入导盲犬培养基地姓名");
                request.setAttribute("dogts", new DogtypeDAO().getAll());
                request.getRequestDispatcher("/institutionadd.jsp").forward(request, response);
                return;
            }
            else if (null == sids || 0 == sids.length)
            {
                request.setAttribute("msg", "请选择导盲犬种类");
                request.setAttribute("dogts", new DogtypeDAO().getAll());
                request.getRequestDispatcher("/institutionadd.jsp").forward(request, response);
                return;
            }
            else
            {
                institution.setName(vname);
                for (String sid : sids)
                {
                    Dogtype d = new Dogtype();
                    d.setId(Integer.parseInt(sid));
                    institution.getDogts().add(d);
                }
                new InstitutionDAO().save(institution);
                request.setAttribute("msg", "添加新导盲犬培养基地成功:" + institution.getName());
                request.getRequestDispatcher("/institutionsearch.jsp").forward(request, response);
            }
        }
        catch (Exception e)
        {
            request.setAttribute("msg", e.getMessage());
            doGet(request, response);
        }
    }
}