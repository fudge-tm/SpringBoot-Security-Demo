package cn.edu.lingnan.servlet.old;

import cn.edu.lingnan.pojo.Employee;
import cn.edu.lingnan.service.EmployeeService;
import cn.edu.lingnan.service.EmployeeServiceMySQLImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@WebServlet("/login2")
public class EmployeeRegisterServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //    1.从页面获取参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        System.out.println(username + "  " + password);

        EmployeeService employeeService = new EmployeeServiceMySQLImpl();
        Employee employee = employeeService.finstaffBynameAndPassword(username, password);


        if (employee != null) {
            req.getSession().setAttribute("employee", employee);
            System.out.println("success");
//            resp.sendRedirect(req.getContextPath() + "/selectAllEmpServlet");
            resp.sendRedirect("welcome.jsp");
        } else {
            System.out.println("error");
            resp.sendRedirect("error.html");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
