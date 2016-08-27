$(function(){
	_xn_init();
});
function _xn_init(){
	$.post("/pageInfo/find",{'id':22},function(result){
		if(result.resultCode == 0){
			if(result.entity){
				$('.join-title:eq(0)').html(result.entity.introdution);
				$('.join-banner img:eq(0)').attr('src',result.entity.extCover);
			}
		}else{
			console.log(result.msg);
		}
	},"json");
	seeMore();
}

function seeMore(){
	var page = $("#join-list .join-ul").length;
	if(page >= 0){
		$.post("/joinUs/list",{'page':page/2+1,'rows':3},function(result){
			if(result.resultCode == 0 && result.rows.length > 0){
				var rows = result.rows;
				var html = buildNewsRows(rows,0,3);
				$('#join-list').append(html);
			}
			
			if((page + 1)*3 >= result.total){
				$("#join-more").css('display',"none");
			}
		},"json");
	}
}

function buildNewsDiv(entity,index){
	var html = '<li class="wow fadeInUp">';
	html += '<h3>' + entity.position +'</h3>';
	html += '<p>' + entity.summary + '</p>';
	html += '<a href="/static/join-inside.html?id='+entity.id+'" class="more wow fadeInUp">查看职位</a>';
	html += '</li>';
	return html;
}

function buildNewsRows(rows,beginIndex,colNum){
	var html = '<ul class="join-ul">';
	for(var i=beginIndex; i< rows.length; i++){
		var newsDiv = buildNewsDiv(rows[i],i-beginIndex);
		html += newsDiv;
	}
	html += '</ul>';
	return html;
}