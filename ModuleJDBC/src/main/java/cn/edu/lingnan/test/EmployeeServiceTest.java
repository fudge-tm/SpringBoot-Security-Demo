package cn.edu.lingnan.test;

import cn.edu.lingnan.pojo.Employee;
import cn.edu.lingnan.service.EmployeeServiceMySQLImpl;

public class EmployeeServiceTest {
    public static EmployeeServiceMySQLImpl employeeServiceMySQL = new EmployeeServiceMySQLImpl();


    public static void main(String[] args) {
        Employee e = new Employee("e110", "wangwu", "789", 2, 1);


        //        由员工id查询
        System.out.println(employeeServiceMySQL.querySingleByeid(Employee.class, "e01"));

        //        查询所有员工
        System.out.println(employeeServiceMySQL.queryAllEmployee(Employee.class));

        //        查询所有员工
        System.out.println(employeeServiceMySQL.queryMultiAllEmployee());

        //        由姓名密码查询员工
        System.out.println(employeeServiceMySQL.finstaffBynameAndPassword("e02", "123"));

        // 由姓名查询员工
        System.out.println(employeeServiceMySQL.queryMultiByename(Employee.class, "zhangsan2"));

        // 更新员工表
        System.out.println(employeeServiceMySQL.updateEmployee(e));

        // 由姓名和旧密码更新员工新密码
        System.out.println(employeeServiceMySQL.updateEmployeePassword("zhangsan3", "123", "456"));

        // 由员工id删除员工
        System.out.println(employeeServiceMySQL.deleteEmployeeByEid("e04"));

        //   插入员工
        System.out.println(employeeServiceMySQL.insertEmployee(e));


    }
//    @Test
//    public void  test13(){
//        var reg = /^\w{3}$/;
//        var flag = reg.test(userid);
//    }

}
