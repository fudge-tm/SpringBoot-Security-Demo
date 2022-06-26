package cn.edu.lingnan.dao;

import cn.edu.lingnan.pojo.Employee;
import org.apache.commons.dbutils.QueryRunner;

import java.util.List;

/**
 * 员工类dao
 */
public class EmployeeDaoMySQLImpl extends BasicDAO<Employee> implements EmployeeDao {

    private QueryRunner qre = new QueryRunner();


    //        由员工id查询
    @Override
    public Employee querySingleByeidUnadmin(Class<Employee> clazz, String eid) {
        String sql = "select * from employee where eid = ? and superuser!=1";
        return querySingle(sql, clazz, eid);
    }


    @Override
    public Employee querySingleByeid(Class<Employee> clazz, String eid) {
        String sql = "select * from employee where eid = ?";
        return querySingle(sql, clazz, eid);

    }

    @Override
    public Employee queryhisSingleByeid(Class<Employee> clazz, String eid) {
        String sql = "select * from history_employee where eid = ?";
        return querySingle(sql, clazz, eid);
    }

    //查询所有员工
    public List<Employee> queryAllEmployee(Class<Employee> clazz) {
        String sql = "select * from employee where superuser!=1";
        return queryMulti(sql, clazz);
    }

    //查询所有员工
    public List<Employee> queryMultiAllEmployee() {
        String sql = "select * from employee";
        return queryMulti(sql, Employee.class);
    }

    //由姓名密码查询员工
    public Employee finstaffBynameAndPassword(String ename, String password) {

        String sql = "select * from employee where ename= ? and password= ?";
        Employee e = querySingle(sql, Employee.class, ename, password);
        return e;

    }

    public Employee finstaffByidAndPassword(String eid, String password) {
        String sql = "select * from employee where eid= ? and password= ?";
        Employee e = querySingle(sql, Employee.class, eid, password);
        return e;

    }


    //由姓名查询员工
    public List<Employee> queryMultiByename(Class<Employee> clazz, String ename) {

        ename = "%" + ename + "%";
        String sql = "select * from employee where ename like ?";
        return queryMulti(sql, clazz, ename);

    }

    //更新员工表
    public boolean updateEmployee(Employee e) {
        boolean flag = false;
        String sql = "update employee set ename=?,password=?,superuser=?,flag=? where eid=?";
        int row = dml(sql, e.getEname(), e.getPassword(), e.getSuperuser(), e.getFlag(), e.getEid());
        if (row > 0)
            flag = true;
        return flag;
    }

    @Override
    public boolean updateEmployeeSuperuser(Employee e) {
        boolean flag = false;
        String sql = "update employee set superuser=? where eid=?";
        int row = dml(sql, e.getSuperuser(), e.getEid());
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
        return queryMulti(sql, Employee.class, began, size);

    }

    @Override
    public int selectTotalCount() {
        String sql = "select count(*) from employee";
        return (int) queryScalar(sql);


    }

    @Override
    public List<Employee> selectByPageAndCondition(int begin, int size, Employee employee) {
        String sql = "select * from employee where 1=1";
        if (employee != null) {
            if ((employee.getEid() != null) && (employee.getEid().length() > 0)) {
                sql = sql + " and eid like '%" + employee.getEid() + "%'";
            }
            if ((employee.getEname() != null) && (employee.getEname().length() > 0)) {
                sql = sql + " and ename like '%" + employee.getEname() + "%'";
            }
        }
        sql = sql + " limit " + begin + "," + size;
        return queryMulti(sql, Employee.class);


    }

    @Override
    public List<Employee> selectByCondition(Employee employee) {
        String sql = "select * from employee where 1=1";
        if (employee != null) {
            if ((employee.getEid() != null) && (employee.getEid().length() > 0)) {
                sql = sql + " and eid like '%" + employee.getEid() + "%'";
            }
            if ((employee.getEname() != null) && (employee.getEname().length() > 0)) {
                sql = sql + " and ename like '%" + employee.getEname() + "%'";
            }
        }
        sql = sql + " and superuser!=1";
        System.out.println(sql);
        return queryMulti(sql, Employee.class);
    }

    @Override
    public int selectTotalCountByCondition(Employee employee) {
        String sql = "select count(*) from employee where 1=1";
        if (employee != null) {
            if ((employee.getEid() != null) && (employee.getEid().length() > 0)) {
                sql = sql + " and eid like '" + employee.getEid() + "'";
            }
            if ((employee.getEname() != null) && (employee.getEname().length() > 0)) {
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


}
