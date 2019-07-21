package gd.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import java.util.List;


import gd.dao.GuidedogDAO;
import gd.dao.InstitutionDAO;
import gd.po.Guidedog;
import gd.dao.CultivateDAO;
import gd.po.Institution;
import gd.po.Cultivate;

//@WebServlet(name = "CultivateServlet")
@WebServlet("/CultivateServlet")
public class CultivateServlet extends HttpServlet
{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String mode = request.getParameter("mode");
        if("search".equals(mode))
        {
            searchGuidedog(request, response);
        }
        else if("saveInformationHistory".equals(mode))
        {
            saveInformationHistory(request, response);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String mode = request.getParameter("mode");
        if ("showInformationHistory".equals(mode))
        {
            showInformationHistory(request, response);
        }
        else if ("addInformationHistory".equals(mode))
        {
            addInformationHistory(request, response);
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
                request.getRequestDispatcher("/cultivateSearch.jsp").forward(request, response);
            }
            else
            {
                request.setAttribute("guidedogs", guidedogs);
                request.getRequestDispatcher("/cultivateSearchResult.jsp").forward(request, response);
            }
        }
        catch (Exception e)
        {
            request.setAttribute("msg", e.getMessage());
            request.getRequestDispatcher("/cultivateSearch.jsp").forward(request, response);
        }
    }

    private void showInformationHistory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        try
        {
            CultivateDAO cultivateDAO = new CultivateDAO();
            List<Cultivate> cultivates = cultivateDAO.getCultivatesByGuidedogId(Integer.parseInt(request.getParameter("petId")));
            request.setAttribute("cultivates", cultivates);
            if (0 == cultivates.size())
            {
                request.setAttribute("msg", "没有找到历史领养信息");
            }
            request.getRequestDispatcher("/showInformationHistory.jsp").forward(request, response);
        }
        catch (Exception e)
        {
            request.setAttribute("msg", e.getMessage());
            request.getRequestDispatcher("/showInformationHistory.jsp").forward(request, response);
        }
    }

    private void addInformationHistory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        try
        {
            InstitutionDAO institutionDAO = new InstitutionDAO();
            List<Institution> institutions = institutionDAO.getAll();
            request.setAttribute("institutions", institutions);
            request.getRequestDispatcher("/addInformationHistory.jsp").forward(request, response);
        }
        catch (Exception e)
        {
            request.setAttribute("msg", e.getMessage());
            request.getRequestDispatcher("/cultivateSearch.jsp").forward(request, response);
        }
    }

    private void saveInformationHistory(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException
    {
        try
        {
            Cultivate cultivate = new Cultivate();
            cultivate.setGuidedogId(Integer.parseInt(request.getParameter("pid")));
            cultivate.setInstitutionId(Integer.parseInt(request.getParameter("vid")));

            cultivate.setDescription(request.getParameter("description"));
            cultivate.setTreatment(request.getParameter("treatment"));
            CultivateDAO cultivateDAO = new CultivateDAO();
            cultivateDAO.save(cultivate);
            String guidedogName = request.getParameter("guidedogName");
            request.setAttribute("msg", "导盲犬"+ guidedogName + "的领养信息添加成功");
            request.getRequestDispatcher("/cultivateSearch.jsp").forward(request, response);
        }
        catch (Exception e)
        {
            request.setAttribute("msg", e.getMessage());
            request.getRequestDispatcher("/cultivateSearch.jsp").forward(request, response);
        }
    }
}