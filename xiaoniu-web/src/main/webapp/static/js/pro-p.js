$(function(){
	_xn_init();
});
function _xn_init(){
	$.post("/pageInfo/find",{'id':7},function(result){
		if(result.resultCode == 0){
			if(result.entity){
				$('#pro-ph .pro-p1 p:eq(0)').html(result.entity.introdution);
				$('#pro-ph .pro-p1 video:eq(0)').attr('src',result.entity.extMedia);
				$('#pro-ph .pro-p1 video:eq(0)').attr('poster',result.entity.extCover);
			}
		}else{
			console.log(result.msg);
		}
	},"json");
	
	$.post("/content/find",{'id':1},function(result){
		if(result.resultCode == 0){
			$(".pro-p2:eq(0)").html(result.entity.content);
		}else{
			console.log(result.msg);
		}
	},"json");
	
}