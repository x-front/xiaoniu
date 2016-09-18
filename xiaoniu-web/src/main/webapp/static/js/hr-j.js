$(function(){
	_xn_init();
});
function _xn_init(){
	$.post("/content/find",{'id':9},function(result){
		if(result.resultCode == 0){
			var entity = result.entity;
			$('#about-us').append(entity.content);
		}else{
			console.log(result.msg);
		}
	},"json");
}