package cn.edu.lingnan.dao;

import cn.edu.lingnan.pojo.Salary;

import java.util.List;

public interface SalaryDao {
    public boolean deleteSalary(Salary s);

    public boolean insertSalary(Salary s);

    public boolean updateSalary(Salary s);

    public List<Salary> queryMultiAllSalary();

    public Salary queryhisSingleBysid(Class<Salary> clazz, Salary salary);

    Salary querySingleByeid(Class<Salary> clazz, Salary salary);

    Salary querySingleBytid(Class<Salary> clazz, Salary salary);

    Salary queryhisSingleByeid(Class<Salary> clazz, Salary salary);

    Salary queryhisSingleBytid(Class<Salary> clazz, Salary salary);

    //按条件查询
    List<Salary> selectByCondition(Salary salary);

    public List<Salary> queryMultiByeid(Class<Salary> clazz, String eid);

    public Salary querySingleBysid(Class<Salary> clazz, Salary salary);

}
