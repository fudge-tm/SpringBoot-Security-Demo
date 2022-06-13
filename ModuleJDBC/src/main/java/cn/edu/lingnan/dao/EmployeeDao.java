package cn.edu.lingnan.dao;

import cn.edu.lingnan.pojo.Employee;

import java.util.List;

public interface EmployeeDao {

    //        由员工id查询
    Employee querySingleByeidUnadmin(Class<Employee> clazz, String eid);

    Employee querySingleByeid(Class<Employee> clazz, String eid);

    Employee queryhisSingleByeid(Class<Employee> clazz, String eid);

    public List<Employee> queryAllEmployee(Class<Employee> clazz);

    public List<Employee> queryMultiAllEmployee();

    public Employee finstaffBynameAndPassword(String ename, String password);

    public List<Employee> queryMultiByename(Class<Employee> clazz, String ename);

    public boolean updateEmployee(Employee e);

    boolean updateEmployeeSuperuser(Employee e);

    public boolean updateEmployeePassword(String ename, String newPassword);

    public boolean insertEmployee(Employee e);

    public List<Employee> selectByPage(int began, int size);

    public int selectTotalCount();

    public List<Employee> selectByPageAndCondition(int begin, int size, Employee employee);

    int selectTotalCountByCondition(Employee employee);

    boolean add(Employee employee);

    public Employee finstaffByidAndPassword(String eid, String password);

    public List<Employee> selectByCondition(Employee employee);
}
