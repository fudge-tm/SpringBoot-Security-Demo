package cn.edu.lingnan.servlet;

import cn.edu.lingnan.pojo.Employee;
import cn.edu.lingnan.pojo.PageBean;
import cn.edu.lingnan.service.EmployeeService;
import cn.edu.lingnan.service.EmployeeServiceMySQLImpl;
import com.alibaba.fastjson.JSON;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

//@WebServlet("/admin/employee/*")
public class EmployeeServlet extends BaseServlet {
    private EmployeeService service = new EmployeeServiceMySQLImpl();

    public void selectAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //1. 调用EmployeeService完成查询
        List<Employee> employees = service.queryAllEmployee(Employee.class);

        //2. 存入request域中
        request.setAttribute("employees", employees);

        //3. 转发到main.jsp
        request.getRequestDispatcher("/admin/allEmployees.jsp").forward(request, response);
    }

    public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //    1.接收员工数据
        BufferedReader br = request.getReader();
        String params = br.readLine();//json字符串

//        转为Employee对象
        Employee employee = JSON.parseObject(params, Employee.class);

        service.insertEmployee(employee);

//        3.响应成功标识
        response.getWriter().write("success");
    }

    /**
     * 批量删除
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void deleteByIds(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //    1.接收数据[1,2,3]
        BufferedReader br = request.getReader();
        String params = br.readLine();//json字符串
        System.out.println("come");
//        转为int[]数组
        String[] ids = JSON.parseObject(params, String[].class);
        for (int i = 0; i < ids.length; i++) {
            System.out.println(ids[i]);
        }
        service.deleteByIds(ids);
//        3.响应成功标识
        response.getWriter().write("success");
    }

    public void employeeUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Employee employee = new Employee();
        employee.setEid(request.getParameter("eid"));

    }

    public void selectPageAndCondition(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int currentPage = Integer.parseInt(request.getParameter("currentPage"));
        int pageSize = Integer.parseInt(request.getParameter("pageSize"));

//        PageBean<Employee> pageBean = service.selectPage(currentPage, pageSize);

        //1. 调用EmployeeService完成查询
//        List<Employee> employees = service.selectPage(currentPage, pageSize);

        //    获取查询条件对象
        BufferedReader br = request.getReader();
        String params = br.readLine();//json字符串

//        转为Brand
        Employee employee = JSON.parseObject(params, Employee.class);

//        PageBean<Employee> pageBean = service.selectByPageAndCondition(currentPage, pageSize,brand);

        PageBean<Employee> pageBean = service.selectByPageAndCondition(currentPage, pageSize, employee);
        System.out.println(pageBean.getRows());
        String jsonString = JSON.toJSONString(pageBean);

        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);

    }


    public void loginout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.invalidate();
        System.out.println("[Debug]用户已注销！");
        response.sendRedirect(request.getContextPath() + "/login.jsp");
    }


    public void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //    1.从页面获取参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        System.out.println(username + "  " + password);

        EmployeeService employeeService = new EmployeeServiceMySQLImpl();
        Employee employee = employeeService.finstaffBynameAndPassword(username, password);


        if (employee != null) {
//            req.getSession().setAttribute("employee", employee);
            System.out.println("success");
//            resp.sendRedirect(req.getContextPath() + "/selectAllEmpServlet");
            resp.sendRedirect("/admin/indexp.html");
        } else {
            System.out.println("error");
            resp.sendRedirect("/error.html");
        }
    }

    public void register(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //    1.从页面获取参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        System.out.println(username + "  " + password);

        EmployeeService employeeService = new EmployeeServiceMySQLImpl();
        Employee employee = employeeService.finstaffBynameAndPassword(username, password);


        if (employee != null) {
//            req.getSession().setAttribute("employee", employee);
            System.out.println("success");
//            resp.sendRedirect(req.getContextPath() + "/selectAllEmpServlet");
            resp.sendRedirect("/admin/indexp.html");
        } else {
            System.out.println("error");
            resp.sendRedirect("/error.html");
        }
    }


}
