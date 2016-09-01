$(function(){
	_xn_init();
});
function _xn_init(){
	$.post("/moreContent/advance",function(result){
			if(result.pp1){
				$(".about-j1-l .about-j1-item1 a:eq(0)").html(result.pp1.title);
				$(".about-j1-l .about-j1-item1 .see-more-div:eq(0)").html(result.pp1.more);
			}
			if(result.pp2){
				$(".about-j1-l .about-j1-item2 a:eq(0)").html(result.pp2.title);
				$(".about-j1-l .about-j1-item2 .see-more-div:eq(0)").html(result.pp2.more);
			}
			if(result.pp3){
				$(".about-j1-l .about-j1-item3 a:eq(0)").html(result.pp3.title);
				$(".about-j1-l .about-j1-item3 .see-more-div:eq(0)").html(result.pp3.more);
			}
			
			if(result.p1){
				$(".about-j1-l h3:eq(0)").html(result.p1.title);
				$('.about-j1 .about-j-r img:eq(0)').attr('src',result.p1.banner);
				$('.about-j1 .about-j-r p:eq(0)').html(result.p1.bannerDesc);
			}
			
			if(result.p2){
				$('.about-j2').attr('background','url('+result.p2.banner+') no-repeat')
				$('.about-j22 h3:eq(0)').html(result.p2.title);
				$('.about-j22 p:eq(0)').html(result.p2.content);
				$('.about-j2-pic').html(result.p2.more);
			}
			
			if(result.p3){
				$('.about-j3-l h3:eq(0)').html(result.p3.title);
				$('.about-j3-l p:eq(0)').html(result.p3.content);
				$('.about-j3-pic').html(result.p3.more);
				
				$('.about-j3 .about-j-r img:eq(0)').attr('src',result.p3.banner);
				$('.about-j3 .about-j-r p:eq(0)').html(result.p3.bannerDesc);
			}
			
			if(result.p4){
				$('.about-j4-l h3:eq(0)').html(result.p4.title);
				$('.about-j4-l p:eq(0)').html(result.p4.content);
				$('.about-j4-pic').html(result.p4.more);
				
				$('.about-j4 .about-j-r img:eq(0)').attr('src',result.p4.banner);
				$('.about-j4 .about-j-r p:eq(0)').html(result.p4.bannerDesc);
			}
			
			if(result.p5){
//				$('.about-j5').attr('background','url('+result.p5.banner+') no-repeat')
//				$(".about-j5 .about-j55 h3:eq(0)").html(result.p5.title);
//				$(".about-j5 .about-j55 p:eq(0)").html(result.p5.content);
				$('.about-j4:eq(1) .about-j4-l h3:eq(0)').html(result.p5.title);
				$('.about-j4:eq(1) .about-j4-l p:eq(0)').html(result.p5.content);
				
				$('.about-j4:eq(1) .about-j-r img:eq(0)').attr('src',result.p5.banner);
				$('.about-j4:eq(1) .about-j-r p:eq(0)').html(result.p5.bannerDesc);
			}
			
			if(result.p6){
//				$('.about-j6').attr('background','url('+result.p6.banner+') no-repeat')
//				$(".about-j6 .about-j66 h3:eq(0)").html(result.p6.title);
//				$(".about-j6 .about-j66 p:eq(0)").html(result.p6.content);
				$('.about-j4:eq(2) .about-j4-l h3:eq(0)').html(result.p6.title);
				$('.about-j4:eq(2) .about-j4-l p:eq(0)').html(result.p6.content);
				
				$('.about-j4:eq(2) .about-j-r img:eq(0)').attr('src',result.p6.banner);
				$('.about-j4:eq(2) .about-j-r p:eq(0)').html(result.p6.bannerDesc);
			}
			
			if(result.p7){
//				$(".about-j7 .about-j7-img img:eq(0)").attr('src',result.p7.banner);
//				$(".about-j7 .about-j7-txt h3:eq(0)").html(result.p6.title);
//				$(".about-j7 .about-j7-txt p:eq(0)").html(result.p6.content);
				$('.about-j4:eq(3) .about-j4-l h3:eq(0)').html(result.p7.title);
				$('.about-j4:eq(3) .about-j4-l p:eq(0)').html(result.p7.content);
				
				$('.about-j4:eq(3) .about-j-r img:eq(0)').attr('src',result.p7.banner);
				$('.about-j4:eq(3) .about-j-r p:eq(0)').html(result.p7.bannerDesc);
			}
			
	},"json");
	
}