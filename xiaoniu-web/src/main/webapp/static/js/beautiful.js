$(function(){
	_xn_init();
});
function _xn_init(){
	$.post("/pageInfo/beauty",function(result){
		if(result.resultCode == 0){
			if(result.family){
				$(".beautiful-f .beautiful-f-inner p:eq(0)").html(result.family.introdution);
				$(".beautiful-f").attr('background-image','url('+result.family.extCover+')');
			}
			
			if(result.sports){
				$(".beautiful-t .beautiful-t-inner p:eq(0)").html(result.sports.introdution);
				$(".beautiful-t").attr('background-image','url('+result.sports.extCover+')');
			}
			
			if(result.welfare){
				$(".beautiful-g .beautiful-g-inner p:eq(0)").html(result.welfare.introdution);
				$(".beautiful-g").attr('background-image','url('+result.welfare.extCover+')');
			}
			
			if(result.education){
				$(".beautiful-j .beautiful-j-inner p:eq(0)").html(result.education.introdution);
				$(".beautiful-j").attr('background-image','url('+result.education.extCover+')');
			}
			
		}else{
			console.log(result.msg);
		}
	},"json");
	
}