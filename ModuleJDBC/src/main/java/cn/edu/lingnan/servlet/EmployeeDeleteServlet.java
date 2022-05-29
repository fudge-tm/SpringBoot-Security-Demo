package cn.edu.lingnan.servlet;

import cn.edu.lingnan.service.EmployeeServiceMySQLImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/deleteEmp")
public class EmployeeDeleteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String eid = request.getParameter("eid");
        String flag = request.getParameter("flag");

        if (flag == null) {//单个删除
            new EmployeeServiceMySQLImpl().deleteEmployeeByEid(eid);
        } else { //批量删除
            String[] split = eid.split(",");
            for (String s : split) {
                new EmployeeServiceMySQLImpl().deleteEmployeeByEid(s);
            }
        }


        response.sendRedirect("/selectAllEmpServlet");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
