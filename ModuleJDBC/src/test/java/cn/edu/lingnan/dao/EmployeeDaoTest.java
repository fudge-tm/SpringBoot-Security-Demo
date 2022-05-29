package cn.edu.lingnan.dao;

import cn.edu.lingnan.pojo.Employee;
import cn.edu.lingnan.service.EmployeeServiceMySQLImpl;
import org.junit.Test;

import java.util.Scanner;

public class EmployeeDaoTest {
    @Test
    public void finstaffBynameAndPasswordTest() {
        EmployeeDaoMySQLImpl employeeDao = new EmployeeDaoMySQLImpl();
        Scanner scanner = new Scanner(System.in);
        System.out.println("输入ename");
        String ename = scanner.nextLine();
        System.out.println("输入password");
        String password = scanner.nextLine();
        System.out.println(employeeDao.finstaffBynameAndPassword(ename, password));

    }

    @Test
    public void querySingleBysidTest() {
        EmployeeDaoMySQLImpl employeeDao = new EmployeeDaoMySQLImpl();
        System.out.println(employeeDao.querySingleByeid(Employee.class, "e02"));
    }

    @Test
    public void queryMultiByename() {
        EmployeeDaoMySQLImpl employeeDao = new EmployeeDaoMySQLImpl();
        System.out.println(employeeDao.queryMultiByename(Employee.class, "zh"));
    }

    @Test
    public void queryAllEmployee() {

        EmployeeDaoMySQLImpl employeeDao = new EmployeeDaoMySQLImpl();
        System.out.println(employeeDao.queryAllEmployee(Employee.class));

    }

    @Test
    public void updateEmployeeT() {
        EmployeeServiceMySQLImpl employeeServiceMySQL = new EmployeeServiceMySQLImpl();
//        EmployeeDao employeeDao = new EmployeeDao();
//        Employee employee = new Employee("e03","zhangsan9","129",2,1);
//
//        System.out.println(employeeDao.updateEmployee(employee));
//        System.out.println(employeeServiceMySQL.updateEmployeePassword("zhangsan9","129","121kk"));
        employeeServiceMySQL.deleteEmployeeByEid("e02");
    }

}
