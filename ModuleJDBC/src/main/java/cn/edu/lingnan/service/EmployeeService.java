package cn.edu.lingnan.service;

import cn.edu.lingnan.pojo.Employee;
import cn.edu.lingnan.pojo.PageBean;

import java.util.List;

/**
 * EmployeeService接口
 */
public interface EmployeeService {

    //由员工id查询
    Employee querySingleByeid(Class<Employee> clazz, String eid);

    Employee querySingleByeidUnadmin(Class<Employee> clazz, Employee employee);

    boolean updateEmployeeSuperuser(Employee e);

    public List<Employee> queryAllEmployee(Class<Employee> clazz);

    public List<Employee> queryMultiAllEmployee();

    public Employee finstaffBynameAndPassword(String ename, String password);

    public Employee finstaffByidAndPassword(String eid, String password);

    public List<Employee> queryMultiByename(Class<Employee> clazz, String ename);

    public boolean updateEmployee(Employee e);

    public boolean updateEmployeePassword(String ename, String oldPassword, String newPassword);

    public boolean deleteEmployeeByEid(String id);

    public boolean insertEmployee(Employee e);

    public PageBean<Employee> selectByPageAndCondition(int currentPage, int pageSize, Employee employee);

    public PageBean<Employee> selectPage(int currentPage, int pageSize);

    void deleteByIds(String[] ids);

    boolean register(Employee employee);

    boolean unexiteid(Employee employee);

    List<Employee> selectByCondition(Employee employee);
}
