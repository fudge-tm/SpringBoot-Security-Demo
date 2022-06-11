<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>login</title>
    <link href="assets/css/login.css" rel="stylesheet">
</head>

<body>
<div id="loginDiv" style="height: 350px">
    <form action="/login" method="post" id="form">
        <h1 id="loginMsg">LOGIN IN</h1>
        <div id="errorMsg">${login_msg} ${register_msg}</div>
        <p>员工编号:<input id="userid" name="userid" value="${cookie.userid.value}" type="text"></p>

        <p>登录密码:<input id="password" name="password" value="${cookie.password.value}" type="password"></p>
        <p>记住我:<input id="remember" name="remember" value="1" type="checkbox"></p>
        <div id="subDiv">
            <input id="loginb" type="submit" class="button" value="login up">
            <a href="register.jsp">注册</a>
        </div>
        <div class="misspwtips"><span>忘记密码请联系管理员！</span></div>
    </form>
</div>


</body>
</html>
