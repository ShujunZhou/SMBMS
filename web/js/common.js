/**
 * Created by alren on 16-8-22.
 */
//创建一个公共的 js方法
var path = $("#path").val(); //通过隐藏域获取根路径
var imgYes = "<img width='15px' src='"+ path +"/images/y.png' />";
var imgNo = "<img width='15px' src='"+ path +"/images/n.png' />";
var queryId = $("#queryId"); //定义一个标志字段，你便于根据id查询时，跳转至不同的页面

/**
 * 提示信息的显示
 * element:显示提示信息的元素
 * css:提示的样式
 * tipString:提示信息
 * status:true/false --验证是否通过
 */

function validateTip(element, css, tipString, status) {
    element.html(tipString);
    element.css(css);
    //自定义一个属性，作为标志字段
    element.prev().attr("validateStatus", status);//为每个input添加一个属性validateStatus,并设置值
}
