<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>核心竞争力</title>
<jsp:include page="../public/common/head.jsp"></jsp:include>
<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/xiaoniu/CRUD.css'/>"/>
<link rel="stylesheet" href="/resources/kindeditor-4.1.10/themes/default/default.css" />
<link rel="stylesheet" href="/resources/css/xiaoniu/advance.css?r=9" />
<script type="text/javascript" src="<c:url value='/resources/js/xiaoniu/dateTool.js'/>?r=1134"></script>
<script type="text/javascript" src="<c:url value='/resources/js/xiaoniu/common.js'/>?r=31"></script>
<script type="text/javascript" src="/resources/kindeditor-4.1.10/kindeditor-all-min.js"></script>
<script type="text/javascript" src="/resources/kindeditor-4.1.10/lang/zh_CN.js"></script>
<script type="text/javascript">
	var PluginUpload;
	var ContentEditor;
	$(function(){
		contentHeight = jQuery(window).height();
		showPageLoading();
		removePageLoading();
		KindEditor.ready(function(K) {
			ContentEditor= K.create('textarea[name="more"]',{
				cssPath : '/resources/kindeditor-4.1.10/plugins/code/prettify.css',
				uploadJson : '/secure/aliyunOss/upload_json',
				fileManagerJson : '/secure/aliyunOss/file_manager_json',
				allowFileManager : true,
				afterBlur: function(){this.sync();}
			});
			
			PluginUpload = K.editor({
				cssPath : '/resources/kindeditor-4.1.10/plugins/code/prettify.css',
				uploadJson : '/secure/aliyunOss/upload_json',
				fileManagerJson : '/secure/aliyunOss/file_manager_json',
				allowFileManager : true,
			});
			
			$("#btn-banner-upload").click(function(){
				PluginUpload.loadPlugin('image',function(){
					PluginUpload.plugin.imageDialog({
						imageUrl : $("#edit-div-banner").textbox('getValue'),
						clickFn : function(url, title, width, height, border, align){
							$('#edit-div-banner').textbox('setValue',url);
							$('#edit-img-banner').attr('src',url).removeClass('none');
							PluginUpload.hideDialog();
						}
					});
				});
			});
			
		});
	});
	
	function initUpdateWhoWindow(type,index){
		$("#display-none-index").val(index);
		$("#display-none-id").val(type);
		
		$("#edit-div-content").textbox('setValue',$("#main-div .description-div:eq("+index+") .content-p:eq(0)").html());
		$("#edit-div-banner").textbox('setValue',
				$("#main-div .description-div:eq("+index+") .content-img:eq(0)").attr('src'));
		$('#edit-img-banner').attr('src',
				$("#main-div .description-div:eq("+index+") .content-img:eq(0)").attr('src'));

		if($('#edit-img-banner').attr('src') != ""){
			$('#edit-img-banner').removeClass('none');
		}
		
		$('#edit-div-title').textbox('setValue',$('#main-div .description-div:eq('+index+') .content-title:eq(0)').html());
		
		var moreDiv = $('#main-div .description-div:eq('+index+') .more-div');
		if(moreDiv.length > 0 ){
			ContentEditor.html(moreDiv.html());
			ContentEditor.focus();
			$('#edit-more-showOrHide').removeClass('none');
		}
		
		var bannerDescDiv = $('#main-div .description-div:eq('+index+') .bannerDesc-p');
		if(bannerDescDiv.length > 0 ){
			$('#edit-div-bannerDesc').textbox('setValue',bannerDescDiv.html())
			$('#edit-bannerDesc-showOrHide').removeClass('none');
		}
		
		if(type > 54){
			$('#edit-content-showOrHide').removeClass('none');
			$('#edit-banner-showOrHide').removeClass('none');
		}
		
		if(type == 51 ){
			$('#edit-banner-showOrHide').removeClass('none');
		}
		
		
		$("#main-div").addClass("none");
		$("#edit-div").removeClass("none");
	}
	
	function cancel(){
		$(".clear-easyui-textbox").textbox('setValue','');
		$(".clear-easyui-datetimebox").datetimebox('clear');
		$(".clear-easyui-combobox").combobox('clear');
		$(".clear-input").val('');
		
		$('#edit-img-banner').addClass('none');
		$('#edit-bannerDesc-showOrHide').addClass('none');
		$('#edit-banner-showOrHide').addClass('none');
		$('#edit-more-showOrHide').addClass('none');
		$('#edit-content-showOrHide').addClass('none');
		
		$("#edit-div").addClass("none");
		$("#main-div").removeClass("none");
		
		ContentEditor.html('');
	}
	
	function save(){
		var $form = $("#edit-form");
		$("#edit-form .opt_btn").hide();
		$("#edit-form .loading").show();
		$.post($form.attr('action'),$form.serialize(),function(result){
			$("#edit-form .opt_btn").show();
			$("#edit-form .loading").hide();
			if ( result['resultCode'] == 0 ) {
				var index = $("#display-none-index").val();
				$("#main-div .description-div:eq("+index+") .content-p:eq(0)").html($("#edit-div-content").textbox('getValue'));
				$("#main-div .description-div:eq("+index+") .content-img:eq(0)").attr('src',$("#edit-div-banner").textbox('getValue'));
				if(index == 4){
					$('.description-div:eq('+ index +')').parent().css('background-image','url("'+$("#edit-div-banner").textbox('getValue')+'")');
				}
				var moreDiv = $('#main-div .description-div:eq('+index+') .more-div');
				if(moreDiv.length > 0 ){
					moreDiv.html(ContentEditor.html());
				}
				
				
				var bannerDescDiv = $('#main-div .description-div:eq('+index+') .bannerDesc-p');
				if(bannerDescDiv.length > 0 ){
					bannerDescDiv.html($('#edit-div-bannerDesc').textbox('getValue'));
				}
				
				var title = $('#edit-div-title').textbox('getValue');
				$("#main-div .description-div:eq("+index+") .content-title:eq(0)").html(title);
				
				cancel();
			} else {
				$.messager.alert('提示',result['msg']);
			}
		});
	}
	function moreDivToggle(div){
		$(div).parent().find('.more-div').toggle();
	}
