$(function(){
	_xn_init();
});
function _xn_init(){
	/*$.post("/pageInfo/find",{'id':22},function(result){
		if(result.resultCode == 0){
			if(result.entity){
				$('.join-title:eq(0)').html(result.entity.introdution);
				$('.join-banner img:eq(0)').attr('src',result.entity.extCover);
			}
		}else{
			console.log(result.msg);
		}
	},"json");*/
	$.post("/news/newsList",{'type':13,'page':1,'rows':4,'top':0},function(result){
		if(result.resultCode == 0){
			var rows = result.rows;
			var html='';
			for(var i=0; i<rows.length; i++){
				var entity = rows[i];
				html += '<div class="hr-s-c">'+entity.content+'</div>';
			} 
			$("#about-us").append(html);
		}else{
			console.log(result.msg);
		}
	},"json");
}

