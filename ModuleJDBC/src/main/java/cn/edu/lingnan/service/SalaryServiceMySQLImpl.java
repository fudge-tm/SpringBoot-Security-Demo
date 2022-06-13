package cn.edu.lingnan.service;

import cn.edu.lingnan.dao.EmployeeDaoMySQLImpl;
import cn.edu.lingnan.dao.SalaryDaoMySQLImpl;
import cn.edu.lingnan.dao.TitleDaoMySQLImpl;
import cn.edu.lingnan.pojo.Salary;

import java.util.List;

/**
 * SalaryServiceMySQLImpl实现类
 */
public class SalaryServiceMySQLImpl implements SalaryService {
    SalaryDaoMySQLImpl salaryDao = new SalaryDaoMySQLImpl();
    TitleDaoMySQLImpl titleDao = new TitleDaoMySQLImpl();
    EmployeeDaoMySQLImpl employeeDao = new EmployeeDaoMySQLImpl();


    //由员工id和职位id查询工资
    @Override
    public Salary querySingleBysid(Class<Salary> clazz, Salary salary) {

        return salaryDao.querySingleBysid(clazz, salary);
    }

    //    按条件查询
    @Override
    public List<Salary> selectByCondition(Salary salary) {
        return salaryDao.selectByCondition(salary);
    }

    //        由员工id查询工资
    @Override
    public List<Salary> queryMultiByeid(Class<Salary> clazz, String eid) {
//        String sql="select * from salary where eid = ?";
//       List<Salary> salaries=salaryDao.queryMulti(sql,clazz,eid);
        List<Salary> salaries = salaryDao.queryMultiByeid(clazz, eid);
        return salaries;
    }

    @Override
    public Salary queryhisSingleBysid(Class<Salary> clazz, Salary salary) {
        return salaryDao.queryhisSingleBysid(clazz, salary);
    }

    //        查询所有工资
    @Override
    public List<Salary> queryMultiAllSalary() {
        return salaryDao.queryMultiAllSalary();
    }

    //        更新工资表
    @Override
    public boolean updateSalary(Salary s) {
        return salaryDao.updateSalary(s);
    }

    //       删除工资记录
    @Override
    public boolean deleteSalary(Salary s) {
        return salaryDao.deleteSalary(s);
    }

    @Override
    public boolean unexitsid(Salary salary) {
        Salary salary1 = salaryDao.querySingleBysid(Salary.class, salary);
        Salary salary2 = salaryDao.queryhisSingleBysid(Salary.class, salary);
        if (salary1 == null && salary2 == null) {
            return true;
        }
        return false;
    }

    //        插入工资记录
    @Override
    public boolean insertSalary(Salary s) {
        boolean flag1 = false;
        boolean flag2 = false;
        boolean flag3 = true;
//        List<Salary> salarys = salaryDao.queryMultiAllSalary();
//
//        List<Employee> employees = employeeDao.queryMultiAllEmployee();
//        List<Title> titles = titleDao.queryMultiAllTitle();
//        if ((employeeDao.querySingleByeid(Employee.class, s.getEid()) != null)) {
//            flag1 = true;
//        }
//        if (flag1 == false) {
//            return false;
//        }
//
//        if (titleDao.querySingleBytid(Title.class, s.getTid()) != null) {
//            flag2 = true;
//        }
//        if (flag1 == false || flag2 == false) {
//            return false;
//        }

        if (salaryDao.querySingleBysid(Salary.class, s) != null) {
            flag3 = true;
        }

        if (flag3 == false) {
            return false;
        }

        return salaryDao.insertSalary(s);
    }
}