</script>
<style type="text/css">
	.float-right{float: right;}
	.content-p{
		font-size: 16px;
		line-height: 33px;
		color: #585858;
		max-width: 602px;
		letter-spacing: 2px;
		padding-bottom: 42px;
		margin: 0 0;
	}
	#main-div{margin: auto;min-width: 1140px;}
	.content-div{margin: auto;}
	.description-div{width: 1140px;margin: auto;}
	#edit-div{text-align: center;}
	#edit-div .textbox {margin-bottom:5px}
	.p-title{max-width: 600px;float: left;}
	.p-banner{
		float: right;
		width: 165px;
	}
	.seeMore {
	    display: inline-block;
	    height: 25px;
	    line-height: 25px;
	    font-size: 15px;
	    color: #c09635;
	    letter-spacing: 1px;
	    position: relative;
	}
	
	.about-j7-img {
	    width: 305px;
	    float: left;
	    margin-left: 115px;
	    padding-top: 75px;
	}
	.title-float-right{
		padding: 115px 0 177px;
	    width: 36%;
	    float: right;
	}
	.about-j5, .about-j6 {
	    padding: 118px 0 200px;
	}
	.p5-float-right{
		width: 36%;
	    float: right;
	}
	.bannerDesc-p{
		width: 100%;
	    font-size: 12px;
	    line-height: 27px;
	    color: #c1b3a5;
	    letter-spacing: 0;
	}
	
	.p-banner img{
		margin-left: 40px;
	}
	
	.pp-div{
		margin-bottom:16px;
	    color: #000;
	}
