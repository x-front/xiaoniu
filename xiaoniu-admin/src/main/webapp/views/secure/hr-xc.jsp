<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% String typeId = request.getParameter("typeId"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"><meta name="_csrf" content="${_csrf.token}"/> <meta name="_csrf_header" content="${_csrf.headerName}"/>
<title>信息管理</title>
<jsp:include page="../public/common/head.jsp"></jsp:include>
<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/xiaoniu/CRUD.css'/>"/>
<link rel="stylesheet" href="/resources/kindeditor-4.1.10/themes/default/default.css" />
<script type="text/javascript" src="/resources/kindeditor-4.1.10/kindeditor-all.js"></script>
<script type="text/javascript" src="/resources/kindeditor-4.1.10/lang/zh_CN.js"></script>
<style type="text/css">
#content hr{border:1px solid #eee;}
#content p{width: 702px;margin: 0 auto;letter-spacing: 2px;font-size: 16px;line-height: 33px;color: #585858;}
</style>
</head>
<body>
	<div id="main-box" class="none" style="width:702px;margin: auto;">
			<c:choose>
				<c:when test="${not empty content}">
					<div id="tool-bar">
						<a id="update-btn" class="easyui-linkbutton"  iconCls="icon-edit" onclick="javascript:update();">修改</a> 
						<hr>
					</div>
					<div id="content">
						${ content.content}
					</div>
				</c:when>
				<c:otherwise>
					<div id="tool-bar">
						<a id="update-btn" class="easyui-linkbutton"  iconCls="icon-add" onclick="javascript:update();">新增</a> 
						<hr>
					</div>
					<div id="content">
					</div>
				</c:otherwise>
			</c:choose>
		
		<div id="edit-div" class="none">
			<form action="/secure/content/save" id="edit-form">
				<textarea name="content" style="visibility:hidden;width: 100%"></textarea>
				<div class="opt_btn"  style="text-align: center;padding-top: 10px;">
					<a class="easyui-linkbutton" id="import-form-submit-btn" iconCls="icon-ok" onclick="javascript:submit();">确定</a> 
					<a class="easyui-linkbutton" iconCls="icon-cancel" onclick="cancel();">取消</a>
					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
				</div>
				<div class="loading none" style="text-align: center; padding-top: 10px; vertical-align:middle;">
					<img alt="" src="/resources/images/loading.gif" style="vertical-align:middle;">
					<span style="vertical-align:middle;">请稍后...</span>
				</div>
				<div id="display-none-input" class="none">
					<input id="display-none-id" name="id" value="${id}">
					<input id="display-none-type" name="type" value="${type}">
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
</script>
</html>