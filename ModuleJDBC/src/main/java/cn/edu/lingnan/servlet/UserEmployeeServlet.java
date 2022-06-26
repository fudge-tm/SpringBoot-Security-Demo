package cn.edu.lingnan.servlet;

import cn.edu.lingnan.pojo.Employee;
import cn.edu.lingnan.pojo.Salary;
import cn.edu.lingnan.pojo.Title;
import cn.edu.lingnan.service.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/user/employee/*")
public class UserEmployeeServlet extends BaseServlet {
    private EmployeeService service = new EmployeeServiceMySQLImpl();
    private SalaryService salaryService = new SalaryServiceMySQLImpl();
    private TitleService titleService = new TitleServiceMySQLImpl();

    //修改用户个人信息
    public void updateEmployeeInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String eid = request.getParameter("eid");
        String username = request.getParameter("empename");
        String password = request.getParameter("emppwd");
        Employee employee = new Employee();
        employee.setEid(eid);
        employee.setEname(username);
        employee.setPassword(password);
        employee.setSuperuser(2);
        employee.setFlag(1);
//            System.out.println(employee);
        boolean b = service.updateEmployee(employee);
        HttpSession session = request.getSession();
        session.setAttribute("employee", employee);
//            System.out.println(b);
        response.sendRedirect("/user/profile.jsp");
    }

    public void selectEmployeeSalaryInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Employee employee = (Employee) session.getAttribute("employee");

        Salary salary = new Salary();
        salary.setEid(employee.getEid());
        Salary salary1 = salaryService.querySingleByeid(Salary.class, salary);
//        HttpSession session2 = request.getSession();
        session.setAttribute("salary", salary1);
//            System.out.println(b);
        response.sendRedirect("/user/salary-profile.jsp");
    }

    public void selectEmployeeTitleInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Employee employee = (Employee) session.getAttribute("employee");

        Salary salary = new Salary();
        salary.setEid(employee.getEid());
        Salary salary1 = salaryService.querySingleByeid(Salary.class, salary);
        if (salary1 != null) {
            Title title = titleService.querySingleBytid(Title.class, salary1.getTid());
            session.setAttribute("title", title);
        }
//        HttpSession session2 = request.getSession();

//            System.out.println(b);
        response.sendRedirect("/user/title-profile.jsp");
    }


}



