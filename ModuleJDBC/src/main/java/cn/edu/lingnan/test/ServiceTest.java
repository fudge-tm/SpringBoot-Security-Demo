package cn.edu.lingnan.test;

import cn.edu.lingnan.pojo.Employee;
import cn.edu.lingnan.pojo.Salary;
import cn.edu.lingnan.pojo.Title;
import cn.edu.lingnan.service.*;

import java.util.List;
import java.util.Scanner;

public class ServiceTest {

    public static Scanner scanf = new Scanner(System.in);
    public static EmployeeService employeeService = new EmployeeServiceMySQLImpl();
    public static SalaryService salaryService = new SalaryServiceMySQLImpl();
    public static TitleService titleService = new TitleServiceMySQLImpl();

    public static void main(String[] args) {
        System.out.println("-----------------------欢迎您来到员工管理系统-----------------------");
        System.out.println("-------------请选择相应的数字键进行测试，这里是主菜单-----------------------");
        System.out.println("---1.查看信息---2.增加信息---3.修改信息---4.删除信息---5.退出系统------");
        while (scanf.hasNextLine()) {

            String str = scanf.nextLine();
            switch (str) {
                case "1": {
                    System.out.println();
                    search();
                    break;
                }
                case "2": {
                    System.out.println();
                    add();
                    break;
                }
                case "3": {
                    System.out.println();
                    alter();
                    break;
                }
                case "4": {
                    System.out.println();
                    delete();
                    break;
                }
                case "5": {
                    System.out.println("退出成功");
                    System.exit(0);
                }
                default: {
                    System.out.println("您输入的信息有误,请重新输入");
                }
            }
            System.out.println("-------------请选择相应的数字键进行测试，这里是主菜单-----------------------");
            System.out.println("---1.查看信息---2.增加信息---3.修改信息---4.删除信息---5.退出系统------");
        }
    }

    public static void search() {
        System.out.println("-------------请选择相应的数字键进行测试，这里是  查看员工信息  二级菜单-----------------------");
        System.out.println("---1.查看所有员工---2.查看所有职位---3.查看所有员工的工资---4.返回主菜单------");
        while (scanf.hasNextLine()) {
            String str = scanf.nextLine();
            switch (str) {
                case "1": {
                    findAllEmployee();
                    break;
                }
                case "2": {
                    findAllTitle();
                    break;
                }
                case "3": {
                    findAllSalary();
                    break;
                }
                case "4": {
                    break;
                }
                default: {
                    System.out.println("您输入的信息有误,请重新输入");
                }
            }
            if (str.equals("4")) {
                break;
            }
            System.out.println("-------------请选择相应的数字键进行测试，这里是  查看员工信息  二级菜单-----------------------");
            System.out.println("---1.查看所有员工---2.查看所有职位---3.查看所有员工的工资---4.返回主菜单------");

        }
    }

    public static void add() {
        System.out.println("-------------请选择相应的数字键进行测试，这里是  增加员工信息  二级菜单-----------------------");
        System.out.println("---1.增加员工---2.增加职位---3.增加员工工资信息---4.返回主菜单------");
        while (scanf.hasNextLine()) {
            String str = scanf.nextLine();
            switch (str) {
                case "1": {
                    insertEmployee();
                    break;
                }
                case "2": {
                    insertTitle();
                    break;
                }
                case "3": {
                    insertSalary();
                    break;
                }
                case "4": {
                    break;
                }
                default: {
                    System.out.println("您输入的信息有误,请重新输入");
                }
            }
            if (str.equals("4")) {
                break;
            }
            System.out.println("-------------请选择相应的数字键进行测试，这里是  增加员工信息  二级菜单-----------------------");
            System.out.println("---1.增加员工---2.增加职位---3.增加员工工资信息---4.返回主菜单------");
        }
    }

