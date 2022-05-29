package cn.edu.lingnan.dao;

import cn.edu.lingnan.pojo.Salary;

import java.util.List;

public interface SalaryDao {
    public boolean deleteSalary(Salary s);

    public boolean insertSalary(Salary s);

    public boolean updateSalary(Salary s);

    public List<Salary> queryMultiAllSalary();

    public List<Salary> queryMultiByeid(Class<Salary> clazz, String eid);

    public Salary querySingleBysid(Class<Salary> clazz, Object... parameters);

}
