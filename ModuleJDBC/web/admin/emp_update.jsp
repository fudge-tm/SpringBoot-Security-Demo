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

                    <li>
                        <a href="index.jsp"><i class="fa fa-dashboard"></i> <span>统计</span></a>
                    </li>


                    <li class="submenu">
                        <a href="#"><i class="fa fa-user"></i> <span> 员工基本信息管理</span> <span
                                class="menu-arrow"></span></a>
                        <ul style="display: none;">
                            <li><a href="${pageContext.request.contextPath}/admin/employee/selectAll">员工基本信息</a></li>
                            <li><a href="emp_insert.jsp">新增员工信息</a></li>
                            <li><a class="active" href="emp_update.jsp">修改员工信息</a></li>
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
                    <li class="submenu">
                        <a href="#"><i class="fa fa-user"></i> <span> 个人信息管理 </span> <span
                                class="menu-arrow"></span></a>
                        <ul style="display: none;">
                            <li>
                                <a href="${pageContext.request.contextPath}/admin/profile.jsp">个人信息</a>
                            </li>
                            <li><a href="${pageContext.request.contextPath}/admin/edit-profile.jsp">个人信息信息修改</a>
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
                <div class="col-sm-12">
                    <h4 class="page-title">修改员工信息</h4>
                </div>
            </div>
            <div class="row">
                <div class="col-md-6">
                    <div class="card-box">
                        <h4 class="card-title">修改员工基本信息</h4>
                        <form action="/admin/employee/updateEmp" id="empupdform">
                            <div class="form-group row">
                                <label class="col-md-3 col-form-label">员工 ID</label>
                                <div class="col-md-9">
                                    <input required id="updempID" type="text" class="form-control"
                                           value="${updateEmp.eid}" name="eid" maxlength="3">
                                    <span id="empid_err" class="err_msg" style="color: red"></span>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label class="col-md-3 col-form-label">修改后的员工姓名</label>
                                <div class="col-md-9">
                                    <input required type="text" class="form-control" maxlength="10" name="empename"
                                           value="${updateEmp.ename}">
                                </div>
                            </div>
                            <div class="form-group row">
                                <label class="col-md-3 col-form-label">修改后的员工密码</label>
                                <div class="col-md-9">
                                    <input id="password" required type="password" class="form-control" maxlength="10"
                                           name="emppwd" value="${updateEmp.password}">
                                    <span id="empinspwd_err" class="err_msg" style="color: red"></span>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label class="col-md-3 col-form-label">再次确认密码</label>
                                <div class="col-md-9">
                                    <input id="acpassword" required type="password" class="form-control" maxlength="10"
                                           value="${updateEmp.password}">
                                    <span id="acempinspwd_err" class="err_msg" style="color: red"></span>
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
    window.onload = function removeempses() {
        // console.log("失去焦点");
        var value = "updateEmp";
        axios({
            method: "get",
            url: "http://localhost:8080/admin/employee/updateEmp?removeflag=true&updateEmp=" + value
        }).then(function (resp) {
        })
    }

    //1. 验证密码是否符合规则
    //1.1 获取密码的输入框
    var passwordInput = document.getElementById("password");
    var getpassword;
    //1.2 绑定onblur事件 失去焦点

    passwordInput.onblur = checkPassword;


    function checkPassword() {

        //1.3 获取用户输入的密码
        var password = passwordInput.value.trim();
        getpassword = password;
        //1.4 判断密码是否符合规则：长度 6~10
        var reg = /^\w{6,10}$/;
        var flag = reg.test(password);
        //var flag = password.length >= 6 && password.length <= 12;
        if (flag) {
            //符合规则
            document.getElementById("empinspwd_err").innerText = '';
        } else {
            //不合规则

            document.getElementById("empinspwd_err").innerText = '密码长度为6~10位';
            passwordInput.value = '';
        }
        return flag;
    }

    //确认密码
    var acpasswordInput = document.getElementById("acpassword");

    //1.2 绑定onblur事件 失去焦点
    acpasswordInput.onblur = checkacPassword;

    function checkacPassword() {
        //1.3 获取用户输入的密码
        var password = acpasswordInput.value.trim();

        var flag = getpassword == password;
        //var flag = password.length >= 6 && password.length <= 12;
        // console.log(getpassword)
        // console.log(password)
        // console.log(flag)
        if (flag) {
            //符合规则
            document.getElementById("acempinspwd_err").innerText = '';
        } else {
            //不合规则
            document.getElementById("acempinspwd_err").innerText = '两次密码输入不一致';
            acpasswordInput.value = '';
        }
        return flag;
    }

    var exitedID = true;
    var updempID = document.getElementById("updempID");
    var empid_err = document.getElementById("empid_err");
    updempID.onblur = checkEmpID;

    function checkEmpID() {
        // console.log("失去焦点");
        var value = updempID.value;
        axios({
            method: "get",
            url: "http://localhost:8080/admin/employee/updateEmp?idflag=true&eid=" + value
        }).then(function (resp) {
            if (resp.data == "success") {
                // alert("##" + resp.data + "##");
                updempID.value = '';
                empid_err.innerText = '员工 ID不存在！';
                exitedID = false;
                console.log("exited" + exitedID);

            } else {
                // alert("##" + resp.data + "##");
                empid_err.innerText = '';
                exitedID = true;
            }
        })
    }

    //1. 获取表单对象
    var empupdform = document.getElementById("empupdform");

    //2. 绑定onsubmit 事件
    empupdform.onsubmit = function () {
        //挨个判断每一个表单项是否都符合要求，如果有一个不合符，则返回false

        var flag = exitedID && checkPassword() && checkacPassword();
        // console.log(exitedID + "  " + checkPassword() + "  " + checkPassword())
        // alert(flag);
        // if (flag) {
        //     //符合规则
        //     document.getElementById("password_err").style.display = 'none';
        // } else {
        //     //不合符规则
        //     document.getElementById("password_err").style.display = '';
        // }
        return flag;
    }


</script>
</body>

</html>
