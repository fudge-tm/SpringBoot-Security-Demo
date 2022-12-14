package cn.edu.lingnan.servlet;

import cn.edu.lingnan.pojo.Employee;
import cn.edu.lingnan.service.EmployeeService;
import cn.edu.lingnan.service.EmployeeServiceMySQLImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    EmployeeService employeeService = new EmployeeServiceMySQLImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //    1.从页面获取参数
        String userid = req.getParameter("userid");
        String password = req.getParameter("password");
        //获取复选框数据
        String remember = req.getParameter("remember");
        Employee employee = employeeService.finstaffByidAndPassword(userid, password);

        if (employee != null) {
            //判断用户是否勾选记住我
            if ("1".equals(remember)) {
                //勾选了，发送Cookie
                //1. 创建Cookie对象
                Cookie c_userid = new Cookie("userid", userid);
                Cookie c_password = new Cookie("password", password);
                // 设置Cookie的存活时间为1小时
                c_userid.setMaxAge(60 * 60);
                c_password.setMaxAge(60 * 60);
                //2. 发送
                resp.addCookie(c_userid);
                resp.addCookie(c_password);
            }
            //将登陆成功后的user对象，存储到session
            HttpSession session = req.getSession();
            session.setAttribute("employee", employee);
            if (employee.getSuperuser() == 1) {
                String contextPath = req.getContextPath();
                resp.sendRedirect(contextPath + "/admin/index.jsp");
            } else if (employee.getSuperuser() == 2) {
                String contextPath = req.getContextPath();
                resp.sendRedirect(contextPath + "/user/profile.jsp");
            }

        } else {
            // 登录失败,
            // 存储错误信息到request
            req.setAttribute("login_msg", "用户id或密码错误");
            // 跳转到login.jsp
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
