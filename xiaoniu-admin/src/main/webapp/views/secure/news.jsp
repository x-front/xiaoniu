<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>管理员信息</title>
<jsp:include page="../public/common/head.jsp"></jsp:include>
<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/xiaoniu/CRUD.css'/>"/>
<link rel="stylesheet" href="/resources/kindeditor-4.1.10/themes/default/default.css" />
<script type="text/javascript" src="<c:url value='/resources/js/xiaoniu/dateTool.js'/>?r=1134"></script>
<script type="text/javascript" src="<c:url value='/resources/js/xiaoniu/common.js'/>?r=1134"></script>
<script type="text/javascript" src="/resources/kindeditor-4.1.10/kindeditor-all-min.js"></script>
<script type="text/javascript" src="/resources/kindeditor-4.1.10/lang/zh_CN.js"></script>
<script type="text/javascript">
	commonTable.loadDateURI = "/secure/news/queryList";
	commonTable.batchUpdateValidURI = "/secure/news/batchUpdateValid?strIds=";
	commonTable.batchDeleteURI = "/secure/news/batchDelete?strIds=";
	commonTable.updateURI = "/secure/news/update";
	commonTable.insertURI = "/secure/news/insert";
	commonTable.title = "文章列表";
	commonTable.columns = [
		{field:'ck',checkbox:true},
		{field:'id', title: 'ID',align:'center',  hidden:true},
		{field:'banner', title: '封面图',align:'center',  hidden:true},
		{field:'title',title: '标题', align:'center',width:200},
		{field:'source',title: '来源',align:'center'},
		{field:'summary',title: '摘要',align:'center',width:340},
		{field:'publishTime',title: '发布时间',align:'center'},
		{field:'clickTimes',title: '点击次数',align:'center'},
		publishColumn,
		createTimeColumn,
		updateTimeColumn,
		{field:'operator',title: '操作',align:'center',
			formatter: function(value,row,index){
					return "<a href='#' onclick='javascript:initUpdateNewsWindow("+index+")'>修改</a>";
				}
		},
	];
	
	var contextEditor;
	$(function(){
		showPageLoading();
		commonTable.init();
		removePageLoading();
		KindEditor.ready(function(K) {
			contextEditor = K.create('textarea[name="content"]', {
				cssPath : '/resources/kindeditor-4.1.10/plugins/code/prettify.css',
				uploadJson : '/secure/kindeditor/upload_json',
				fileManagerJson : '/secure/kindeditor/file_manager_json',
				allowFileManager : true,
				height:350,
				width:700
			});
		});
	});
	
	function initUpdateNewsWindow(index){
		var rows = $("#html_table").datagrid("getRows"),
		row = rows[index];
		$("#display-none-id").val(row.id);
		$("#edit-div-source").val(row.source);
		$("#edit-div-title").val(row.title);
		$("#edit-div-banner").val(row.banner);
		$("#edit-div-summary").val(row.summary);
		$("#edit-div-publishTime").val(row.publishTime);
		$("#edit-div-clickTimes").val(row.clickTimes);
		$("#edit-div-valid").combobox('setValue',row.valid);
		contextEditor.html(row.content);
		$("#edit-form").attr("action",commonTable.updateURI);
		$("#html_table").addClass("none");
		$("#edit-div").removeClass("none");
	}
	
	function initAddNewsWindow(){
		$("#html_table").hide();
		$("#html_table").addClass("none");
		$("#edit-div").removeClass("none");
		$("#edit-form").attr("action",commonTable.insertURI);
	}
	function cancel(){
		contextEditor.html('');
		$("#edit-div").addClass("none");
		$("#html_table").removeClass("none");
	}
	
	function save(){
		var $form = $("#edit-form");
		$("#edit-form .opt_btn").hide();
		$("#edit-form .loading").show();
		$.post($form.attr('action'),$form.serialize(),function(result){
			$("#edit-form .opt_btn").show();
			$("#edit-form .loading").hide();
			if ( result['resultCode'] == 0 ) {
				$("#edit-div").addClass("none");
				$("#html_table").removeClass("none");
				$("#html_table").datagrid("reload");
			} else {
				$.messager.alert('提示',result['msg']);
			}
		});
	}
	
</script>
</head>
<body>
		<div id="html_table" >
		</div>
		
		<!-- tool bar -->
		<div id="table_tb" style="padding:5px;height:auto" class="none">
			<a href="javascript:void(0);" onclick="javascript:initAddNewsWindow()"class="easyui-linkbutton" title="添加" plain="true" iconCls="icon-add" id="addBtn">添加</a>
			<a href="javascript:void(0);" onclick="javascript:commonTable.batchDelete()"class="easyui-linkbutton" title="删除" plain="true" iconCls="icon-cut" id="delBtn">删除</a>
		</div>
		
		<!-- 添加 -->
		<div id="edit-div" class="none">
			<form id="edit-form" method="post">
				<input type="text" id="edit-div-banner" name="banner" class="clear-input" placeholder="封面图"/>
				<input type="button" id="btn-banner-upload" value="选择图片"/>
				
				<input type="text" id="edit-div-title" name="title" class="clear-input" placeholder="标题"/>
				<input type="text" id="edit-div-source" name="source" class="clear-input" placeholder="来源"/>
				<input type="text" id="edit-div-publishTime" name="publishTime" class="clear-input" placeholder="发布时间"/>
				<input type="number" id="edit-div-clickTimes" name="clickTimes" class="clear-input" placeholder="点击次数"/>
				<select class="easyui-combobox" style="width:40%;text-align:center;" id="edit-div-valid" name="valid">
					<option value="0">提交后不发布</option>
					<option value="1">提交后直接发布</option>
				</select>
				
				<textarea rows="5" id="edit-div-summary" name="summary"></textarea>
				
				<textarea name="content" style="height:90%; visibility:hidden;"></textarea>
				<br>
				<div class="opt_btn"  style="text-align: center;padding-top: 10px;">
					<a class="easyui-linkbutton" id="import-form-submit-btn" iconCls="icon-ok" onclick="javascript:submit();">确定</a> 
					<a class="easyui-linkbutton" iconCls="icon-cancel" onclick="cancel();">取消</a>
				</div>
				<div class="loading display-none" style="text-align: center; padding-top: 10px; vertical-align:middle;">
					<img alt="" src="/resources/images/loading.gif" style="vertical-align:middle;">
					<span style="vertical-align:middle;">请稍后...</span>
				</div>
				
				<div id="display-none-input" class="none">
					<input id="display-none-id" name="id" class="clear-input">
				</div>
			</form>
		</div>
		
</body>
</html>