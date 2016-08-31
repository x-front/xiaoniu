$(function(){
	_xn_init();
});
function _xn_init(){
	$.post("/pageInfo/dongtai",function(result){
		if(result.resultCode == 0){
			if(result.news){
//				$("#news-t p:eq(0)").html(result.news.introdution);
			}
			
			if(result.report){
//				$("#news-m p:eq(0)").html(result.report.introdution);
			}
			
		}else{
			console.log(result.msg);
		}
	},"json");
	
	$.post('/news/list',{'type':11,'page':1,'rows':4,'top':0},function(result){
		if(result.resultCode == 0){
			var rows = result.rows;
			var picHtml = '',titleHtml='',dotHtml='';
			for(var i=0; i<rows.length; i++){
				var entity = rows[i];
				picHtml += '<li><a href="/static/news-t-inside.html?id='+entity.id+'"><img src="'+entity.banner+'"/></a></li>';
				dotHtml += '<li><a href="javascript:;"></a></li>';
				titleHtml += '<li><h3>'+entity.title+'</h3><p>'+entity.summary+'</p></li>';
			} 
			$('.news-pic ul:eq(0)').html(picHtml);
			$('.news-pic ul:eq(1)').html(dotHtml);
			$('.news-pic ul:eq(2)').html(titleHtml);
		}else{
			console.log(result.msg);
		}
	});
	
	$.post('/news/list',{'type':5,'page':1,'rows':3,'top':0},function(result){
		if(result.resultCode == 0){
			var rows = result.rows;
			var html = '';
			for(var i=0; i<rows.length; i++){
				entity = rows[i];
				html += '<li><a href="/static/news-t-inside.html?id='+entity.id+'">';
				html += '<div class="iN-img wow fadeInUp"><img src="'+entity.banner+'"/></div>';
				html += '<p>'+entity.title+'</p>';
				html += '<span>'+entity.summary+'</span>';
				html += '</a>';
				html += '<a href="/static/news-t-inside.html?id='+entity.id+'" class="more wow fadeInUp">See more<span></span></a></li>';
			}
			html += '<div class="clearfix"></div>';
			$('#news-t ul:eq(0)').html(html);
		}else{
			console.log(result.msg);
		}
	});
	
	$.post('/news/list',{'type':6,'page':1,'rows':3,'top':0},function(result){
		if(result.resultCode == 0){
			var rows = result.rows;
			var html = '';
			for(var i=0; i<rows.length; i++){
				entity = rows[i];
				html += '<li><a href="/static/news-m-inside.html?id='+entity.id+'">';
				html += '<div class="iN-img wow fadeInUp"><img src="'+entity.banner+'"/></div>';
				html += '<p>'+entity.title+'</p>';
				html += '<span>'+entity.summary+'</span>';
				html += '</a>';
				html += '<a href="/static/news-m-inside.html?id='+entity.id+'" class="more wow fadeInUp">See more<span></span></a></li>';
			}
			html += '<div class="clearfix"></div>';
			$('#news-m ul:eq(0)').html(html);
		}else{
			console.log(result.msg);
		}
	});
	
}