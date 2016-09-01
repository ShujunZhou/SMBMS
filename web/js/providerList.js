/**
 * Created by alren on 16-8-26.
 */

$(document).ready(function () {
    //修改
    $(".modifyProvider").on("click", function () {
        var obj = $(this);
        window.location.href = path + "/provider.do?queryProvider=id&queryId=" + queryId +"&queryProviders=" + obj.attr("providerId");
    });

    //删除
    $(".deleteProvider").on("click", function () {
        var obj = $(this);
        if (confirm("Do you confirm to delete?")) {
            var providerId = $(this).attr("providerId");
            $.ajax({
                type:"GET",
                data:{"providerId": providerId},
                dataType:"json",
                url: path+ "/deleteProvider.do",
                success: function (data) {
                    if (data.message == "succeed") {
                        alert("succeed");
                        obj.parents("tr").remove(); //Delete, delete the current line;      $(this).parents("tr").remove(); error
                    } else {
                        alert("failed");
                    }
                },
                error: function (data) {
                    alert("The network seems to be not.....");
                }
            });
        }
    });
});