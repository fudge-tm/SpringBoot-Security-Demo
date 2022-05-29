package cn.edu.lingnan.dao;

import cn.edu.lingnan.pojo.Employee;
import cn.edu.lingnan.utils.JDBCUtilsByDruid;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * 员工类dao
 */
public class EmployeeDaoMySQLImpl extends BasicDAO<Employee> implements EmployeeDao {

    private QueryRunner qre = new QueryRunner();


    //        由员工id查询
    public Employee querySingleByeid(Class<Employee> clazz, Object... parameters) {
        Connection connection = null;
        String sql = "select * from employee where eid = ?";
        try {
            connection = JDBCUtilsByDruid.getConnection();
            return qre.query(connection, sql, new BeanHandler<Employee>(clazz), parameters);

        } catch (SQLException e) {
            throw new RuntimeException(e); //将编译异常->运行异常 ,抛出
        } finally {
            JDBCUtilsByDruid.close(null, null, connection);
        }
    }

    //查询所有员工
    public List<Employee> queryAllEmployee(Class<Employee> clazz) {
        Connection connection = null;
        String sql = "select * from employee";
        try {
            connection = JDBCUtilsByDruid.getConnection();
            return qre.query(connection, sql, new BeanListHandler<Employee>(clazz));

        } catch (SQLException e) {
            throw new RuntimeException(e); //将编译异常->运行异常 ,抛出
        } finally {
            JDBCUtilsByDruid.close(null, null, connection);
        }
    }

    //查询所有员工
    public List<Employee> queryMultiAllEmployee() {
        String sql = "select * from employee";
        return queryMulti(sql, Employee.class);
    }

    //由姓名密码查询员工
    public Employee finstaffBynameAndPassword(String ename, String password) {
        Connection connection = null;
        String sql = "select * from employee where ename= ? and password= ?";
        try {
            connection = JDBCUtilsByDruid.getConnection();
            Employee e = querySingle(sql, Employee.class, ename, password);
            return e;
        } catch (SQLException e) {
            throw new RuntimeException(e); //将编译异常->运行异常 ,抛出
        } finally {
            JDBCUtilsByDruid.close(null, null, connection);
        }
    }


    //由姓名查询员工
    public List<Employee> queryMultiByename(Class<Employee> clazz, String ename) {
        Connection connection = null;
        ename = "%" + ename + "%";
        String sql = "select * from employee where ename like ?";
        try {
            connection = JDBCUtilsByDruid.getConnection();
            return qre.query(connection, sql, new BeanListHandler<Employee>(clazz), ename);

        } catch (SQLException e) {
            throw new RuntimeException(e); //将编译异常->运行异常 ,抛出
        } finally {
            JDBCUtilsByDruid.close(null, null, connection);
        }
    }

//    public int insertUpdateEmployee(String sql,Class<Employee> clazz,Object... parameters){
//        Connection connection = null;
//        try {
//            connection = JDBCUtilsByDruid.getConnection();
//            return  qre.update(connection, sql,parameters);
//
//        } catch (SQLException e) {
//            throw  new RuntimeException(e); //将编译异常->运行异常 ,抛出
//        } finally {
//            JDBCUtilsByDruid.close(null, null, connection);
//        }
//    }

    //更新员工表
    public boolean updateEmployee(Employee e) {
        boolean flag = false;
        String sql = "update employee set ename=?,password=?,superuser=?,flag=? where eid=?";
        int row = dml(sql, e.getEname(), e.getPassword(), e.getSuperuser(), e.getFlag(), e.getEid());
        if (row > 0)
            flag = true;
        return flag;
    }

    // 由姓名和旧密码更新员工新密码
    public boolean updateEmployeePassword(String ename, String newPassword) {
        boolean flag = false;
        String sql = "update employee set password=? where ename=?";
        int row = dml(sql, newPassword, ename);
        if (row > 0)
            flag = true;
        return flag;
    }

    //插入员工
    public boolean insertEmployee(Employee e) {
        boolean flag = false;
        String sql = "insert into employee VALUES(?,?,?,?,?)";
        int row = dml(sql, e.getEid(), e.getEname(), e.getPassword(), e.getSuperuser(), e.getFlag());
        if (row > 0)
            flag = true;
        return flag;
    }

    @Override
    public List<Employee> selectByPage(int began, int size) {
        String sql = "select * from employee limit ?,?";
        Connection connection = null;
        try {
            connection = JDBCUtilsByDruid.getConnection();
            return qre.query(connection, sql, new BeanListHandler<Employee>(Employee.class), began, size);

        } catch (SQLException e) {
            throw new RuntimeException(e); //将编译异常->运行异常 ,抛出
        } finally {
            JDBCUtilsByDruid.close(null, null, connection);
        }
    }

    @Override
    public int selectTotalCount() {
        String sql = "select count(*) from employee";
//        Connection connection = null;
//            connection = JDBCUtilsByDruid.getConnection();
//            List<Employee> employees = qre.query(connection, sql, new BeanListHandler<Employee>(Employee.class));
//            return employees.size();
        return (int) queryScalar(sql);


    }

    @Override
    public List<Employee> selectByPageAndCondition(int begin, int size, Employee employee) {
        String sql = "select * from employee where 1=1";

        if (employee != null) {
//            if ((employee.getEid() != null) && (!(employee.getEid().equals("")))) {
            if ((employee.getEid() != null) && (employee.getEid().length() > 0)) {

//                System.out.println(employee.getEid() == "");
//                System.out.println(employee.getEid().equals(""));
                sql = sql + " and eid like '" + employee.getEid() + "'";
            }
//            if ((employee.getEname() != null) && (!(employee.getEname().equals("")))) {
            if ((employee.getEname() != null) && (employee.getEname().length() > 0)) {

//                System.out.println(employee.getEname() == "");
//                System.out.println(employee.getEname().equals(""));
                sql = sql + " and ename like '" + employee.getEname() + "'";
            }
        }

        sql = sql + " limit " + begin + "," + size;
        System.out.println(sql);
        Connection connection = null;
        try {
            connection = JDBCUtilsByDruid.getConnection();
            return qre.query(connection, sql, new BeanListHandler<Employee>(Employee.class));

        } catch (SQLException e) {
            throw new RuntimeException(e); //将编译异常->运行异常 ,抛出
        } finally {
            JDBCUtilsByDruid.close(null, null, connection);
        }

    }

    @Override
    public int selectTotalCountByCondition(Employee employee) {
        String sql = "select count(*) from employee where 1=1";

        if (employee != null) {
//            if ((employee.getEid() != null) && (!(employee.getEid().equals("")))) {
            if ((employee.getEid() != null) && (employee.getEid().length() > 0)) {

//                System.out.println(employee.getEid() == "");
//                System.out.println(employee.getEid().equals(""));
                sql = sql + " and eid like '" + employee.getEid() + "'";
            }
//            if ((employee.getEname() != null) && (!(employee.getEname().equals("")))) {
            if ((employee.getEname() != null) && (employee.getEname().length() > 0)) {

//                System.out.println(employee.getEname() == "");
//                System.out.println(employee.getEname().equals(""));
                sql = sql + " and ename like '" + employee.getEname() + "'";
            }
        }

        Number num = (Number) queryScalar(sql);
        return num.intValue();

    }

    @Override
    public boolean add(Employee e) {
        boolean flag = false;
        String sql = "insert into employee VALUES(?,?,?,?,?)";
        int row = dml(sql, e.getEid(), e.getEname(), e.getPassword(), 2, 1);
        if (row > 0)
            flag = true;
        return flag;
    }


//    分页查询


}
