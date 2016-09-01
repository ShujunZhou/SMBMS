/**
 * Created by alren on 16-8-28.
 */
var proCode;
var proName;
var proDesc;
var proContact;
var proPhone;
var proAddress;
var proFax;
var modifyBtn;

$(document).on("focus", function() {
    //init
    proCode = $("#proCode");
    proName = $("#proName");
    proDesc = $("#proDesc");
    proContact = $("#proContact");
    proPhone = $("#proPhone");
    proAddress = $("#proAddress");
    proFax = $("#proFax");
    modifyBtn = $("#modifyBtn");
    
    proCode.next().html("*");
    proName.next().html("*");
    proDesc.next().html("*");
    proContact.next().html("*");
    proPhone.next().html("*");
    proAddress.next().html("*");
    proFax.next().html("*");
    
    //proCode Ajax
    proCode.on("focus", function () {
        validateTip(proCode.next(), {"color": "#666666"}, "* Please enter the four accounts", false);
    }).on("blur", function () {
        if (proCode.val().length != 4) {
            validateTip(proCode.next(), {"color": "red"}, "* Please enter the correct account", false);
        } else {
            $.ajax({
                type: "GET",
                url: path + "/provider.do",
                data: {"queryProvider": "proCode", "queryProviders": proCode.val()},
                dataType: "json",
                success: function (data) {
                    if (data.message == "empty"){
                        validateTip(proCode.next(), {"color": "green"}, imgYes, true);
                    } else {
                        validateTip(proCode.next(), {"color": "red"}, imgNo + "User already exist!", false);
                    }
                },
                error: function () {
                    validateTip(proCode.next(), {"color": "red"}, imgNo + "Network seems to be not.....", false);
                }
            });
        }
    }).focus();

    //proName
    proName.on("focus", function () {
        validateTip(proName.next(), {"color": "#666666"}, "* 请输入2到20位的供应商名称", false);
    }).on("blur", function () {
        if (proName != null && proName.val().length > 1 && proName.val().length < 21) {
            validateTip(proName.next(), {"color": "green"}, imgYes, true);
        } else {
            validateTip(proName.next(), {"color": "red"}, imgNo + "请输入正确名称", false);
        }
    });

    //proDesc
    proDesc.on("focus", function () {
        validateTip(proDesc.next(), {"color":"#666666"}, "* 请输入至少两位供应商描述", false);
    }).on("blur", function () {
        if (proDesc != null && proDesc.val().length > 1) {
            validateTip(proDesc.next(), {"color": "green"}, imgYes, true);
        } else {
            validateTip(proDesc.next(), {"color": "red"}, imgNo + "请输入正确的供应商名称", false);
        }
    });

    //proContact
    proContact.on("focus", function () {
        validateTip(proContact.next(), {"color": "#666666"}, "* 请输入2到4位联系人姓名", false);
    }).on("blur", function () {
        if (proContact != null && proContact.val().length >1 && proContact.val().length < 5) {
            validateTip(proContact.next(), {"color": "green"}, imgYes, true);
        } else {
            validateTip(proContact.next(), {"color": "red"}, imgNo + "请输入正确的姓名", false);
        }
    });

    //proPhone
    proPhone.on("focus", function () {
        validateTip(proPhone.next(), {"color": "#666666"}, "* 请输入11位电话号码", false);
    }).on("blur", function () {
        var regexp = new RegExp("^1[34578]\\d{9}$");
        if (proPhone != null && proPhone.val().match(regexp) != null) {
            validateTip(proPhone.next(), {"color": "green"}, imgYes, true);
        } else {
            validateTip(proPhone.next(), {"color": "red"}, imgNo + "请输入正确的电话号码", false);
        }
    });

    //proAddress
    proAddress.on("focus", function () {
        validateTip(proAddress.next(), {"color": "#666666"}, "* 请输入供应商地址", false);
    }).on("blur", function () {
        if (proAddress != null && proAddress.val().length > 1) {
            validateTip(proAddress.next(), {"color": "green"}, imgYes, true);
        } else {
            validateTip(proAddress.next(), {"color": "red"}, imgNo + "请输入正确的供应商地址", false);
        }
    })

    //proFax
    proFax.on("focus", function () {
        validateTip(proFax.next(), {"color": "#666666"}, "* 请输入6位传真", false);
    }).on("blur", function () {
        if (proFax != null && proFax.val().length == 6) {
            validateTip(proFax.next(), {"color": "green"}, imgYes, true);
        } else {
            validateTip(proFax.next(), {"color": "red"}, imgNo + "请输入正确的传真", false);
        }
    });

    modifyBtn.on("click", function () {
        if (proCode.attr("validateStatus") == undefined || proCode.attr("validateStatus") == "false") {
            proCode.blur();
        } else if (proName.attr("validateStatus") == undefined || proName.attr("validateStatus") == "false") {
            proName.blur();
        } else if (proDesc.attr("validateStatus") == undefined || proDesc.attr("validateStatus") == "false") {
            proDesc.blur();
        } else if (proContact.attr("validateStatus") == undefined || proContact.attr("validateStatus") == "false") {
            proContact.blur();
        } else if (proPhone.attr("validateStatus") == undefined || proPhone.attr("validateStatus") == "false") {
            proPhone.blur();
        } else if (proFax.attr("validateStatus") == undefined || proFax.attr("validateStatus") == "false") {
            proFax.blur();
        } else {
            if (confirm("Are you true to submit?")) {
                $("#providerForm").submit();
            }
        }
    });

});