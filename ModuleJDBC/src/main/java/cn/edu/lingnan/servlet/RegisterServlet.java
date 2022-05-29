package cn.edu.lingnan.servlet;

import cn.edu.lingnan.pojo.Employee;
import cn.edu.lingnan.service.EmployeeService;
import cn.edu.lingnan.service.EmployeeServiceMySQLImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/registerServlet")
public class RegisterServlet extends HttpServlet {
    EmployeeService employeeService = new EmployeeServiceMySQLImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        System.out.println("aaaa");
        //1. 获取用户名和密码数据
        String userid = request.getParameter("userid");
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        Employee employee = new Employee();
        employee.setEid(userid);
        employee.setEname(username);
        employee.setPassword(password);

        // 获取用户输入的验证码
        String checkCode = request.getParameter("checkCode");

        // 程序生成的验证码，从Session获取
        HttpSession session = request.getSession();
        String checkCodeGen = (String) session.getAttribute("checkCodeGen");

        // 比对
        if (!checkCodeGen.equalsIgnoreCase(checkCode)) {

            request.setAttribute("register_msg", "验证码错误");
            request.getRequestDispatcher("/register.jsp").forward(request, response);

            // 不允许注册
            return;
        }


        //2. 调用service 注册
        boolean flag = employeeService.register(employee);
        //3. 判断注册成功与否
        if (flag) {
            //注册功能，跳转登陆页面

            request.setAttribute("register_msg", "注册成功，请登录");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        } else {
            //注册失败，跳转到注册页面

            request.setAttribute("register_msg", "用户编号已存在");
            request.getRequestDispatcher("/register.jsp").forward(request, response);
        }


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
