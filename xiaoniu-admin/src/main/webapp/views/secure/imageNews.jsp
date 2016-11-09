<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>首页新闻</title>
<jsp:include page="../public/common/head.jsp"></jsp:include>
<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/xiaoniu/CRUD.css'/>"/>
<script type="text/javascript" src="<c:url value='/resources/js/xiaoniu/dateTool.js'/>?r=1134"></script>
<script type="text/javascript" src="<c:url value='/resources/js/xiaoniu/common.js'/>?r=31"></script>
<script type="text/javascript">
	commonTable.loadDateURI = "/secure/imageNews/queryList";
	commonTable.batchUpdateValidURI = "/secure/imageNews/batchUpdateValid?strIds=";
	commonTable.batchDeleteURI = "/secure/imageNews/batchDelete?strIds=";
	commonTable.updateURI = "/secure/imageNews/save";
	commonTable.insertURI = "/secure/imageNews/save";
	commonTable.title = "新闻图集";
	commonTable.nowrap = false;
	commonTable.tableQueryParams = {
			orderBy:'id desc'
	}
	commonTable.columns = [
		{field:'ck',checkbox:true},
		{field:'id', title: 'ID',align:'center',  hidden:true},
		{field:'newsId',title: '新闻ID',align:'left'},
		{field:'image',title: '图片连接',align:'left',width:340},
		{field:'serialNumber',title: '序号',align:'center'},
		validColumn,
		createTimeColumn,
		updateTimeColumn,
		{field:'operator',title: '操作',align:'center',
			formatter: function(value,row,index){
				return "<a href='#' onclick='javascript:commonTable.initUpdateWindow("+index+")'>修改</a>";
				}
		},
	];
	
	commonTable.beforeInitUpdateWindow = function(index){
		var rows = $("#html_table").datagrid("getRows"),
		row = rows[index];
		$("#edit_form_id").val(row.id);
		$("#edit_form_title").textbox('setValue',row.title);
		$("#edit_form_serialNumber").numberbox('setValue',row.serialNumber);
		$("#edit_form_valid").combobox('setValue',row.valid);
		$("#edit-form").attr("action",commonTable.updateURI);
	};
	
	$(function(){
		showPageLoading();
		commonTable.defineAddWindow(460, 290);
		commonTable.init();
		removePageLoading();
	});
	
	
</script>
</head>
<body>
		<div id="html_table" >
		</div>
		
		<!-- tool bar -->
		<div id="table_tb" style="padding:5px;height:auto" class="none">
			<!-- <a href="javascript:void(0);" onclick="javascript:commonTable.initAddWindow()"class="easyui-linkbutton" title="添加" plain="true" iconCls="icon-add" id="addBtn">添加</a> -->
			<a href="javascript:void(0);" onclick="javascript:commonTable.batchDelete()"class="easyui-linkbutton" title="删除" plain="true" iconCls="icon-cancel" id="delBtn">删除</a>
			<a href="javascript:void(0);" onclick="javascript:commonTable.batchPublish()"class="easyui-linkbutton" title="发布" plain="true" iconCls="icon-ok">发布</a>
			<a href="javascript:void(0);" onclick="javascript:commonTable.batchCancelPublish()"class="easyui-linkbutton" title="撤销" plain="true" iconCls="icon-undo">撤销发布</a>
		</div>
		
		<!-- 添加 -->
		<div id="edit-div" class="none" >
			<form id="edit-form" method="post">
			
				<div></div>
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
					<input id="display-none-top" name="top" class="" value="0">
				</div>
			</form>
		</div>
</body>
</html>