package cn.edu.lingnan.servlet;

import cn.edu.lingnan.pojo.Title;
import cn.edu.lingnan.service.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin/title/*")
public class AdmTitleServlet extends BaseServlet {
    private EmployeeService Empservice = new EmployeeServiceMySQLImpl();
    private SalaryService salaryService = new SalaryServiceMySQLImpl();
    private TitleService titleService = new TitleServiceMySQLImpl();


    public void selectAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //1. 调用EmployeeService完成查询
        List<Title> titles = titleService.queryMultiAllTitle();
        HttpSession session = request.getSession();
        session.setAttribute("titles", titles);
        String contextPath = request.getContextPath();
        response.sendRedirect(contextPath + "/admin/empstitle.jsp");
    }

    public void selectPageAndCondition(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String titletid = request.getParameter("titletid");
        String titletname = request.getParameter("titletname");
        Title title = new Title();
        title.setTid(titletid);
        title.setTname(titletname);
        List<Title> titles = titleService.selectByCondition(title);
        HttpSession session = request.getSession();
        session.setAttribute("titles", titles);
        String contextPath = request.getContextPath();
        response.sendRedirect(contextPath + "/admin/empstitle.jsp");

    }

    public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("##进来了" + "##");
        //1. 获取用户名和密码数据
        String titletid = request.getParameter("titletid");
        String titletname = request.getParameter("titletname");
        String flag = request.getParameter("flag");
        System.out.println("##flag=" + flag + "##");
        Title title = new Title();
        title.setTid(titletid);
        title.setTname(titletname);
        title.setFlag(1);
        System.out.println(title);
        // 比对
        if (flag != null) {
            boolean unexittid = titleService.unexittid(title);
            System.out.println("##unexittid=" + unexittid + "##");
            if (unexittid) {
                response.getWriter().write("success");//success表示不存在

            } else {
                System.out.println("已存在");
                response.getWriter().write("fail");//fail表示已存在

            }
        } else {

            System.out.println("$$" + title + "$$");
            //2. 调用service 新增工资记录
            boolean addsuc = titleService.insertTitle(title);
            //3. 判断新增成功与否
            //跳转查询所有
            response.sendRedirect("/admin/title/selectAll");
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
    public void deleteTitle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String titletid = request.getParameter("titletid");
        String titletname = request.getParameter("titletname");
        String flag = request.getParameter("flag");
        Boolean sucflag = false;
        Title title = new Title();
        title.setTid(titletid);
        title.setTname(titletname);
        title.setFlag(1);
        if (flag == null) {//单个删除
            sucflag = titleService.deleteTitle(title);
        } else { //批量删除
            String[] splitt = titletid.split(",");
            for (String s : splitt) {
                Title temtitle = new Title();
                temtitle.setTid(titletid);
                sucflag = titleService.deleteTitle(temtitle);
            }

        }
        HttpSession session = request.getSession();
        if (sucflag) {
            session.setAttribute("deleteTitle_msg", "删除成功！");
        } else {
            session.setAttribute("deleteTitle_msg", "删除失败！");
        }


        String contextPath = request.getContextPath();
        response.sendRedirect(contextPath + "/admin/title/selectAll");

    }

    public void updateTitle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        System.out.println("##进来了" + "##");
        String titletid = request.getParameter("titletid");
        String titletname = request.getParameter("titletname");
        String flag = request.getParameter("flag");
        String idflag = request.getParameter("idflag");
        String removeflag = request.getParameter("removeflag");
        String updateTitle = request.getParameter("updateTitle");

        Title title = new Title();
        title.setTid(titletid);
        title.setTname(titletname);
        title.setFlag(1);

        if ("true".equals(flag)) {//修改前回显
//            System.out.println("修改前回显");
            Title title1 = titleService.querySingleBytid(Title.class, title.getTid());
//            System.out.println(title1);
            HttpSession session = request.getSession();
            session.setAttribute("updateTitle", title1);
            response.sendRedirect("/admin/title_update.jsp");
        }
        if ("true".equals(idflag)) {//异步检测id
//            System.out.println("异步检测id");
//            System.out.println(title);
            Title titletesttid1 = titleService.querySingleBytid(Title.class, title.getTid());
            System.out.println("##" + titletesttid1 + "##");
            if (titletesttid1 == null) {
                response.getWriter().write("success");//success表示不存在

            } else {
                response.getWriter().write("fail");//fail表示已存在

            }

        }
        if ("true".equals(removeflag)) {//清除updateTitle的session
//            System.out.println("清除session");
            HttpSession session = request.getSession();
            session.removeAttribute(updateTitle);
            response.getWriter().write("success");
        }
        if (flag == null && idflag == null && removeflag == null) { //修改提交
            boolean b = titleService.updateTitle(title);
//            System.out.println(b);
            response.sendRedirect("/admin/title/selectAll");


        }


    }


    public void loginout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.invalidate();
        System.out.println("[Debug]用户已注销！");
        response.sendRedirect(request.getContextPath() + "/login.jsp");
    }
}
