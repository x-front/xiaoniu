$(function () {
    _xn_init()
});
function _xn_init() {
    $.post("/pageInfo/voice", function (a) {
        if (a.resultCode == 0) {
            if (a.voice) {
                $(".about-i p:eq(0)").html(a.voice.introdution)
            }
            if (a.thinking) {
                $("#idV-i p:eq(0)").html(a.thinking.introdution)
            }
            if (a.video) {
                $("#idV-v p:eq(0)").html(a.video.introdution)
            }
            if (a.journal) {
                $("#journal-summary").html(a.journal.introdution)
            }
        } else {
            console.log(a.msg)
        }
    }, "json");
    $.post("/news/list", {type: 10, page: 1, rows: 4, top: 0}, function (a) {
        if (a.resultCode == 0) {
            var g = a.rows;
            var e = "", f = "", b = "";
            for (var d = 0; d < g.length; d++) {
                var c = g[d];
                e += '<li><a href="/static/idea-inside.html?id=' + c.id + '"><img src="' + c.banner + '"/></a></li>';
                b += '<li><a href="javascript:;"></a></li>';
                f += '<li><a href="/static/idea-inside.html?id=' + c.id + '"><p>' + c.summary + '</p></li>'
            }
            $(".idV-pic ul:eq(0)").html(e);
            $(".idV-pic ul:eq(1)").html(b);
            $(".idV-pic ul:eq(2)").html(f);
            var w = $(".idV-img").css("width").slice(0, -2);
            var w1 = $(".idV-txt").css("width").slice(0, -2);
            var n = $(".idV-pic-t li").size();
            $(".idV-pic-t,.idV-pic-b").css("width",w*n+"px");
            if($(window).width()<400){
                $(".idV-img ul li").css("width",100/n+'%');
                $(".idV-txt ul li").css("width",100/n+'%');
            }
            var i=0;
            var id= setInterval(p, 3000);
            function p(){
                $(".idV-img ul").animate({"margin-left": -w * i + "px"});
                $(".idV-txt ul").animate({"margin-left": -w1 * i + "px"});
                $(".idV-pic-c li").eq(i).addClass("active").siblings().removeClass("active");
                i++;
                if (i >= $(".idV-img ul li").length) {
                    i = 0
                }
            }
            $(".idV-pic-c li").hover(function(){
                clearInterval(id);
                var i=$(this).index();
                $(".idV-img ul").animate({"margin-left": -w * i + "px"});
                $(".idV-txt ul").animate({"margin-left": -w1 * i + "px"});
                $(this).addClass("acitve").siblings().removeClass("active");
            },function(){
                id= setInterval(p, 3000);
            })
        } else {
            console.log(a.msg)
        }
    });
    $.post("/news/list", {type: 7, page: 1, rows: 3, top: 0}, function (a) {
        if (a.resultCode == 0) {
            var d = a.rows;
            var c = "";
            for (var b = 0; b < d.length; b++) {
                entity = d[b];
                c += '<li><a href="/static/idea-inside.html?id=' + entity.id + '">';
                c += '<div class="iN-img"><img src="' + entity.banner + '"/></div>';
                c += "<p>" + entity.title + "</p>";
                c += "<span>" + entity.summary + "</span>";
                c += "</a>";
                c += '<a href="/static/idea-inside.html?id=' + entity.id + '" class="more">See more<span></span></a></li>'
            }
            c += '<div class="clearfix"></div>';
            $("#idV-i ul:eq(0)").html(c)
        } else {
            console.log(a.msg)
        }
    });
    $.post("/news/list", {type: 8, page: 1, rows: 3, top: 0}, function (a) {
        if (a.resultCode == 0) {
            var d = a.rows;
            var c = "";
            for (var b = 0; b < d.length; b++) {
                entity = d[b];
                c += '<li><a href="/static/audio-inside.html?id=' + entity.id + '">';
                c += '<div class="iN-img"><img src="' + entity.banner + '"/><img class="playBtn" src="/static/images/idV/play.png"/></div>';
                c += "<p>" + entity.title + "</p>";
                c += "<span>" + entity.summary + "</span>";
                c += "</a>";
                c += '<a href="/static/audio-inside.html?id=' + entity.id + '" class="more">See more<span></span></a></li>'
            }
            c += '<div class="clearfix"></div>';
            $("#idV-v ul:eq(0)").html(c)
        } else {
            console.log(a.msg)
        }
    })
};