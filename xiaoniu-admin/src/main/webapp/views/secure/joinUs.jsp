<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% String type = request.getParameter("type"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>招聘职位</title>
<jsp:include page="../public/common/head.jsp"></jsp:include>
<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/xiaoniu/CRUD.css'/>"/>
<link rel="stylesheet" href="/resources/kindeditor-4.1.10/themes/default/default.css" />
<script type="text/javascript" src="<c:url value='/resources/js/xiaoniu/dateTool.js'/>?r=1134"></script>
<script type="text/javascript" src="<c:url value='/resources/js/xiaoniu/common.js'/>?r=31"></script>
<script type="text/javascript" src="/resources/kindeditor-4.1.10/kindeditor-all-min.js"></script>
<script type="text/javascript" src="/resources/kindeditor-4.1.10/lang/zh_CN.js"></script>
<script type="text/javascript">
	commonTable.loadDateURI = "/secure/joinUs/queryList";
	commonTable.batchUpdateValidURI = "/secure/joinUs/batchUpdateValid?strIds=";
	commonTable.batchDeleteURI = "/secure/joinUs/batchDelete?strIds=";
	commonTable.updateURI = "/secure/joinUs/update";
	commonTable.insertURI = "/secure/joinUs/insert";
	commonTable.title = "招聘职位列表";
	commonTable.nowrap = false;
	commonTable.tableQueryParams = {
			orderBy:'serial_number asc,id desc'
	}
	commonTable.columns = [
		{field:'ck',checkbox:true},
		{field:'id', title: 'ID',align:'center',  hidden:true},
		{field:'position',title: '职位',align:'center',width:120},
		{field:'summary',title: '描述',align:'left',width:340},
		{field:'serialNumber',title: '序号',align:'center'},
		validColumn,
		createTimeColumn,
		updateTimeColumn,
		{field:'operator',title: '操作',align:'center',
			formatter: function(value,row,index){
					return "<a href='#' onclick='javascript:initUpdatejoinUsWindow("+index+")'>修改</a>";
				}
		},
	];
	
	var contextEditor;
	var contentHeight;
	var type = <%=type%>;
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
				height:contentHeight - 310,
				afterBlur: function(){this.sync();}
			});
			
		});
	});
	
	function initUpdatejoinUsWindow(index){
		var rows = $("#html_table").datagrid("getRows"),
		row = rows[index];
		$("#display-none-id").val(row.id);
		$("#edit-div-position").textbox('setValue',row.position);
		$("#edit-div-summary").textbox('setValue',row.summary);
		$("#edit-div-serialNumber").numberbox('setValue',row.serialNumber);
		$("#edit-div-valid").combobox('setValue',row.valid);
		contextEditor.html(row.content);
		contextEditor.focus();
		$("#edit-form").attr("action",commonTable.updateURI);
		$(".datagrid").addClass("none");
		$("#edit-div").removeClass("none");
	}
	
	function initAddjoinUsWindow(){
		$(".datagrid").addClass("none");
		$("#edit-div").removeClass("none");
		$("#edit-form").attr("action",commonTable.insertURI);
	}
	function cancel(){
		$(".clear-easyui-textbox").textbox('setValue','');
		$(".clear-easyui-datetimebox").datetimebox('clear');
		$(".clear-easyui-combobox").combobox('clear');
		$(".clear-input").val('');
		$('#edit-img-banner').addClass('none');
		$("#edit-div").addClass("none");
		$(".datagrid").removeClass("none");
		contextEditor.html('');
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
	#edit-div{width: 770px;margin: auto;margin-top: 30px;}

	#edit-div .textbox {margin-bottom:5px}
</style>
</head>
<body>
		<div id="html_table" >
		</div>
		
		<!-- tool bar -->
		<div id="table_tb" style="padding:5px;height:auto" class="none">
			<a href="javascript:void(0);" onclick="javascript:initAddjoinUsWindow()"class="easyui-linkbutton" title="添加" plain="true" iconCls="icon-add" id="addBtn">添加</a>
			<a href="javascript:void(0);" onclick="javascript:commonTable.batchDelete()"class="easyui-linkbutton" title="删除" plain="true" iconCls="icon-cancel" id="delBtn">删除</a>
			<a href="javascript:void(0);" onclick="javascript:commonTable.batchPublish()"class="easyui-linkbutton" title="发布" plain="true" iconCls="icon-ok">发布</a>
			<a href="javascript:void(0);" onclick="javascript:commonTable.batchCancelPublish()"class="easyui-linkbutton" title="撤销" plain="true" iconCls="icon-undo">撤销发布</a>
		</div>
		
		<!-- 添加 -->
		<div id="edit-div" class="none" >
			<form id="edit-form" method="post">
				
				<div id="div-title" >
					<input id="edit-div-position" name="position" class="easyui-textbox clear-easyui-textbox" prompt="职位" required="true" style="width:770px;">
					<select class="easyui-combobox clear-easyui-combobox" required="true" id="edit-div-valid" name="valid" style="width:270px;">
						<option value="0">提交后不发布</option>
						<option value="1">提交后直接发布</option>
					</select>
					<input id="edit-div-serialNumber" name="serialNumber" required="true" class="easyui-numberbox clear-easyui-numberbox " prompt="序号(越小排序越靠前)" style="width:490px"/>
					<input  id="edit-div-summary" name="summary" class="easyui-textbox clear-easyui-textbox" maxlength="512" required="true" data-options="multiline:true" prompt="描述" style="width: 770px;height: 168px;"/>
				</div>
				<div id="div-content">
					<textarea name="content" style="width:770px; visibility:hidden;"></textarea>
				</div>
				
				<div class="opt_btn"  style="text-align: center;padding-top: 10px;">
					<a class="easyui-linkbutton" id="import-form-submit-btn" iconCls="icon-ok" onclick="javascript:save();">确定</a> 
					<a class="easyui-linkbutton" iconCls="icon-cancel" onclick="cancel();">取消</a>
				</div>
				<div class="loading none" style="text-align: center; padding-top: 250px; vertical-align:middle;">
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