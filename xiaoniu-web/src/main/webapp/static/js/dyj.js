/**
 * Created by Administrator on 2016/10/19.
 */
$(function(){
    $("#dowebok").fullpage({
        navigation:true,
        navigationPosition:"left",
        verticalCentered:true,
        scrollingSpeed:400,
        anchors:["dyj1","dyj2","dyj3","dyj4","dyj5","dyj6","dyj7","dyj8","dyj9","dyj10"],
        onLeave:function(e,d,f){
            if(e==1){
                $(".dyj1_img1").removeClass("a-fadeinL").addClass("a-fadeoutL");
                $(".dyj1_img2_im1").removeClass("a-bounceinR").addClass("a-bounceoutR");
                $(".dyj1_img2_im2").removeClass("a-fadeinR1").addClass("a-fadeoutR");
                $(".toTop").show();
            }
            if(e==2){
                $(".dyj2_img1 .im1").removeClass("a-fadeinL").addClass("a-fadeoutL");
                $(".dyj2_img1 .im2").removeClass("a-fadeinL1").addClass("a-fadeoutL");
            }
            if(e==3){
                $(".dyj3_img1").removeClass("a-fadeinL").addClass("a-fadeoutL");
                $(".dyj3_img2").removeClass("a-fadeinT1").addClass("a-fadeoutT");
                $(".dyj3_img3_im1").removeClass("a-fadeinL2").addClass("a-fadeoutL");
                $(".dyj3_img3_im2").removeClass("a-fadeinR3").addClass("a-fadeoutR");
            }
            if(e==4){
                $(".dyj4_img2").removeClass("a-fadeinT").addClass("a-fadeoutT");
                $(".dyj4_img1_im1").removeClass("a-fadeinL1").addClass("a-fadeoutL");
                $(".dyj4_img1_im2").removeClass("a-fadeinR2").addClass("a-fadeoutR");
            }
            if(e==5){
                $(".dyj5_img1_im1").removeClass("a-fadeinL").addClass("a-fadeoutL");
                $(".dyj5_img1_im2").removeClass("a-bounceinL").addClass("a-bounceoutL");
                $(".dyj5_img2").removeClass("a-fadeinR1").addClass("a-fadeoutR");
            }
            if(e==6){
                $(".dyj6_img1").removeClass("a-flipin a-flipinY").addClass("a-flipout a-flipoutY");
                $(".dyj6 audio").removeClass("a-flipin a-flipinY").addClass("a-flipout a-flipoutY");
            }
            if(e==7){
                $(".dyj7_img1").removeClass("a-flipinX").addClass("a-flipoutX");
            }
            if(e==8){
                $(".dyj8_img2").removeClass("a-fadeinT").addClass("a-fadeoutT");
                $(".dyj8_img1_im1").removeClass("a-fadeinL1").addClass("a-fadeoutL");
                $(".dyj8_img1_im2").removeClass("a-fadeinR2").addClass("a-fadeoutR");
            }
            if(e==9){
                $(".dyj9_img1").removeClass("a-fadeinL").addClass("a-fadeoutL");
                $(".dyj9_img2").removeClass("a-fadeinR1").addClass("a-fadeoutR");

            }
            if(e==10){
                $(".dyj10_img1").removeClass("a-ring")
            }
        },
        afterLoad:function(f,e){
            if(e==1){
                $(".dyj1_img1").removeClass("a-fadeoutL").addClass("a-fadeinL");
                $(".dyj1_img2_im1").removeClass("a-bounceoutR").addClass("a-bounceinR");
                $(".dyj1_img2_im2").removeClass("a-fadeoutR").addClass("a-fadeinR1");
                $(".toTop").hide();
            }
            if(e==2){
                $(".dyj2_img1 .im1").removeClass("a-fadeoutL").addClass("a-fadeinL");
                $(".dyj2_img1 .im2").removeClass("a-fadeoutL").addClass("a-fadeinL1");
                $(".toTop").show();
            }
            if(e==3){
                $(".dyj3_img1").removeClass("a-fadeoutL").addClass("a-fadeinL");
                $(".dyj3_img2").removeClass("a-fadeoutT").addClass("a-fadeinT1");
                $(".dyj3_img3_im1").removeClass("a-fadeoutL").addClass("a-fadeinL2");
                $(".dyj3_img3_im2").removeClass("a-fadeoutR").addClass("a-fadeinR3");
            }
            if(e==4){
                $(".dyj4_img2").removeClass("a-fadeoutT").addClass("a-fadeinT");
                $(".dyj4_img1_im1").removeClass("a-fadeoutL").addClass("a-fadeinL1");
                $(".dyj4_img1_im2").removeClass("a-fadeoutR").addClass("a-fadeinR2");
            }
            if(e==5){
                $(".dyj5_img1_im1").removeClass("a-fadeoutL").addClass("a-fadeinL");
                $(".dyj5_img1_im2").removeClass("a-bounceoutL").addClass("a-bounceinL");
                $(".dyj5_img2").removeClass("a-fadeoutR").addClass("a-fadeinR1");
            }
            if(e==6){
                $(".dyj6_img1").removeClass("a-flipout a-flipoutY").addClass("a-flipin a-flipinY");
                $(".dyj6 audio").removeClass("a-flipout a-flipoutY").addClass("a-flipin a-flipinY");
            }
            if(e==7){
                $(".dyj7_img1").removeClass("a-flipoutX").addClass("a-flipinX");
            }
            if(e==8){
                $(".dyj8_img2").removeClass("a-fadeoutT").addClass("a-fadeinT");
                $(".dyj8_img1_im1").removeClass("a-fadeoutL").addClass("a-fadeinL1");
                $(".dyj8_img1_im2").removeClass("a-fadeoutR").addClass("a-fadeinR2");
            }
            if(e==9){
                $(".dyj9_img1").removeClass("a-fadeoutL").addClass("a-fadeinL");
                $(".dyj9_img2").removeClass("a-fadeoutR").addClass("a-fadeinR1");
            }
            if(e==10){
                $(".dyj10_img1").addClass("a-ring")
            }

        }
    });
});