package cn.edu.lingnan.test;

import cn.edu.lingnan.dao.TitleDaoMySQLImpl;
import cn.edu.lingnan.pojo.Title;

public class aaa {
    public static void main(String[] args) {
//        JDBCUtilsSingle instance = JDBCUtilsSingle.getInstance();
//        Connection connection = instance.getConnection();
//        PreparedStatement preparedStatement = null;
//        String sql = "select * from employee";
//        ResultSet rs = null;
//        try {
//            PreparedStatement prep = connection.prepareStatement(sql);
//            rs = prep.executeQuery();
//            System.out.println(rs);
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        String flag = new String("true");
//        System.out.println(flag);
//        System.out.println("true".equals(flag));
//        EmployeeDao employeeDao = new EmployeeDaoMySQLImpl();
//        Employee employee = employeeDao.querySingleByeid(Employee.class, "e00");
//        System.out.println(employee);
//        Salary salary = new Salary();
//        salary.setEid("d02");
//        salary.setTid("t02");
//        salary.setSmoney(4560.0);
//        salary.setFlag(1);
//        System.out.println(salary);
//        System.out.println(salaryService.updateSalary(salary));
        TitleDaoMySQLImpl titleDao = new TitleDaoMySQLImpl();
        System.out.println(titleDao.querySingleBytid(Title.class, "ttt"));
    }
}
