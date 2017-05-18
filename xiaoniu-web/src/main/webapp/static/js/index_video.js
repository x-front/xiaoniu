/**
 * Created by Administrator on 2017/3/18.
 */
$(function(){
    /*if($(window).width()<769){
     setTimeout(function(){
     $(".s0_btn").show(500);
     },5000)
     }*/
    /*s1_video_1*/
    var sv1=$(".s1_video_1");
    var sv= $(".s1_video_1 video");
    var ww=$(window).width();
    if(ww<1025){
        var u = navigator.userAgent;
        var isiOS = !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/); //ios终端
        if (isiOS) {
            $(".play_1").css("background","none");
        }
    }
    if(ww<769&&ww>750){
        sv.attr({"src":"/static/images/s2.mp4","poster":"/static/images/s2.png"})
    }
    if(ww<500){
        sv.attr({"src":"/static/images/s3.mp4","poster":"/static/images/s3.png"})
    }

    var video_w=sv.width();
    var video_h;
    if(ww<769&&ww>750){
        video_h=video_w*(250/768);
    }else if(ww<500){
        video_h=video_w*(300/750);
    }else{
        video_h=video_w*(350/1920);
    }
    console.log(video_h);
    sv1.css("height",video_h+"px");
    $("#header.index-header").css("top",video_h+"px");
    sv[0].play();
    var num;
    if(ww<=1280){
        num=100
    }else if(ww<=1366){
        num=90
    }else if(ww<=1440){
        num=80
    }else if(ww<=1680){
        num=25
    }else if(ww<=1920){
        num=20
    }else if(ww>1920){
        num=110
    }
    console.log(num);
    $(".banner").css("background-position-y",video_h-num+"px");

    $(".play_1").click(function(){
        sv.attr({"src":"/static/images/s1.mp4","controls":"controls","poster":"/static/images/s1_video.jpg"});
        sv[0].play();
        var sv_h=video_w*(720/1280);
        sv1.animate({"height":sv_h+"px"},1500,'linear',function(){
            if(ww>1025){
                sv1.css('top','auto');
                sv1.animate({"bottom":0},1000,function(){
                    $(".s1_video_1 .close").animate({"top":(sv_h-$(window).height()+20)+"px"});
                    /*if(ww>750){
                        $(".s1_video_1 .close").animate({"top":(sv_h-$(window).height()+20)+"px"})
                    }else{
                        $(".s1_video_1 .close").animate({"top":(sv_h-$(window).height()+8)+"px"})
                    }*/
                });
            }else{
                sv1.animate({'top':'50%'});
                sv1.css({'transform':'translate(-50%,-50%)'});
                $(".s1_video_1 .close").animate({"top":-($(window).height()-sv_h)/2+8+"px"})
            }

        });
        console.log(sv_h);
        $(".banner .slogan").animate({"top":"120%"},700,'linear');
        $("#fp-nav.left").animate({"top":"110%"},700,'linear');
        $("#header.index-header").animate({"top":"100%"},1200,'linear');
        $(".banner").animate({"background-position-y":"100vh"},1500,'linear');
        $(".b-news").animate({"top":"110%"},700,'linear');
        $(this).fadeOut();
        $(".s1_video_1 span").fadeOut();
        $.fn.fullpage.setAllowScrolling(false);
    });
    var sv_v=sv[0].volume,n=100;
    $(".s1_video_1 .close").click(function(){
        var sh=sv.height();
        $.fn.fullpage.setAllowScrolling(true);
        sv1.animate({"top":-sh+'px'},700,'linear',function(){
            $(this).hide();
        });
        var id= setInterval(function (){
            console.log(sv_v);
            sv[0].volume=sv_v;
            sv_v=sv_v-0.01;
            n--;
            if(n==0){
                sv_v=0;
                clearInterval(id);
                sv[0].pause();
            }
        },30);
        $(this).css("display","none");
        $(".play_1").fadeOut();
        if(ww>750){
            $(".index-header .big-logo").animate({"height":"66px"},300,'linear');
        }
        $(".banner .slogan").animate({"top":"50%"},700,'linear');
        $("#fp-nav.left").animate({"top":"50%"},400,'linear');
        $("#header.index-header").animate({"top":0},700,'linear');
        $(".banner").animate({"background-position-y":"0"},700,'linear');
        $(".b-news").animate({"top":"92%"},300,'linear');
    });

    /*s1_video*/
/*
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
*/
});
