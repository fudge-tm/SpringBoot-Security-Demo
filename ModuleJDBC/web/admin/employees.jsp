<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0">

    <title>蓝色星海管理系统</title>
    <link rel="stylesheet" type="text/css" href="../assets/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="../assets/css/font-awesome.min.css">
    <link rel="stylesheet" type="text/css" href="../assets/css/dataTables.bootstrap4.min.css">
    <link rel="stylesheet" type="text/css" href="../assets/css/select2.min.css">
    <link rel="stylesheet" type="text/css" href="../assets/css/bootstrap-datetimepicker.min.css">
    <link rel="stylesheet" type="text/css" href="../assets/css/style.css">
</head>

<body>
<div class="main-wrapper">
    <div class="header">
        <div class="header-left">
            <a href="index.jsp" class="logo">
                <img src="../assets/img/starlogo.png" width="35" height="35" alt=""> <span>员工管理系统</span>
            </a>
        </div>
        <a id="toggle_btn" href="javascript:void(0);"><i class="fa fa-bars"></i></a>
        <a id="mobile_btn" class="mobile_btn float-left" href="#sidebar"><i class="fa fa-bars"></i></a>
        <ul class="nav user-menu float-right">
            <li class="nav-item dropdown has-arrow">
                <a href="#" class="dropdown-toggle nav-link user-link" data-toggle="dropdown">
                        <span class="user-img"><img class="rounded-circle" src="../assets/img/user.jpg" width="40"
                                                    alt="Admin">
                            <span class="status online"></span></span>
                    <span>${employee.ename}</span>
                </a>
                <div class="dropdown-menu">
                    <a class="dropdown-item" href="/logout">Logout</a>
                </div>
            </li>
        </ul>

    </div>
    <div class="sidebar" id="sidebar">
        <div class="sidebar-inner slimscroll">
            <div id="sidebar-menu" class="sidebar-menu">
                <ul>

                    <li>
                        <a href="index.jsp"><i class="fa fa-dashboard"></i> <span>统计</span></a>
                    </li>


                    <li class="submenu">
                        <a href="#"><i class="fa fa-user"></i> <span> 员工基本信息管理</span> <span
                                class="menu-arrow"></span></a>
                        <ul style="display: none;">
                            <li><a class="active" href="${pageContext.request.contextPath}/admin/employee/selectAll">员工基本信息</a>
                            </li>
                            <li><a href="emp_insert.jsp">新增员工信息</a></li>
                            <li><a href="emp_update.jsp">修改员工信息</a></li>
                        </ul>
                    </li>
                    <li class="submenu">
                        <a href="#"><i class="fa fa-id-card"></i> <span> 员工职位信息管理</span> <span
                                class="menu-arrow"></span></a>
                        <ul style="display: none;">
                            <li><a href="${pageContext.request.contextPath}/admin/title/selectAll">员工职位信息</a></li>
                            <li><a href="title_insert.jsp">新增员工职位信息</a></li>
                            <li><a href="title_update.jsp">修改员工职位信息</a></li>
                        </ul>
                    </li>

                    <li class="submenu">
                        <a href="#"><i class="fa fa-book"></i> <span> 员工工资信息管理 </span> <span
                                class="menu-arrow"></span></a>
                        <ul style="display: none;">
                            <li><a href="${pageContext.request.contextPath}/admin/salary/selectAll">员工工资信息</a></li>
                            <li><a href="salary_insert.jsp">新增员工工资信息</a></li>
                            <li><a href="salary_update.jsp">修改员工工资信息</a></li>
                        </ul>
                    </li>
                </ul>
            </div>
        </div>
    </div>

    <div class="page-wrapper">
        <div class="content">
            <div class="row">
                <div class="col-sm-4 col-3">
                    <h4 class="page-title">员工基本信息</h4>
                </div>
                <div class="col-sm-8 col-9 text-right m-b-20">
                    <a href="emp_insert.jsp" class="btn btn-primary float-right btn-rounded"><i
                            class="fa fa-plus"></i> 增加员工</a>
                </div>
            </div>
            <form action="/admin/employee/selectPageAndCondition" method="post">
                <div class="row filter-row">
                    <div class="col-sm-6 col-md-3">
                        <div class="form-group form-focus">
                            <label class="focus-label">员工 ID</label>
                            <input type="text" class="form-control floating" name="empeid" maxlength="3">
                        </div>
                    </div>
                    <div class="col-sm-6 col-md-3">
                        <div class="form-group form-focus">
                            <label class="focus-label">员工姓名</label>
                            <input type="text" class="form-control floating" name="empename" maxlength="10">
                        </div>
                    </div>

                    <div class="col-sm-6 col-md-3">
                        <button type="submit" class="btn btn-success btn-block"> 查询</button>
                    </div>
                </div>
            </form>
            <div class="row">
                <div class="col-md-12">
                    <div class="table-responsive">
                        <table class="table table-striped custom-table">
                            <thead>
                            <tr>
                                <th><input type="checkbox" onclick="allcheck(this)"></th>
                                <th style="min-width:200px;">员工姓名</th>
                                <th>员工 ID</th>
                                <th>员工密码</th>
                                <th>员工权限</th>
                                <th style="min-width: 110px;">员工备注</th>
                                <th class="text-right"><input type="button" value="批量删除" onclick="delall()">&nbsp;&nbsp;操作
                                </th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${employees}" var="Employee" varStatus="status">
                                <tr>
                                    <td><input type="checkbox" name="check" value=${Employee.eid}></td>
                                    <td>
                                        <img width="28" height="28" src="../assets/img/user.jpg" class="rounded-circle"
                                             alt="">
                                        <h2>${Employee.ename}</h2>
                                    </td>
                                    <td>${Employee.eid}</td>
                                    <td>${Employee.password}</td>
                                    <td>${Employee.superuser}</td>
                                    <td>${Employee.flag}</td>
                                    <td class="text-right">
                                        <div class="dropdown dropdown-action">
                                            <a href="#" class="action-icon dropdown-toggle" data-toggle="dropdown"
                                               aria-expanded="false"><i class="fa fa-ellipsis-v"></i></a>
                                            <div class="dropdown-menu dropdown-menu-right">
                                                    <%--                                                <a class="dropdown-item" href="emp_update.jsp"><i--%>
                                                    <%--                                                        class="fa fa-pencil m-r-5"></i> 编辑</a>--%>
                                                <button onclick="updbyids('${Employee.eid}')"
                                                        class="dropdown-item">
                                                    <i class="fa fa-pencil m-r-5"></i> 编辑
                                                </button>
                                                <button onclick="delbyids('${Employee.eid}')"
                                                        class="dropdown-item">
                                                    <i class="fa fa-trash-o m-r-5"></i> 删除
                                                </button>
                                                <button onclick="updateEmployeeSuperuser('${Employee.eid}')"
                                                        class="dropdown-item">
                                                    <i class="fa fa-trash-o m-r-5"></i> 设为管理员
                                                </button>
                                            </div>
                                        </div>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>


</div>
<div class="sidebar-overlay" data-reff=""></div>
<script src="../assets/js/jquery-3.2.1.min.js"></script>
<script src="../assets/js/popper.min.js"></script>
<script src="../assets/js/bootstrap.min.js"></script>
<script src="../assets/js/jquery.dataTables.min.js"></script>
<script src="../assets/js/dataTables.bootstrap4.min.js"></script>
<script src="../assets/js/jquery.slimscroll.js"></script>
<script src="../assets/js/select2.min.js"></script>
<script src="../assets/js/moment.min.js"></script>
<script src="../assets/js/bootstrap-datetimepicker.min.js"></script>
<script src="../assets/js/app.js"></script>
<script src="../assets/js/allEmp.js"></script>
<script src="../assets/js/axios-0.18.0.js"></script>
<script>
    function updateEmployeeSuperuser(eid) {
        var value = eid;
        location.href = "http://localhost:8080/admin/employee/updateEmployeeSuperuser?eid=" + value

    }

</script>

</body>

</html>
