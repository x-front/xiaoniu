/**
 * Created by Administrator on 2016/8/29.
 */
    $(function(){
        //新闻类页面轮播效果
        var i=0;
        setInterval(function(){
            var oW1=$(".idV-img").css("width").slice(0,-2);
            var oW2=$(".idV-txt").css("width").slice(0,-2);
            $(".idV-img ul").animate({"margin-left":-oW1*i+'px'});
            $(".idV-txt ul").animate({"margin-left":-oW2*i+'px'});
            $(".idV-pic-c li").eq(i).addClass("active").siblings().removeClass("active");
            i++;
            if(i>=$(".idV-img ul li").length){
                i=0;
            }
        },3000);

    });
