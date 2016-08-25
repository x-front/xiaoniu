$(function(){
	_xn_init();
});
function _xn_init(){
	$.post("/pageInfo/find",{'id':3},function(result){
		if(result.resultCode == 0){
			if(result.entity){
				$('#about-t p:eq(0)').html(result.entity.introdution);
			}
		}else{
			console.log(result.msg);
		}
	},"json");
	
	$.post("/leader/list",function(result){
		if(result.resultCode == 0){
			var rows = result.rows;
			var html = '',block='<ul class="wow fadeInUp">';
			for(var i=0;i<result.rows.length; i++){
				var entity = rows[i];
				if(i%3 == 0 && i!=0){
					html += block + '</ul>';
					block = '<ul class="wow fadeInUp">';
				}
				block += '<li><a href=""><div class="t-p"><img src="' + entity.banner + '"/></div><br/><h4>' + entity.name + '</h4><span>' + entity.summary+'</span></a></li>';  
			}
			if(result.rows.length != 0){
				block += '</ul>';
				html += block;
			}
			$('#about-t').append(html);
		}
	},"json");
	
}