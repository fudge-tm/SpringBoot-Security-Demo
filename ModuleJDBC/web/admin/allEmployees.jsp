<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link href="../css/allEmployeescss.css" rel="stylesheet">
    <script>
        // document.querySelector("loginout") = function () {
        //     this.style.cursor = "pointer";
        //     document.querySelector(".pit .txt").style.height = "60px";
        //
        // }
    </script>
</head>
<body>

<h1 align="center">员工信息管理系统</h1>
<hr>
<br>
<%--<h3>${employee.ename},欢迎您</h3>--%>

<%--<input type="button" value="新增" id="add"><br>--%>
<hr>

<button id="loginout">用户注销</button>
<table border="1" cellspacing="0" width="80%">
    <tr>
        <th><input type="checkbox" onclick="allcheck(this)"></th>
        <th>员工id</th>
        <th>员工姓名</th>
        <th>员工密码</th>
        <th>员工权限</th>
        <th>备注信息</th>
        <th>操作&nbsp;&nbsp;<input type="button" value="批量删除" onclick="delall()"></th>
    </tr>


    <c:forEach items="${employees}" var="employee" varStatus="status">
        <tr align="center">
                <%--<td>${brand.id}</td>--%>
            <td><input type="checkbox" name="check" value=${employee.eid}></td>
            <td>${employee.eid}</td>
            <td>${employee.ename}</td>
            <td>${employee.password}</td>
            <td>${employee.superuser}</td>
            <td>${employee.flag}</td>
                <%--            <c:if test="${employee.flag == 1}">--%>
                <%--                <td>启用</td>--%>
                <%--            </c:if>--%>
                <%--            <c:if test="${employee.flag != 1}">--%>
                <%--                <td>禁用</td>--%>
                <%--            </c:if>--%>

            <td><a href="/admin/updateEmp?eid=${employee.eid}">修改</a>
                <a href="/admin/deleteEmp?eid=${employee.eid}">删除</a></td>
        </tr>

    </c:forEach>

</table>

<script type="text/javascript" src="../assets/js/allEmp.js"></script>

</body>
</html>
