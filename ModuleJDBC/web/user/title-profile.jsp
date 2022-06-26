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
    <link rel="stylesheet" type="text/css" href="../assets/css/style.css">
    <!--[if lt IE 9]>
    <script src="../assets/js/html5shiv.min.js"></script>
    <script src="../assets/js/respond.min.js"></script>
    <![endif]-->
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
                    <a class="dropdown-item" href="profile.jsp">个人信息设置</a>
                    <a class="dropdown-item" href="/logout">退出登录</a>
                </div>
            </li>
        </ul>

    </div>
    <div class="sidebar" id="sidebar">
        <div class="sidebar-inner slimscroll">
            <div id="sidebar-menu" class="sidebar-menu">
                <ul>
                    <li class="submenu">
                        <a href="#"><i class="fa fa-user"></i> <span> 个人信息管理 </span> <span
                                class="menu-arrow"></span></a>
                        <ul style="display: none;">
                            <li>
                                <a href="${pageContext.request.contextPath}/user/profile.jsp">个人信息</a>
                            </li>
                            <li><a href="${pageContext.request.contextPath}/user/edit-profile.jsp">个人信息修改</a>
                            </li>
                            <li><a href="${pageContext.request.contextPath}/user/employee/selectEmployeeSalaryInfo">个人工资信息</a>
                            </li>
                            <li><a class="active"
                                   href="${pageContext.request.contextPath}/user/employee/selectEmployeeTitleInfo">个人职位信息</a>
                            </li>
                        </ul>
                    </li>

                </ul>
            </div>
        </div>
    </div>
    <div class="page-wrapper">
        <div class="content">
            <div class="row">
                <div class="col-sm-7 col-6">
                    <h4 class="page-title">个人信息</h4>
                </div>

                <%--                <div class="col-sm-5 col-6 text-right m-b-30">--%>
                <%--                    <a href="edit-profile.jsp" class="btn btn-primary btn-rounded"><i class="fa fa-plus"></i> 编辑个人信息</a>--%>
                <%--                </div>--%>
            </div>
            <div class="card-box profile-header">
                <div class="row">
                    <div class="col-md-12">
                        <div class="profile-view">
                            <div class="profile-basic">
                                <div class="row">
                                    <div class="col-md-5">
                                        <div class="profile-info-left">
                                            <h3 class="user-name m-t-0 mb-0">职位名称 : ${title.tname}</h3>
                                            <small class="text-muted"></small>
                                            <div class="staff-id">职位 ID : ${title.tid}</div>
                                        </div>
                                    </div>
                                    <div class="col-md-7">
                                        <ul class="personal-info">
                                            <li>
                                                <span class="title">职位标志位</span>
                                                <span class="text">${title.flag}</span>
                                            </li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                        </div>
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
<script src="../assets/js/jquery.slimscroll.js"></script>
<script src="../assets/js/app.js"></script>
</body>

</html>
