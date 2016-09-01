/**
 * Created by alren on 16-8-24.
 */
var oldPwd = null;
var newPwd = null;
var rnewPwd = null;
var updBtn = null;

$(document).ready(function() {
    oldPwd = $("#oldPwd");
    newPwd = $("#newPwd");
    rnewPwd = $("#rnewPwd");
    updBtn = $("#updBtn");

    oldPwd.next().html("*");
    newPwd.next().html("*");
    rnewPwd.next().html("*");

    oldPwd.on("focus", function () {
        validateTip(oldPwd.next(), {"color":"#666666"}, "* 请输入6到11位密码", false);
    }).on("blur", function () {
        $.ajax({
            type: "GET",
            url: path + "/updatePwd.do",
            data: {"oldPwd": oldPwd.val()},
            dataType: "json",
            success : function (data) {
               if(data.result == "empty") {
                   validateTip(oldPwd.next(), {"color": "red"}, imgNo + "密码不见了。。。", false);
               } else if (data.result == "failed") {
                   validateTip(oldPwd.next(), {"color" : "red"}, imgNo + "密码输入有误！", false);
               } else if (data.result == "ok") {
                   validateTip(oldPwd.next(), {"color": "green"}, imgYes , true);
               }
            },
            error: function () {
                validateTip(oldPwd.next(), {"color":"red"}, "* 网络似乎不通哦。。。");
            }
        });
    });

    newPwd.on("focus", function() {
        validateTip(newPwd.next(), {"color": "#666666"}, "* 请输入6到11位新密码", false);
    }).on("blur", function () {
        if (newPwd.val() != null && newPwd.val().length > 5 && newPwd.val().length < 12) {
            validateTip(newPwd.next(), {"color": "green"}, imgYes, true);
        } else {
            validateTip(newPwd.next(), {"color":"red"}, imgNo + "请输入正确的密码", false);
        }
    });

    rnewPwd.on("focus", function() {
        validateTip(rnewPwd.next(), {"color":"#666666"}, "* 清再次输入密码", false);
    }).on("blur", function() {
        if (rnewPwd.val() != "" && rnewPwd.val() != null && rnewPwd.val() == newPwd.val()) {
            validateTip(rnewPwd.next(), {"color": "green"}, imgYes, true);
        } else {
            validateTip(rnewPwd.next(), {"color":"red"}, imgNo + "两次密码输入不一致", false);
        }
    });

    updBtn.on("focus", function () {
        if (oldPwd.attr("validateStatus") == undefined || oldPwd.attr("validateStatus") != "true") {
            oldPwd.blur();
        } else if (newPwd.attr("validateStatus") == undefined || newPwd.attr("validateStatus") != "true") {
            newPwd.blur();
        } else if (rnewPwd.attr("validateStatus") == undefined || rnewPwd.attr("validateStatus") != "true") {
            rnewPwd.blur();
        } else {
                $.ajax({
                    type: "POST",
                    url: path + "/mofidypwd.do",
                    data: {"newPwd": newPwd.val()},
                    dataType: "json",
                    success: function (data) {
                        if (data.message == "ok") {
                            alert("修改密码成功,请重新登陆");
                            window.parent.location.href = path + "/login.jsp";//window.parent当前页面的父页面
                        } else {
                            alert("修改密码失败");
                            window.location.reload();
                        }
                    },
                    error: function () {
                        alert("网络似乎不通哦。。。");
                    }
                });
        }
    })
});