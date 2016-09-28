$(function () {
    _xn_init()
});
function _xn_init() {
    $.post("/pageInfo/find", {id: 5}, function (a) {
        if (a.resultCode == 0) {
            if (a.entity) {
                $("#about-r p:eq(0)").html(a.entity.introdution)
            }
        } else {
            console.log(a.msg)
        }
    }, "json");
    $.post("/honor/list", function (a) {
        if (a.resultCode == 0) {
            var e = a.rows;
            var d = "", f = '<ul class="wow fadeInUp">';
            if ($(window).width() <= 500) {
                f = '<ul class="">';
            }
            for (var c = 0; c < a.total; c++) {
                var b = e[c];
                if (c % 2 == 0 && c != 0) {
                    d += f + '<div class="clearfix"></div></ul>';
                    f = '<ul class="wow fadeInUp">'
                    if ($(window).width() <= 500) {
                        f = '<ul class="">';
                    }
                }
                f += '<li><div><img src="' + b.banner + '"/></div><br/><span>' + b.summary + "</span></li>";

            }
            if (a.total != 0) {
                f += '<div class="clearfix"></div></ul>';
                d += f
            }
            $("#about-r").append(d)
        }
    }, "json")
};