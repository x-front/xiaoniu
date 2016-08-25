$(function(){
	_xn_init();
});
function _xn_init(){
	$.post("/pageInfo/find",{'id':5},function(result){
		if(result.resultCode == 0){
			if(result.entity){
				$('#about-r p:eq(0)').html(result.entity.introdution);
			}
		}else{
			console.log(result.msg);
		}
	},"json");
	
	$.post("/honor/list",function(result){
		if(result.resultCode == 0){
			var rows = result.rows;
			var html = '',block='<ul class="wow fadeInUp">';
			for(var i=0;i<result.total; i++){
				var entity = rows[i];
				if(i%2 == 0 && i!=0){
					html += block + '<div class="clearfix"></div>'+'</ul>';
					block = '<ul class="wow fadeInUp">';
				}
				block += '<li><div><img src="' + entity.banner + '"/></div><br/><span>' + entity.summary + '</span></li>' ;  
				
			}
			if(result.total != 0){
				block += '<div class="clearfix"></div>' + '</ul>';
				html += block;
			}
			$('#about-r').append(html);
		}
	},"json");
	
}