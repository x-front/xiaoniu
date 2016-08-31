$(function(){
	_xn_init();
});
function _xn_init(){
	var id = getParam('id');
	$.post("/news/find",{'id':id},function(result){
		if(result.resultCode == 0){
			if(result.entity){
				$('.news-item1 .audio-title h3:eq(0)').html(result.entity.title);
				$('.news-item1 .audio-title span:eq(0)').html('文章来源：'+result.entity.source+'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 浏览次数：'+result.entity.clickTimes);
				var content = result.entity.content;
				content = content.replace(/\<embed/g,'<video controls="controls" autoplay="autoplay" ');
				$('.news-item2:eq(0)').html(content);
			}
		}else{
			console.log(result.msg);
		}
	},"json");
}