</style>
</head>
<body>
		<div id="main-div">
			<div class="content-div">
				<div class="about-j1">
					<div class="description-div about-j1-l">
						<h2 class="content-title">${p1.title }</h2>
						<a href="javascript:void(0);" onclick="javascript:initUpdateWhoWindow(51,0)" class="easyui-linkbutton float-right" title="修改" plain="true" iconCls="icon-edit" id="addBtn">修改</a>
						<p class="content-p none">${p1.content }</p>
						<div id="p1-banner" class="p-banner">
							<img class="who-img content-img" alt="" src="${p1.banner }" >
							<p class="bannerDesc-p">${p1.bannerDesc }</p>
						</div>
					</div>
					
					<div class="description-div pp-div" id="pp1-div">
						<span class="content-title more-title" onclick="moreDivToggle(this)" >${pp1.title }</span>
						<a href="javascript:void(0);" onclick="javascript:initUpdateWhoWindow(52,1)" class="easyui-linkbutton float-right" title="修改" plain="true" iconCls="icon-edit" id="addBtn">修改</a>
						<p class="content-p none">${pp1.content }</p>
						<img class="who-img content-img none" alt="" src="${pp1.banner }" >
						<div class="more-div none">${pp1.more }</div>
					</div>
					
					<div class="description-div pp-div" id="pp2-div">
						<span class="content-title more-title" onclick="moreDivToggle(this)" >${pp2.title }</span>
						<a href="javascript:void(0);" onclick="javascript:initUpdateWhoWindow(53,2)" class="easyui-linkbutton float-right" title="修改" plain="true" iconCls="icon-edit" id="addBtn">修改</a>
						<p class="content-p none">${pp2.content }</p>
						<img class="who-img content-img none" alt="" src="${pp2.banner }" >
						<div class="more-div none">${pp2.more }</div>
					</div>
					
					
					<div class="description-div pp-div" id="pp3-div">
						<span class="content-title more-title" onclick="moreDivToggle(this)">${pp3.title }</span>
						<a href="javascript:void(0);" onclick="javascript:initUpdateWhoWindow(54,3)" class="easyui-linkbutton float-right" title="修改" plain="true" iconCls="icon-edit" id="addBtn">修改</a>
						<p class="content-p none">${pp3.content }</p>
						<img class="who-img content-img none" alt="" src="${pp3.banner }" >
						<div class="more-div none">${pp3.more }</div>
					</div>
					
				</div>
				
				<div class="clearfix"></div>
				
				<div style="background-image:url(${p2.banner});">
					<div class="description-div about-j2" >
						<h2 class="content-title">${p2.title }</h2>
						<a href="javascript:void(0);" onclick="javascript:initUpdateWhoWindow(61,4)" class="easyui-linkbutton float-right" title="修改" plain="true" iconCls="icon-edit" id="addBtn">修改</a>
						<p class="content-p">${p2.content }</p>
						<a href="javascript:void(0);" onclick="moreDivToggle(this)" class="seeMore">See more</a>
						<div class="more-div none">${p2.more }</div>
						<img class="who-img content-img none" alt="" src="${p2.banner }" ">
					</div>
				</div>
				
				<div class="description-div about-j3">
					<a href="javascript:void(0);" onclick="javascript:initUpdateWhoWindow(62,5)" class="easyui-linkbutton float-right" title="修改" plain="true" iconCls="icon-edit" id="addBtn">修改</a>
					<div id="p3-title" class="p-title">
						<h2 class="content-title">${p3.title }</h2>
						<p class="content-p">${p3.content }</p>
						<a href="javascript:void(0);" onclick="moreDivToggle(this)" class="seeMore">See more</a>
						<div class="more-div none">${p3.more }</div>
					</div>
					<div id="p3-banner" class="p-banner">
						<img class="who-img content-img" alt="" src="${p3.banner }">
						<p class="bannerDesc-p">${p3.bannerDesc }</p>
					</div>
				</div>
				
				<div class="clearfix"></div>
				<div class="description-div about-j4">
					<a href="javascript:void(0);" onclick="javascript:initUpdateWhoWindow(63,6)" class="easyui-linkbutton float-right" title="修改" plain="true" iconCls="icon-edit" id="addBtn">修改</a>
					<div class="p-title">
						<h2 class="content-title">${p4.title }</h2>
						<p class="content-p">${p4.content }</p>
						<a href="javascript:void(0);" onclick="moreDivToggle(this)" class="seeMore">See more</a>
						<div class="more-div none">${p4.more }</div>
					</div>
					<div class="p-banner">
						<img class="who-img content-img" alt="" src="${p4.banner }">
						<p class="bannerDesc-p">${p4.bannerDesc }</p>
					</div>
				</div>
				
				<div class="clearfix"></div>
				<div class="description-div about-j5" >
					<a href="javascript:void(0);" onclick="javascript:initUpdateWhoWindow(64,7)" class="easyui-linkbutton float-right" title="修改" plain="true" iconCls="icon-edit" id="addBtn">修改</a>
					<div class="p-title">
						<h2 class="content-title">${p5.title }</h2>
						<p class="content-p">${p5.content }</p>
					</div>
					<div class="p-banner">
						<img class="who-img content-img" alt="" src="${p5.banner }">
						<p class="bannerDesc-p">${p5.bannerDesc }</p>
					</div>
				</div>
				
				<div class="clearfix"></div>
				
				<div class="description-div about-j6" >
					<a href="javascript:void(0);" onclick="javascript:initUpdateWhoWindow(65,8)" class="easyui-linkbutton float-right" title="修改" plain="true" iconCls="icon-edit" id="addBtn">修改</a>
					<div class="p-title">
						<h2 class="content-title">${p6.title }</h2>
						<p class="content-p">${p6.content }</p>
					</div>
					<div class="p-banner">
						<img class="who-img content-img" alt="" src="${p6.banner }">
						<p class="bannerDesc-p">${p6.bannerDesc }</p>
					</div>
				</div>
				<div class="clearfix"></div>
				
				<div class="description-div about-j7">
					<a href="javascript:void(0);" onclick="javascript:initUpdateWhoWindow(66,9)" class="easyui-linkbutton float-right" title="修改" plain="true" iconCls="icon-edit" id="addBtn">修改</a>
					<div class="p-title">
						<h2 class="content-title">${p7.title }</h2>
						<p class="content-p">${p7.content }</p>
					</div>
					<div class="p-banner">
						<img class="who-img content-img" alt="" src="${p7.banner }">
						<p class="bannerDesc-p">${p7.bannerDesc }</p>
					</div>
				</div>
				
			</div>
		</div>
		<!-- 添加 -->
		<div id="edit-div" class="none" >
			<form id="edit-form" method="post" action="/secure/moreContent/save">
				<div class="">
					<input  id="edit-div-title" name="title" class="easyui-textbox clear-easyui-textbox" maxlength="255" required="true"  prompt="标题" style="width: 703px;"/>
					<div class="none " id="edit-content-showOrHide" >
						<input  id="edit-div-content" name="content" class="easyui-textbox clear-easyui-textbox" maxlength="512" data-options="multiline:true" prompt="描述" style="width: 703px;height: 158px;"/>
					</div>
					<div class="none " id="edit-banner-showOrHide" >
						<input id="edit-div-banner"  name="banner" class="easyui-textbox clear-easyui-textbox"   prompt="背景图片" style="width:628px;"/>
						<input type="button" id="btn-banner-upload"  value="选择图片"/><br>
						<img id="edit-img-banner" alt="" src="" class="none" style="max-width: 908px;">
					</div>
					
					<div class="none " id="edit-bannerDesc-showOrHide">
						<span>图片描述：</span><input  id="edit-div-bannerDesc" name="bannerDesc" class="easyui-textbox clear-easyui-textbox" maxlength="512" data-options="multiline:true" prompt="图片描述" style="width: 660px;height: 148px;"/>
					</div>
					
					<div class="none " id="edit-more-showOrHide" style="width:772px;margin: auto;text-align: left;">
						<br>
						<div style="background-color: yellow;">下面的编辑器是编辑See more里面的内容</div>
						<textarea  id="edit-div-more" name="more" style="width: 769px;height: 358px;"></textarea>
					</div>
					
				</div>
				
				<div class="opt_btn"  style="text-align: center;padding-top: 20px;">
					<a class="easyui-linkbutton" id="import-form-submit-btn" iconCls="icon-ok" onclick="javascript:save();">确定</a> 
					<a class="easyui-linkbutton" iconCls="icon-cancel" onclick="cancel();">取消</a>
				</div>
				<div class="loading none" style="text-align: center; padding-top: 20px; vertical-align:middle;">
					<img alt="" src="/resources/images/loading.gif" style="vertical-align:middle;">
					<span style="vertical-align:middle;">请稍后...</span>
				</div>
				
				<div id="display-none-input" class="none">
					<input id="display-none-id" name="id" class="clear-input">
					<input id="display-none-type" name="type" value="1">
					<input id="display-none-valid" name="valid" value="1">
					<input id="display-none-index" >
				</div>
			</form>
		</div>
		
</body>
</html>