    public static void alter() {
        System.out.println("-------------请选择相应的数字键进行测试，这里是  修改信息  二级菜单-----------------------");
        System.out.println("---1.修改员工信息---2.修改职位信息---3.修改员工工资信息---4.返回主菜单------");
        while (scanf.hasNextLine()) {
            String str = scanf.nextLine();
            switch (str) {
                case "1": {
                    updateEmployee();
                    break;
                }
                case "2": {
                    updateTitle();
                    break;
                }
                case "3": {
                    updateSalary();
                    break;
                }
                case "4": {
                    break;
                }
                default: {
                    System.out.println("您输入的信息有误,请重新输入");
                }
            }
            if (str.equals("4")) {
                break;
            }
            System.out.println("-------------请选择相应的数字键进行测试，这里是  修改信息  二级菜单-----------------------");
            System.out.println("---1.修改员工信息---2.修改职位信息---3.修改员工工资信息---4.返回主菜单------");
        }
    }

    public static void delete() {
        System.out.println("-------------请选择相应的数字键进行测试，这里是  删除信息  二级菜单-----------------------");
        System.out.println("---1.删除员工信息---2.删除职位信息---3.删除员工工资信息---4.返回主菜单------");
        while (scanf.hasNextLine()) {
            String str = scanf.nextLine();
            switch (str) {
                case "1": {
                    deleteEmployee();
                    break;
                }
                case "2": {
                    deleteTitle();
                    break;
                }
                case "3": {
                    deleteSalary();
                    break;
                }
                case "4": {
                    break;
                }
                default: {
                    System.out.println("您输入的信息有误,请重新输入");
                }
            }
            if (str.equals("4")) {
                break;
            }
            System.out.println("-------------请选择相应的数字键进行测试，这里是  删除信息  二级菜单-----------------------");
            System.out.println("---1.删除员工信息---2.删除职位信息---3.删除员工工资信息---4.返回主菜单------");
        }
    }


    public static void findAllEmployee() {
        List<Employee> allEmployee = employeeService.queryMultiAllEmployee();
        System.out.println("----------以下是所有员工的信息-------------");
        for (Employee employee : allEmployee) {
            System.out.println(employee.toString());
        }
        System.out.println("-----------------------");
        System.out.println();
    }

    public static void findAllSalary() {
        List<Salary> allSalary = salaryService.queryMultiAllSalary();
        System.out.println("-------------------以下是所有员工的工资信息-------------------");
        System.out.println("        员工编号     职位编号        工资         标志位");
        System.out.println("-------------------------------------------------");
        for (Salary Salary : allSalary) {
            System.out.println(Salary.toString());
        }
        System.out.println("-----------------------");
        System.out.println();
    }

    public static void findAllTitle() {
        List<Title> allTitle = titleService.queryMultiAllTitle();
        System.out.println("---------------------以下是所有职位的信息------------------------");
        System.out.println("      职位编号      职位名称      标志位");
        System.out.println("-------------------------------------------------");
        for (Title Title : allTitle) {
            System.out.println(Title.toString());
        }
        System.out.println("-----------------------");
        System.out.println();
    }

    public static void insertEmployee() {
        Employee e = new Employee();
        System.out.println("请输入需要新增的员工ID:");
        e.setEid(scanf.nextLine());
        System.out.println("请输入需要新增的员工姓名:");
        e.setEname(scanf.nextLine());
        System.out.println("请输入需要新增的员工密码:");
        e.setPassword(scanf.nextLine());
        System.out.println("请输入需要新增的员工权限:");
        e.setSuperuser(Integer.parseInt(scanf.nextLine()));
        System.out.println("请输入需要新增的员工标志位:");
        e.setFlag(Integer.parseInt(scanf.nextLine()));
        if (employeeService.insertEmployee(e) == true)
            System.out.println("员工信息新增成功!");
        else {
            System.out.println("员工信息新增失败，请检查输入数据是否有误，返回增加员工信息二级菜单!");
        }

    }

    public static void insertSalary() {
        Salary s = new Salary();
        System.out.println("请输入需要新增的员工ID:");
        s.setEid(scanf.nextLine());
        System.out.println("请输入需要新增的职位ID:");
        s.setTid(scanf.nextLine());
        System.out.println("请输入需要新增的工资:");
        s.setSmoney(Double.parseDouble(scanf.nextLine()));
        System.out.println("请输入需要新增的工资标志位:");
        s.setFlag(Integer.parseInt(scanf.nextLine()));
        if (salaryService.insertSalary(s) == true)
            System.out.println("员工工资信息新增成功!");
        else {
            System.out.println("员工工资信息新增失败，请检查输入数据是否有误，返回增加员工信息二级菜单!");
        }

    }

