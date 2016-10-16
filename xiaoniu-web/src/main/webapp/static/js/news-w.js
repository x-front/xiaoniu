$(function () {
    _xn_init()
});
function _xn_init() {
    $.post("/pageInfo/find", {id: 17}, function (a) {
        if (a.resultCode == 0) {
            if (a.entity) {
                $("#news-w p:eq(0)").html(a.entity.introdution);
            }
        } else {
            console.log(a.msg)
        }
    }, "json");
    seeMore()
}
function seeMore() {
    var a = $("#news-w .news-m-ul").length;
    if (a >= 0) {
        $.post("/news/list", {type: 15, page: a / 2 + 1, rows: 6, top: 0}, function (b) {
            if (b.resultCode == 0 && b.rows.length > 0) {
                var d = b.rows;
                var c = buildNewsRows(d, 0, 3);
                if($(window).width()<=500){
                    c=buildNewsRows1(d, 0, 3);
                }
                $("#news-w").append(c)
            }
            if ((a + 1) * 3 >= b.total) {
                $("#news-w-more").css("display", "none")
            }
        }, "json")
    }
}
function buildNewsDiv(a) {
    var b = "";
    b += '<li><a href="/static/news-w-inside.html?id=' + a.id + '">';
    b += '<div class="iN-img"><img src="' + a.banner + '"/></div>';
    b += "<p>" + a.title + "</p>";
    b += "<span>" + a.summary + "</span>";
    b += "</a>";
    b += '<a href="/static/news-w-inside.html?id=' + a.id + '" class="more">See more<span></span></a></li>';
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