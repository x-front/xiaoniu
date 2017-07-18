$(function(){
	_xn_init();
});

function _xn_init(){
	getImageHeadList(1,6);
}

function seeMore(){
	var liCount = $(".pics_list li").length;
	var page = Math.ceil(liCount / 6);
	getImageHeadList(page+1,6);
}

function getImageHeadList(page,rows){
	$.post("/imageNews/queryImageHeadList",{"page":page,"rows":rows},function(result){
//		if(result.resultCode == 0){
			var total = result.total;
			var rows = result.rows;
			var imgHtml = "";
			for(var i = 0; i < rows.length; i++){
				var row = rows[i];
				imgHtml += '<li><a href="/static/pics_in.html?count=5&id='+row.id+'">'
					+ '<div class="pics_item">'
					+ '<div class="img img1">'
					+ '<img src="'+row.imgUrl1+'"/>'
					+ '</div>'
					+ '<div class="img img2">'
					+ '<img src="'+row.imgUrl2+'"/>'
					+ '</div>'
					+ '<div class="img img3">'
					+ '<img src="'+row.imgUrl3+'"/>'
					+ '</div>'
					+ '</div>'
					+ '<p>'+row.title+'</p>'
					+ '</a></li>'
			}
			$(".pics_list").append(imgHtml);
			if(total <=  page*rows){
				$("#news-t-more").css("display", "none");
			}
//		}else{
//			console.log('提示',result['msg']);
//		}
	},"json");
}