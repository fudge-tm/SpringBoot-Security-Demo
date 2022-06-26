package cn.edu.lingnan.filter;

import cn.edu.lingnan.pojo.Employee;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/user/*")
public class LoginFilter2 implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession();
        Object o = session.getAttribute("employee");

        if (o != null) {
            Employee employee = (Employee) o;
            if (employee.getSuperuser() == 2) {
                chain.doFilter(request, response);
            } else {
                // 存储错误信息到request
                session.setAttribute("login_msg", "权限不足，请登录!");
                res.sendRedirect(req.getContextPath() + "/login.jsp");
//                req.getRequestDispatcher("/purviewError.jsp").forward(req, response);
            }
        } else {
            // 存储错误信息到request
            session.setAttribute("login_msg", "权限不足，请登录!");
            res.sendRedirect(req.getContextPath() + "/login.jsp");
//                req.getRequestDispatcher("/purviewError.jsp").forward(req, response);
        }

    }
}
