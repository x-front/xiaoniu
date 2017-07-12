$(function(){
	_xn_init();
});

function _xn_init(){
	
}

function getImageHeadList(page,rows){
	$.post("/imageNews/queryImageHeadList",{"page":page,"rows":rows},function(result){
		if(result.resultCode == 0){
			var total = result.total;
			var rows = result.rows;
			var imgHtml = "";
			for(var i = 0; i < rows.length; i++){
				
			}
		}else{
			$.messager.alert('提示',result['msg']);
		}
	},"json");
}