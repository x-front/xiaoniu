$(function () {
    _xn_init()
});
function _xn_init() {
    $.post("/news/list", {type: 12, page: 1, rows: 4, top: 1}, function (a) {
        if (a.resultCode == 0) {
            var g = a.rows;
            var e = "", f = "", b = "";
//            var e = '<li><a href="/static/zlbg.pdf"><img src="/static/images/lbt.jpg"/></a></li>', f = '<li><a href="/static/zlbg.pdf"><h3>小牛资本金融科技战略报告</h3><p></p></a></li>', b = '<li><a href="javascript:;"></a></li>';
            for (var d = 0; d < g.length; d++) {
                var c = g[d];
                e += '<li><a href="/static/news-t-inside.html?id=' + c.id + '"><img src="' + c.banner + '"/></a></li>';
                b += '<li><a href="javascript:;"></a></li>';
                f += '<li><a href="/static/news-t-inside.html?id=' + c.id + '"><h3>' + c.title + '</h3><p>' + c.summary + '</p></a></li>'
            }
            $(".news-pic ul:eq(0)").html(e);
            $(".news-pic ul:eq(1)").html(b);
            $(".news-pic ul:eq(2)").html(f);
        } else {
            console.log(a.msg)
        }
    });
    $.post("/news/list", {type: 12, page: 1, rows: 6, top: 0}, function (a) {
        if (a.resultCode == 0) {
            var d = a.rows;
            var c = "";
            for (var b = 0; b < d.length; b++) {
                entity = d[b];
                c += '<li><a href="/static/news-t-inside.html?id=' + entity.id + '">';
                c += '<div class="iN-img"><img src="' + entity.banner + '"/></div>';
                c += "<p>" + entity.title + "</p>";
                c += "<span>" + entity.summary + "</span>";
                c += "</a>";
                c += '<a href="/static/news-t-inside.html?id=' + entity.id + '" class="more">See more<span></span></a></li>'
            }
            c += '<div class="clearfix"></div>';
            $("#news-t ul:eq(0)").html(c)
        } else {
            console.log(a.msg)
        }
    });
};

function seeMore() {
    var a = $(".news-t .idV-ul").length;
    if (a >= 0) {
        $.post("/news/list", {type: 12, page: Math.ceil(a / 6) + 1, rows: 6, top: 0}, function (b) {
            if (b.resultCode == 0 && b.rows.length > 0) {
                var d = b.rows;
                var c = buildNewsRows(d, 0, 3);
                if($(window).width()<=500){
                    c=buildNewsRows1(d, 0, 3);
                }
                $(".news-t .idV-ul").append(c)
            }
            if ((a + 1) * 6 >= b.total) {
                $(".news-t .idV-more").css("display", "none")
            }
        }, "json")
    }
}

function buildNewsDiv(a) {
    var b = "";
    b += '<li><a href="/static/news-m-inside.html?id=' + a.id + '">';
    b += '<div class="iN-img"><img src="' + a.banner + '"/></div>';
    b += "<p>" + a.title + "</p>";
    b += "<span>" + a.summary + "</span>";
    b += "</a>";
    b += '<a href="/static/news-m-inside.html?id=' + a.id + '" class="more">See more<span></span></a></li>';
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