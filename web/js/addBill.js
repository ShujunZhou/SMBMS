/**
 * Created by alren on 16-8-29.
 */

var billCode;
var productName;
var productDesc;
var productUnit;
var productCount;
var totalPrice;
var providerId;
var payed;
var addBtn;

$(document).ready(function () {
    billCode = $("#billCode");
    productName = $("#productName");
    productDesc = $("#productDesc");
    productUnit = $("#productUnit");
    productCount = $("#productCount");
    totalPrice = $("#totalPrice");
    providerId = $("#providerId");
    payed = $("input:checked");
    addBtn = $("#addBtn");

    billCode.next().html("*");
    productName.next().html("*");
    productUnit.next().html("*");
    productCount.next().html("*");
    totalPrice.next().html("*");

    //billCode
    billCode.on("focus", function () {
        validateTip(billCode.next(), {"color": "#666666"}, "* 请输入账单编号", false);
    }).on("blur", function () {
        if (billCode != null && billCode.val().length > 0 && billCode.val() > 0) {
            validateTip(billCode.next(), {"color": "green"}, imgYes, true);
        } else {
            validateTip(billCode.next(), {"color": "red"}, imgNo + "请输入正确的账单编号", false);
        }
    }).focus();

    //productName
    productName.on("focus", function () {
        validateTip(productName.next(), {"color": "#666666"}, "* 请输入2到15位商品名称", false);
    }).on("blur", function () {
        if (productName != null && productName.val().length > 1 && productName.val().length < 16) {
            validateTip(productName.next(), {"color": "green"}, imgYes, true);
        } else {
            validateTip(productName.next(), {"color": "red"}, imgNo + "请输入正确的供应商名称", false);
        }
    });

    //productDesc
    productDesc.on("focus", function () {
        validateTip(productDesc.next(), {"color": "#666666"}, "* 请输入（2到20位）商品描述", false)
    }).on("blur", function () {
        if (productDesc != null && productDesc.val().length > 1 && productDesc.val().length < 21) {
            validateTip(productDesc.next(), {"color": "green"}, imgYes, true);
        } else {
            validateTip(productDesc.next(), {"color": "red"}, imgNo + "请输入正确的商品描述", false);
        }
    });

    //productUnit
    productUnit.on("focus", function () {
        validateTip(productUnit.next(), {"color": "#666666"}, "* 请输入商品的单位", false);
    }).on("blur", function () {
        if (productUnit != null && productUnit.val().length > 0 && productUnit.val().length < 3) {
            validateTip(productUnit.next(), {"color": "green"}, imgYes, true);
        } else {
            validateTip(productUnit.next(), {"color": "red"}, imgNo + "请输入正确的商品单位", false);
        }
    });

    //productCount
    productCount.on("focus", function () {
        validateTip(productCount.next(), {"color": "#666666"}, "* 请输入商品总量（最多保留小数点后两位）", false);
    }).on("blur", function () {
        var regexp = new RegExp("^[0-9]+(.[0-9]{0,2})?$");
        if (productCount.val().match(regexp) != null) {
            validateTip(productCount.next(), {"color": "green"}, imgYes, true);
        } else {
            validateTip(productCount.next(), {"color": "red"}, imgNo + "请输入正确的商品总量", false);
        }
    });

    //totalPrice
    totalPrice.on("focus", function () {
        validateTip(totalPrice.next(), {"color": "#666666"}, "* 请输入总价钱(最多保留小数点后两位)", false);
    }).on("blur", function () {
        var regexp = new RegExp("^[0-9]+(.[0-9]{0,2})?$");
        if (totalPrice.val().match(regexp)) {
            validateTip(totalPrice.next(), {"color": "green"}, imgYes, true);
        } else {
            validateTip(totalPrice.next(), {"color": "red"}, imgNo + "请输入正确的总价钱", false);
        }
    });

    //providerId
    providerId.on("change", function() {
        validateTip(providerId.next(), {"color" :"green"}, imgYes, true);
    });

    //addBtn
    addBtn.on("click", function () {
        if (billCode.attr("validateStatus") == undefined || billCode.attr("validateStatus") == "false") {
            billCode.blur();
        } else if (productName.attr("validateStatus") == undefined || productName.attr("validateStatus") == "false") {
            productName.blur();
        } else if (productUnit.attr("validateStatus") == undefined ||productUnit.attr("validateStatus") == "false") {
            productUnit.blur();
        } else if (productCount.attr("validateStatus") == undefined || productCount.attr("validateStatus") == "false") {
            productCount.blur();
        } else if (totalPrice.attr("validateStatus") == undefined || totalPrice.attr("validateStatus") == "false") {
            totalPrice.blur();
        } else if (providerId.attr("validateStatus") == undefined || providerId.attr("validateStatus") != "true") {
            validateTip(providerId.next(), {"color" : "red"}, imgNo + "请选择", false);
        } else {
            if (confirm("Do you true to submit?")) {
                $("#billForm").submit();
            }
        }
    });
});