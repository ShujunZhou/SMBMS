/**
 * Created by alren on 16-8-23.
 */

var userName = null;
var gender = null;
var birthday = null;
var phone = null;
var address = null;
var update = null;

$(document).ready(function() {
    userName = $("#userName");
    gender = $("#gender");
    birthday = $("#birthday");
    phone = $("#phone");
    address = $("#address");
    update = $("#update");

    //初始化。为每个文本框后面加上*
    userName.next("font").html("*");
    gender.next("font").html("*");
    birthday.next("font").html("*");
    phone.next("font").html("*");
    address.next("font").html("*");

    //验证用户姓名
    userName.on("focus", function() {
        validateTip($(this).next(), {"color": "#666666"}, "* 用户名是4到9位的字符", false);
    }).on("blur", function() {
        if (userName.val() != null && userName.val().length > 3 && userName.val().length < 10) {
            validateTip($(this).next(), {"color": "green"}, imgYes, true);
        } else {
            validateTip($(this).next(), {"color": "red"}, imgNo + "请输入正确的用户名", false);
        }
    });
    
    //验证用户生日
    birthday.on("focus", function() {
        validateTip($(this).next(), {"color": "#666666"}, "* 点击输入框输入密码", false);
    }).on("blur", function() {
        if (birthday.val() != null && birthday .val()!= "") {
            validateTip($(this).next(), {"color": "green"}, imgYes, true);
        } else {
            validateTip($(this).next(), {"color": "red"}, imgNo + "请输入正确出生年月日", false);
        }
    });
    
    //验证用户手机号
    phone.on("focus", function() {
        validateTip($(this).next(), {"color": "#666666"}, "* 请输入11位正确的手机号", false);
    }).on("blur", function() {
        var regexp = new RegExp("^1[34578]\\d{9}$");
        if (phone.val().match(regexp) != null) {
            validateTip($(this).next(), {"color": "green"}, imgYes, true);
        } else {
            validateTip($(this).next(), {"color": "red"}, imgNo + "请输入正确的手机号", false);
        }
    });
    
    //验证地址
    address.on("focus", function () {
        validateTip($(this).next(), {"color": "#666666"}, "* 请输入家庭地址", false);
    }).on("blur", function () {
        if (address.val() != null && address.val().length > 1) {
            validateTip($(this).next(), {"color": "green"}, imgYes, true)
        } else {
            validateTip($(this).next(), {"color": "red"}, imgNo + "请输入正确的家庭地址", false);
        }
    });

    //整体验证
    update.on("click", function() {
        userName.blur();
        birthday.blur();
        phone.blur();
        address.blur();

        if (userName.attr("validateStatus") != "true") {
            userName.blur();
        } else if (birthday.attr("validateStatus") != "true") {
            birthday.blur();
        } else if (phone.attr("validateStatus") != "true") {
            phone.blur();
        } else if (address.attr("validateStatus") != "true") {
            address.blur();
        } else {
            if (confirm("确定提交吗?")) {
                $("#userForm").submit();
            }
        }
    });
});
