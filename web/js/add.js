/**
 * Created by alren on 16-8-21.
 */

//先把需要验证的表单元素列举出来
var userName = null;
var userCode = null;
var userPassword = null;
var ruserPassword = null;
var birthday = null;
var phone = null;
var address = null;
var addBtn = null;


$(document).ready(function() {
    userCode = $("#userCode");
    userName = $("#userName");
    userPassword = $("#userPassword");
    ruserPassword = $("#ruserPassword");
    birthday = $("#birthday");
    phone = $("#phone");
    address = $("#address");
    addBtn = $("#addBtn");

    //初始化所有的文本框后面添加 * ，更加灵活
    userCode.next("font").html("*");
    userName.next("font").html("*");
    userPassword.next("font").html("*");
    ruserPassword.next("font").html("*");
    birthday.next("font").html("*");
    phone.next("font").html("*");
    address.next("font").html("*");


    /*
    * 用户名的验证，失焦，获焦
    * jquery的方法传递
    * */
    userCode.bind("blur", function () {
        //ajax后台验证--userCode是否已存在
        //此账户可用true
        //不可用

        $.ajax({
            type:"GET", //请求类型
            url: path+ "/userexist.do",  //请求的url
            data: {userCode: userCode.val()}, //请求参数
            dataType: "json", //ajax接口,(请求url) 返回的数据类型
            success: function (data) {//data, 返回数据,json对象
                if(data.userCode == "exist") {//账号已存在，错误提示
                    validateTip(userCode.next(), {"color": "red"}, imgNo + "用户账号已存在", false);
                } else if(data.userCode == "empty") {//账号为空，错误提示
                    validateTip(userCode.next(), {"color": "red"}, imgNo + "请输入用户账户", false);
                } else {//账号可用
                    validateTip(userCode.next(), {"color": "green"}, imgYes, true);
                }
            },
            error: function (data) { //当访问时候，404,500，等非200的错误码
                validateTip(userCode.next(), {"color":"red"}, imgNo + "您访问的页面不存在", false);
            }
        });
    }).bind("focus", function() {
        validateTip(userCode.next(), {"color": "#666666"}, "* 用户编码就是您登陆系统的账户", false);
    }).focus();

    //用户名进行判断
    userName.bind("focus", function() {
        validateTip(userName.next(), {"color": "#666666"}, "* 用户名是4到9位的字符", false);
    }).bind("blur", function() {
        if (userName.val() != null && userName.val().length > 3 && userName.val().length < 10) {
            validateTip(userName.next(), {"color": "green"}, imgYes, true);
        } else {
            validateTip(userName.next(), {"color": "red"}, imgNo + "请输入正确的用户名", false);
        }
    });

    //密码进行判断
    userPassword.bind("focus", function() {
        validateTip(userPassword.next(), {"color": "#666666"}, "* 请输入6到11位密码", false);
    }).bind("blur", function () {
        if (userPassword.val() != null && userPassword.val().length > 5 && userPassword.val().length < 12){
            validateTip(userPassword.next(), {"color": "green"}, imgYes, true);
        } else {
            validateTip(userPassword.next(), {"color": "red"}, imgNo + "请输入正确密码", false);
        }
    });

    //密码的一致性
    ruserPassword.bind("focus", function() {
        validateTip(ruserPassword.next(), {"color": "#666666"}, "* 请再次输入密码", false);
    }).bind("blur", function() {
        if (ruserPassword.val() != null && ruserPassword.val() != "" && userPassword.val() == ruserPassword.val()) {
            validateTip(ruserPassword.next(), {"color": "green"}, imgYes, true);
        } else {
            validateTip(ruserPassword.next(), {"color": "red"}, imgNo + "请输入一致的密码", false);
        }
    });

    //验证生日
    birthday.bind("focus", function() {
        validateTip(birthday.next(), {"color": "#666666"}, "* 点击输入框输入生日", false);
    }).bind("blur", function() {
        // var jsBithday = birthday.get(0); //转换为dom对象
        // var date = new Date();
        if (birthday.val() != null && birthday.val() != "") {
            validateTip(birthday.next(), {"color": "green"}, imgYes, true);
        } else {
            validateTip(birthday.next(), {"color":"red"}, imgNo + "请输入正确出生年月日", false);
        }
    });

    //验证手机号
    phone.bind("focus", function() {
        validateTip(phone.next(), {"color": "#666666"}, "* 请输入11位正确的手机号", false);
    }).bind("blur", function () {
        var regexp = new RegExp("^1[34578]\\d{9}$");
        if (phone.val().match(regexp) != null) {
            validateTip(phone.next(), {"color": "green"}, imgYes, true);
        } else {
            validateTip(phone.next(), {"color": "red"}, imgNo + "请输入正确的手机号", false);
        }
    });

    //验证地址
    address.on("focus", function() {
        validateTip(address.next(), {"color": "#666666"}, "* 请输入家庭地址", false);
    }).on("blur", function () {
        if (address.val() != null && address.val().length >= 2) {
            validateTip(address.next(), {"color":"green"}, imgYes, true);
        } else {
            validateTip(address.next(), {"color": "red"}, imgNo + "请输入正确的家庭地址");
        }
    });

    //提交验证//这段代码有bug//
    addBtn.bind("click", function() {
        if (userCode.attr("validateStatus") == undefined || userCode.attr("validateStatus") != "true") {  //虽然值传递时，传的是boolean，但判断是字符串
            userCode.blur();
        } else if (userName.attr("validateStatus") == undefined || userName.attr("validateStatus") != "true"){
            userName.blur();
        }else if (userPassword.attr("validateStatus") == undefined || userPassword.attr("validateStatus") != "true"){
            userPassword.blur();
        }else if (ruserPassword.attr("validateStatus") == undefined || ruserPassword.attr("validateStatus") != "true"){
            ruserPassword.blur();
        }else if (birthday.attr("validateStatus") == undefined || birthday.attr("validateStatus") != "true"){
            birthday.blur();
        }else if (phone.attr("validateStatus") == undefined || phone.attr("validateStatus") != "true"){
            phone.blur();
        } else if (address.attr("validateStatus") == undefined || address.attr("validateStatus") != "true") {
            address.blur();
        }
        else {
            if (confirm("确认提交吗？")) {
                $("#userform").submit();
            }
        }
    });
});
