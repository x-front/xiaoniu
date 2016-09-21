$(function () {
    _xn_init();
});
function _xn_init() {
    $.post("/content/find", {id: 12}, function (a) {
        if (a.resultCode == 0) {
            var b = a.entity;
            $("#about-us").append(b.content);
            $("#about-us p span *").addClass("wow fadeInUp");
            sc();
        } else {
            console.log(a.msg)
        }
    }, "json")
}
function sc(){
    $("#about-us p span *:gt(5)").css("display","none");
    $(window).bind("scroll",function() {
        ($(document).scrollTop() + $(window).height() > $(document).height() - 50)&&db();
    });
}
var i=6;
function db(){
    $("#about-us p span *").eq(i).css("display","block");
    i++;
    if(i== ($("#about-us p span *").length-1)){
        $("#about-us p:last-child span").append("<p>亲，已经是最底部啦...</p>");
        $(window).unbind("scroll");
    }
}