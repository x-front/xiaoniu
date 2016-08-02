<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>权限管理</title>
<jsp:include page="../public/common/head.jsp"></jsp:include>
<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/xiaoniu/CRUD.css'/>"/>
<script type="text/javascript" src="<c:url value='/resources/js/xiaoniu/dateTool.js'/>?r=1134"></script>
<script type="text/javascript" src="<c:url value='/resources/js/xiaoniu/common.js'/>?r=22"></script>
<script type="text/javascript">
	commonTable.loadDateURI = "/secure/privileges/queryList";
	commonTable.batchUpdateValidURI = "/secure/privileges/batchDelete?strIds=";
	commonTable.batchDeleteURI = "/secure/privileges/batchUpdateValid?strIds=";
	commonTable.updateURI = "/secure/privileges/update";
	commonTable.insertURI = "/secure/privileges/insert";
	commonTable.title = "权限列表";
	commonTable.columns = [
		{field:'ck',checkbox:true},
		{field:'id', title: '权限ID',align:'center' },
		{field:'name',title: '权限名称',align:'center',width:120},
		{field:'groupId', title: '所在组ID',align:'center' },
		{field:'groupName',title: '所在组',align:'center',width:120},
		{field:'url',title: '页面路径',align:'left',width:320},
		{field:'icon',title: 'icon',align:'center'},
		{field:'serialNumber',title: '系列号',align:'center'},
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
		$("#edit_form_groupId").combobox("setValue",row.groupId);
		$("#edit_form_pvgName").val(row.name);
		$("#edit_form_pageUrl").val(row.url);
		$("#edit_form_icon").val(row.icon);
		$("#edit_form_id").val(row.id);
		$("#edit_form_serialNumber").val(row.serialNumber);
	};
	
	$(function(){
		showPageLoading();
		commonTable.defineAddWindow(500, 290);
		commonTable.init();
		removePageLoading();
		
		$("#edit_form_groupId").combobox({
			valueField:'id',
			textField:'groupName',
			selectOnNavigation:false,
			url:"<c:url value='/secure/privilegesGroup/list'/>"
		});
		
	});
	
	
	function searchPrivilege(){
		var name = $('#ss_privilege').searchbox('getValue');
		maxId = 0;
		tableQueryParams.maxId = maxId;
		tableQueryParams.name = name;
		$("#html_table").datagrid("load",tableQueryParams);
	}
</script>
</head>
<body>
	<div id="html_table">
	
	</div>
	
	<!-- tool bar -->
	<div id="table_tb" style="padding:5px;height:auto" class="none">
		<a href="javascript:void(0);" onclick="javascript:commonTable.initAddWindow()"class="easyui-linkbutton" title="添加" plain="true" iconCls="icon-add" id="addBtn">添加</a>
		<a href="javascript:void(0);" onclick="javascript:commonTable.batchDelete()"class="easyui-linkbutton" title="删除" plain="true" iconCls="icon-cut" id="delBtn">删除</a>
		<input id="ss_privilege" searcher="searchPrivilege" class="easyui-searchbox" prompt="搜索权限" style="width:100px;"></input>
	</div>
	
	<!-- 添加-->
	<div id="htm_edit">
		<form id="edit_form" method="post">
			<table id="htm_edit_table" width="480">
				<tbody>
					<tr>
						<td>权限组ID:</td>
						<td><input id="edit_form_groupId" class="clear-input" name="groupId"></td>
					</tr>
					<tr>
						<td>权限名称:</td>
						<td><input id="edit_form_pvgName" class="clear-input" name="name"/></td>
					</tr>
					<tr>
						<td>页面路径:</td>
						<td><input id="edit_form_pageUrl" class="clear-input" name="url"/></td>
					</tr>
					<tr>
						<td>icon:</td>
						<td><input id="edit_form_icon" value="icon-nav" class="clear-input" name="icon"/></td>
					</tr>
					<tr>
						<td>有效性:</td>
						<td>
							<select id="edit_form_valid" class="easyui-combobox clear-combobox" name="valid">
								<option value="1">有效</option>
								<option value="0">无效</option>
							</select>
						</td>
					</tr>
					<tr>
						<td>序号:</td>
						<td><input id="edit_form_serialNumber"  name="serialNumber" value="1"/></td>
					</tr>
					<tr>
						<td><input style="display:none" readonly="readonly" id="edit_form_id" name="id" class="clear-input"></td>
					</tr>
					<tr>
						<td class="opt_btn" colspan="2" style="text-align: center;padding-top: 10px;">
							<a class="easyui-linkbutton" id="edit_form_submit_btn" iconCls="icon-ok" onclick="javascript:commonTable.save();">确定</a> 
							<a class="easyui-linkbutton" iconCls="icon-cancel" onclick="$('#htm_edit').window('close');">取消</a>
						</td>
					</tr>
					<tr class="loading none">
						<td colspan="2" style="text-align: center; padding-top: 10px; vertical-align:middle;">
							<img alt="" src="/resources/images/loading.gif" style="vertical-align:middle;">
							<span style="vertical-align:middle;">请稍后...</span>
						</td>
					</tr>
				</tbody>
			</table>
		</form>
	</div>
	
</body>
</html>