$(function(){
	console.log("index init");
	_xn_init();
});
function _xn_init(){
	$.post("/media/list",{'page':1,'rows':1,'type':6,'lang':0},function(result){
		if(result.resultCode == 0){
			var rows = result.rows;
			var row = rows[0];
			var media = row.extMedia;
			$('.s1_v video').attr('src',media)
		}else{
			console.log(result.msg);
			var meida='http://xiaoniu66-web.oss-cn-shenzhen.aliyuncs.com/media/20170704/20170704184846_326.mp4';
			$('.s1_v video').attr('src',meida)
		}
	},"json");

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
				for(var i = 0; i < result.rows.length && i < 3; i++){
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
			for(var i = 0; i < result.rows.length ; i++){
				var entity = result.rows[i];
				html += '<li><a href="/static/news-t-inside.html?id='+entity.id+'">'+entity.title+'</a></li>';
			}
			$("#dowebok .b-news .bd ul").html(html);
			if($(window).width()>1024){
				$(".b-news").slide({mainCell:".bd ul",autoPage:true,effect:"topLoop",autoPlay:true,vis:1});
			}else{
				$(".b-news").slide({mainCell:".bd ul",autoPage:true,effect:"topLoop",autoPlay:true,vis:1,mouseOverStop:false});
			}
					/*var t=parseInt($(".b-news .bd ul").css("top").slice(0,-2));
					for(var j=0;j<$(".timer span").length;j++){
						if(t==-(j+1)*30){
							$(".timer span").eq(j).css("opacity","1").siblings().css("opacity","0.3");
						}
					}*/
/*
					var timer = $(".b-news .timer");
					timer.stop(true,true).animate({ "width":"0%" },0).animate({ "width":"100%" },2500);
*/          /*$(".hd ul li").text("");*/

			$("#dowebok .b-news .bd ul li").hover(function(){
				var n=$(this).index();
				$(".hd ul li").eq(n-1).css({"background":"#eea807"})
			},function(){
				var n=$(this).index();
				$(".hd ul li").eq(n-1).css({"background":"#FFF"})
			})
		}else{
			console.log(result.msg);
		}
	},'json');
	
}