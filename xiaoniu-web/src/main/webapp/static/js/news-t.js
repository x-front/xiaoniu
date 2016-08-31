$(function(){
	_xn_init();
});
function _xn_init(){
	$.post("/pageInfo/find",{'id':16},function(result){
		if(result.resultCode == 0){
			if(result.entity){
				$("#news-t p:eq(0)").html(result.entity.introdution);
			}
		}else{
			console.log(result.msg);
		}
	},"json");
	
	seeMore();
}

function seeMore(){
	var page = $("#news-t .news-t-ul").length;
	if(page >= 0){
		$.post("/news/list",{'type':5,'page':page/2+1,'rows':6,'top':0},function(result){
			if(result.resultCode == 0 && result.rows.length > 0){
				var rows = result.rows;
				var html = buildNewsRows(rows,0,3);
				$('#news-t').append(html);
			}
			
			if((page + 1)*3 >= result.total){
				$("#news-t-more").css("display",'none');
			}
		},"json");
	}
}

function buildNewsDiv(entity){
	var html = '';
	html += '<li><a href="/static/news-t-inside.html?id='+entity.id+'">';
	html += '<div class="iN-img wow fadeInUp"><img src="'+entity.banner+'"/></div>';
	html += '<p>'+entity.title+'</p>';
	html += '<span>'+entity.summary+'</span>';
	html += '</a>';
	html += '<a href="/static/news-t-inside.html?id='+entity.id+'" class="more wow fadeInUp">See more<span></span></a></li>';
	return html;
}

function buildNewsRows(rows,beginIndex,colNum){
	var html = '';
	var block ='<ul class="idV-ul news-t-ul wow fadeInUp">';
	for(var i=beginIndex; i< rows.length; i++){
		var newsDiv = buildNewsDiv(rows[i],i-beginIndex);
		block += newsDiv;
		if( (i-beginIndex +1)%colNum == 0){
			block += '<div class="clearfix"></div></ul>';
			html += block;
			block ='<ul class="idV-ul news-t-ul wow fadeInUp">';
		}
	}
	if((rows.length - beginIndex) % colNum != 0){
		block += '<div class="clearfix"></div></ul>';
		html += block;
	}
	return html;
}