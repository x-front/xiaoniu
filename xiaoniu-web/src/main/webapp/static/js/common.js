/*!
 * 小牛资本
 * @Author http://www.lonwin.net huabin.
 * @Copyright 2016
 */$(function(){$(".menu").click(function(){$(".sidebar").animate({right:0},500);$(this).animate({opacity:0},500);if($(window).width()>500){$(".nav li").eq(0).css({animation:"mLi .5s .3s",opacity:"1",marginLeft:"0"});$(".nav li").eq(1).css({animation:"mLi .5s .33s",opacity:"1",marginLeft:"0"});$(".nav li").eq(2).css({animation:"mLi .5s .36s",opacity:"1",marginLeft:"0"});$(".nav li").eq(3).css({animation:"mLi .5s .39s",opacity:"1",marginLeft:"0"});$(".nav li").eq(4).css({animation:"mLi .5s .42s",opacity:"1",marginLeft:"0"});$(".nav li").eq(5).css({animation:"mLi .5s .45s",opacity:"1",marginLeft:"0"});$(".nav li").eq(6).css({animation:"mLi .5s .48s",opacity:"1",marginLeft:"0"});$(".search").css({animation:"mLi .5s .51s",opacity:"1",marginLeft:"0"});$(".share").css({animation:"mLi .5s .54s",opacity:"1",marginLeft:"0"});$(".ce").css({animation:"mLi .5s .57s",opacity:"1",marginLeft:"0"});$(".copy").css({animation:"mLi .5s .6s",opacity:"1",marginLeft:"0"});$(".scan").css({animation:"mLi .5s .63s",opacity:"1",marginLeft:"0"})}});$(".s2").click(function(){$(this).addClass("s2-active").siblings().removeClass("s2-active")});$(".sidebar .close").click(function(){$(".menu").animate({opacity:1},500);$(".sidebar").animate({right:"-100%"},500);if($(window).width()>500){$(".nav li").css({opacity:"0",marginLeft:"200px"});$(".search").css({opacity:"0",marginLeft:"200px"});$(".share").css({opacity:"0",marginLeft:"200px"});$(".ce").css({opacity:"0",marginLeft:"200px"});$(".copy").css({opacity:"0",marginLeft:"200px"});$(".scan").css({opacity:"0",marginLeft:"200px"})}});$("#audio-more").click(function(){$(".audio-ul").css("display","block")});$("#idea-more").click(function(){$(".idea-ul").css("display","block")});$("#news-t-more").click(function(){$(".news-t-ul").css("display","block")});$("#news-m-more").click(function(){$(".news-m-ul").css("display","block")});$("#join-more").click(function(){$(".join-ul").css("display","block")});$(".about-j1-l a").toggle(function(){$(this).css({color:"#c19a3f",background:"url(images/about/about-jt_06.jpg) no-repeat right center"}).next().slideDown(500)},function(){$(this).css({color:"#000",background:"url(images/about/about-jt_11.jpg) no-repeat right center"}).next().slideUp(500)});$(".about-j2 a").click(function(){$(".about-j2-pic").slideToggle(500)});$(".about-j3 a").click(function(){$(".about-j3-pic").slideToggle(500)});$("#about-j4 a").click(function(){$(".about-j4-pic").slideToggle(500)});$(".about-j5 a").click(function(){$(".about-j5-pic").slideToggle(500)});$(".about-j6 a").click(function(){$(".about-j6-pic").slideToggle(500)});$(".about-j7 a").click(function(){$(".about-j7-pic").slideToggle(500)});if($(window).width()<=500){$(".hr-banner img").attr("src","images/hr/hr-banner_03.jpg");}})

