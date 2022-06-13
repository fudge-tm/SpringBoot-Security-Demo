package cn.edu.lingnan.test;


import cn.edu.lingnan.pojo.Salary;
import cn.edu.lingnan.service.SalaryServiceMySQLImpl;

public class SalaryServiceTest {
    public static SalaryServiceMySQLImpl salaryServiceMySQL = new SalaryServiceMySQLImpl();

    public static void main(String[] args) {
        Salary s = new Salary("e01", "t01", 10000.0, 1);

        //由员工id和职位id查询工资
        System.out.println(salaryServiceMySQL.querySingleBysid(Salary.class, s));

        //        由员工id查询工资
        System.out.println(salaryServiceMySQL.queryMultiByeid(Salary.class, "e01"));

        //        查询所有工资
        System.out.println(salaryServiceMySQL.queryMultiAllSalary());

        //        更新工资表
        System.out.println(salaryServiceMySQL.updateSalary(s));

        //       删除工资记录
        System.out.println(salaryServiceMySQL.deleteSalary(s));

        //        插入工资记录
        System.out.println(salaryServiceMySQL.insertSalary(s));
    }


}

