/*!
 * 小牛资本
 * @Author http://www.lonwin.net huabin.
 * @Copyright 2016
 */
$(function () {
    /*nav*/
    $(".menu").click(function () {
        console.log(222);
        $(".sidebar").animate({right: 0}, 500);
        $(this).animate({opacity: 0}, 500);
        $(".sidewrap").css({"opacity":1,"filter":"alpha(opacity=100)","-webkit-transform":"translateX(0)","-moz-transform":"translateX(0)","-ms-transform":"translateX(0)","transform":"translateX(0)"});
/*
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
*/
    });
    $(".s2").click(function () {
        $(this).addClass("s2-active").siblings().removeClass("s2-active");
    });
    $(".sidebar .close").click(function () {
        close();
    });
    function close(){
        $(".menu").animate({opacity: 1}, 500);
        $(".sidebar").animate({right: "-100%"}, 500);
        $(".sidewrap").css({"opacity":0,"filter":"alpha(opacity=0)","-webkit-transform":"translateX(50px)","-moz-transform":"translateX(50px)","-ms-transform":"translateX(50px)","transform":"translateX(50px)"});

        /*
                if ($(window).width() > 500) {
                    menuPlay1(".nav li");
                    menuPlay1(".search");
                    menuPlay1(".share");
                    menuPlay1(".ce");
                    menuPlay1(".copy");
                    menuPlay1(".scan");
                }
        */
    }

    $(window).scroll(function(){
        $(".menu").animate({opacity: 1}, 500);
        $(".sidebar").animate({right: "-100%"}, 500);
    });
    if(isFirefox=navigator.userAgent.indexOf("Firefox")>0){
        document.body.addEventListener("DOMMouseScroll", function(event) {
            close();
        });
    }else{
        document.body.onmousewheel = function(event) {
            /*event = event || window.event;*/
            close();
        };
    }

/*
    function menuPlay(className,index,time){
        $(className).eq(index).css({animation: "mLi .5s "+time, opacity: "1", "margin-left": "0"});
    }
    function menuPlay1(className){
        $(className).css({opacity: "0", "margin-left": "200px"});
    }
*/
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
    /*$(".idV-more").append("<p>More</p>");*/
    $(".menu").html("<span class='ms1'></span><span class='ms2'></span><span class='ms1 ms3'></span>");
    /*zz_video*/
    $(".play_btn").click(function(){
        $(".zz").fadeIn(300);
        $(".zz_video").fadeIn(500);
        $(".zz_video video")[0].play();
        $(window).bind("wheel",function(event){
            event.preventDefault();
            return false;
        });
    });
    $(".zz_video a").click(function(){
        $(".zz").fadeOut();
        $(".zz_video").fadeOut();
        $(".zz_video video")[0].pause();
        $(window).unbind("wheel")
    });
    /*pics*/
    var p_i_l=$(".pics_img_l"),p_i_r=$(".pics_img_r"),p_i_ul=$(".pics_img ul"),p_i_li=$(".pics_img ul li"),p_i1=$(".pics_txt .num .i1"),p_i3=$(".pics_txt .num .i3"),p_t_li=$(".pics_txt ul li"),p_b_l=$(".pics_b_l"),p_b_r=$(".pics_b_r"),p_b_list=$(".pics_in_list"),p_b_ul=$(".pics_in_list ul"),p_b_li=$(".pics_in_list ul li");
    p_i3.text(p_b_li.length);
    for(var i=0;i<p_b_li.length;i++){
        if(p_i_li.eq(i).hasClass("on")){
            p_i1.text(i+1);
        }
    }
    var p_lw=p_b_li.width()+parseInt(p_b_li.css("margin-left"))*2+parseInt(p_b_li.css("border-width"))*2;
    var p_uw=p_lw*p_b_li.length;
    console.log(p_uw,p_lw);
    p_b_ul.css("width",p_uw+"px");
    p_b_li.click(function() {
        var _i = $(this).index();
        $(this).addClass("on").siblings().removeClass("on");
        p_i_li.eq(_i).addClass("on").siblings().removeClass("on");
        p_t_li.eq(_i).addClass("on").siblings().removeClass("on");
        p_i1.text(_i+1);
/*
        var n=parseInt(p_uw/p_b_list.width());
        console.log(n);
        for(var _n=1;_n<n;_n++){
            if((_i+1)*p_lw==p_b_list.width()*_n&&p_uw>p_b_list.width()*_n){
                /!*var p_b_ul_l=parseInt(p_b_ul.css("left"));*!/
                p_b_ul.css("left",-p_b_list.width()*_n+"px");
                console.log(_n)
            }
        }
*/
    });
    p_i_l.click(function(){
        lC();
    });
    p_i_r.click(function(){
        rC();
    });
    p_b_l.click(function(){
        lC();
    });
    p_b_r.click(function(){
        rC();
    });
    var n=p_uw/p_b_list.width();
    console.log(n);
    function rC(){
        for(var i=0;i<p_b_li.length;i++){
            if(p_b_li.eq(i).hasClass("on")){
                console.log(i);
                if((i+1)%(p_b_list.width()/p_lw)==0){
                    var p_b_ul_l=parseInt(p_b_ul.css("left"));
                    if((i+1)/(p_b_list.width()/p_lw)<n){
                        p_b_ul.css("left",p_b_ul_l-p_b_list.width()+"px");
                    }else{
                        i=-1;
                        p_b_ul.css("left",0);
                    }
                }else if(i==p_b_li.length-1){
                    console.log(111);
                    i=-1;
                    p_b_ul.css("left",0);
                }
                p_b_li.eq(i+1).addClass("on").siblings().removeClass("on");
                p_i_li.eq(i+1).addClass("on").siblings().removeClass("on");
                p_t_li.eq(i+1).addClass("on").siblings().removeClass("on");
                p_i1.text(i+2);
                break;
            }
        }
    }
    function lC(){
        for(var i=p_b_li.length-1;i>=0;i--){
            if(p_b_li.eq(i).hasClass("on")){
                console.log(i);
                if((i)%(p_b_list.width()/p_lw)==0&&i!=0){
                    var p_b_ul_l=parseInt(p_b_ul.css("left"));
                    p_b_ul.css("left",p_b_ul_l+p_b_list.width()+"px");
                }else if(i==0){
                    console.log(111);
                    i=p_b_li.length;
                    if(p_b_li.length/(p_b_list.width()/p_lw)==parseInt(n)){
                        p_b_ul.css("left",-(n-1)*p_b_list.width()+"px");
                    }else{
                        p_b_ul.css("left",-parseInt(n)*p_b_list.width()+"px");
                    }
                }
                p_b_li.eq(i-1).addClass("on").siblings().removeClass("on");
                p_i_li.eq(i-1).addClass("on").siblings().removeClass("on");
                p_t_li.eq(i-1).addClass("on").siblings().removeClass("on");
                p_i1.text(i);
                break;
            }
        }

    }
});
