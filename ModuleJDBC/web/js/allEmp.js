// import alert from "../element-ui/packages/alert";

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
    var flag = false;
    var oCheck = document.getElementsByName("check");
    for (var i = 0; i < oCheck.length; i++) {
        if (oCheck[i].checked == true) { //需要删除的编号
            alleid.push(oCheck[i].value);
            flag = true;
        }
    }
    alert(alleid);
    if (flag == true) {
        if (confirm("您确定要删除这些记录吗?")) {
            // console.log(alleid);
            location.href = "/admin/deleteEmp?flag=all&eid=" + alleid;
        }
    } else {
        alert("您至少要选择一条待删除的记录！");
    }

}
