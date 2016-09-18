$(function(){
	_xn_init();
});
function _xn_init(){
	$.post("/content/find",{'id':13},function(result){
		if(result.resultCode == 0){
			if(result.entity){
				$('.hr-xc-main').html(result.entity.content);
			}
		}else{
			console.log(result.msg);
		}
	},"json");
}