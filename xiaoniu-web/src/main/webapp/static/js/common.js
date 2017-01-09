/*!
 * 小牛资本
 * @Author http://www.lonwin.net huabin.
 * @Copyright 2016
 */
$(function () {
    $(".menu").click(function () {
        $(".sidebar").animate({right: 0}, 500);
        $(this).animate({opacity: 0}, 500);
        if ($(window).width() > 500) {
            for(var i= 0,t=0.3;i<7;i++,t=t+0.03){
                menuPlay(".nav li",i,t+"s");
            }
            menuPlay(".search",0,".51s");
            menuPlay(".share",0,".54s");
            menuPlay(".ce",0,".57s");
            menuPlay(".copy",0,".6s");
            menuPlay(".scan",0,".63s");
        }
    });
    $(".s2").click(function () {
        $(this).addClass("s2-active").siblings().removeClass("s2-active")
    });
    $(".sidebar .close").click(function () {
        $(".menu").animate({opacity: 1}, 500);
        $(".sidebar").animate({right: "-100%"}, 500);
        if ($(window).width() > 500) {
            menuPlay1(".nav li");
            menuPlay1(".search");
            menuPlay1(".share");
            menuPlay1(".ce");
            menuPlay1(".copy");
            menuPlay1(".scan");
        }
    });
    function menuPlay(className,index,time){
        $(className).eq(index).css({animation: "mLi .5s "+time, opacity: "1", "margin-left": "0"});
    }
    function menuPlay1(className){
        $(className).css({opacity: "0", "margin-left": "200px"});
    }

    $("#audio-more").click(function () {
        $(".audio-ul").css("display", "block")
    });
    $("#idea-more").click(function () {
        $(".idea-ul").css("display", "block")
    });
    $("#news-t-more").click(function () {
        $(".news-t-ul").css("display", "block")
    });
    $("#news-m-more").click(function () {
        $(".news-m-ul").css("display", "block")
    });
    $("#join-more").click(function () {
        $(".join-ul").css("display", "block")
    });
    $(".about-j1-l a").toggle(function () {
        $(this).css({
            color: "#c19a3f",
            background: "url(images/about/about-jt_06.jpg) no-repeat right center"
        }).next().slideDown(500)
    }, function () {
        $(this).css({
            color: "#000",
            background: "url(images/about/about-jt_11.jpg) no-repeat right center"
        }).next().slideUp(500)
    });
    $(".about-j2 a").click(function () {
        $(".about-j2-pic").slideToggle(500)
    });
    $(".about-j3 a").click(function () {
        $(".about-j3-pic").slideToggle(500)
    });
    $("#about-j4 a").click(function () {
        $(".about-j4-pic").slideToggle(500)
    });
    $(".about-j5 a").click(function () {
        $(".about-j5-pic").slideToggle(500)
    });
    $(".about-j6 a").click(function () {
        $(".about-j6-pic").slideToggle(500)
    });
    $(".about-j7 a").click(function () {
        $(".about-j7-pic").slideToggle(500)
    });
    if ($(window).width() <= 500) {
        $(".hr-banner img").attr("src", "images/hr/hr-banner_03.jpg");
        $("*").removeClass("wow fadeInUp fadeInLeft fadeInRight");
        $(".sub_nav").append("<span class='btn'></span>");
        $(".sub_nav .btn").click(function(){
            $(".sub_nav ul").slideToggle();
        });
    }
    if ($.browser.msie && parseInt($.browser.version) == 8) {
        $("*").removeClass("wow fadeInUp fadeInLeft fadeInRight")
    }
    $(".idV-more").append("<p>More</p>");
    $(".menu").html("<span class='ms1'></span><span class='ms2'></span><span class='ms1 ms3'></span>");

});
