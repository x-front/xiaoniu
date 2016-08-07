<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% String typeId = request.getParameter("typeId"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>公司描述</title>
<jsp:include page="../public/common/head.jsp"></jsp:include>
<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/xiaoniu/CRUD.css'/>"/>
<link rel="stylesheet" href="/resources/kindeditor-4.1.10/themes/default/default.css" />
<script type="text/javascript" src="/resources/kindeditor-4.1.10/kindeditor-all-min.js"></script>
<script type="text/javascript" src="/resources/kindeditor-4.1.10/lang/zh_CN.js"></script>
<style type="text/css">
	#desc-name {
	    display: block;
	    font-size: 1.5em;
	    -webkit-margin-before: 0.83em;
	    -webkit-margin-after: 0.83em;
	    -webkit-margin-start: 0px;
	    -webkit-margin-end: 0px;
	    font-size: 24px;
	    line-height: 80px;
	    color: #a7810c;
	    font-weight: normal;
	    margin: 0;
	}
	#desc-name small {
	    font-size: 12px;
	    color: #6a6969;
	}
	#desc-banner{
		float: left;
    	margin-right: 88px;
    	margin-top: 15px;
	}
	#content p{
		line-height: 24px;
		padding-bottom: 22px;
		margin: 0;
	}
	
</style>
</head>
<body>
	<div id="main-box" class="none" style="width:1200px;margin: auto;">
			<c:choose>
				<c:when test="${not empty desc}">
					<div id="tool-bar">
						<a id="update-btn" class="easyui-linkbutton"  iconCls="icon-edit" onclick="javascript:update();">修改</a> 
						<hr>
					</div>
				</c:when>
				<c:otherwise>
					<div id="tool-bar">
						<a id="update-btn" class="easyui-linkbutton"  iconCls="icon-add" onclick="javascript:update();">新增</a> 
						<hr>
					</div>
				</c:otherwise>
			</c:choose>
			<div id="content">
				<img alt="" src="${desc.banner }" id="desc-banner">
				<h2 id="desc-name">${desc.name }<small id="desc-subName">&nbsp;&nbsp;&nbsp;${desc.subName }</small></h2>
				${desc.summary }
			</div>
		
		<div id="edit-div" class="none">
			<form action="/secure/desc/save" id="edit-form">
				<div id="div-banner">
					<input id="edit-div-banner" required="true" name="banner" class="easyui-textbox clear-easyui-textbox"  prompt="相片(158*209)"/>
					<input type="button" id="btn-banner-upload" value="选择图片"/>
					<img id="edit-img-banner" alt="" src="" class="none" style="width: 158px;height: 209px;">
				</div>
				
				<div id="div-title" >
					<input id="edit-div-name" name="name" required="true" class="easyui-textbox clear-easyui-textbox " maxlength="12" prompt="名字" style="width:204px"/>
					<input id="edit-div-subName" name="subName" class="easyui-textbox clear-easyui-textbox " maxlength="128" required="true" prompt="职位" style="width:490px"/>
					<textarea name="summary" style="visibility:hidden;width: 100%"></textarea>
				</div>
				
				<div class="opt_btn"  style="text-align: center;padding-top: 10px;">
					<a class="easyui-linkbutton" id="import-form-submit-btn" iconCls="icon-ok" onclick="javascript:submit();">确定</a> 
					<a class="easyui-linkbutton" iconCls="icon-cancel" onclick="cancel();">取消</a>
				</div>
				<div class="loading none" style="text-align: center; padding-top: 10px; vertical-align:middle;">
					<img alt="" src="/resources/images/loading.gif" style="vertical-align:middle;">
					<span style="vertical-align:middle;">请稍后...</span>
				</div>
				<div id="display-none-input" class="none">
					<input id="display-none-id" name="id" value="1">
					<input id="display-none-valid" name="valid" value="1">
				</div>
			</form>
		</div>
			
	</div>	
</body>
<script type="text/javascript">
	var contextEditor,contentHeight,PluginUpload;
	$(function(){
		contentHeight = jQuery(window).height();
		KindEditor.ready(function(K) {
			contextEditor = K.create('textarea[name="summary"]', {
				cssPath : '/resources/kindeditor-4.1.10/plugins/code/prettify.css',
				uploadJson : '/secure/aliyunOss/upload_json',
				fileManagerJson : '/secure/aliyunOss/file_manager_json',
				allowFileManager : true,
				height:contentHeight - 100,
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
		$("#main-box").removeClass("none");
	});
	
	function update(){
		$("#content").addClass("none");
		$("#edit-div").removeClass("none");
	}
	
	function submit(){
		$("#edit-div .opt_btn").hide();
		$("#edit-div .loading").show();
		var $form = $("#edit-form");
		$.post($form.attr('action'),$form.serialize(),function(result){
			if ( result['resultCode'] == 0 ) {
				$("#content").html(contextEditor.html());
				$("#edit-div").addClass("none");
				$("#content").removeClass("none");
				contextEditor.html('');
				$("#update-btn").linkbutton({
					text:'修改',
					iconCls:'icon-edit'
				});
			}else{
				$.messager.alert('提示',result['msg']);
			}
			$("#edit-div .opt_btn").show();
			$("#edit-div .loading").hide();
		},"json");
	}
	
	function cancel(){
		contextEditor.html('');
		$("#content").removeClass("none");
		$("#edit-div").addClass("none");
	}
</script>
</html>