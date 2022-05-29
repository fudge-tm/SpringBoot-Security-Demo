package cn.edu.lingnan.servlet;

import cn.edu.lingnan.pojo.Employee;
import cn.edu.lingnan.service.EmployeeService;
import cn.edu.lingnan.service.EmployeeServiceMySQLImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/selectAllEmpServlet")
public class EmployeeFindAllServlet extends HttpServlet {
    private EmployeeService service = new EmployeeServiceMySQLImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        //1. 调用EmployeeService完成查询
        List<Employee> employees = service.queryAllEmployee(Employee.class);

//        employees.forEach(employee -> System.out.println(employee));
        //2. 存入request域中
        request.setAttribute("employees", employees);

        //3. 转发到main.jsp
        request.getRequestDispatcher("/admin/allEmployees.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
