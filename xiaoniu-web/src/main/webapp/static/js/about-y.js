$(function(){
	_xn_init();
});
function _xn_init(){
	/*$.post("/moreContent/principle",function(result){
			if(result.p1){
				$('.about-y1 .beautiful-f-inner p:eq(0)').html(result.p1.content);
				$('.about-y1').css('background-image','url('+result.p1.banner+')');
			}
			
			if(result.p2){
				$('.about-y2 .beautiful-f-inner p:eq(0)').html(result.p2.content);
				$('.about-y2').css('background-image','url('+result.p2.banner+')');
			}
			
			if(result.p3){
				$('.about-y3 .beautiful-f-inner p:eq(0)').html(result.p3.content);
				$('.about-y3').css('background-image','url('+result.p3.banner+')');
			}
			
			if(result.p4){
				$('.about-y4 .beautiful-f-inner p:eq(0)').html(result.p4.content);
				$('.about-y4').css('background-image','url('+result.p4.banner+')');
			}
			
			if(result.p5){
				$('.about-y5 .beautiful-f-inner p:eq(0)').html(result.p5.content);
				$('.about-y5').css('background-image','url('+result.p5.banner+')');
			}
			
			if(result.p6){
				$('.about-y6 .beautiful-f-inner p:eq(0)').html(result.p6.content);
				$('.about-y6').css('background-image','url('+result.p6.banner+')');
			}
			
			if(result.p7){
				$('.about-y7 .beautiful-f-inner p:eq(0)').html(result.p7.content);
				$('.about-y7').css('background-image','url('+result.p7.banner+')');
			}
	},"json");*/
	$.post("/content/find",{'id':8,'lang':0},function (result) {
		if (result.resultCode == 0) {
			var entity = result.entity;
			$(".about-yz").append(entity.content);
        }else {
			console.log(result.msg);
        }
    },"json");
}