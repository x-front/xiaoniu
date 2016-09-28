$(function(){
	console.log("index init");
	_xn_init();
});
function _xn_init(){
	$.post("/pageInfo/index",function(result){
		if(result.resultCode == 0){
			if(result.who){
				$(".section1 .desc").html(result.who.introdution);
			}
			
			if(result.voice){
				$(".section3 .desc").html(result.voice.introdution);
			}
			
			if(result.family){
				$(".section4 .g1 .bg").css("background",'url('+result.family.extCover+') no-repeat 50%');
			}
			
			if(result.sports){
				$(".section4 .g2 .bg").css("background",'url('+result.sports.extCover+') no-repeat 50%');
			}
			
			if(result.welfare){
				$(".section4 .g3 .bg").css("background",'url('+result.welfare.extCover+') no-repeat 50%');
			}
			
			if(result.education){
				$(".section4 .g4 .bg").css("background",'url('+result.education.extCover+') no-repeat 50%');
			}
			
		}else{
			console.log(result.msg);
		}
	},"json");
	
	$.post("/news/list",{
		'page':1,
		'rows':3,
		'type':11
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
	$.post("/indexNews/list",{
		'page':1,
		'rows':20
	},function(result){
		if(result.resultCode == 0){
			var html = "";
			for(var i = 0; i < result.total ; i++){
				var entity = result.rows[i];
				html += '<li><a href="/static/news-t-inside.html?id='+entity.id+'">'+entity.title+'</a></li>';
			}
			$("#dowebok .b-news ul").html(html);
				$(".b-news").slide({mainCell:".bd ul",autoPage:true,effect:"topLoop",autoPlay:true,vis:1,startFun:function(){
					var timer = $(".b-news .timer");
					timer.stop(true,true).animate({ "width":"0%" },0).animate({ "width":"90%" },2500);
				}});
		}else{
			console.log(result.msg);
		}
	},'json');
	
}