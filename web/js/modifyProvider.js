/**
 * Created by alren on 16-8-26.
 */

var proName;
var proContact;
var proPhone;
var proAddress;
var proFax;
var modifyBtn;
var proDesc;

$(document).ready(function () {
    //页面加载时初始化
    proName = $("#proName");
    proContact = $("#proContact");
    proPhone = $("#proPhone");
    proAddress = $("#proAddress");
    proFax = $("#proFax");
    modifyBtn = $("#modifyBtn");
    proDesc = $("#proDesc");
    
    proName.next().val("*");
    proContact.next().val("*");
    proPhone.next().val("*");
    proAddress.next().val("*");
    proFax.next().val("*");
    
    //验证供应商名称
    proName.on("click", function() {
        validateTip(proName.next(), {"color": "#666666"}, "* 请输入2到20位的供应商名称", false);
    }).on("blur", function () {
        if (proName.val() != null && proName.val().length >1 && proName.val().length < 21) {
            validateTip(proName.next(), {"color" : "green"}, imgYes , true);
        } else {
            validateTip(proName.next(), {"color" : "red"}, imgNo + "请输入正确名称", false);
        }
    }).focus();
    
    //验证联系人
    proContact.on("click", function () {
        validateTip(proContact.next(), {"color":"#666666"}, "请输入2到4位联系人姓名", false);
    }).on("blur", function () {
        if (proContact.val() !=null && proContact.val().length > 1 && proContact.val().length < 5) {
            validateTip(proContact.next(), {"color": "green"}, imgYes, true);
        } else {
            validateTip(proContact.next(), {"color": "red"}, imgNo + "请输入正确的姓名", false);
        }
    });
    
    //验证联系人电话
    proPhone.on("click", function () {
        validateTip(proPhone.next(), {"color": "#666666"}, "请输入11位电话号码", false);
    }).on("blur", function () {
        var regexp = new RegExp("^1[34578]\\d{9}$");
        if (proPhone.val().match(regexp) != null) {
            validateTip(proPhone.next(), {"color": "green"}, imgYes, true);
        } else {
            validateTip(proPhone.next(), {"color": "red"}, imgNo + "请输入正确的电话号码", false);
        }
    });
    
    //验证地址
    proAddress.on("click", function () {
        validateTip(proAddress.next(), {"color": "#666666"}, "* 请输入供应商地址", false);
    }).on("blur", function () {
        if (proAddress.val() != null && proAddress.val().length > 1) {
            validateTip(proAddress.next(), {"color":"green"}, imgYes, true);
        } else {
            validateTip(proAddress.next(), {"color":"red"}, imgNo + "请输入正确的供应商地址", false);
        }
    });
    
    //验证传真
    proFax.on("click", function () {
        validateTip(proFax.next(), {"color":"#666666"}, "请输入6位传真", false);
    }).on("blur", function () {
        if (proFax.val() != null && proFax.val().length == 6) {
            validateTip(proFax.next(), {"color":"green"}, imgYes, true);  
        } else {
            validateTip(proFax.next(), {"color":"red"}, imgNo + "请输入正确的传真", false);
        }
    });
    //验证供应商描述
    proDesc.on("click", function () {
        validateTip(proDesc.next(), {"color": "#666666"}, "* 请输入至少两位供应商描述", false);
    }).on("blur", function () {
        if (proDesc.val() != null && proDesc.val().length > 1) {
            validateTip(proDesc.next(), {"color": "green"}, imgYes, true);
        } else {
            validateTip(proDesc.next(), {"color": "red"}, imgNo + "请输入正确的供应商名称", false);
        }
    });

    
    //提交数据
    modifyBtn.on("click", function () {
        proName.blur();
        proContact.blur();
        proPhone.blur();
        proAddress.blur();
        proFax.blur();
        proDesc.blur();

        if (proName.attr("validateStatus") != "true") {
            proName.blur();
        } else if (proContact.attr("validateStatus") != "true") {
            proContact.blur();
        } else if (proPhone.attr("validateStatus") != "true") {
            proPhone.blur();
        } else if (proAddress.attr("validateStatus") != "true") {
            proAddress.blur();
        } else if (proFax.attr("validateStatus") != "true") {
            proFax.blur();
        } else if (proDesc.attr("validateStatus") != "true") {
            proDesc.blur();
        } else {
            if (confirm("确认修改吗?")) {
                $("#providerForm").submit();
            }
        }
    });
});