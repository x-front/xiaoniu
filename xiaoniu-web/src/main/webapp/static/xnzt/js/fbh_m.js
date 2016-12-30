/**
 * Created by Administrator on 2016/12/12.
 */
$(function(){
    $(".txt .b").click(function(){
        var txt=$(this).parent().prev(".txt_in");
        var p=$(this).parent().prev(".txt_in").find("p");
        txt.css("height","auto");
        txt.parent(".txt").css("height","auto");
        $(this).siblings(".t").show();
        $(this).hide();

    });
    $(".txt .t").click(function(){
        var txt=$(this).parent().prev(".txt_in");
        txt.css("height","");
        txt.parent(".txt").css("height","");
        $(this).siblings(".b").show();
        $(this).hide();
    })
});
