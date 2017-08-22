$(function () {
    _xn_init()
});
function _xn_init() {
    $.post("/moreContent/advance", function (a) {
        if (a.pp1) {
            $(".about-j1-l .about-j1-item1 a:eq(0)").html(a.pp1.title);
            if ($(window).width() > 500) {
                $(".about-j1-l .about-j1-item1 .see-more-div:eq(0)").html(a.pp1.more)
            } else {
                $(".about-j1-l .about-j1-item1 .see-more-div:eq(0)").html('<img src="images/about/table_01.jpg"/>')
            }
        }
        if (a.pp2) {
            $(".about-j1-l .about-j1-item2 a:eq(0)").html(a.pp2.title);
            if ($(window).width() > 500) {
                $(".about-j1-l .about-j1-item2 .see-more-div:eq(0)").html(a.pp2.more)
            } else {
                $(".about-j1-l .about-j1-item2 .see-more-div:eq(0)").html('<img src="images/about/table_02.jpg"/>')
            }
        }
        if (a.pp3) {
            $(".about-j1-l .about-j1-item3 a:eq(0)").html(a.pp3.title);
            if ($(window).width() > 500) {
                $(".about-j1-l .about-j1-item3 .see-more-div:eq(0)").html(a.pp3.more)
            } else {
                $(".about-j1-l .about-j1-item3 .see-more-div:eq(0)").html('<img src="images/about/table_03.jpg"/>')
            }
        }
        if (a.p1) {
            $(".about-j1-l h3:eq(0)").html(a.p1.title);
            $(".about-j1 .about-j-r img:eq(0)").attr("src", a.p1.banner);
            $(".about-j1 .about-j-r p:eq(0)").html(a.p1.bannerDesc)
        }
        if (a.p2) {
            $(".about-j2").attr("background", "url(" + a.p2.banner + ") no-repeat");
            $(".about-j22 h3:eq(0)").html(a.p2.title);
            $(".about-j22 p:eq(0)").html(a.p2.content);
            $(".about-j2-pic").html(a.p2.more)
        }
        if (a.p3) {
            $(".about-j3-l h3:eq(0)").html(a.p3.title);
            $(".about-j3-l p:eq(0)").html(a.p3.content);
            $(".about-j3-pic").html(a.p3.more);
            $(".about-j3 .about-j-r img:eq(0)").attr("src", a.p3.banner);
            $(".about-j3 .about-j-r p:eq(0)").html(a.p3.bannerDesc)
        }
        if (a.p4) {
            $("#about-j4").attr("background", "url(" + a.p4.banner + ") no-repeat");
            $(".about-j4-l h3:eq(0)").html(a.p4.title);
            $(".about-j4-l p:eq(0)").html(a.p4.content);
            $(".about-j4-pic").html(a.p4.more);
            $(".about-j4 .about-j-r img:eq(0)").attr("src", a.p4.banner);
            $(".about-j4 .about-j-r p:eq(0)").html(a.p4.bannerDesc)
        }
        if (a.p5) {
            $(".about-j4:eq(1) .about-j4-l h3:eq(0)").html(a.p5.title);
            $(".about-j4:eq(1) .about-j4-l p:eq(0)").html(a.p5.content);
            $(".about-j4:eq(1) .about-j-r img:eq(0)").attr("src", a.p5.banner);
            $(".about-j4:eq(1) .about-j-r p:eq(0)").html(a.p5.bannerDesc);
            $("#about-j5 .about-j5-pic").html(p5.more);
        }
        if (a.p6) {
            $("#about-j6").attr("background", "url(" + a.p6.banner + ") no-repeat");
            $(".about-j4:eq(2) .about-j4-l h3:eq(0)").html(a.p6.title);
            $(".about-j4:eq(2) .about-j4-l p:eq(0)").html(a.p6.content);
            $(".about-j4:eq(2) .about-j-r img:eq(0)").attr("src", a.p6.banner);
            $(".about-j4:eq(2) .about-j-r p:eq(0)").html(a.p6.bannerDesc);
            $("#about-j6 .about-j6-pic").html(p6.more);
        }
        if (a.p7) {
            $(".about-j4:eq(3) .about-j4-l h3:eq(0)").html(a.p7.title);
            $(".about-j4:eq(3) .about-j4-l p:eq(0)").html(a.p7.content);
            $(".about-j4:eq(3) .about-j-r img:eq(0)").attr("src", a.p7.banner);
            $(".about-j4:eq(3) .about-j-r p:eq(0)").html(a.p7.bannerDesc);
            $("#about-j7 .about-j7-pic").html(p7.more);
        }
    }, "json")
};