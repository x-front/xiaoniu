$(function(){
	console.log("about init");
	_xn_init();
});
function _xn_init(){
	getAbout();
	$.post("/pageInfo/who",function(result){
		if(result.resultCode == 0){
			if(result.who){
				$("#about-us p:eq(0)").html(result.who.introdution);
				$("#about-us img:eq(0)").attr('src',result.who.extCover);
			}
			
			if(result.advance){
				$("#about-j p:eq(0)").html(result.advance.introdution);
			}
			
			if(result.manager){
				$("#about-t p:eq(0)").html(result.manager.introdution);
			}
			
			if(result.princeple){
				$("#about-y p:eq(0)").html(result.princeple.introdution);
			}
			
			if(result.honor){
				$("#about-r p:eq(0)").html(result.honor.introdution);
			}
			if(result.culture){
				$("#about-w p:eq(0)").html(result.culture.introdution);
				$("#about-w .about-w-pic img:eq(0)").attr('src',result.culture.extCover);
			}
		}else{
			console.log(result.msg);
		}
	},"json");
	
}

function getAbout(){
	$.post("/media/list",{'page':1,'rows':6,'type':4},function(result){
		if(result.resultCode == 0){
			var rows = result.rows;
			var titleHtml = '';
			var liHtml = '';
			var coverHtml = '';
			var mediaHtml = '';
			for (var i = 0; i < rows.length; i++) {
				var row = rows[i];
				titleHtml += buildTitle(row.introdution);
				liHtml += buildLi(i);
				coverHtml += buildCover(row.extCover);
				mediaHtml += buildMedia(row.extCover);
			}
			$(".idV-pic-t:eq(0)").append(coverHtml);
			$(".idV-pic-c:eq(0)").append(liHtml);
			$(".idV-pic-b:eq(0)").append(titleHtml);
			$(".zz_video a:eq(0)").before(mediaHtml);
			videoPlay();
		}else{
			console.log(result.msg);
		}
	},"json");
}

function buildTitle(title){
	var html ='';
	html += '<li><a href="javascript:;"><p>'+title+'</p></a></li>';
	return html;
}

function buildLi(index){
	if(index == 0){
		return '<li class="active"><a href="javascript:;"></a></li>';
	}else{
		return '<li><a href="javascript:;"></a></li>';
	}
}

function buildCover(img){
	var html ='';
	html += '<li><a href="javascript:;">'
		+ '<img src="'+img+'"/>'
		+ '<span class="play_btn"><img src="/static/images/idV/play.png"/></span>'
		+ '</a></li>';
	return html;
}

function buildMedia(media){
	var html = '<video src="'+media+'" controls></video>';
	return html;
}