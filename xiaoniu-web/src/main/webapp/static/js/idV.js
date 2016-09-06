$(function(){
	_xn_init();
});
function _xn_init(){
	$.post("/pageInfo/voice",function(result){
		if(result.resultCode == 0){
			if(result.voice){
				$(".about-i p:eq(0)").html(result.voice.introdution);
			}
			
			if(result.thinking){
				$("#idV-i p:eq(0)").html(result.thinking.introdution);
			}
			
			if(result.video){
				$("#idV-v p:eq(0)").html(result.video.introdution);
			}
			
			if(result.journal){
				$("#journal-summary").html(result.journal.introdution);
			}
			
		}else{
			console.log(result.msg);
		}
	},"json");
	
	$.post('/news/list',{'type':10,'page':1,'rows':4,'top':0},function(result){
		if(result.resultCode == 0){
			var rows = result.rows;
			var picHtml = '',titleHtml='',dotHtml='';
			for(var i=0; i<rows.length; i++){
				var entity = rows[i];
				picHtml += '<li><a href="/static/idea-inside.html?id='+entity.id+'"><img src="'+entity.banner+'"/></a></li>';
				dotHtml += '<li><a href="javascript:;"></a></li>';
				titleHtml += '<li><p>'+entity.summary+'</p></li>';
			} 
			$('.idV-pic ul:eq(0)').html(picHtml);
			$('.idV-pic ul:eq(1)').html(dotHtml);
			$('.idV-pic ul:eq(2)').html(titleHtml);
		}else{
			console.log(result.msg);
		}
	});
	
	
	$.post('/news/list',{'type':7,'page':1,'rows':3,'top':0},function(result){
		if(result.resultCode == 0){
			var rows = result.rows;
			var html = '';
			for(var i=0; i<rows.length; i++){
				entity = rows[i];
				html += '<li><a href="/static/idea-inside.html?id='+entity.id+'">';
				html += '<div class="iN-img"><img src="'+entity.banner+'"/></div>';
				html += '<p>'+entity.title+'</p>';
				html += '<span>'+entity.summary+'</span>';
				html += '</a>';
				html += '<a href="/static/idea-inside.html?id='+entity.id+'" class="more">See more<span></span></a></li>';
			}
			html += '<div class="clearfix"></div>';
			$('#idV-i ul:eq(0)').html(html);
		}else{
			console.log(result.msg);
		}
	});
	
	$.post('/news/list',{'type':8,'page':1,'rows':3,'top':0},function(result){
		if(result.resultCode == 0){
			var rows = result.rows;
			var html = '';
			for(var i=0; i<rows.length; i++){
				entity = rows[i];
				html += '<li><a href="/static/audio-inside.html?id='+entity.id+'">';
				html += '<div class="iN-img"><img src="'+entity.banner+'"/><img class="playBtn" src="/static/images/idV/play.png"/></div>';
				html += '<p>'+entity.title+'</p>';
				html += '<span>'+entity.summary+'</span>';
				html += '</a>';
				html += '<a href="/static/audio-inside.html?id='+entity.id+'" class="more">See more<span></span></a></li>';
			}
			html += '<div class="clearfix"></div>';
			$('#idV-v ul:eq(0)').html(html);
		}else{
			console.log(result.msg);
		}
	});
	
}