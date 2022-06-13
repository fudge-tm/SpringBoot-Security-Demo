package cn.edu.lingnan.servlet;

import cn.edu.lingnan.pojo.Employee;
import cn.edu.lingnan.pojo.Salary;
import cn.edu.lingnan.pojo.Title;
import cn.edu.lingnan.service.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin/salary/*")
public class AdmSalaryServlet extends BaseServlet {
    private EmployeeService Empservice = new EmployeeServiceMySQLImpl();
    private SalaryService salaryService = new SalaryServiceMySQLImpl();
    private TitleService titleService = new TitleServiceMySQLImpl();


    public void selectAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //1. 调用EmployeeService完成查询
        List<Salary> salaries = salaryService.queryMultiAllSalary();
        HttpSession session = request.getSession();
        session.setAttribute("salaries", salaries);
        String contextPath = request.getContextPath();
        response.sendRedirect(contextPath + "/admin/salary.jsp");
    }

    public void selectPageAndCondition(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String salaryeid = request.getParameter("salaryeid");
        String salarytid = request.getParameter("salarytid");


        Salary salary = new Salary();
        salary.setEid(salaryeid);
        salary.setTid(salarytid);

        List<Salary> salaries = salaryService.selectByCondition(salary);
        HttpSession session = request.getSession();
        session.setAttribute("salaries", salaries);
        String contextPath = request.getContextPath();
        response.sendRedirect(contextPath + "/admin/salary.jsp");

    }

    public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        System.out.println("##进来了" + "##");
        //1. 获取用户名和密码数据
        String salaryeid = request.getParameter("salaryeid");
        String salarytid = request.getParameter("salarytid");
        String salarymoney = request.getParameter("salarymoney");
        String flag = request.getParameter("flag");
//        System.out.println("%" + flag + "%");
        Salary salary = new Salary();
        salary.setEid(salaryeid);
        salary.setTid(salarytid);
        salary.setFlag(1);
//        System.out.println(salary);

        // 比对
        if (flag != null) {
            Employee exitedemployee1 = Empservice.querySingleByeid(Employee.class, salary.getEid());
            boolean unexitsid = salaryService.unexitsid(salary);
            Title exitedtitle = titleService.querySingleBytid(Title.class, salary.getTid());
//            System.out.println("flag!=null");
//            System.out.println("##" + exitedemployee1 + "##" + "##" + unexitsid + "##" + "##" + exitedtitle + "##");
//            System.out.println("##" + unexitsid + "##");
            if (exitedemployee1 != null && unexitsid && exitedtitle != null) {
                response.getWriter().write("success");//success

            } else {
//                System.out.println("已存在");
                response.getWriter().write("fail");//fail表示已存在

            }
        } else {
            System.out.println("##" + flag + "##");
            salary.setSmoney(Double.parseDouble(salarymoney));
            System.out.println("$$" + salary + "$$");
            //2. 调用service 新增工资记录
            boolean addsuc = salaryService.insertSalary(salary);
            //3. 判断新增成功与否

            //跳转查询所有
            response.sendRedirect("/admin/salary/selectAll");
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
    public void deleteSalary(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String salaryeid = request.getParameter("eid");
        String salarytid = request.getParameter("tid");
        String flag = request.getParameter("flag");
        Boolean sucflag = false;
        Salary salary = new Salary();
        salary.setEid(salaryeid);
        salary.setTid(salarytid);
        if (flag == null) {//单个删除
            sucflag = salaryService.deleteSalary(salary);
        } else { //批量删除
            String[] splite = salaryeid.split(",");
            String[] splitt = salarytid.split(",");
            for (int i = 0; i < splite.length; i++) {
                String eid = splite[i];
                String tid = splitt[i];
                Salary temsalary = new Salary();
                temsalary.setEid(eid);
                temsalary.setTid(tid);
                sucflag = salaryService.deleteSalary(temsalary);
            }

        }
        HttpSession session = request.getSession();
        if (sucflag) {
            session.setAttribute("deleteSalary_msg", "删除成功！");
        } else {
            session.setAttribute("deleteSalary_msg", "删除失败！");
        }


        String contextPath = request.getContextPath();
        response.sendRedirect(contextPath + "/admin/salary/selectAll");

    }

    public void updateSalary(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String eid = request.getParameter("updeid");
        String tid = request.getParameter("updtid");
        String flag = request.getParameter("flag");
        String testidflag = request.getParameter("testidflag");
        String removeflag = request.getParameter("removeflag");
        String updateSalary = request.getParameter("updateSalary");

        Salary salary = new Salary();
        salary.setEid(eid);
        salary.setTid(tid);
//        System.out.println(flag + "##" + idflag);
//        System.out.println("true".equals(flag));
        if ("true".equals(flag)) {//修改前回显
//            System.out.println("修改前回显");
            Salary salary1 = salaryService.querySingleBysid(Salary.class, salary);
            HttpSession session = request.getSession();
            session.setAttribute("updateSalary", salary1);
            response.sendRedirect("/admin/salary_update.jsp");
        }
        if ("true".equals(removeflag)) {//清除updateSalary的session
            HttpSession session = request.getSession();
            session.removeAttribute(updateSalary);
            response.getWriter().write("success");
        }
        if ("true".equals(testidflag)) {
            boolean unexitsid = salaryService.unexitsid(salary);
            if (!unexitsid) {
                response.getWriter().write("success");//success
            } else {
                response.getWriter().write("fail");//fail表示不存在
            }
        }
        System.out.println(flag + "  " + removeflag + "  " + testidflag);
        if (flag == null && removeflag == null && testidflag == null) { //修改提交
            System.out.println("修改提交");
            String smoney = request.getParameter("salarymoney");
            salary.setSmoney(Double.parseDouble(smoney));
            salary.setFlag(1);
//            System.out.println(employee);
            System.out.println(salary);
            boolean b = salaryService.updateSalary(salary);
//            System.out.println(b);
            System.out.println(b);
            response.sendRedirect("/admin/salary/selectAll");


        }


    }


    public void loginout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.invalidate();
        System.out.println("[Debug]用户已注销！");
        response.sendRedirect(request.getContextPath() + "/login.jsp");
    }
}
