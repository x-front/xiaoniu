    $(function(){
    	//首页导航效果
        $(".menu").click(function() {
        $(".sidebar").animate({right:0}, 500);
          $(this).animate({opacity:0},500);
          $(".nav li").eq(0).css({animation:"mLi .5s .3s",opacity:"1",marginLeft:"0"});
          $(".nav li").eq(1).css({animation:"mLi .5s .33s",opacity:"1",marginLeft:"0"});
          $(".nav li").eq(2).css({animation:"mLi .5s .36s",opacity:"1",marginLeft:"0"});
          $(".nav li").eq(3).css({animation:"mLi .5s .39s",opacity:"1",marginLeft:"0"});
          $(".nav li").eq(4).css({animation:"mLi .5s .42s",opacity:"1",marginLeft:"0"});
          $(".nav li").eq(5).css({animation:"mLi .5s .45s",opacity:"1",marginLeft:"0"});
          $(".nav li").eq(6).css({animation:"mLi .5s .48s",opacity:"1",marginLeft:"0"});
          $(".search").css({animation:"mLi .5s .51s",opacity:"1",marginLeft:"0"});
          $(".share").css({animation:"mLi .5s .54s",opacity:"1",marginLeft:"0"});
          $(".ce").css({animation:"mLi .5s .57s",opacity:"1",marginLeft:"0"});
          $(".copy").css({animation:"mLi .5s .6s",opacity:"1",marginLeft:"0"});
          $(".scan").css({animation:"mLi .5s .63s",opacity:"1",marginLeft:"0"});
      });
        $(".s2").click(function(){
            $(this).addClass("s2-active").siblings().removeClass("s2-active");
        });
        $(".sidebar .close").click(function() {
            $(".menu").animate({opacity:1},500);
            $(".sidebar").animate({right:'-100%'}, 500);
            $(".nav li").css({opacity:"0",marginLeft:"200px"});
            $(".search").css({opacity:"0",marginLeft:"200px"});
            $(".share").css({opacity:"0",marginLeft:"200px"});
            $(".ce").css({opacity:"0",marginLeft:"200px"});
            $(".copy").css({opacity:"0",marginLeft:"200px"});
            $(".scan").css({opacity:"0",marginLeft:"200px"});
        });
        //首页banner区跳转链接
        $(".slogan").click(function(){
           location.href="about.html";
        });
        
        //内页显示更多
 	    $("#audio-more").click(function(){
 		$(".audio-ul").css("display","block");
 	    })
 	    $("#idea-more").click(function(){
 		$(".idea-ul").css("display","block");
 	    })
 	    $("#news-t-more").click(function(){
 		$(".news-t-ul").css("display","block");
 	    })
 	    $("#news-m-more").click(function(){
 		$(".news-m-ul").css("display","block");
 	    })
 	    $("#join-more").click(function(){
 		$(".join-ul").css("display","block");
 	    })
 	   //新闻类页面轮播效果
 	   setInterval("newsPlay()",3000);
       setInterval("idVPlay()",3000);
       //新闻类页面轮播区域高度
     var oH1=$(".idV-img img").css("height");
     $(".idV-img").css("height",oH1);
     var oH2=$(".news-images img").css("height");
     $(".news-images").css("height",oH2);
     var oH3=$(".news-pic .idV-pic-b li").css("height");
     $(".news-pic .news-txt").css("height",oH3);
       $(".about-j1-l a").toggle(function(){    $(this).css({color:"#c19a3f",background:"url(images/about/about-jt_06.jpg) no-repeat right center"}).next().slideDown(500);
      },function(){    $(this).css({color:"#000",background:"url(images/about/about-jt_11.jpg) no-repeat right center"}).next().slideUp(500);
       })
       
       $(".about-j3 a").click(function(){
       	$(".about-j3-pic").slideToggle(500);
       })
       $(".about-j4 a").click(function(){
       	$(".about-j4-pic").slideToggle(500);
       })  
        //分屏效果动画
        $('#dowebok').fullpage({
        navigation:true,
        anchors:["banner","section1","section2","section3","section4","section5","footer"],
        onLeave: function(index, nextIndex, direction){
           if(index==3){
               $(".section2 .wrap").removeClass('a-fadeinT').addClass('a-fadeoutT');
                $(".s2-ul li").css({left:"50%",top:"50%",opacity:"0","margin-top":"-20px","margin-left":"-68px"});     
       }
          if(index==4){
            $(".section3 .tit").removeClass('a-fadeinT').addClass('a-fadeoutT');
            $(".section3 .desc").removeClass('a-fadeinT').addClass('a-fadeoutT');
            $(".section3 .video").removeClass('a-fadeinB').addClass('a-fadeoutB');
          }
          if(index==2){
            $(".section1 .tit").removeClass('a-fadeinT').addClass('a-fadeoutT');
            $(".section1 .desc").removeClass('a-fadeinT').addClass('a-fadeoutT');
            $(".section1 .models").removeClass('a-fadeinB').addClass('a-fadeoutB');
          }
          if(index==5){
            $(".section5 .tit").removeClass('a-fadeinT').addClass('a-fadeoutT');
            $(".section5 .models").removeClass('a-fadeinB').addClass('a-fadeoutB');
          }
          if(index==7){
            $(".footer .cr").removeClass('a-fadeinT').addClass('a-fadeoutT');
            $(".footer .br").removeClass('a-fadeinB').addClass('a-fadeoutB');
            }
          if(index == 1){
            $(".banner .slogan").removeClass('a-fadeinT').addClass('a-fadeoutT');
            $(".banner .ft").removeClass('a-fadeinB').addClass('a-fadeoutB');
          }
        },
        afterLoad:function(anchorLink, index){
          if(index==4){
            $(".section3 .tit").removeClass('a-fadeoutT').addClass('a-fadeinT');
            $(".section3 .desc").removeClass('a-fadeoutT').addClass('a-fadeinT');
            $(".section3 .video").removeClass('a-fadeoutB').addClass('a-fadeinB');
          }
            if(index==3){
                $(".section2 .wrap").removeClass('a-fadeoutT').addClass('a-fadeinT');
                $(".s2-ul li").css({left:"50%",top:"50%",opacity:"0","margin-top":"-20px","margin-left":"-68px"});
                s2move();
            }

            if(index==2){
            $(".section1 .tit").removeClass('a-fadeoutT').addClass('a-fadeinT');
            $(".section1 .desc").removeClass('a-fadeoutT').addClass('a-fadeinT');
            $(".section1 .models").removeClass('a-fadeoutB').addClass('a-fadeinB');
            $(".toTop").show();
            }
            if(index==5){
                $(".section5 .tit").removeClass('a-fadeoutT').addClass('a-fadeinT');
                $(".section5 .models").removeClass('a-fadeoutB').addClass('a-fadeinB');
            }
            if(index==7){
                $(".footer .cr").removeClass('a-fadeoutT').addClass('a-fadeinT');
                $(".footer .br").removeClass('a-fadeoutB').addClass('a-fadeinB');
            }
          if(index == 1){
            $(".banner .slogan").removeClass('a-fadeoutT').addClass('a-fadeinT');
            $(".banner .ft").removeClass('a-fadeoutB').addClass('a-fadeinB');
            $(".toTop").hide();
            var myVideo=document.getElementById("video1");
            myVideo.play();
          }
        }
      });
        $("#fp-nav").removeClass("right").addClass("left"); //首页分屏导航在左边显示
    });
    //内页新闻类页面轮播方法   （ 上面有调用 ）
 	var i=0;
 function newsPlay(){
 	var oH1=$(".news-img").css("height").slice(0,-2);
 	var oH2=$(".news-txt").css("height").slice(0,-2);
 	$(".news-img ul").animate({"top":-oH1*i+'px'});
 	$(".news-txt ul").animate({"top":-oH2*i+'px'});
 	$(".news-pic-c li").eq(i).addClass("active").siblings().removeClass("active");
 	i++;
 	if(i>=$(".news-img ul li").length){
 		i=0;
 	}
}
 function idVPlay(){
 	var oH1=$(".idV-img").css("height").slice(0,-2);
 	var oH2=$(".idV-txt").css("height").slice(0,-2);
 	$(".idV-img ul").animate({"top":-oH1*i+'px'});
 	$(".idV-txt ul").animate({"top":-oH2*i+'px'});
 	$(".idV-pic-c li").eq(i).addClass("active").siblings().removeClass("active");
 	i++;
 	if(i>=$(".idV-img ul li").length){
 		i=0;
 	}

 }
 
 function s2Hide(){
 	$(".s2-ul li").css({left:"50%",top:"50%",opacity:"0","margin-top":"-20px","margin-left":"-68px"});
 }
 function s2move(){
 	       //首页section2鼠标悬停效果
        $(".section2 .wrap .s2-txt").hover(function(){
            $(".mainxz1 img").css({	"animation":" mainzx1 30s infinite linear",
                "-webkit-animation":"mainzx1 30s infinite linear",
                "-moz-animation":"mainzx1 30s infinite linear",
                "-o-animation":"mainzx1 30s infinite linear",
                "opacity":"1"
            });
            $(".mainxz2 img").css({	"animation":" mainzx2 15s infinite linear",
                "-webkit-animation":"mainzx2 15s infinite linear",
                "-moz-animation":"mainzx2 15s infinite linear",
                "-o-animation":"mainzx2 15s infinite linear",
                "opacity":"1"
            })
            $(".s2-ul li").eq(0).animate({left:"0",top:"0",opacity:"1",margin:"0"},650,function(){$(".s2-ul li").eq(2).animate({left:"0",top:"236px",opacity:"1",margin:"0"},650,function(){$(".s2-ul li").eq(1).animate({left:"0",top:"118px",opacity:"1",margin:"0"},650)
     })
 })
        $(".s2-ul li").eq(5).animate({left:"856px",top:"236px",opacity:"1",margin:"0"},650,function(){ $(".s2-ul li").eq(3).animate({left:"856px",top:"0",opacity:"1",margin:"0"},650,function(){$(".s2-ul li").eq(4).animate({left:"856px",top:"118px",opacity:"1",margin:"0"},650)
      })
    })
        },function(){
            $(".mainxz1 img").css({	"animation":" mainzx1 120s infinite linear",
                "-webkit-animation":"mainzx1 120s infinite linear",
                "-moz-animation":"mainzx1 120s infinite linear",
                "-o-animation":"mainzx1 120s infinite linear",
                "opacity":"0.7"
            });
            $(".mainxz2 img").css({	"animation":" mainzx2 60s infinite linear",
                "-webkit-animation":"mainzx2 60s infinite linear",
                "-moz-animation":"mainzx2 60s infinite linear",
                "-o-animation":"mainzx2 60s infinite linear",
                "opacity":"0.7"
            })
        });  
 }
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
