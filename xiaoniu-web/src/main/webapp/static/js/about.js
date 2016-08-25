$(function(){
	console.log("about init");
	_xn_init();
});
function _xn_init(){
	$.post("/pageInfo/who",function(result){
		if(result.resultCode == 0){
			if(result.who){
				$("#about-us p").html(result.who.introdution);
				$("#about-us video").attr('src',who.extMedia);
				$("#about-us video").attr('poster',who.extCover);
			}
		}else{
			console.log(result.msg);
		}
	},"json");
	
	$.post("/news/list",{
		'page':1,
		'rows':3,
		'type':5
	},function(result){
		if(result.resultCode == 0){
			if(result.rows){
				var html = "";
				for(var i = 0; i < result.total && i < 3; i++){
					var entity = result.rows[i];
					html += '<li><a href="/static/news-t-inside.html?id='+entity.id +'"><div><img src="'+entity.banner+'"/></div><p>'
							+ entity.title + '</p><span>'+entity.summary+'</span></a></li>';
				}
				$(".section5 .models .s5-m").html(html);
			}
		}else{
			console.log(result.msg);
		}
	},"json");
}