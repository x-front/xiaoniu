$(function(){
	_xn_init();
});
function _xn_init(){
	$.post("/pageInfo/doWhat",function(result){
		if(result.resultCode == 0){
			if(result.who){
				$("#pro-p p:eq(0)").html(result.who.introdution);
				$("#pro-p .pro-p-video img:eq(0)").attr('src',result.who.extCover);
			}
			
			if(result.advance){
				$("#pro-z p:eq(0)").html(result.advance.introdution);
			}
			
			if(result.manager){
				$("#pro-x p:eq(0)").html(result.manager.introdution);
			}
			
			if(result.princeple){
				$("#pro-f p:eq(0)").html(result.princeple.introdution);
			}
			
			if(result.honor){
				$("#pro-t p:eq(0)").html(result.honor.introdution);
			}
			if(result.culture){
				$("#pro-k p:eq(0)").html(result.culture.introdution);
			}
		}else{
			console.log(result.msg);
		}
		
		
	},"json");
	
}