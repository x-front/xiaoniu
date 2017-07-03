/**
 * Created by Administrator on 2016/10/19.
 */
$(function(){
    $("#dowebok").fullpage({
        navigation:true,
        navigationPosition:"left",
        scrollingSpeed:500,
        navigationColor:"#FFF",
        anchors:["slide1","slide2","slide3","slide9","slide4","slide10","slide5","slide6","slide7","slide8"],
        onLeave:function(index,nextIndex,direction){
            if(index==1){
            }
            if(index==2){
                if(direction=="down"){
                    $(".slide3 .slide3_l").show();
                }
            }
            if(index==3){
                if(direction=="down"){
                    $(".s_fix").css({"display":"block","opacity":"1"});
                    $(".s_fix a").text("核心竞争力")
                }else{
                    $(".s_fix").css({"display":"none","opacity":"0"});
                    $(".slide3 .slide3_l").show();
                }
            }
            if(index==4){
                if(direction=="up"){
                    $(".slide3 .slide3_l").hide();
                }else{
                    $(".s_fix a").text("核心竞争力")
                }
            }
            if(index==5){
                if(direction=="up"){
                    $(".s_fix a").text("核心竞争力")
                }else{
                    $(".s_fix a").text("核心竞争力")
                }
            }
            if(index==6){
                if(direction=="up"){
                    $(".s_fix a").text("核心竞争力");
                }else{
                    $(".s_fix a").text("社会责任");
                    $('.slide3_l').css("background","url(images/fold/1920_04.jpg) no-repeat 50%")
                }
            }
            if(index==7){
                if(direction=="up"){
                    $(".s_fix a").text("核心竞争力");
                    $('.slide3_l').css("background","url(images/fold/1920_03.jpg) no-repeat 50%")

                }else{
                    $(".s_fix a").text("社会责任");
                    $('.slide3_l').css("background","url(images/fold/1920_04.jpg) no-repeat 50%")
                }
            }
            if(index==8){

            }
            if(index==9){
                if(direction=="up"){
                    $(".s_fix a").text("社会责任");
                    $('.slide3_l').css("background","url(images/fold/1920_04.jpg) no-repeat 50%")

                }else{
                    $(".s_fix a").text("思想工厂");
                    $('.slide3_l').css("background","url(images/fold/1920_05.jpg) no-repeat 50%")
                }
            }
            if(index==10){
                if(direction=="up"){
                    $(".s_fix a").text("社会责任");
                    $('.slide3_l').css("background","url(images/fold/1920_04.jpg) no-repeat 50%")
                }
            }
        },
        afterLoad:function(anchorLink,index){
            if(index==1){
                $(".s_fix").eq(0).css({"display":"none","opacity":"0"});
            }
            if(index==2){
                $(".s_fix").eq(0).css({"display":"none","opacity":"0"});
            }
            if(index==3){
                $(".s_fix").eq(0).css({"display":"block","opacity":"1"});
                $(".s_fix a").text("核心竞争力");
                $(".slide3 .slide3_l").hide();
            }
            if(index==4){
                $(".s_fix").eq(0).css({"display":"block","opacity":"1"});
                $(".s_fix a").text("核心竞争力")
            }
            if(index==5){
                $(".s_fix").eq(0).css({"display":"block","opacity":"1"});
                $(".s_fix a").text("核心竞争力")
            }
            if(index==6){
                $(".s_fix").eq(0).css({"display":"block","opacity":"1"});
                $(".s_fix a").text("核心竞争力")
            }
            if(index==7){
                $(".s_fix").eq(0).css({"display":"block","opacity":"1"});
                $(".s_fix a").text("社会责任")
            }
            if(index==8){
                $(".s_fix").eq(0).css({"display":"block","opacity":"1"});
                $(".s_fix a").text("社会责任")
            }
            if(index==9){
                $(".s_fix").eq(0).css({"display":"block","opacity":"1"});
                $(".s_fix a").text("社会责任")
            }
            if(index==10){
                $(".s_fix").eq(0).css({"display":"block","opacity":"1"});
                $(".s_fix a").text("思想工厂")
            }

        }
    });
    var w=parseInt($(window).width());
    var h=parseInt($(window).height());
    $(".slide3_l").css({"width":(w-960)/2+"px","height":h+"px"});
});