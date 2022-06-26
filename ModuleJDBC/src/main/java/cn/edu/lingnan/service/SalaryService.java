package cn.edu.lingnan.service;

import cn.edu.lingnan.pojo.Salary;

import java.util.List;

/**
 * SalaryService接口
 */
public interface SalaryService {

    //由员工id和职位id查询工资
    Salary querySingleBysid(Class<Salary> clazz, Salary salary);

    Salary querySingleByeid(Class<Salary> clazz, Salary salary);

    List<Salary> selectByCondition(Salary salary);

    public List<Salary> queryMultiByeid(Class<Salary> clazz, String eid);

    Salary queryhisSingleBysid(Class<Salary> clazz, Salary salary);

    public List<Salary> queryMultiAllSalary();

    public boolean updateSalary(Salary s);

    public boolean deleteSalary(Salary s);

    boolean unexitsid(Salary salary);

    public boolean insertSalary(Salary s);
}
