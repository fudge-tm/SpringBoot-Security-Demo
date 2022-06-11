package cn.edu.lingnan.service;

import cn.edu.lingnan.dao.EmployeeDaoMySQLImpl;
import cn.edu.lingnan.dao.SalaryDaoMySQLImpl;
import cn.edu.lingnan.pojo.Employee;
import cn.edu.lingnan.pojo.PageBean;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * EmployeeServiceMySQLImpl实现类
 */

public class EmployeeServiceMySQLImpl implements EmployeeService {
    EmployeeDaoMySQLImpl employeeDao = new EmployeeDaoMySQLImpl();
    SalaryDaoMySQLImpl salaryDao = new SalaryDaoMySQLImpl();


    //由员工id查询
    @Override
    public Employee querySingleByeid(Class<Employee> clazz, Object... parameters) {
        return employeeDao.querySingleByeid(clazz, parameters);
    }

    @Override
    public Employee querySingleByeidUnadmin(Class<Employee> clazz, Object... parameters) {
        return employeeDao.querySingleByeidUnadmin(clazz, parameters);
    }


    //查询所有员工
    @Override
    public List<Employee> queryAllEmployee(Class<Employee> clazz) {
        return employeeDao.queryAllEmployee(clazz);
    }

    //查询所有员工
    @Override
    public List<Employee> queryMultiAllEmployee() {
        return employeeDao.queryMultiAllEmployee();
    }

    //由姓名密码查询员工
    @Override
    public Employee finstaffBynameAndPassword(String ename, String password) {
        return employeeDao.finstaffBynameAndPassword(ename, password);
    }

    @Override
    public Employee finstaffByidAndPassword(String eid, String password) {
        return employeeDao.finstaffByidAndPassword(eid, password);
    }

    //由姓名查询员工
    @Override
    public List<Employee> queryMultiByename(Class<Employee> clazz, String ename) {
        return employeeDao.queryMultiByename(clazz, ename);
    }

    //更新员工表
    @Override
    public boolean updateEmployee(Employee e) {
        return employeeDao.updateEmployee(e);
    }

    //由姓名和旧密码更新员工新密码
    @Override
    public boolean updateEmployeePassword(String ename, String oldPassword, String newPassword) {
        boolean flag = false;
        Employee employee = employeeDao.finstaffBynameAndPassword(ename, oldPassword);
        if (employee != null) {
            Boolean aBoolean = employeeDao.updateEmployeePassword(ename, newPassword);
            if (aBoolean) {
                flag = true;
            }
        }
        return flag;
    }

    //由员工id删除员工
    @Override
    public boolean deleteEmployeeByEid(String id) {
        boolean flag = false;
//        先删除薪水表的id,再删除employee表
//        String sql = "delete from salary where eid=?";
//        int rows1 = salaryDao.dml(sql, id);

//
//        Employee employee = querySingleBysid(Employee.class, id);

        String sql2 = "delete from employee where eid=?";
        int rows2 = employeeDao.dml(sql2, id);
        System.out.println("**" + rows2 + "**");
        if (rows2 > 0)
            flag = true;
//        想要出错回滚
        System.out.println("%%" + flag + "%%");
        return flag;
    }

    //插入员工
    @Override
    public boolean insertEmployee(Employee e) {
        boolean flag = true;
        List<Employee> employees = employeeDao.queryAllEmployee(Employee.class);

        for (Employee employee : employees) {
            if (employee.getEid().equals(e.getEid())) {
                flag = false;
            }
        }
        if (flag == false) {
            return flag;
        }
        return employeeDao.insertEmployee(e);
    }

    @Override
    public PageBean<Employee> selectByPageAndCondition(int currentPage, int pageSize, Employee employee) {
        //        计算开始索引
        int begin = (currentPage - 1) * pageSize;
//        计算查询条目数
        int size = pageSize;

//        处理employee条件，模糊表达式
        if (employee != null) {
            String employeeName = employee.getEid();
            if (employeeName != null && employeeName.length() > 0) {
                employee.setEid("%" + employeeName + "%");
            }

            String companyName = employee.getEname();
            if (companyName != null && companyName.length() > 0) {
                employee.setEname("%" + companyName + "%");
            }
        }
//        查询当前页数据
        List<Employee> rows = employeeDao.selectByPageAndCondition(begin, size, employee);

//        查询总记录数
        int totalCount = employeeDao.selectTotalCountByCondition(employee);

        PageBean<Employee> pageBean = new PageBean<>();
        pageBean.setRows(rows);
        pageBean.setTotalCount(totalCount);


        return pageBean;
    }

    @Test
    public void testsel() {
        EmployeeService employeeService = new EmployeeServiceMySQLImpl();
        Employee employee = new Employee();
        PageBean<Employee> employeePageBean = employeeService.selectByPageAndCondition(2, 2, employee);
        System.out.println(employeePageBean.getRows());
        System.out.println(employeePageBean.getTotalCount());

    }

    @Override
    public PageBean<Employee> selectPage(int currentPage, int pageSize) {

//        计算开始索引
        int begin = (currentPage - 1) * pageSize;
//        计算查询条目数
        int size = pageSize;

//        查询当前页数据
        List<Employee> rows = employeeDao.selectByPage(begin, size);

//        查询总记录数
        int totalCount = employeeDao.selectTotalCount();

        PageBean<Employee> pageBean = new PageBean<>();
        pageBean.setRows(rows);
        pageBean.setTotalCount(totalCount);

        return pageBean;
    }

    @Override
    public void deleteByIds(String[] ids) {
        for (String id : ids) {
            this.deleteEmployeeByEid(id);
        }
    }

    @Override
    public boolean register(Employee employee) {
        Employee employee1 = employeeDao.querySingleByeid(Employee.class, employee.getEid());
        Employee employee2 = employeeDao.queryhisSingleByeid(Employee.class, employee.getEid());
        if (employee1 == null && employee2 == null) {
            employeeDao.add(employee);
            return true;
        }
        return false;
    }

    @Override
    public boolean unexiteid(Employee employee) {
        Employee employee1 = employeeDao.querySingleByeid(Employee.class, employee.getEid());
        Employee employee2 = employeeDao.queryhisSingleByeid(Employee.class, employee.getEid());
        if (employee1 == null && employee2 == null) {

            return true;
        }
        return false;
    }

    @Override
    public List<Employee> selectByCondition(Employee employee) {
        return employeeDao.selectByCondition(employee);
    }


}
