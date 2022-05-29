<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>欢迎注册</title>
    <link href="css/register.css" rel="stylesheet">
</head>
<body>

<div class="form-div">
    <div class="reg-content">
        <h1>欢迎注册</h1>
        <span>已有帐号？</span> <a href="login.jsp">登录</a>
    </div>
    <form id="reg-form" action="/registerServlet" method="post">

        <table>

            <tr>
                <td>用户编号</td>
                <td class="inputs">
                    <input name="userid" type="text" id="userid">
                    <br>
                    <span id="userid_err" class="err_msg">${register_msg}</span>
                </td>

            </tr>
            <tr>
                <td>用户名称</td>
                <td class="inputs">
                    <input name="username" type="text" id="username">
                    <br>
                    <span id="username_err" class="err_msg"></span>
                </td>

            </tr>

            <tr>
                <td>密码</td>
                <td class="inputs">
                    <input name="password" type="password" id="password">
                    <br>
                    <span id="password_err" class="err_msg" style="display: none">密码为6~10位</span>
                </td>
            </tr>

            <tr>
                <td>确认密码</td>
                <td class="inputs">
                    <input name="acpassword" type="password" id="acpassword">
                    <br>
                    <span id="acpassword_err" class="err_msg"></span>
                </td>
            </tr>

            <tr>
                <td>验证码</td>
                <td class="inputs">
                    <input name="checkCode" type="text" id="checkCode">
                    <img id="checkCodeImg" src="/checkCodeServlet">
                    <a href="#" id="changeImg">看不清？</a>
                </td>
            </tr>

        </table>

        <div class="buttons">
            <input value="注 册" type="submit" id="reg_btn">
        </div>
        <br class="clear">
    </form>

</div>

<script>
    <%--window.onload = function () {--%>
    <%--    if (${register_msg}!=--%>
    <%--    null--%>
    <%--)--%>
    <%--    {--%>
    <%--        document.getElementById("userid_err").style.display = '';--%>
    <%--    }--%>

    <%--}--%>
    document.getElementById("changeImg").onclick = function () {
        document.getElementById("checkCodeImg").src = "/checkCodeServlet?" + new Date().getMilliseconds();
    }
    //1. 验证用户名是否符合规则
    //1.1 获取用户名的输入框
    var useridInput = document.getElementById("userid");

    //1.2 绑定onblur事件 失去焦点
    useridInput.onblur = checkUserid;

    function checkUserid() {
        //1.3 获取用户输入的用户名
        var userid = useridInput.value.trim();

        //1.4 判断用户名是否符合规则：长度 3,单词字符组成
        var reg = /^\w{3}$/;
        var flag = reg.test(userid);
        // console.log(flag);
        //var flag = userid.length >= 6 && userid.length <= 12;
        if (flag) {
            //符合规则
            // document.getElementById("userid_err").style.display = 'none';
            document.getElementById("userid_err").innerText = "";
        } else {
            //不合符规则
            // document.getElementById("userid_err").style.display = '';
            document.getElementById("userid_err").innerText = " 用户编号为3个字符";
            useridInput.value = '';
        }

        return flag;
    }

    var usernameInput = document.getElementById("username");

    //1.2 绑定onblur事件 失去焦点
    usernameInput.onblur = checkUsername;

    function checkUsername() {
        //1.3 获取用户输入的用户名
        var username = usernameInput.value.trim();

        //1.4 判断用户名是否符合规则：长度 3,单词字符组成
        var reg = /^\w{3,10}$/;
        var flag = reg.test(username);
        // console.log(flag);
        //var flag = username.length >= 6 && username.length <= 12;
        if (flag) {
            //符合规则
            // document.getElementById("username_err").style.display = 'none';
            document.getElementById("username_err").innerText = "";
        } else {
            //不合符规则
            // document.getElementById("username_err").style.display = '';
            document.getElementById("username_err").innerText = " 用户名为3~10个字符";
            usernameInput.value = '';
        }

        return flag;
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
            document.getElementById("password_err").style.display = 'none';
        } else {
            //不合规则

            document.getElementById("password_err").style.display = '';
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
        console.log(getpassword)
        console.log(password)
        console.log(flag)
        if (flag) {
            //符合规则
            document.getElementById("acpassword_err").innerText = '';
        } else {
            //不合规则
            document.getElementById("acpassword_err").innerText = '两次密码输入不一致';
            acpasswordInput.value = '';
        }

        return flag;
    }


    //1. 获取表单对象
    var regForm = document.getElementById("reg-form");

    //2. 绑定onsubmit 事件
    regForm.onsubmit = function () {
        //挨个判断每一个表单项是否都符合要求，如果有一个不合符，则返回false

        var flag = checkUserid() && checkUsername() && checkPassword() && checkacPassword;
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
