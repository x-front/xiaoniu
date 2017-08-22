$(function(){
    console.log("index init");
    _xn_init();
});
function _xn_init(){
    $.post("/media/list",{'page':1,'rows':6,'type':6,'lang':0},function(result){
        if(result.resultCode == 0){
            var rows = result.rows;
            var row = rows[0];
            var media = row.extMedia;
            $('.s1_v video').attr('src',media)
        }else{
            console.log(result.msg);
            var meida='http://xiaoniu66-web.oss-cn-shenzhen.aliyuncs.com/media/20170704/20170704184846_326.mp4';
            $('.s1_v video').attr('src',meida)
        }
    },"json");

    var terminal;
    if($(window).width()<1025){
        terminal=2;
        if($(window).width()<500){
            terminal=1;
        }
    }else{
        terminal=0
    }
    $.post("/pageInfo/index",{'lang':0,'terminal':terminal},function(result){
        if(result.resultCode == 0){
            if(result.who){
                $(".banner").css("background-image",'url('+result.who.extCover+')');
            }

            if(result.doWhat){
                $(".section2 .s2-txt span,.section2 .s2-txt1 span,.section2 .s2-txt2 span").html(result.doWhat.introdution);
                $(".section2").css("background-image",'url('+result.doWhat.extCover+')');

            }

            if(result.voice){
                $(".section1 .desc,.section1 .desc1").html(result.voice.introdution);
                $(".section1").css("background-image",'url('+result.voice.extCover+')');

            }
            if(result.family){
                $(".section3 .desc,.section3 .desc1").html(result.family.introdution);
                $(".section3").css("background-image",'url('+result.family.extCover+')');

            }
            if(result.sports){
                $(".section5 .desc,.section5 .desc1").html(result.sports.introdution);
                $(".section5").css("background-image",'url('+result.sports.extCover+')');

            }
            if(result.welfare){
                $(".section4 .desc,.section4 .desc1").html(result.welfare.introdution);
                $(".section4").css("background-image",'url('+result.welfare.extCover+')');

            }
            if(result.education){
                $(".footer .desc,.footer .desc1").html(result.education.introdution);
                $(".footer").css("background-image",'url('+result.education.extCover+')');

            }

        }else{
            console.log(result.msg);
        }
    },"json");

    /*$.post("/news/list",{
        'page':1,
        'rows':3,
        'type':11
    },function(result){
        if(result.resultCode == 0){
            if(result.rows){
                var html = "";
                for(var i = 0; i < result.rows.length && i < 3; i++){
                    var entity = result.rows[i];
                    html += '<li><a href="/static/news-t-inside.html?id='+entity.id +'"><div><img src="'+entity.banner+'"/></div><p>'
                            + entity.title + '</p><span>'+entity.summary+'</span></a></li>';
                }
                $(".section5 .models .s5-m").html(html);
            }
        }else{
            console.log(result.msg);
        }
    },"json");*/
    $.post("/indexNews/list",{
        'page':1,
        'rows':20
    },function(result){
        if(result.resultCode == 0){
            var html = "";
            for(var i = 0; i < result.rows.length ; i++){
                var entity = result.rows[i];
                html += '<li><a href="/static/news-t-inside.html?id='+entity.id+'">'+entity.title+'</a></li>';
            }
            $("#dowebok .b-news .bd ul").html(html);
            if($(window).width()>1024){
                $(".b-news").slide({mainCell:".bd ul",autoPage:true,effect:"topLoop",autoPlay:true,vis:1});
            }else{
                $(".b-news").slide({mainCell:".bd ul",autoPage:true,effect:"topLoop",autoPlay:true,vis:1,mouseOverStop:false});
            }
            /*var t=parseInt($(".b-news .bd ul").css("top").slice(0,-2));
            for(var j=0;j<$(".timer span").length;j++){
                if(t==-(j+1)*30){
                    $(".timer span").eq(j).css("opacity","1").siblings().css("opacity","0.3");
                }
            }*/
            /*
                                var timer = $(".b-news .timer");
                                timer.stop(true,true).animate({ "width":"0%" },0).animate({ "width":"100%" },2500);
            */          /*$(".hd ul li").text("");*/

            $("#dowebok .b-news .bd ul li").hover(function(){
                var n=$(this).index();
                $(".hd ul li").eq(n-1).css({"background":"#eea807"})
            },function(){
                var n=$(this).index();
                $(".hd ul li").eq(n-1).css({"background":"#FFF"})
            })
        }else{
            console.log(result.msg);
        }
    },'json');

}