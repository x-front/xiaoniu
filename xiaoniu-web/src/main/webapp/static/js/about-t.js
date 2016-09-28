$(function () {
    _xn_init()
});
function _xn_init() {
    $.post("/pageInfo/find", {id: 3}, function (a) {
        if (a.resultCode == 0) {
            if (a.entity) {
                $("#about-t p:eq(0)").html(a.entity.introdution)
            }
        } else {
            console.log(a.msg)
        }
    }, "json");
    $.post("/leader/list", function (a) {
        if (a.resultCode == 0) {
            var e = a.rows;
            var d = "", f = '<ul class="wow fadeInUp">';
            if ($(window).width() <= 500) {
                f = '<ul class="">';
            }
            for (var c = 0; c < a.rows.length; c++) {
                var b = e[c];
                if (c % 3 == 0 && c != 0) {
                    d += f + "</ul>";
                    f = '<ul class="wow fadeInUp">'
                    if ($(window).width() <= 500) {
                        f = '<ul class="">';
                    }
                }
                f += '<li><a href=""><div class="t-p"><img src="' + b.banner + '"/></div><br/><h4>' + b.name + "</h4><span>" + b.summary + "</span></a></li>"
            }
            if (a.rows.length != 0) {
                f += "</ul>";
                d += f
            }
            ;
            if ($(window).width() <= 500) {
                $("*").removeClass("wow fadeInUp");
            }
            $("#about-t").append(d)
        }
    }, "json")
};