﻿<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0">

    <title>蓝色星海管理系统</title>
    <link rel="stylesheet" type="text/css" href="../assets/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="../assets/css/font-awesome.min.css">
    <link rel="stylesheet" type="text/css" href="../assets/css/select2.min.css">
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
                            <li><a href="${pageContext.request.contextPath}/admin/employee/selectAll">员工基本信息</a></li>
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
                            <li><a class="active" href="title_update.jsp">修改员工职位信息</a></li>
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
                <div class="col-sm-12">
                    <h4 class="page-title">修改职位</h4>
                </div>
            </div>
            <div class="row">
                <div class="col-md-6">
                    <div class="card-box">
                        <h4 class="card-title">修改职位信息</h4>
                        <form action="/admin/title/updateTitle" id="titleupdform">
                            <div class="form-group row">
                                <label class="col-md-3 col-form-label">职位 ID</label>
                                <div class="col-md-9">
                                    <input required id="updtitleID" type="text" class="form-control"
                                           value="${updateTitle.tid}" name="titletid" maxlength="3">
                                    <span id="titleid_err" class="err_msg" style="color: red"></span>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label class="col-md-3 col-form-label">修改后的职位名称</label>
                                <div class="col-md-9">
                                    <input required type="text" class="form-control" maxlength="10" name="titletname"
                                           value="${updateTitle.tname}">
                                </div>
                            </div>
                            <div class="text-right">
                                <button type="submit" class="btn btn-primary">提交</button>
                            </div>
                        </form>
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
<script src="../assets/js/select2.min.js"></script>
<script src="../assets/js/app.js"></script>
<script src="../assets/js/axios-0.18.0.js"></script>
<script>
    window.onload = function removetitleses() {
        // console.log("失去焦点");
        var value = "updateTitle";
        axios({
            method: "get",
            url: "http://localhost:8080/admin/title/updateTitle?removeflag=true&updateTitle=" + value
        }).then(function (resp) {
        })
    }


    var exitedID = true;
    var updtitleID = document.getElementById("updtitleID");
    var titleid_err = document.getElementById("titleid_err");
    updtitleID.onblur = checkEmpID;

    function checkEmpID() {
        // console.log("失去焦点");
        var value = updtitleID.value;
        axios({
            method: "get",
            url: "http://localhost:8080/admin/title/updateTitle?idflag=true&titletid=" + value
        }).then(function (resp) {
            if (resp.data == "success") {
                // alert("##" + resp.data + "##");
                updtitleID.value = '';
                titleid_err.innerText = '职位 ID不存在！';
                exitedID = false;
                // console.log("exited" + exitedID);

            } else {
                // alert("##" + resp.data + "##");
                titleid_err.innerText = '';
                exitedID = true;
            }
        })
    }

    //1. 获取表单对象
    var titleupdform = document.getElementById("titleupdform");

    //2. 绑定onsubmit 事件
    titleupdform.onsubmit = function () {
        //挨个判断表单项是否符合要求，如果有不合符，则返回false

        var flag = exitedID;
        return flag;
    }


</script>
</body>

</html>
