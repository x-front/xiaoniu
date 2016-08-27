$(function(){
	_xn_init();
});
function _xn_init(){
	$.post("/pageInfo/find",{'id':9},function(result){
		if(result.resultCode == 0){
			if(result.entity){
				$('#content-head p:eq(0)').html(result.entity.introdution);
				$('#content-head img:eq(0)').attr('src',result.entity.extCover);
			}
		}else{
			console.log(result.msg);
		}
	},"json");
	
	$.post("/content/find",{'id':4},function(result){
		if(result.resultCode == 0){
			$("#content-body").html(result.entity.content);
		}else{
			console.log(result.msg);
		}
	},"json");
	
}