$(function(){
	_xn_init();
});

function _xn_init(){
	getReport(1,6);
}

function seeMore(){
	var liCount = $("#journal-ul li").length;
	var page = Math.ceil(liCount / 6);
	getReport(page+1,6);
}

function getReport(page,size){
	$.post("/media/list",{'page':page,'rows':size,'type':1},function(result){
		if(result.resultCode == 0){
			var html = buildReport(result.rows);
			$("#journal-ul").append(html);
			if(page * size >= result.total){
				$("#news-t-more").css("display", "none");
			}
		}
	},"json");
}

function buildReport(data){
	var html = '';
	for (var i = 0; i < data.length; i++) {
		var item = data[i];
		html += '<li><a href="'+ item.extMedia+'" target="_blank">'
			+ '<div class="iN-img wow fadeInUp"><img src="'+item.extCover+'"/></div>'
			+ '<p>'+item.introdution+'</p>'
			+ '</a>'
			+ '<a href="'+ item.extMedia+'" class="download" download></a></li>';
		if((i+1) % 3 == 0){
			html += '<div class="clearfix"></div>';
		}
	}
	return html;
}