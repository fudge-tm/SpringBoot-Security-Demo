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
    var alleid = new Array();
    var alltid = new Array();
    var flag = false;
    var oCheck = document.getElementsByName("check");
    var checktid = document.getElementsByName("checktid");
    for (var i = 0; i < oCheck.length; i++) {
        if (oCheck[i].checked == true) { //需要删除的编号
            alleid.push(oCheck[i].value);
            alltid.push(checktid[i].value);
            flag = true;
        }
    }

    if (flag == true) {
        if (confirm("您确定要删除这些员工工资信息记录吗?")) {
            // console.log(alleid);
            location.href = "/admin/salary/deleteSalary?flag=all&eid=" + alleid + "&tid=" + alltid;
        }
    } else {
        alert("您至少要选择一条待删除的记录！");
    }

}

function delbyids(eid, tid) {
    // alert("sss");
    if (confirm("您确定要删除这条员工工资记录吗?")) {
        // console.log(alleid);
        location.href = "/admin/salary/deleteSalary?eid=" + eid + "&tid=" + tid;
    }

}

function updbyids(eid, tid) {
    // alert("sss");
    // console.log(alleid);
    location.href = "/admin/salary/updateSalary?flag=true&updeid=" + eid + "&updtid=" + tid;
}
