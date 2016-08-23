<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>小牛思想与声音</title>
<jsp:include page="../public/common/head.jsp"></jsp:include>
<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/xiaoniu/CRUD.css'/>"/>
<link rel="stylesheet" href="/resources/kindeditor-4.1.10/themes/default/default.css" />
<script type="text/javascript" src="<c:url value='/resources/js/xiaoniu/dateTool.js'/>?r=1134"></script>
<script type="text/javascript" src="<c:url value='/resources/js/xiaoniu/common.js'/>?r=31"></script>
<script type="text/javascript" src="/resources/kindeditor-4.1.10/kindeditor-all-min.js"></script>
<script type="text/javascript" src="/resources/kindeditor-4.1.10/lang/zh_CN.js"></script>
<script type="text/javascript">
	var PluginUpload;
	$(function(){
		contentHeight = jQuery(window).height();
		showPageLoading();
		removePageLoading();
		KindEditor.ready(function(K) {
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
			
			$("#btn-media-upload").click(function(){
				PluginUpload.loadPlugin('media',function(){
					PluginUpload.plugin.imageDialog({
						imageUrl : $("#edit-div-media").textbox('getValue'),
						clickFn : function(url, title, width, height, border, align){
							$('#edit-div-media').textbox('setValue',url);
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
		$("#display-none-type").val(type);
		$("#edit-div-h2").html($("#main-div .content-h2:eq("+ index +")").html());
		
		$("#edit-div-introdution").textbox('setValue',$("#main-div .content-p:eq("+ index +")").html());
		$("#edit-div-banner").textbox('setValue',
				$("#main-div .content-img:eq("+ index +")").attr('src'));
		$('#edit-img-banner').attr('src',
				$("#main-div .content-img:eq("+ index +")").attr('src'));
		
		$("#edit-div-media").textbox('setValue',
				$("#main-div .content-img:eq("+ index +")").attr('media'));
		
		
		$("#main-div").addClass("none");
		$("#edit-div").removeClass("none");
	}
	
	function cancel(){
		$(".clear-easyui-textbox").textbox('setValue','');
		$(".clear-easyui-datetimebox").datetimebox('clear');
		$(".clear-easyui-combobox").combobox('clear');
		$(".clear-input").val('');
		
		$("#edit-media-showOrHide").addClass('none');
		$("#edit-banner-showOrHide").addClass('none');
		
		$('#edit-img-banner').addClass('none');
		
		$("#edit-div").addClass("none");
		$("#main-div").removeClass("none");
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
				$("#main-div .content-p:eq("+ index +")").html($("#edit-div-introdution").textbox('getValue'));
				$("#main-div .content-img:eq("+ index +")").attr('src',$("#edit-div-banner").textbox('getValue'));
				cancel();
			} else {
				$.messager.alert('提示',result['msg']);
			}
		});
	}
	
</script>
<style type="text/css">
	.float-right{float: right;}
	.content-p{
		font-size: 16px;
		line-height: 33px;
		color: #585858;
		width: 702px;
		max-width: 702px;
		letter-spacing: 2px;
		margin: 0 auto;
		padding-bottom: 42px;
	}
	#main-div{width: 1140px;margin: auto;}
	.content-div{margin: auto;}
	.description-div{text-align: center;margin: auto;border-bottom: 1px solid #eee;padding-top: 10px;padding-bottom: 10px;}
	.who-img{
		width: 908px;
		height: 393px;
	}
	.culture-img{
		width: 702px;
		height: 357px;
	}
	#edit-div{text-align: center;}
	#edit-div .textbox {margin-bottom:5px}
</style>
</head>
<body>
		
		<div id="main-div">
			<div class="content-div">
				<div class="description-div">
					<a href="javascript:void(0);" onclick="javascript:initUpdateWhoWindow(13,0)" class="easyui-linkbutton float-right" title="修改" plain="true" iconCls="icon-edit" id="addBtn">修改</a>
					<h2 class="content-h2">小牛思想声音</h2>
					<p class="content-p">${voice.introdution }</p>
					<img class="who-img content-img none" alt="" src="${voice.extCover }" media="${voice.extMedia }">
				</div>
				
				<div class="description-div">
					<a href="javascript:void(0);" onclick="javascript:initUpdateWhoWindow(14,1)" class="easyui-linkbutton float-right" title="修改" plain="true" iconCls="icon-edit" id="addBtn">修改</a>
					<h2 class="content-h2">小牛思想</h2>
					<p class="content-p">${thinking.introdution }</p>
					<img class="who-img content-img none" alt="" src="${thinking.extCover }" media="${thinking.extMedia }">
				</div>
				
				<div class="description-div">
					<a href="javascript:void(0);" onclick="javascript:initUpdateWhoWindow(15,2)" class="easyui-linkbutton float-right" title="修改" plain="true" iconCls="icon-edit" id="addBtn">修改</a>
					<h2 class="content-h2">小牛视频</h2>
					<p class="content-p">${video.introdution }</p>
					<img class="who-img content-img none" alt="" src="${video.extCover }" media="${video.extMedia }">
				</div>
				
				<div class="description-div">
					<a href="javascript:void(0);" onclick="javascript:initUpdateWhoWindow(30,3)" class="easyui-linkbutton float-right" title="修改" plain="true" iconCls="icon-edit" id="addBtn">修改</a>
					<h2 class="content-h2">小牛内刊</h2>
					<p class="content-p">${journal.introdution }</p>
					<img class="who-img content-img none" alt="" src="${journal.extCover }" media="${journal.extMedia }">
				</div>
				
			</div>
		</div>
		<!-- 添加 -->
		<div id="edit-div" class="none" >
			<form id="edit-form" method="post" action="/secure/pageIntrodution/save">
				<div class="">
					<h2 id="edit-div-h2" class="content-h2">小牛思想声音</h2>
					<input  id="edit-div-introdution" name="introdution" class="easyui-textbox clear-easyui-textbox" maxlength="512" required="true" data-options="multiline:true" prompt="描述" style="width: 703px;height: 158px;"/>
					<div class="none " id="edit-media-showOrHide">
						<input id="edit-div-media" name="extMedia" class="easyui-textbox clear-easyui-textbox"  prompt="宣传片" style="width:628px;">
						<input type="button" id="btn-media-upload"  value="选择视频"/>
					</div>
					<div class="none " id="edit-banner-showOrHide">
						<input id="edit-div-banner"  name="extCover" class="easyui-textbox clear-easyui-textbox"   prompt="封面图片" style="width:628px;"/>
						<input type="button" id="btn-banner-upload"  value="选择图片"/><br>
						<img id="edit-img-banner" alt="" src="" class="none" style="max-width: 908px;">
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
					<input id="display-none-type" name="type" class="clear-input">
					<input id="display-none-valid" name="valid" value="1">
					<input id="display-none-index" >
				</div>
			</form>
		</div>
		
</body>
</html>