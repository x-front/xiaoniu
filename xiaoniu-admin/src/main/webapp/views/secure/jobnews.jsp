<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>招聘动态</title>
<jsp:include page="../public/common/head.jsp"></jsp:include>
<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/xiaoniu/CRUD.css'/>"/>
<link rel="stylesheet" href="/resources/kindeditor-4.1.10/themes/default/default.css" />
<script type="text/javascript" src="<c:url value='/resources/js/xiaoniu/dateTool.js'/>?r=1134"></script>
<script type="text/javascript" src="<c:url value='/resources/js/xiaoniu/common.js'/>?r=44"></script>
<script type="text/javascript" src="/resources/kindeditor-4.1.10/kindeditor-all-min.js"></script>
<script type="text/javascript" src="/resources/kindeditor-4.1.10/lang/zh_CN.js"></script>
<script type="text/javascript">
	commonTable.loadDateURI = "/secure/news/queryList";
	commonTable.batchUpdateValidURI = "/secure/news/batchUpdateValid?strIds=";
	commonTable.batchDeleteURI = "/secure/news/batchDelete?strIds=";
	commonTable.updateURI = "/secure/news/update";
	commonTable.insertURI = "/secure/news/insert";
	commonTable.title = "招聘动态列表";
	commonTable.tableQueryParams = {
			orderBy:'serial_number asc,id desc',
			type:12
	}
	commonTable.columns = [
		{field:'ck',checkbox:true},
		{field:'id', title: 'ID',align:'center',  hidden:true},
		{field:'title',title: '标题', align:'center',width:200},
		{field:'summary',title: '摘要',align:'center',width:340},
		publishTimeColumn,
		{field:'serialNumber',title: '序号',align:'center'},
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
	var PluginUpload;
	var contentHeight;
	var type = 12;
	$(function(){
		contentHeight = jQuery(window).height();
		showPageLoading();
		commonTable.init();
		removePageLoading();
		KindEditor.ready(function(K) {
			contextEditor = K.create('textarea[name="content"]', {
				cssPath : '/resources/kindeditor-4.1.10/plugins/code/prettify.css',
				uploadJson : '/secure/aliyunOss/upload_json',
				fileManagerJson : '/secure/aliyunOss/file_manager_json',
				allowFileManager : true,
				height:contentHeight - 200,
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
	
	function initUpdateNewsWindow(index){
		var rows = $("#html_table").datagrid("getRows"),
		row = rows[index];
		$("#display-none-id").val(row.id);
		$('#display-none-type').val(row.type);
		$("#edit-div-source").textbox('setValue',row.source);
		$("#edit-div-title").textbox('setValue',row.title);
		$("#edit-div-summary").textbox('setValue',row.summary);
		if(row.publishTime){
			$("#edit-div-publishTime").datetimebox('setValue',dateTools.LongTimeToDateString(row.publishTime));
		}
		$("#edit-div-clickTimes").numberbox('setValue',row.clickTimes);
		$("#edit-div-serialNumber").numberbox('setValue',row.serialNumber);
		$("#edit-div-valid").combobox('setValue',row.valid);
		contextEditor.html(row.content);
		contextEditor.focus();
		$("#edit-form").attr("action",commonTable.updateURI);
		$(".datagrid").addClass("none");
		$("#edit-div").removeClass("none");
	}
	
	function initAddNewsWindow(){
		$('#display-none-type').val(type);
		$(".datagrid").addClass("none");
		$("#edit-div").removeClass("none");
		$("#edit-form").attr("action",commonTable.insertURI);
	}
	function cancel(){
		contextEditor.html('');
		$(".clear-easyui-textbox").textbox('setValue','');
		$(".clear-easyui-datetimebox").datetimebox('clear');
		$(".clear-easyui-combobox").combobox('clear');
		$(".clear-easyui-numberbox").numberbox('clear');
		$(".clear-input").val('');
		$('#edit-img-banner').addClass('none');
		$("#edit-div").addClass("none");
		$(".datagrid").removeClass("none");
	}
	
	function save(){
		var $form = $("#edit-form");
		$("#edit-form .opt_btn").hide();
		$("#edit-form .loading").show();
		$.post($form.attr('action'),$form.serialize(),function(result){
			$("#edit-form .opt_btn").show();
			$("#edit-form .loading").hide();
			if ( result['resultCode'] == 0 ) {
				$("#html_table").datagrid("reload");
				cancel();
			} else {
				$.messager.alert('提示',result['msg']);
			}
		});
	}
	
</script>
<style type="text/css">
	#edit-div{width: 1000px;margin: auto;margin-top: 10px;}

	#div-banner{
		height: 150px;width: 255px;float: left;overflow: hidden;text-align: center;
		vertical-align: middle;
	}
	#edit-div-banner{width:170px;}
	
	#div-content-info{height: 150px;width: 255px;float: left;text-align: right;overflow: hidden;}
	#div-content-info input{margin-bottom: 5px;width: 170px;}
	#div-content-info select{width: 174px;}
	#div-title{height: 150px;width: 540px;float: right;}
	#edit-div .textbox {margin-bottom:5px}
</style>
</head>
<body>
		<div id="html_table" >
		</div>
		
		<!-- tool bar -->
		<div id="table_tb" style="padding:5px;height:auto" class="none">
			<a href="javascript:void(0);" onclick="javascript:initAddNewsWindow()"class="easyui-linkbutton" title="添加" plain="true" iconCls="icon-add" id="addBtn">添加</a>
			<a href="javascript:void(0);" onclick="javascript:commonTable.batchDelete()"class="easyui-linkbutton" title="删除" plain="true" iconCls="icon-cancel" id="delBtn">删除</a>
			<a href="javascript:void(0);" onclick="javascript:commonTable.batchPublish()"class="easyui-linkbutton" title="发布" plain="true" iconCls="icon-ok">发布</a>
			<a href="javascript:void(0);" onclick="javascript:commonTable.batchCancelPublish()"class="easyui-linkbutton" title="撤销" plain="true" iconCls="icon-undo">撤销发布</a>
		</div>
		
		<!-- 添加 -->
		<div id="edit-div" class="none" >
			<form id="edit-form" method="post">
				<div id="div-content-info" >
					<span>发布时间:</span><input id="edit-div-publishTime" name="publishTime" required="true" class="easyui-datetimebox clear-easyui-datetimebox " prompt="发布时间"/><br>
					<span>序号:</span><input id="edit-div-serialNumber" name="serialNumber" required="true" class="easyui-numberbox clear-easyui-numberbox " prompt="序号(越大排序越靠前)"/><br>
					<span>发布状态:</span><select class="easyui-combobox" required="true" id="edit-div-valid" name="valid">
						<option value="0">提交后不发布</option>
						<option value="1">提交后直接发布</option>
					</select>
				</div>
				<div id="div-title" >
					<span>标题:</span><input id="edit-div-title" name="title" class="easyui-textbox clear-easyui-textbox " required="true" prompt="标题" style="width:490px"/><br>
					<span>摘要:</span><input  id="edit-div-summary" name="summary" class="easyui-textbox clear-easyui-textbox" required="true" data-options="multiline:true" prompt="摘要" style="width:488px;;height: 100px;"/>
				</div>
				
				<div>
					<textarea name="content" style="height:400px;width:100%; visibility:hidden;"></textarea>
				</div>
				
				<div class="opt_btn"  style="text-align: center;padding-top: 10px;">
					<a class="easyui-linkbutton" id="import-form-submit-btn" iconCls="icon-ok" onclick="javascript:save();">确定</a> 
					<a class="easyui-linkbutton" iconCls="icon-cancel" onclick="cancel();">取消</a>
				</div>
				<div class="loading display-none" style="text-align: center; padding-top: 10px; vertical-align:middle;">
					<img alt="" src="/resources/images/loading.gif" style="vertical-align:middle;">
					<span style="vertical-align:middle;">请稍后...</span>
				</div>
				
				<div id="display-none-input" class="none">
					<input id="display-none-id" name="id" class="clear-input">
					<input id="display-none-type" name="type" class="clear-input">
					<input id="display-none-top" name="isTop" class="" value="0">
					<input id="display-none-clickTimes" name="clickTimes" value="0"/>
				</div>
			</form>
		</div>
		
</body>
</html>