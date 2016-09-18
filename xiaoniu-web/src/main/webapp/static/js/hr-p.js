$(function(){
	_xn_init();
});
function _xn_init(){
	$.post("/content/find",{'id':11},function(result){
		if(result.resultCode == 0){
			var entity = result.entity;
			$('#about-us .hr-p-c').html(entity.content);
		}else{
			console.log(result.msg);
		}
	},"json");
	seeMore();
}

function seeMore(){
	var page = $("#idV-i .idV-ul").length;
	if(page >= 0){
		$.post("/news/list",{'type':7,'page':page+1,'rows':2,'top':0},function(result){
			if(result.resultCode == 0 && result.rows.length > 0){
				var rows = result.rows;
				var html = buildNewsRows(rows,0,2);
				$('#idV-i').append(html);
			}
			page = $("#idV-i .idV-ul").length;
			if(page *2 >= result.total){
				$("#idea-more").css("display",'none');
			}
		},"json");
	}
}

function buildNewsDiv(entity){
	var html = '';
	html += '<li><a href="/static/idea-inside.html?id='+entity.id+'">';
	html += '<div class="iN-img"><img src="'+entity.banner+'"/></div>';
	html += '<p>'+entity.title+'</p>';
	html += '<span>'+entity.summary+'</span>';
	html += '</a>';
	html += '<a href="/static/idea-inside.html?id='+entity.id+'" class="more">See more<span></span></a></li>';
	return html;
}

function buildNewsRows(rows,beginIndex,colNum){
	var html = '';
	var block ='<ul class="hr-p-ul idV-ul wow fadeInUp">';
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