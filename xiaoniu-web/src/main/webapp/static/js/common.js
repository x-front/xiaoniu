/*!
 * 小牛资本
 * @Author http://www.lonwin.net huabin.
 * @Copyright 2016
 */
$(function () {
    /*s1_video*/
    $(".s1_video .close").click(function(){
        $(".s1_zz").fadeOut(500);
        $(".s1_video").fadeOut(500);
        $(".s1_video video")[0].pause();
    });
    var u = navigator.userAgent;
    var isiOS = !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/); //ios终端
    if (isiOS) {
        $(".s1_video .play").hide();
        $(".s1_video video").attr({"controls":"controls","autoplay":"autoplay"});
    }else{
        $(".s1_video .play").click(function(){
            $(".s1_video video")[0].play();
            $(".s1_video video").attr("controls","controls");
            $(this).animate({opacity:0},500)
        });

    }
    /*nav*/
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
/*pics*/
    var p_i_l=$(".pics_img_l"),p_i_r=$(".pics_img_r"),p_i_ul=$(".pics_img ul"),p_i_li=$(".pics_img ul li"),p_i1=$(".pics_txt .num .i1"),p_i3=$(".pics_txt .num .i3"),p_t_li=$(".pics_txt ul li"),p_b_l=$(".pics_b_l"),p_b_r=$(".pics_b_r"),p_b_li=$(".pics_in_list ul li");
    var pi=0;
    p_i3.text(p_i_li.length);
    for(var i=0;i<p_i_li.length;i++){
        if(p_i_li.eq(i).hasClass("on")){
            p_i1.text(i+1);
        }
    }
    p_i_l.click(function(){

    });
    p_i_r.click(function(){

    })
});
