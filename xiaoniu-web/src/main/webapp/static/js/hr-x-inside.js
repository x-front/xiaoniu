$(function(){
	_xn_init();
});
function _xn_init(){
var id = getParam('id');
	
	/*$.post("/pageInfo/find",{'id':22},function(result){
		if(result.resultCode == 0){
			if(result.entity){
				$('.join-title:eq(0)').html(result.entity.introdution);
				$('.join-banner img:eq(0)').attr('src',result.entity.extCover);
			}
		}else{
			console.log(result.msg);
		}
	},"json");*/
	
	$.post("/joinUs/find",{'id':id},function(result){
		if(result.resultCode == 0){
			if(result.entity){
				$("#join-list h3:eq(0)").html(result.entity.position);
				$('#join-list').append(result.entity.content);
			}
		}else{
			console.log(result.msg);
		}
	},"json");
}