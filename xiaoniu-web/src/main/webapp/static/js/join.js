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
/*	societyJob();
	schoolJob();*/
	jobNews();
}

/*
function societyJob(){
	$.post("/joinUs/list",{'page':1,'rows':5},function(result){
		if(result.resultCode == 0 && result.rows.length > 0){
				var rows = result.rows;
				var html = buildJobRow(rows);
				$('#wrap .hr-index .hr-index-item ul:eq(0)').append(html);
		}else{
			console.log(result.msg);
		}
	},'json');
}

function schoolJob(){
	$.post("/joinUs/list",{'page':1,'rows':5},function(result){
		if(result.resultCode == 0 && result.rows.length > 0){
				var rows = result.rows;
				var html = buildJobRow(rows);
				$('#wrap .hr-index .hr-index-item2 ul:eq(0)').append(html);
		}else{
			console.log(result.msg);
		}
	},'json');
}


function buildJobRow(rows){
	var html ='';
	for(var i=0; i< rows.length; i++){
		html += '<li><a href="/static/hr-x-inside.html?id='+rows[i].id+'">'+rows[i].position+'</a></li>';
	}
	return html;
}
 */
function jobNews(){
	$.post('/news/list',{'type':12,'page':1,'rows':5,'top':0},function(result){
		if(result.resultCode == 0){
			var rows = result.rows;
			var html = '';
			for(var i=0; i<rows.length; i++){
				var entity = rows[i];
				html += '<li><a href="/static/hr-d-inside.html?id='+entity.id+'">'+entity.title+'</a></li>'
			} 
			$('#wrap .hr-index .hr-index-item3 ul:eq(0)').append(html);
		}else{
			console.log(result.msg);
		}
	});
}