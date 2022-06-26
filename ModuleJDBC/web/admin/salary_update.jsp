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
                    <h4 class="page-title">修改员工工资</h4>
                </div>
            </div>
            <div class="row">
                <div class="col-md-6">
                    <div class="card-box">
                        <h4 class="card-title">修改员工工资信息</h4>
                        <form action="/admin/salary/updateSalary" id="salaryupdform">
                            <div class="form-group row">
                                <label class="col-md-3 col-form-label">员工 ID</label>
                                <div class="col-md-9">
                                    <input required type="text" class="form-control" maxlength="3" name="updeid"
                                           id="addsalaryeID" value="${updateSalary.eid}">
                                    <span id="salaryeid_err" class="err_msg" style="color: red"></span>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label class="col-md-3 col-form-label">职位 ID</label>
                                <div class="col-md-9">
                                    <input required type="text" class="form-control" maxlength="3" name="updtid"
                                           id="addsalarytID" value="${updateSalary.tid}">
                                    <span id="salarytid_err" class="err_msg" style="color: red"></span>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label class="col-md-3 col-form-label">修改后的员工工资</label>
                                <div class="col-md-9">
                                    <input required type="text" class="form-control" maxlength="10" name="salarymoney"
                                           id="smoney" value="${updateSalary.smoney}">
                                    <span id="smoney_err" class="err_msg" style="color: red"></span>
                                </div>
                            </div>
                            <div class="text-right">
                                <button type="button" id="salpost" class="btn btn-primary" onclick="checkandsubmit()">
                                    提交
                                </button>
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

    window.onload = function removesalaryses() {
        // console.log("失去焦点");
        var value = "updateSalary";
        axios({
            method: "get",
            url: "http://localhost:8080/admin/salary/updateSalary?removeflag=true&updateSalary=" + value
        }).then(function (resp) {
        })
    }

    //1. 验证密码是否符合规则
    //1.1 获取工资的输入框
    var smoneyInput = document.getElementById("smoney");
    //1.2 绑定onblur事件 失去焦点
    smoneyInput.onblur = checksmoney;

    function checksmoney() {
        //1.3 获取用户输入的工资
        var smoney = smoneyInput.value.trim();
        //1.4 判断工资是否符合规则：零或者非零开头的正整数
        var reg = /^(0|[1-9][0-9]*)+(?:\.\d{0,2})?$/;
        var flag = reg.test(smoney);
        if (flag) {
            //符合规则
            document.getElementById("smoney_err").innerText = '';
        } else {
            //不合规则
            document.getElementById("smoney_err").innerText = '工资金额需为零或者整数和带两位小数的小数';
            smoneyInput.value = '';
        }
        return flag;
    }

    var addsalaryeID = document.getElementById("addsalaryeID");
    var salaryeid_err = document.getElementById("salaryeid_err");
    var addsalarytID = document.getElementById("addsalarytID");
    var salarytid_err = document.getElementById("salarytid_err");

    var exitedID;

    //2. 绑定onsubmit 事件
    function checkandsubmit() {
        var evalue = addsalaryeID.value;
        var tvalue = addsalarytID.value;
        if (evalue != null && tvalue != null) {
            axios({
                method: "get",
                url: "http://localhost:8080/admin/salary/updateSalary?testidflag=true&updeid=" + evalue + "&updtid=" + tvalue
            }).then(function (resp) {
                if (resp.data == "fail") {//fail表示不存在
                    addsalaryeID.value = '';
                    addsalarytID.value = '';
                    salaryeid_err.innerText = '该员工的该职位已存在！';
                    salarytid_err.innerText = '该员工的该职位已存在！';
                    exitedID = false;
                    // console.log("exited" + exitedID);

                } else {
                    salaryeid_err.innerText = '';
                    salarytid_err.innerText = '';
                    exitedID = true;
                    if (checksmoney() && exitedID) {
                        document.getElementById("salaryupdform").submit();
                    }

                }
            })

        }

    }


</script>
</body>

</html>
