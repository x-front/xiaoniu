$(function () {
    _xn_init()
});
function _xn_init() {
    var a = getParam("id");
    $.post("/news/find", {id: a}, function (b) {
        if (b.resultCode == 0) {
            if (b.entity) {
                $(".news-item1 .news-title h3:eq(0)").html(b.entity.title);
                $(".news-item1 .news-title span:eq(0)").html("文章来源：" + b.entity.source + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 浏览次数：" + b.entity.clickTimes);
                var c = b.entity.content;
                if (c.indexOf("application/x-shockwave-flash") == -1) {
                    c = c.replace(/\<embed/g, '<video controls="controls"  ');
                }
                $(".meta").attr("content", b.entity.title);
                $("title").html(b.entity.title);
                $(".news-item2:eq(0)").html(c);
                $("embed").attr("height", "400");
            }
        } else {
            console.log(b.msg)
        }
    }, "json")
};