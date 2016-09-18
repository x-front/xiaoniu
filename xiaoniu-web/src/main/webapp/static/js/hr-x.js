$(function(){
	_xn_init();
});
function _xn_init(){
	$(".hr-x-address a").click(function(){
		$(".hr-x-address span").removeClass('a-active');
		$(this).parent("span").addClass('a-active');
		typeSearch();
	});
	$(".hr-x-work a").click(function(){
		$(".hr-x-work span").removeClass('a-active');
		$(this).parent("span").addClass('a-active');
		typeSearch();
	});
	seeMore();
}

function typeSearch(){
	$('.hr-x-main table tr:gt(0)').remove();
	seeMore();
}

function seeMore(){
	var length = $('.hr-x-main table tr').length - 1;
	var page = Math.ceil(length/10);
	var type = $('.hr-x-work .a-active a').attr('type');
	var address = $('.hr-x-address .a-active a').attr('address');
	var position = $("#position-input").val();
	$.post("/joinUs/queryJoinUsList",{'page':page + 1,'rows':10,'type':type,'address':address,'position':position},function(result){
		if(result.total > 0 && result.rows.length>0){
				var rows = result.rows;
				var html = buildJobRow(rows);
				$('.hr-x-main table').append(html);
				length = $('.hr-x-main table tr').length - 1;
				if(length >= result.total){
					$('#seeMoreBtn').css("display",'none');
				}
		}else{
			console.log(result.msg);
		}
	},'json');
}

function buildJobRow(rows){
	var html ='';
	for(var i=0; i< rows.length; i++){
		html += '<tr><td><input type="checkbox"><a href="hr-x-inside.html?id='+rows[i].id+'">'
				+ rows[i].position +'</a></td><td>'+rows[i].count+'</td><td>'+jobType(rows[i].type)
				+'</td><td>'+rows[i].address+'</td><td>'+dateTools.LongTimeToSimpleFormatDate(rows[i].updateTime)+'</td></tr>'
	}
	return html;
}

function jobType(type){
	var result ='';
	switch(type){
	case 1:result = '技术类';break;
	case 2:result = '市场类';break;
	case 3:result = '投资类';break;
	case 4:result = '风控类';break;
	case 5:result = '销售类';break;
	case 6:result = '职能类';break;
	}
	return result;
}

