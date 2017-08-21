<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% String typeId = request.getParameter("typeId"); %>
<% String lang = request.getParameter("lang"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>信息管理</title>
<jsp:include page="../public/common/head.jsp"></jsp:include>
<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/xiaoniu/CRUD.css'/>"/>
<link rel="stylesheet" href="/resources/kindeditor-4.1.10/themes/default/default.css" />
<script type="text/javascript" src="/resources/kindeditor-4.1.10/kindeditor-all-min.js"></script>
<script type="text/javascript" src="/resources/kindeditor-4.1.10/lang/zh_CN.js"></script>
<style type="text/css">
	.btns a{text-align: center;text-decoration: none;display:inline-block;width:110px;height:50px;line-height: 50px;font-size: 16px;color:#FFF;border-radius: 5px;background: #ce2f10;margin-right: 10px;}
	.btns .btn2{background: #4863ff}
</style>
</head>
<body>
	<div id="main-box" class="none" style="width:702px;margin: auto;">
		<div class="btns">
			<!--lang-->
			<c:if test="${lang eq 1 }">
				<a class="btn1" href="javascript:jump(0,${type});">查看中文版</a>
			</c:if>
			<c:if test="${lang eq 0 }">
				<a class="btn1" href="javascript:jump(1,${type});">查看英文版</a>
			</c:if>

			<c:if test="${not empty content}">
				<a class="btn2 update-btn"   href="javascript:update();">修改</a>
			</c:if>
			<c:if test="${empty content}">
				<a class="btn2 update-btn"   href="javascript:update();">新增</a>
			</c:if>

		</div>
		<div id="content">
			<c:if test="${not empty content}">${ content.content}</c:if>
		</div>

		<div id="edit-div" class="none">
			<form action="/secure/content/save" id="edit-form">
				<textarea name="content" style="visibility:hidden;width: 100%"></textarea>
				<div class="opt_btn"  style="text-align: center;padding-top: 10px;">
					<a class="easyui-linkbutton" id="import-form-submit-btn" iconCls="icon-ok" onclick="javascript:submit();">确定</a> 
					<a class="easyui-linkbutton" iconCls="icon-cancel" onclick="cancel();">取消</a>
				</div>
				<div class="loading none" style="text-align: center; padding-top: 10px; vertical-align:middle;">
					<img alt="" src="/resources/images/loading.gif" style="vertical-align:middle;">
					<span style="vertical-align:middle;">请稍后...</span>
				</div>
				<div id="display-none-input" class="none">
					<input id="display-none-id" name="id" value="${id}">
					<input id="display-none-type" name="type" value="${type}">
					<input id="display-none-lang" name="lang" value="${lang}">
					<input id="display-none-terminal" name="terminal" value="0">
					<input id="display-none-valid" name="valid" value="1">
				</div>
			</form>
		</div>
			
	</div>	
</body>
<script type="text/javascript">
	var contextEditor,contentHeight;
	$(function(){
		contentHeight = jQuery(window).height();
		KindEditor.ready(function(K) {
			contextEditor = K.create('textarea[name="content"]', {
				cssPath : '/resources/kindeditor-4.1.10/plugins/code/prettify.css',
				uploadJson : '/secure/aliyunOss/upload_json',
				fileManagerJson : '/secure/aliyunOss/file_manager_json',
				allowFileManager : true,
				height:contentHeight - 100,
				afterBlur: function(){this.sync();}
			});
		});
		$("#main-box").removeClass("none");
	});
	
	function update(){
		var content = $("#content").html();
		$("#content").addClass("none");
		contextEditor.html(content);
		contextEditor.focus();
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

    function jump(lang,type){
        location.href='/secure/content/hr-j.html?type='+type+'&lang='+lang;
    }
</script>
</html>