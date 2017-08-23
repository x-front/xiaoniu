$(function () {
    _xn_init()
});
function _xn_init() {
     seeMore()
}
function seeMore() {
    var a = $("#news-m .news-m-ul").length;
    if (a >= 0) {
        $.post("/media/list", {type: 7, page: a / 2 + 1, rows: 6, lang: 0}, function (b) {
            if (b.resultCode == 0 && b.rows.length > 0) {
                var d = b.rows;
                var c = buildNewsRows(d, 0, 3);
                if($(window).width()<=500){
                    c=buildNewsRows1(d, 0, 3);
                }
                $("#news-m").append(c)
            }
            if ((a + 1) * 3 >= b.total) {
                $("#news-m-more").css("display", "none")
            }
        }, "json")
    }
}
function buildNewsDiv(a) {
    var b = "";
    b += '<li><a href="' + a.extMedia + '">';
    b += '<div class="iN-img"><img src="' + a.extCover + '"/></div>';
    b += "<p>" + a.introdution + "</p>";
    // b += "<span>" + a.summary + "</span>";
    b += "</a>";
    b += '<a href="' + a.extMedia + '" class="more">See more<span></span></a></li>';
    return b
}
function buildNewsRows(f, e, a) {
    var d = "";
    var g = '<ul class="idV-ul news-m-ul wow fadeInUp">';
    for (var c = e; c < f.length; c++) {
        var b = buildNewsDiv(f[c], c - e);
        g += b;
        if ((c - e + 1) % a == 0) {
            g += '<div class="clearfix"></div></ul>';
            d += g;
            g = '<ul class="idV-ul news-m-ul wow fadeInUp">'
        }
    }
    if ((f.length - e) % a != 0) {
        g += '<div class="clearfix"></div></ul>';
        d += g
    }
    return d
}
function buildNewsRows1(f, e, a) {
    var d = "";
    var g = '<ul class="idV-ul news-m-ul">';
    for (var c = e; c < f.length; c++) {
        var b = buildNewsDiv(f[c], c - e);
        g += b;
        if ((c - e + 1) % a == 0) {
            g += '<div class="clearfix"></div></ul>';
            d += g;
            g = '<ul class="idV-ul news-m-ul">'
        }
    }
    if ((f.length - e) % a != 0) {
        g += '<div class="clearfix"></div></ul>';
        d += g
    }
    return d
};