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
        String username = req.getParameter("username");
        String password = req.getParameter("password");
//        System.out.println(username + "  " + password);

        //获取复选框数据
        String remember = req.getParameter("remember");

        Employee employee = employeeService.finstaffBynameAndPassword(username, password);


        if (employee != null) {

            //判断用户是否勾选记住我
            if ("1".equals(remember)) {
                //勾选了，发送Cookie
//                为了解决用户名是中文导致cookie乱码创建cookie时设置
//                username= URLEncoder.encode(username,"utf-8")
                //1. 创建Cookie对象
                Cookie c_username = new Cookie("username", username);
                Cookie c_password = new Cookie("password", password);
                // 设置Cookie的存活时间
                c_username.setMaxAge(60 * 60 * 24 * 7);
                c_password.setMaxAge(60 * 60 * 24 * 7);
                //2. 发送
                resp.addCookie(c_username);
                resp.addCookie(c_password);


            }

            //将登陆成功后的user对象，存储到session
            HttpSession session = req.getSession();
            session.setAttribute("employee", employee);
            req.getSession().setAttribute("employee", employee);

//            System.out.println("success");
//            resp.sendRedirect(req.getContextPath() + "/selectAllEmpServlet");
            String contextPath = req.getContextPath();
            resp.sendRedirect(contextPath + "/selectAllEmpServlet");
        } else {

            // 登录失败,

            // 存储错误信息到request
            req.setAttribute("login_msg", "用户名或密码错误");
            // 跳转到login.jsp
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
