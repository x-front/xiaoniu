$(function(){
	_xn_init();
});
function _xn_init(){
	seeMore();
}

function seeMore(){
	var length = $('#qa-list-div dl').length;
	var page = Math.ceil(length/5);
	$.post("/news/newsList",{'type':14,'page':1+page,'rows':5,'top':0},function(result){
		if(result.resultCode == 0){
			var rows = result.rows;
			var html='';
			for(var i=0; i<rows.length; i++){
				var entity = rows[i];
				html += '<dl class="wow fadeInUp">'+entity.content+'</dl>';
			} 
			$("#qa-list-div").append(html);
			length = $('#qa-list-div dl').length;
			if(length >=  result.total){
				$('#seeMoreBtn').css("display",'none');
			}
		}else{
			console.log(result.msg);
		}
	},"json");
}

