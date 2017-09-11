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
        $("#keyword").val(decodeURI(keyword));
        searchNews(lang,1,20);
    }
}
var searchResultTotalCount = 0;
function searchNews(lang,pageIndex,pageSize) {
    var keyword = $("#keyword").val();
    keyword = trim(keyword);
    if(keyword.length == 0){
        $(".search_list").html('');
        return;
    }
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
            var rows = result.rows;
            var html = buildRows(rows,lang);
            $(".search_list").html(html);
            searchResultTotalCount = result.total;
        }else{
            console.error(result.msg);
        }
    },"json");
}
function buildRows(rows,lang) {
    var html = '';
    for (var i=0; i<rows.length; i++){
        html += buildRow(rows[i],lang);
    }
    return html;
}
function buildRow(row,lang) {
    if(lang == 1){
        return '<a href="/static/en/news-m-inside.html?id=' + row.id + '" class="search_item"><h3>'+row.title+'</h3><p>'+row.summary+'</p></a>';
    }else{
        return '<a href="/static/news-m-inside.html?id=' + row.id + '" class="search_item"><h3>'+row.title+'</h3><p>'+row.summary+'</p></a>';
    }
}

function ltrim(s){
    return s.replace( /^\s*/, "");
}
//去右空格;
function rtrim(s){
    return s.replace( /\s*$/, "");
}
function trim(s){
    return rtrim(ltrim(s));
}