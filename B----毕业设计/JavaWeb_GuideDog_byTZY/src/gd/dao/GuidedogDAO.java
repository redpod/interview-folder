package gd.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import gd.po.Guidedog;
public class GuidedogDAO
{
    public List<Guidedog> getGuidedogsByOwnerId(int ownerId) throws Exception
    {
        List<Guidedog> guidedogs = new ArrayList<Guidedog>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_gd","root","123456");//  协议://域名(ip):端口/资源（数据库名）
            ps = con.prepareStatement("select * from t_guidedog where ownerId=?");
            ps.setInt(1, ownerId);
            rs = ps.executeQuery();
            while(rs.next())
            {
                Guidedog guidedog=new Guidedog();
                guidedog.setBirthdate(rs.getString("birthdate"));
                guidedog.setId(rs.getInt("id"));
                guidedog.setName(rs.getString("name"));
                guidedog.setOwnerId(ownerId);
                guidedog.setPhoto(rs.getString("photo"));
                guidedogs.add(guidedog);
            }
        }
        catch(ClassNotFoundException e)
        {
            e.printStackTrace();
            throw new Exception("找不到驱动:"+e.getMessage());//异常不能在底层丢失了
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            throw new Exception("数据库操作错误:"+e.getMessage());
        }
        finally
        {
            if(rs!=null)
            {
                rs.close();
            }
            if(ps!=null)
            {
                ps.close();
            }
            if(con!=null)
            {
                con.close();
            }
        }
        return guidedogs;
    }

    public List<Guidedog> search(String guidedogName, String ownerName) throws Exception
    {
        List<Guidedog> guidedogs = new ArrayList<Guidedog>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_gd", "root", "123456");

            String sql = "select t_guidedog.*, t_user.name from t_guidedog, t_user  where t_guidedog.ownerId=t_user.id and t_guidedog.name like ? and t_user.name like ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, "%" + guidedogName + "%");
            ps.setString(2, "%" + ownerName + "%");
            rs = ps.executeQuery();
            while (rs.next())
            {
                Guidedog guidedog = new Guidedog();
                guidedog.setId(rs.getInt("id"));
                guidedog.setName(rs.getString("name"));
                guidedog.setBirthdate(rs.getString("birthdate"));
                guidedog.setPhoto(rs.getString("photo"));
                guidedog.setOwnerId(rs.getInt("ownerId"));
                guidedog.setOwnerName(rs.getString("t_user.name"));
                guidedogs.add(guidedog);
            }
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
            throw new Exception("找不到驱动:" + e.getMessage());
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            throw new Exception("SQL异常:" + e.getMessage());
        }
        finally
        {
            if (rs != null)
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
        return guidedogs;
    }



    public void delete(int guidedogId) throws Exception
    {
        Connection con = null;
        PreparedStatement ps = null;
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_gd","root","123456");//  协议://域名(ip):端口/资源（数据库名）
            ps = con.prepareStatement("delete from t_guidedog where id=?");
            ps.setInt(1,guidedogId);
            ps.executeUpdate();
        }
        catch(ClassNotFoundException e)
        {
            e.printStackTrace();
            throw new Exception("找不到驱动:"+e.getMessage());//异常不能在底层丢失了
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            throw new Exception("数据库操作错误:"+e.getMessage());
        }
        finally
        {
            if(ps!=null)
            {
                ps.close();
            }
            if(con!=null)
            {
                con.close();
            }
        }
    }


    public void save(Guidedog guidedog) throws Exception
    {
        Connection con = null;
        PreparedStatement ps = null;
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_gd","root","123456");//  协议://域名(ip):端口/资源（数据库名）
            ps = con.prepareStatement("insert into t_guidedog value(null,?,?,?,?)");
            ps.setString(1, guidedog.getName());
            ps.setString(2, guidedog.getBirthdate());
            ps.setString(3, guidedog.getPhoto());
            ps.setInt(4, guidedog.getOwnerId());
            ps.executeUpdate();
        }
        catch(ClassNotFoundException e)
        {
            e.printStackTrace();
            throw new Exception("找不到驱动:"+e.getMessage());//异常不能在底层丢失了
        }catch (SQLException e)
        {
            e.printStackTrace();
            throw new Exception("数据库操作错误:"+e.getMessage());
        }
        finally
        {
            if(ps!=null)
            {
                ps.close();
            }
            if(con!=null)
            {
                con.close();
            }
        }
    }
}