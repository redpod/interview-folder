package gd.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import gd.po.Dogtype;
import gd.po.Institution;

public class InstitutionDAO
{
    public List<Institution> getAll() throws Exception
    {
        return search("", "");
    }


    public List<Institution> search(String vname, String sname) throws Exception
    {
        List<Institution> institutions = new ArrayList<Institution>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_gd", "root", "123456");

            String sql = "select distinct t_institution.*\n" +
                    "from t_institution, t_dogtype, t_institution_dogtype\n" +
                    "where t_institution.id = t_institution_dogtype.institutionId AND\n" +
                    "t_dogtype.id = t_institution_dogtype.dogtypeId AND\n" +
                    "t_institution.name like ? AND\n" +
                    "t_dogtype.name like ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, "%" + vname + "%");
            ps.setString(2, "%" + sname + "%");
            rs = ps.executeQuery();
            while (rs.next())
            {
                Institution in = new Institution();
                in.setId(rs.getInt("id"));
                in.setName(rs.getString("name"));
                institutions.add(in);
            }

            for (Institution in : institutions)
            {
                sql = "select t_dogtype.*\n" +
                        " from t_dogtype, t_institution_dogtype\n" +
                        " where t_institution_dogtype.dogtypeId = t_dogtype.id\n" +
                        " AND   t_institution_dogtype.institutionId=?";
                ps = con.prepareStatement(sql);
                ps.setInt(1, in.getId());
                rs = ps.executeQuery();
                while (rs.next())
                {
                    Dogtype d = new Dogtype();
                    d.setId(rs.getInt("id"));
                    d.setName(rs.getString("name"));
                    in.getDogts().add(d);
                }
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
        return institutions;
    }


    public void save(Institution institution) throws Exception
    {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_gd", "root", "123456");
            //JDBC默认是事务自动提交  即所有的executeUpdate会立即更新到数据库，如果需要使用事务要 1  停止自动提交  2.在操作完成后手动提交  3 出现异常回滚
            //事务1  停止自动提交
            con.setAutoCommit(false);
            ps = con.prepareStatement("insert into t_institution value(null,?)", PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, institution.getName());
            ps.executeUpdate();
            // 2.同时取得其 id 首先需要对ps进行修改
            rs = ps.getGeneratedKeys();
            if (rs.next())
            {
                institution.setId(rs.getInt(1));
            }
            if (institution.getDogts().size() > 0)
            {
                String sql = "insert into t_institution_dogtype values (?,?)";
                for (int i = 1; i < institution.getDogts().size(); i++)
                {
                    sql += ",(?,?)";
                }
                ps=con.prepareStatement(sql);
                int i=1;
                for(Dogtype d:institution.getDogts())
                {
                    ps.setInt(i++	, institution.getId());
                    ps.setInt(i++   , d.getId());
                }
                ps.executeUpdate();
            }
//			事务2.在操作完成后手动提交
            con.commit();
        } catch (ClassNotFoundException e)
        {
            e.printStackTrace();
            throw new Exception("找不到驱动:" + e.getMessage());
        }
        catch (SQLException e)
        {
            //事务3 出现异常回滚
            if(con!=null)con.rollback();

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
    }



    public void delete(int institutionId) throws Exception
    {
        Connection con = null;
        PreparedStatement ps = null;
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_gd","root","123456");//  协议://域名(ip):端口/资源（数据库名）
            ps = con.prepareStatement("delete from t_institution where id=?");
            ps.setInt(1, institutionId);
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


}