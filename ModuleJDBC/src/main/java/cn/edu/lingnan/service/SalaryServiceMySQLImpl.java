package cn.edu.lingnan.service;

import cn.edu.lingnan.dao.EmployeeDaoMySQLImpl;
import cn.edu.lingnan.dao.SalaryDaoMySQLImpl;
import cn.edu.lingnan.dao.TitleDaoMySQLImpl;
import cn.edu.lingnan.pojo.Employee;
import cn.edu.lingnan.pojo.Salary;
import cn.edu.lingnan.pojo.Title;

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
    public Salary querySingleBysid(Class<Salary> clazz, Object... parameters) {
        return null;
    }

    //        由员工id查询工资
    @Override
    public List<Salary> queryMultiByeid(Class<Salary> clazz, String eid) {
//        String sql="select * from salary where eid = ?";
//       List<Salary> salaries=salaryDao.queryMulti(sql,clazz,eid);
        List<Salary> salaries = salaryDao.queryMultiByeid(clazz, eid);
        return salaries;
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

    //        插入工资记录
    @Override
    public boolean insertSalary(Salary s) {
        boolean flag1 = false;
        boolean flag2 = false;
        boolean flag3 = true;
        List<Salary> salarys = salaryDao.queryMultiAllSalary();

        List<Employee> employees = employeeDao.queryMultiAllEmployee();
        List<Title> titles = titleDao.queryMultiAllTitle();

//        for (Salary salary : salarys) {
//            if ((salary.getEid().equals(s.getEid())) && (salary.getTid().equals(s.getTid()))) {
//                flag1 = true;
//            }
//        }

//        for (Salary salary : salarys) {
//            if (salary.getEid().equals(s.getEid())){
//                flag1 = true;
//            }
//        }

//        for (Employee employee : employees) {
//            if (employee.getEid().equals(s.getEid())) {
//                flag1 = true;
//            }
//        }
        if ((employeeDao.querySingleByeid(Employee.class, s.getEid()) != null)) {
            flag1 = true;
        }
        if (flag1 == false) {
            return false;
        }

        if (titleDao.querySingleBytid(Title.class, s.getTid()) != null) {
            flag2 = true;
        }
//        for (Title title : titles) {
//            if (title.getTid().equals(s.getTid())) {
//                flag2 = true;
//            }
//        }

        if (flag1 == false || flag2 == false) {
            return false;
        }

        if (salaryDao.querySingleBysid(Salary.class, s.getEid(), s.getTid()) != null) {
            flag3 = true;
        }

//        for (Salary salary : salarys) {
//            if ((salary.getEid().equals(s.getEid())) && (salary.getTid().equals(s.getTid()))) {
//                flag3 = false;
//            }
//        }
        if (flag3 == false) {
            return false;
        }

        return salaryDao.insertSalary(s);
    }
}
