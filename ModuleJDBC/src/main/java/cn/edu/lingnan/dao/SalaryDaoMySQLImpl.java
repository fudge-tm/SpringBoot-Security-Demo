package cn.edu.lingnan.dao;

import cn.edu.lingnan.pojo.Salary;
import cn.edu.lingnan.utils.JDBCUtilsByDruid;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * 工资类dao
 */
public class SalaryDaoMySQLImpl extends BasicDAO<Salary> implements SalaryDao {
    private QueryRunner qrs = new QueryRunner();

    //由员工id和职位id查询工资
    public Salary querySingleBysid(Class<Salary> clazz, Object... parameters) {
        Connection connection = null;
//              String sql="select * from salary where eid='"+eid+"'and tid='"+tid+"'";
        String sql = "select * from salary where eid= ? and tid= ?";
        try {
            connection = JDBCUtilsByDruid.getConnection();
            return qrs.query(connection, sql, new BeanHandler<Salary>(clazz), parameters);

        } catch (SQLException e) {
            throw new RuntimeException(e); //将编译异常->运行异常 ,抛出
        } finally {
            JDBCUtilsByDruid.close(null, null, connection);
        }
    }

//   public T querySingle(String sql, Class<T> clazz, Object... parameters) {
//
//       Connection connection = null;
//       try {
//           connection = JDBCUtilsByDruid.getConnection();
//           return  qr.query(connection, sql, new BeanHandler<T>(clazz), parameters);
//
//       } catch (SQLException e) {
//           throw  new RuntimeException(e); //将编译异常->运行异常 ,抛出
//       } finally {
//           JDBCUtilsByDruid.close(null, null, connection);
//       }
//   }

    //        由员工id查询工资
    public List<Salary> queryMultiByeid(Class<Salary> clazz, String eid) {
        Connection connection = null;
        String sql = "select * from salary where eid = ?";
        try {
            connection = JDBCUtilsByDruid.getConnection();
            return qrs.query(connection, sql, new BeanListHandler<Salary>(clazz), eid);

        } catch (SQLException e) {
            throw new RuntimeException(e); //将编译异常->运行异常 ,抛出
        } finally {
            JDBCUtilsByDruid.close(null, null, connection);
        }
    }

    //        查询所有工资
    public List<Salary> queryMultiAllSalary() {
        String sql = "select * from salary";
        return queryMulti(sql, Salary.class);
    }

    //        更新工资表
    public boolean updateSalary(Salary s) {
        boolean flag = false;
        String sql = "update salary set smoney=?,flag=? where eid=? and tid=?";
        int row = dml(sql, s.getSmoney(), s.getFlag(), s.getEid(), s.getTid());
        if (row > 0)
            flag = true;
        return flag;
    }

    //        插入工资记录
    public boolean insertSalary(Salary s) {
        boolean flag = false;
        String sql = "insert into salary VALUES(?,?,?,?)";
        int row = dml(sql, s.getEid(), s.getTid(), s.getSmoney(), s.getFlag());
        if (row > 0)
            flag = true;
        return flag;
    }

    //       删除工资记录
    public boolean deleteSalary(Salary s) {
        boolean flag = false;
        String sql = "delete from salary where eid=? and tid=?";
        int row = dml(sql, s.getEid(), s.getTid());
        if (row > 0)
            flag = true;
        return flag;
    }

}
