$(function(){
    _xn_init();
});
function _xn_init(){
    var keyword= getParam("kw");
    var lang = getParam("lang");
    if(!lang){
        lang = 0;
    }
    if(keyword){
        $("#keyword").val(keyword);
        searchNews(lang,1,20);
    }
}
var searchResultTotalCount = 0;
function searchNews(lang,pageIndex,pageSize) {
    var keyword = $("#keyword").val();
    if(pageIndex == 1){
        searchResultTotalCount = 0;
    }
    $.post("/news/search",{
        'page':pageIndex,
        'rows':pageSize,
        'totalCount':searchResultTotalCount,
        'lang':lang,
        'keyword':keyword},function (result) {
        if ( result.resultCode == 0 ){
            searchResultTotalCount = result.total;
            var rows = result.rows;
            var html = buildRows(rows);
            $(".search_list").html(html);
        }else{
            console.error(result.msg);
        }
    },"json");
}
function buildRows(rows) {
    var html = '';
    for (var i=0; i<rows.length; i++){
        html += buildRow(rows[i]);
    }
    return html;
}
function buildRow(lang,row) {
    if(lang == 1){
        return '<a href="/static/en/news-m-inside.html?id=' + row.id + '" class="search_item"><h3>'+row.title+'</h3><p>'+row.summary+'</p></a>';
    }else{
        return '<a href="/static/news-m-inside.html?id=' + row.id + '" class="search_item"><h3>'+row.title+'</h3><p>'+row.summary+'</p></a>';
    }
}