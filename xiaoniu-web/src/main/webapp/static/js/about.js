$(function(){
	console.log("about init");
	_xn_init();
});
function _xn_init(){
	$.post("/pageInfo/who",function(result){
		if(result.resultCode == 0){
			if(result.who){
				$("#about-us p:eq(0)").html(result.who.introdution);
				$("#about-us video:eq(0)").attr('src',result.who.extMedia);
				$("#about-us video:eq(0)").attr('poster',result.who.extCover);
			}
			
			if(result.advance){
				$("#about-j p:eq(0)").html(result.advance.introdution);
			}
			
			if(result.manager){
				$("#about-t p:eq(0)").html(result.manager.introdution);
			}
			
			if(result.princeple){
				$("#about-y p:eq(0)").html(result.princeple.introdution);
			}
			
			if(result.honor){
				$("#about-r p:eq(0)").html(result.honor.introdution);
			}
			if(result.culture){
				$("#about-w p:eq(0)").html(result.culture.introdution);
				$("#about-w .about-w-pic img:eq(0)").attr('src',result.culture.extCover);
			}
		}else{
			console.log(result.msg);
		}
		
		
	},"json");
	
}