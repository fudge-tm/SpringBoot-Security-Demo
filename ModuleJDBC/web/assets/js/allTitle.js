function allcheck(checkbox) {
    var oCheck = document.getElementsByName("check");
    if (checkbox.checked == true) {
        for (var i = 0; i < oCheck.length; i++) {
            oCheck[i].checked = true;
        }
    } else {
        for (var i = 0; i < oCheck.length; i++) {
            oCheck[i].checked = false;
        }
    }
}

function delall() {
    var alltid = new Array();
    var flag = false;
    var oCheck = document.getElementsByName("check");
    for (var i = 0; i < oCheck.length; i++) {
        if (oCheck[i].checked == true) { //需要删除的编号
            alltid.push(oCheck[i].value);
            flag = true;
        }
    }

    if (flag == true) {
        if (confirm("您确定要删除这些职位吗?")) {
            // console.log(alleid);
            location.href = "/admin/title/deleteTitle?flag=all&titletid=" + alltid;
        }
    } else {
        alert("您至少要选择一条待删除的记录！");
    }

}

function delbyids(a) {
    // alert("sss");
    if (confirm("您确定要删除该职位吗?")) {
        // console.log(alleid);
        location.href = "/admin/title/deleteTitle?titletid=" + a;
    }

}

function updbyids(a) {
    // alert("职位发请求");
    location.href = "/admin/title/updateTitle?flag=true&titletid=" + a;
}
