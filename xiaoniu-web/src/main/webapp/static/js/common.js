/*!
 * 小牛资本
 * @Author http://www.lonwin.net huabin.
 * @Copyright 2016
 */
$(function () {
    /*隐藏小牛简介*/
    /*$(".nav li:not(':last'),.inside-nav li:not(':last')").eq(1).hide();*/
    //首页分享
    $(".share iframe").hover(function(){
        $(".share .bdshare-button-style0-16 .bds_tsina").css("opacity","0.8!important")
    },function(){
        $(".share .bdshare-button-style0-16 .bds_tsina").css("opacity","1!important")
    });
    /*nav*/
    var a=0;
    $(".menu").click(function () {
        a=0;
        console.log(222);
        $(".sidebar").animate({right: 0}, 500);
        $(this).animate({opacity: 0}, 500);
        $(".sidewrap").css({"opacity":1,"filter":"alpha(opacity=100)","-webkit-transform":"translateX(0)","-moz-transform":"translateX(0)","-ms-transform":"translateX(0)","transform":"translateX(0)"});

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

    }
    /*$(window).scroll(function(){
        if(a==0){
            close();
        }
        a++;
    });*/
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
    $(".about-j1-l a").click(function () {
        $(this).next().slideToggle(500);
        $(this).toggleClass('on');
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
    if ($(window).width() <= 750) {
        $(".hr-banner img").attr("src", "images/hr/hr-banner_03.jpg");
        $("*").removeClass("wow fadeInUp fadeInLeft fadeInRight");
        $(".sub_nav").append("<span class='btn'></span>");
        $(".sub_nav .btn").click(function(){
            $(".sub_nav ul").slideToggle();
            $(this).toggleClass('on')
        });
    }
    var browser=navigator.appName;
    var b_version=navigator.appVersion;
    var version=b_version.split(";");
    var trim_Version=version[1].replace(/[ ]/g,"");
    if(browser=="Microsoft Internet Explorer" && trim_Version=="MSIE8.0"){
        $("*").removeClass("wow fadeInUp fadeInLeft fadeInRight")
    }
    else if(browser=="Microsoft Internet Explorer" && trim_Version=="MSIE9.0"){}
    /* 内页轮播图 */
    window.onload=function(){
        $('.idV-pic-c li').eq(0).addClass('active');
        inPlay();
        /*pics*/
        $(".num_nav li a").click(function(){
            $(this).addClass("on").siblings("li").removeClass("on")
        });
        $(".num_nav li a").hover(function(){
            $(this).next("p").css({"opacity":1,"filter":"alpha(opacity=100)","left":"160%","z-index": "999"})
        },function(){
            $(this).next("p").css({"opacity":0,"filter":"alpha(opacity=0)","left":"100%","z-index": "-1"})

        });
    };
    window.onresize=function(){
        inPlay();
        picPlay();
    };
    if($(window).width()<750){
        $(".pics_in").append('<a class="pics_in_back" href="#" onClick="javascript:history.go(-1);"><img src="/static/images/pics/back.svg"/></a>');
        $(".pics_in_wrap").prev().find(".btn").hide();
        $(".pics_wrap").prev().hide();
        $(".pics_wrap").css("margin-top","42px")
    }

    $('body').append("<script>(function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){(i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)})(window,document,'script','https://www.google-analytics.com/analytics.js','ga');ga('create', 'UA-97950139-1', 'auto');ga('send', 'pageview'); </script>");
});
function picPlay(){
    var p_i_l=$(".pics_img_l"),p_i_r=$(".pics_img_r"),p_i_ul=$(".pics_img ul"),p_i_li=$(".pics_img ul li"),p_i1=$(".pics_txt .num .i1"),p_i3=$(".pics_txt .num .i3"),p_t_li=$(".pics_txt ul li"),p_b_l=$(".pics_b_l"),p_b_r=$(".pics_b_r"),p_b_list=$(".pics_in_list"),p_b_ul=$(".pics_in_list ul"),p_b_li=$(".pics_in_list ul li");
    $(".pics_img").css("height",$(".pics_img").width()*(456/889)+"px");
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
    if($(window).width()<1140&&$(window).width()>750){
        p_i_ul.click(function(){
            $(".pics_img a").show();
        })
    }
    p_i_l.click(function(){
        if($(window).width()<1140&&$(window).width()>750){
            $(".pics_img a").hide();
        }
        lC();
    });
    p_i_r.click(function(){
        if($(window).width()<1140&&$(window).width()>750){
            $(".pics_img a").hide();
        }
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
}
function inPlay(){
    var w = $(".idV-img").width();
    var w1 = $(".idV-txt").width();
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
        $(this).addClass("active").siblings().removeClass("active");        },function(){
        id= setInterval(p, 3000);
    })
}
/*zz_video*/
function videoPlay(){
    $(".play_btn").click(function(){
        var i=$(this).parent().parent().index();
        console.log(i);
        $(".zz").fadeIn(300);
        $(".zz_video").fadeIn(500);
        $(".zz_video video").eq(i).show().siblings('video').hide();
        $(".zz_video video")[i].play();
        $(window).bind("wheel",function(event){
            event.preventDefault();
            return false;
        });
    });
    $(".zz_video a").click(function(){
        $(".zz").fadeOut();
        $(".zz_video").fadeOut();
        for(var i=0;i<$(".zz_video video").length;i++){
            if($(".zz_video video").eq(i).show()){}
            $(".zz_video video")[i].pause();
        }
        $(window).unbind("wheel")
    });

}