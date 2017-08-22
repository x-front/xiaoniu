$(function () {
    _xn_init()
});
function _xn_init() {
    $.post("/news/list", {type: 1, page: 1, rows: 1, top: 1}, function (a) {
        if (a.resultCode == 0 && a.total > 0) {
            var c = a.rows;
            var b = c[0];
            $("#wrap .about-w-banner-pic img:eq(0)").attr("src", b.banner);
            $("#wrap .about-w-banner h3:eq(0)").html(b.title);
            $("#wrap .about-w-banner p:eq(0)").html(b.summary);
            $(".about-w-link").attr("href", "/static/beautiful-f-inside.html?id=" + b.id)
        }
    }, "json");
    seeMore()
}
function seeMore() {
    var a = $(".about-w-main .about-w-list .about-w-item").length;
    if (a >= 0) {
        var targetPage =  Math.ceil(a / 2) + 1;
        $.post("/news/list", {type: 1, page: targetPage, rows: 2, top: 0}, function (b) {
            if (b.resultCode == 0 && b.rows.length > 0) {
                var d = b.rows;
                var c = buildNewsRows(d, 0, 2);
                $(".about-w-list:eq(0)").append(c)
            }
            if (targetPage * 2 >= b.total) {
                $("#seeMoreBtn").css("display", "none")
            }
        }, "json")
    }
}
function buildNewsDiv(a, b) {
    var c = '<div class="about-w-item about-w-item' + (b % 2 + 1) + ' wow fadeInUp"><a href="/static/beautiful-f-inside.html?id=' + a.id + '"><div class="about-wh-img"><img src="' + a.banner + '"/></div>';
    c += "<h3>" + a.title + "</h3>";
    c += "<p>" + a.summary + "</p></a>";
    c += '<a href="/static/beautiful-f-inside.html?id=' + a.id + '" class="more">See more<span></span></a>';
    c += "</div>";
    return c
}
function buildNewsDiv1(a, b) {
    var c = '<div class="about-w-item about-w-item' + (b % 2 + 1) + '"><a href="/static/beautiful-f-inside.html?id=' + a.id + '"><div class="about-wh-img"><img src="' + a.banner + '"/></div>';
    c += "<h3>" + a.title + "</h3>";
    c += "<p>" + a.summary + "</p></a>";
    c += '<a href="/static/beautiful-f-inside.html?id=' + a.id + '" class="more">See more<span></span></a>';
    c += "</div>";
    return c
}
function buildNewsRows(f, e, a) {
    var d = "";
    for (var c = e; c < f.length; c++) {
        var b = buildNewsDiv(f[c], c - e);
        if($(window).width()<=500){b = buildNewsDiv1(f[c], c - e);}

        d += b;
        if ((c - e + 1) % a == 0) {
            d += '<div class="clearfix"></div>'
        }
    }
    if ((f.length - e) % a != 0) {
        d += '<div class="clearfix"></div>'
    }
    return d
};