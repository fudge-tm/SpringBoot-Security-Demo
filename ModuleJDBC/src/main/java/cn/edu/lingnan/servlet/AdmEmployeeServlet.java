package cn.edu.lingnan.servlet;

import cn.edu.lingnan.pojo.Employee;
import cn.edu.lingnan.service.EmployeeService;
import cn.edu.lingnan.service.EmployeeServiceMySQLImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin/employee/*")
public class AdmEmployeeServlet extends BaseServlet {
    private EmployeeService service = new EmployeeServiceMySQLImpl();

    public void selectAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("进来了");
        //1. 调用EmployeeService完成查询
        List<Employee> employees = service.queryAllEmployee(Employee.class);

        HttpSession session = request.getSession();
        session.setAttribute("employees", employees);

        String contextPath = request.getContextPath();
//            System.out.println("**" + contextPath + "**");
        response.sendRedirect(contextPath + "/admin/employees.jsp");
//            req.getSession().setAttribute("employee", employee);

//            System.out.println("success");
//            resp.sendRedirect(req.getContextPath() + "/selectAllEmpServlet");


        //3. 转发到main.jsp
//        request.getRequestDispatcher(contextPath + "/admin/employees.jsp").forward(request, response);
    }

    public void selectPageAndCondition(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String empeid = request.getParameter("empeid");
        String empename = request.getParameter("empename");


        Employee employee = new Employee();
        employee.setEid(empeid);
        employee.setEname(empename);

        List<Employee> employees = service.selectByCondition(employee);
        HttpSession session = request.getSession();
        session.setAttribute("employees", employees);
        String contextPath = request.getContextPath();
        response.sendRedirect(contextPath + "/admin/employees.jsp");

    }

    public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        System.out.println("##进来了" + "##");
        //1. 获取用户名和密码数据
        String userid = request.getParameter("empeid");
        String username = request.getParameter("empename");
        String password = request.getParameter("emppwd");
        String flag = request.getParameter("flag");

        Employee employee = new Employee();
        employee.setEid(userid);
        employee.setEname(username);
        employee.setPassword(password);
        employee.setSuperuser(2);
        employee.setFlag(1);
        // 比对
        if (flag != null) {
            boolean unexiteid = service.unexiteid(employee);
            if (unexiteid) {
                response.getWriter().write("success");//success表示不存在

            } else {
                System.out.println("已存在");
                response.getWriter().write("fail");//fail表示已存在

            }
        } else {

            //2. 调用service 新增员工
            boolean addsuc = service.insertEmployee(employee);
            //3. 判断新增成功与否

            //跳转查询所有
            response.sendRedirect("/admin/employee/selectAll");
        }


    }

    /**
     * 批量删除
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void deleteEmp(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String eid = request.getParameter("eid");
        String flag = request.getParameter("flag");
        Boolean sucflag = false;
        EmployeeServiceMySQLImpl employeeServiceMySQL = new EmployeeServiceMySQLImpl();
        if (flag == null) {//单个删除
            sucflag = employeeServiceMySQL.deleteEmployeeByEid(eid);
        } else { //批量删除
            String[] split = eid.split(",");
            for (String s : split) {
                System.out.println(s);
                sucflag = employeeServiceMySQL.deleteEmployeeByEid(s);
                System.out.println(employeeServiceMySQL.deleteEmployeeByEid(s));
            }
        }
        HttpSession session = request.getSession();
        if (sucflag) {

            session.setAttribute("delemp_msg", "删除成功！");
        } else {
            session.setAttribute("delemp_msg", "删除失败！");
        }


        String contextPath = request.getContextPath();
        response.sendRedirect(contextPath + "/admin/employee/selectAll");

    }

    public void updateEmp(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String eid = request.getParameter("eid");
        String flag = request.getParameter("flag");
        String idflag = request.getParameter("idflag");
        String removeflag = request.getParameter("removeflag");
        String updEmp = request.getParameter("updateEmp");

        Employee employee = new Employee();
        employee.setEid(eid);
//        System.out.println(flag + "##" + idflag);
//        System.out.println("true".equals(flag));
        if ("true".equals(flag)) {//修改前回显
//            System.out.println("修改前回显");
            Employee employee1 = service.querySingleByeid(Employee.class, employee.getEid());
            HttpSession session = request.getSession();
            session.setAttribute("updateEmp", employee1);
            response.sendRedirect("/admin/emp_update.jsp");
        }
        if ("true".equals(idflag)) {//异步检测id
//            System.out.println("异步检测id");
            Employee employee1 = service.querySingleByeid(Employee.class, employee.getEid());
//            System.out.println("##" + unexiteid + "##");
            if (employee1 == null) {
//                System.out.println("不存在");
                response.getWriter().write("success");//success表示不存在

            } else {

                response.getWriter().write("fail");//fail表示已存在

            }

        }
        if ("true".equals(removeflag)) {//清除updateEmp的session
            HttpSession session = request.getSession();
            session.removeAttribute(updEmp);
            response.getWriter().write("success");
        }
        if (flag == null && idflag == null && removeflag == null) { //修改提交
//            System.out.println("修改提交");
            String username = request.getParameter("empename");
            String password = request.getParameter("emppwd");
            employee.setEname(username);
            employee.setPassword(password);
            employee.setSuperuser(2);
            employee.setFlag(1);
//            System.out.println(employee);
            boolean b = service.updateEmployee(employee);
//            System.out.println(b);
            response.sendRedirect("/admin/employee/selectAll");
        }


    }

    public void updateEmployeeSuperuser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String eid = request.getParameter("eid");
        Employee employee = new Employee();
        employee.setEid(eid);
        employee.setSuperuser(1);
        boolean b = service.updateEmployeeSuperuser(employee);
        System.out.println(b);
        response.sendRedirect("/admin/employee/selectAll");
    }


    public void loginout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.invalidate();
        System.out.println("[Debug]用户已注销！");
        response.sendRedirect(request.getContextPath() + "/login.jsp");
    }
}
