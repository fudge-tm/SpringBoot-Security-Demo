package cn.edu.lingnan.service;

import cn.edu.lingnan.pojo.Salary;

import java.util.List;

/**
 * SalaryService接口
 */
public interface SalaryService {
    public Salary querySingleBysid(Class<Salary> clazz, Object... parameters);

    public List<Salary> queryMultiByeid(Class<Salary> clazz, String eid);

    public List<Salary> queryMultiAllSalary();

    public boolean updateSalary(Salary s);

    public boolean deleteSalary(Salary s);

    public boolean insertSalary(Salary s);
}
