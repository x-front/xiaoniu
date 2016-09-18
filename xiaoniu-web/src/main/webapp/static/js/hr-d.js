$(function(){
	_xn_init();
});
function _xn_init(){
	seeMore();
}



function seeMore(){
	var length = $('#about-us .hr-s-c').length;
	var page = Math.ceil(length / 10);
	$.post('/news/list',{'type':12,'page':1+page,'rows':10,'top':0},function(result){
		if(result.resultCode == 0){
			var rows = result.rows;
			var html = '';
			for(var i=0; i<rows.length; i++){
				var entity = rows[i];
				html += '<div class="hr-s-c"><a href="hr-d-inside.html?id='+entity.id+'"><p>'+entity.title+'<span>'+dateTools.LongTimeToSimpleFormatDate(entity.updateTime)+'</span></p></a></div>';
			} 
			$('#about-us').append(html);
			length = ('#about-us .hr-s-c').length;
			if(length >= result.total){
				$('#seeMoreBtn').css("display",'none');
			}
		}else{
			console.log(result.msg);
		}
	});
}