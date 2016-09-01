/**
 * Created by alren on 16-8-23.
 */
$(document).ready(function() {
    //通过jquery的class选择器
    //对每个class为viewUser的元素进行动作绑定(click)
    $(".viewUser").bind("click", function() {
        var obj = $(this);
        window.location.href = path + "/view.do?userid=" + obj.attr("userid");
    });
    //修改
    $(".modifyUser").bind("click", function() {
        var obj = $(this);
        window.location.href = path + "/update.do?userid=" + obj.attr("userid");
    });

    $(".deleteUser").bind("click", function () {
        var obj = $(this);
        if (confirm("您确定要删除用户【"+ obj.attr("username") + "】吗?")) {
            $.ajax({
                type: "GET",
                url: path + "/delete.do",
                data: {"userid" : obj.attr("userid")},
                dateType: "json",
                success: function (data) {
                    if (data.result == "success") {//删除成功,然后需要删除行
                        alert("删除成功");
                        obj.parents("tr").remove();
                    } else if (data.result == "notexits") {
                        alert("删除的用户不存在");
                    } else if (data.result == "failed") {
                        alert("删除失败");
                    }
                },
                error: function (data) {
                    alert("删除失败......");
                }
            });
        }
    });
});