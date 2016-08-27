$(function(){
	_xn_init();
});
function _xn_init(){
	$.post("/pageInfo/find",{'id':11},function(result){
		if(result.resultCode == 0){
			if(result.entity){
				$('#pro-t  p:eq(0)').html(result.entity.introdution);
				$('#pro-t .pro-img img:eq(0)').attr('src',result.entity.extCover);
			}
		}else{
			console.log(result.msg);
		}
	},"json");
	/*
	$.post("/content/find",{'id':1},function(result){
		if(result.resultCode == 0){
			$(".pro-p2:eq(0)").html(result.entity.content);
		}else{
			console.log(result.msg);
		}
	},"json");
	*/
}