    public static void insertTitle() {
        Title t = new Title();
        System.out.println("请输入需要新增的职位ID:");
        t.setTid(scanf.nextLine());
        System.out.println("请输入需要新增的职位名称:");
        t.setTname(scanf.nextLine());
        System.out.println("请输入需要新增的职位标志位:");
        t.setFlag(Integer.parseInt(scanf.nextLine()));
        if (titleService.insertTitle(t) == true)
            System.out.println("职位信息新增成功!");
        else {
            System.out.println("职位信息新增失败，请检查输入数据是否有误，返回增加员工信息二级菜单!");
        }

    }

    private static void updateSalary() {
        Salary s = new Salary();
        System.out.println("请输入员工ID进行工资修改:");
        s.setEid(scanf.nextLine());
        System.out.println("请输入职位ID进行工资修改:");
        s.setTid(scanf.nextLine());
        System.out.println("请输入修改后的工资:");
        s.setSmoney(Double.parseDouble(scanf.nextLine()));
        System.out.println("请输入修改后的工资标志位:");
        s.setFlag(Integer.parseInt(scanf.nextLine()));
        if (salaryService.updateSalary(s) == true)
            System.out.println("员工工资信息修改成功!");
        else {
            System.out.println("员工工资信息修改失败，请检查输入数据是否有误，返回修改信息二级菜单!");
        }
    }

    private static void updateTitle() {
        Title t = new Title();
        System.out.println("请输入职位ID进行职位名称和职位标志位的修改:");
        t.setTid(scanf.nextLine());
        System.out.println("请输入修改后的职位名称:");
        t.setTname(scanf.nextLine());
        System.out.println("请输入修改后的职位标志位:");
        t.setFlag(Integer.parseInt(scanf.nextLine()));
        if (titleService.updateTitle(t) == true)
            System.out.println("职位信息修改成功!");
        else {
            System.out.println("职位信息修改失败，请检查输入数据是否有误，返回修改信息二级菜单!");
        }
    }

    private static void updateEmployee() {
        Employee e = new Employee();
        System.out.println("请输入员工ID进行员工信息的修改:");
        e.setEid(scanf.nextLine());
        System.out.println("请输入修改后的员工姓名:");
        e.setEname(scanf.nextLine());
        System.out.println("请输入修改后的员工密码:");
        e.setPassword(scanf.nextLine());
        System.out.println("请输入修改后的员工权限:");
        e.setSuperuser(Integer.parseInt(scanf.nextLine()));
        System.out.println("请输入修改后的员工标志位:");
        e.setFlag(Integer.parseInt(scanf.nextLine()));
        if (employeeService.updateEmployee(e) == true)
            System.out.println("员工信息修改成功!");
        else {
            System.out.println("员工信息修改失败，请检查输入数据是否有误，返回修改信息二级菜单!");
        }
    }

    private static void deleteSalary() {
        Salary s = new Salary();
        System.out.println("请输入员工ID进行工资信息删除:");
        s.setEid(scanf.nextLine());
        System.out.println("请输入职位ID进行工资信息删除:");
        s.setTid(scanf.nextLine());
        if (salaryService.deleteSalary(s) == true)
            System.out.println("员工工资信息删除成功!");
        else {
            System.out.println("员工工资信息删除失败，请检查输入数据是否有误，返回删除信息二级菜单!");
        }
    }

    private static void deleteTitle() {
        Title t = new Title();
        System.out.println("请输入职位ID进行职位信息删除:");
        t.setTid(scanf.nextLine());
        if (titleService.deleteTitle(t) == true)
            System.out.println("职位信息删除成功!");
        else {
            System.out.println("职位信息删除失败，请检查输入数据是否有误，返回删除信息二级菜单!");
        }
    }

    private static void deleteEmployee() {
        Employee e = new Employee();
        System.out.println("请输入员工ID进行员工信息删除:");
        e.setEid(scanf.nextLine());
        if (employeeService.deleteEmployeeByEid(e.getEid()) == true)
            System.out.println("员工信息删除成功!");
        else {
            System.out.println("员工信息删除失败，请检查输入数据是否有误，返回删除信息二级菜单!");
        }
    }


}
