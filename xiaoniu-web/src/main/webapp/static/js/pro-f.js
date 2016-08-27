$(function(){
	_xn_init();
});
function _xn_init(){
	$.post("/pageInfo/find",{'id':10},function(result){
		if(result.resultCode == 0){
			if(result.entity){
				$('#pro-zx .pro-zx p:eq(0)').html(result.entity.introdution);
				$('#pro-zx .pro-img img:eq(0)').attr('src',result.entity.extCover);
			}
		}else{
			console.log(result.msg);
		}
	},"json");
	
	/*
	$.post("/content/find",{'id':2},function(result){
		if(result.resultCode == 0){
			$(".pro-p2:eq(0)").html(result.entity.content);
		}else{
			console.log(result.msg);
		}
	},"json");
	*/
}