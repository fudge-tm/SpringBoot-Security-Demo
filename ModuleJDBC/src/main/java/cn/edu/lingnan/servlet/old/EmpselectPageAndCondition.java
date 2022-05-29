package cn.edu.lingnan.servlet.old;

import cn.edu.lingnan.pojo.Employee;
import cn.edu.lingnan.pojo.PageBean;
import cn.edu.lingnan.service.EmployeeService;
import cn.edu.lingnan.service.EmployeeServiceMySQLImpl;
import com.alibaba.fastjson.JSON;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@WebServlet("/selectPageAndCondition")
public class EmpselectPageAndCondition extends HttpServlet {
    private EmployeeService service = new EmployeeServiceMySQLImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int currentPage = Integer.parseInt(request.getParameter("currentPage"));
        int pageSize = Integer.parseInt(request.getParameter("pageSize"));

        //1. 调用EmployeeService完成查询
//        List<Employee> employees = service.selectPage(currentPage, pageSize);
        PageBean<Employee> pageBean = service.selectPage(currentPage, pageSize);
        String jsonString = JSON.toJSONString(pageBean);

        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
