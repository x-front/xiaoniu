$(function(){
	_xn_init();
});
function _xn_init(){
	$.post("/news/list",{'type':1,'page':1,'rows':1,'top':1},function(result){
		if(result.resultCode == 0 && result.total > 0){
			var rows = result.rows;
			var head = rows[0];
			$("#wrap .about-w-banner-pic img:eq(0)").attr('src',head.banner);
			$("#wrap .about-w-banner h3:eq(0)").html(head.title);
			$("#wrap .about-w-banner p:eq(0)").html(head.summary);
			$(".about-w-banner a:eq(0)").attr('href','/static/beautiful-f-inside.html?id='+head.id);
		}
	},"json");
	seeMore();
}

function seeMore(){
	var page = $(".about-w-main .about-w-list .about-w-item").length;
	if(page >= 0){
		$.post("/news/list",{'type':1,'page':page/2+1,'rows':2,'top':0},function(result){
			if(result.resultCode == 0 && result.rows.length > 0){
				var rows = result.rows;
				var html = buildNewsRows(rows,0,2);
				$('.about-w-list:eq(0)').append(html);
			}
			
			if((page + 1)*2 >= result.total){
				$("#seeMoreBtn").css("display",'none');
			}
		},"json");
	}
}

function buildNewsDiv(entity,index){
	
	var html = '<div class="about-w-item about-w-item'+(index%2 + 1)+'"><a href="/static/beautiful-f-inside.html?id='+entity.id +'"><div class="about-wh-img wow fadeInUp"><img src="' + entity.banner +'"/></div>';
	html += '<h3 class="wow fadeInUp">' + entity.title +'</h3>';
	html += '<p class="wow fadeInUp">' + entity.summary + '</p></a>';
	html += '<a href="/static/beautiful-f-inside.html?id='+entity.id+'" class="more wow fadeInUp">See more<span></span></a>';
	html += '</div>';
	return html;
}

function buildNewsRows(rows,beginIndex,colNum){
	var html = "";
	for(var i=beginIndex; i< rows.length; i++){
		var newsDiv = buildNewsDiv(rows[i],i-beginIndex);
		html += newsDiv;
		if( (i-beginIndex +1)%colNum == 0){
			html += '<div class="clearfix"></div>';
		}
	}
	if((rows.length - beginIndex) % colNum != 0){
		html += '<div class="clearfix"></div>';
	}
	return html;
}