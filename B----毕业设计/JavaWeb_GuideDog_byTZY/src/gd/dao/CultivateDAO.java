package gd.dao;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import gd.po.Cultivate;
public class CultivateDAO
{
    public List<Cultivate> getCultivatesByGuidedogId(int guidedogId) throws Exception
    {
        List<Cultivate> cultivates = new ArrayList<Cultivate>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_gd", "root", "123456");
            ps = con.prepareStatement("select t_institution.name, t_cultivate.*\n" +
                    " from t_institution, t_cultivate\n" +
                    " where t_institution.id = t_cultivate.institutionId\n" +
                    " and   t_cultivate.guidedogId=?");
            ps.setInt(1,guidedogId);
            rs = ps.executeQuery();
            while(rs.next())
            {
                Cultivate cultivate=new Cultivate();
                cultivate.setId(rs.getInt("id"));
                cultivate.setGuidedogId(guidedogId);
                cultivate.setInstitutionId(rs.getInt("institutionId"));
                cultivate.setCultivatedate(rs.getString("cultivatedate"));
                cultivate.setDescription(rs.getString("description"));
                cultivate.setTreatment(rs.getString("treatment"));
                cultivate.setInstitutionName(rs.getString("name"));
                cultivates.add(cultivate);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw new Exception("数据库访问出现异常:" + e);
        }
        finally
        {
            if(rs!=null)
            {
                rs.close();
            }
            if (ps != null)
            {
                ps.close();
            }
            if (con != null)
            {
                con.close();
            }
        }
        return cultivates;
    }


    public void save(Cultivate cultivate) throws Exception
    {
        Connection con = null;
        PreparedStatement ps = null;
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_gd","root", "123456");// 协议://域名(ip):端口/资源（数据库名）

            ps = con.prepareStatement("insert into t_cultivate value(null,?,?,NOW(),?,?)");
            ps.setInt(1, cultivate.getGuidedogId());
            ps.setInt(2, cultivate.getInstitutionId());
            ps.setString(3, cultivate.getDescription());
            ps.setString(4, cultivate.getTreatment());

            ps.executeUpdate();
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
            throw new Exception("找不到驱动:" + e.getMessage());// 异常不能在底层丢失了
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            throw new Exception("数据库操作错误:" + e.getMessage());
        }
        finally
        {
            if (ps != null)
            {
                ps.close();
            }
            if (con != null)
            {
                con.close();
            }
        }
    }
}