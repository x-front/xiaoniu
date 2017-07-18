$(function(){
	_xn_init();
});

function _xn_init(){
	id=getParam("id");
	count = getParam("count");
	$.post("/imageNews/queryImageNews",{"id":id,"count":count},function(result){
		if(result.resultCode == 0){
			var head = result.data.hd;
			var imgs = result.data.imgs;
			var showtimes = result.data.dl;
			buildShowTime(showtimes);
			buildPreImg(imgs);
			buildText(imgs);
			buildTitle(head);
			$(".pics_img ul li:eq(0)").addClass("on");
			$(".pics_txt ul li:eq(0)").addClass("on");
			$(".pics_in_list ul li:eq(0)").addClass("on");
			picPlay();
		}else{
			console.log('提示',result['msg']);
		}
	},"json");
}

function buildShowTime(showtimes){
	var html = "";
	for(var i=0; i<showtimes.length; i++){
		var showtime = showtimes[i];
		var title = showtime["title"];
		var strDay = dateTools.LongTimeToSimpleFormatDate(showtime.showTime);
		strDay = strDay.substring(5, 10);
		var href = "/static/pics_in.html?count=5&id="+showtime['id'];
		html += '<li><a href="' + href+'">'+strDay+"</a>"+'<p>'+title+'</p></li>';
	}
	$(".num_nav ul").append(html);
}

function buildPreImg(imgs){
	var html = "";
	for (var i = 0; i < imgs.length; i++) {
		var img = imgs[i].image;
		html += '<li><img src="'+img+'"></li>';
	}
	$(".pics_img ul").append(html);
	$(".pics_in_list ul").append(html);
}

function buildText(imgs){
	var html = "";
	for (var i = 0; i < imgs.length; i++) {
		var content = imgs[i].content;
		html += '<li>'+content+"</li>";
	}
	$(".pics_txt ul").append(html);
}

function buildTitle(head){
	$("#pic-title").html(head.title);
}