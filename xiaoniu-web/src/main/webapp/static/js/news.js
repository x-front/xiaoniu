$(function () {
    _xn_init()
});
function _xn_init() {
    $.post("/pageInfo/dongtai", function (a) {
        if (a.resultCode == 0) {
            if (a.news) {
            }
            if (a.report) {
            }
        } else {
            console.log(a.msg)
        }
    }, "json");
    $.post("/news/list", {type: 11, page: 1, rows: 4, top: 0}, function (a) {
        if (a.resultCode == 0) {
            var g = a.rows;
            var e = "", f = "", b = "";
//            var e = '<li><a href="/static/zlbg.pdf"><img src="/static/images/lbt.jpg"/></a></li>', f = '<li><a href="/static/zlbg.pdf"><h3>小牛资本金融科技战略报告</h3><p></p></a></li>', b = '<li><a href="javascript:;"></a></li>';
            for (var d = 0; d < g.length; d++) {
                var c = g[d];
                e += '<li><a href="/static/news-t-inside.html?id=' + c.id + '"><img src="' + c.banner + '"/></a></li>';
                b += '<li><a href="javascript:;"></a></li>';
                f += '<li><a href="/static/news-t-inside.html?id=' + c.id + '"><h3>' + c.title + '</h3></a></li>'
            }
            $(".news-pic ul:eq(0)").html(e);
            $(".news-pic ul:eq(1)").html(b);
            $(".news-pic ul:eq(2)").html(f);
        } else {
            console.log(a.msg)
        }
    });
    $.post("/news/list", {type: 5, page: 1, rows: 6, top: 0}, function (a) {
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
    $.post("/news/list", {type: 6, page: 1, rows: 6, top: 0}, function (a) {
        if (a.resultCode == 0) {
            var d = a.rows;
            var c = "";
            for (var b = 0; b < d.length; b++) {
                entity = d[b];
                c += '<li><a href="/static/news-m-inside.html?id=' + entity.id + '">';
                c += '<div class="iN-img"><img src="' + entity.banner + '"/></div>';
                c += "<p>" + entity.title + "</p>";
                c += "<span>" + entity.summary + "</span>";
                c += "</a>";
                c += '<a href="/static/news-m-inside.html?id=' + entity.id + '" class="more">See more<span></span></a></li>'
            }
            c += '<div class="clearfix"></div>';
            $("#news-m ul:eq(0)").html(c)
        } else {
            console.log(a.msg)
        }
    });
    $.post("/news/list", {type: 15, page: 1, rows: 6, top: 0}, function (a) {
        if (a.resultCode == 0) {
            var d = a.rows;
            var c = "";
            for (var b = 0; b < d.length; b++) {
                entity = d[b];
                c += '<li><a href="/static/news-m-inside.html?id=' + entity.id + '">';
                c += '<div class="iN-img"><img src="' + entity.banner + '"/></div>';
                c += "<p>" + entity.title + "</p>";
                c += "<span>" + entity.summary + "</span>";
                c += "</a>";
                c += '<a href="/static/news-m-inside.html?id=' + entity.id + '" class="more">See more<span></span></a></li>'
            }
            c += '<div class="clearfix"></div>';
            $("#news-w ul:eq(0)").html(c)
        } else {
            console.log(a.msg)
        }
    })
};