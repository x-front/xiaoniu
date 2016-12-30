/**
 * Created by Administrator on 2016/12/12.
 */
$(function(){
    $(window).scroll(function(){
        if($(window).scrollTop()>=$(".s1").height()){
            $(".nav").css({"position":"fixed",top:0,left:0});
            $(".s2").css({"padding-top":"110px"})
        }else{
            $(".nav").css({"position":"static",top:"",left:""});
            $(".s2").css({"padding-top":"0"})
        }
    });
    $(".txt .b").click(function(){

        var txt=$(this).parent().prev(".txt_in");
        var p=$(this).parent().prev(".txt_in").find("p");
/*
        var t=parseInt(p.css("margin-top"));
        var th=parseInt(txt.css("height"));
        var h=parseInt(p.css("height"));
        var lh=parseInt(p.css("line-height"));
*/
        txt.css("height","auto");
        txt.parent(".txt").css("height","auto");
        $(this).siblings(".t").show();
        $(this).hide();
/*
        $(".s6").css("background-position-x","8%");
        $(".s11").css("background-position-x","1%");
*/
/*
        p.animate({"margin-top":(t-lh)+"px"},50,function(){
            t=parseInt(p.css("margin-top"));
            if(-(h-th)==t){
                $(this).parent().siblings(".btn").find(".b").hide();
            }
        });
*/
    });
    $(".txt .t").click(function(){
/*
        var p=$(this).parent().prev(".txt_in").find("p");
        var t=parseInt(p.css("margin-top"));
        var lh=parseInt(p.css("line-height"));
*/
        var txt=$(this).parent().prev(".txt_in");

        txt.css("height","");
        txt.parent(".txt").css("height","");

        $(this).siblings(".b").show();
        $(this).hide();
/*
        $(".s6").css("background-position-x","");
        $(".s11").css("background-position-x","");
*/
        /*
                p.animate({"margin-top":(t+lh)+"px"},50,function(){
                    t=parseInt(p.css("margin-top"));
                    if(t==0){
                        $(this).parent().siblings(".btn").find(".t").hide();
                    }
                });
        */
    });
    $(".s11 .txt .b").click(function(){
        $(".s11 .line").css("height","7500px");
    });
    $(".s11 .txt .t").click(function(){
        $(".s11 .line").css("height","4470px");
    })
});
