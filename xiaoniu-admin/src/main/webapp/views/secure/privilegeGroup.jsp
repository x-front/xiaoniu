<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>权限管理</title>
<jsp:include page="../../public/common/head.jsp"></jsp:include>
<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/CRUD.css'/>"/>
<script type="text/javascript" src="<c:url value='/resources/js/common/dateTool.js'/>?r=1134"></script>
<script type="text/javascript">

	commonTable.loadDateURI = "<c:url value='/secure/privilegesGroup/queryList'/>";
	commonTable.batchUpdateValidURI = "<c:url value='/secure/privilegesGroup/batchDelete?strIds='/>";
	commonTable.batchDeleteURI = "<c:url value='/secure/privilegesGroup/batchUpdateValid?strIds='/>";
	commonTable.updateURI = "<c:url value='/secure/privilegesGroup/update'/>";
	commonTable.insertURI = "<c:url value='/secure/privilegesGroup/insert'/>";
	commonTable.title = "权限列表";
	commonTable.columns =[
		{field:'ck',checkbox:true},
		{field:'id', title: 'ID',align:'center' },
		{field:'groupName',title: '组名称',align:'center',width:120},
		{field:'icon',title: 'icon',align:'center'},
		{field:'serialNumber',title: '系列号',align:'center'},
		validColumn,
		createTimeColumn,
		updateTimeColumn,
		{field:'operator',title: '操作',align:'center',
			formatter: function(value,row,index){
					return "<a href='#' onclick='javascript:initUpdateWindow(\""+ row.id + "\",\"" + row.groupName 
							+ "\",\""  + row.icon + "\",\"" + row.valid + "\",\"" + row.serialNumber +"\")'>修改</a>";
				}
		},
    ];
	commonTable.beforeInitUpdateWindow = function(index){
		var rows = $("#html_table").datagrid("getRows"),
		row = rows[index];
		$("#edit_form_groupName").val(row.groupName);
		$("#edit_form_icon").val(row.icon);
		$("#edit_form_id").val(row.id);
		$("#edit_form_serialNumber").val(row.serialNumber);
	};
	
	
	$(function(){
		
		showPageLoading();
		commonTable.defineAddWindow(500, 290);
		commonTable.init();
		removePageLoading();
		
	});
	
</script>
</head>
<body>
	<div id="html_table">
	
	</div>
	
	<!-- tool bar -->
	<div id="table_tb" style="padding:5px;height:auto" class="none">
		<a href="javascript:void(0);" onclick="javascript:initAddWindow()"class="easyui-linkbutton" title="添加" plain="true" iconCls="icon-add" id="addBtn">添加</a>
		<a href="javascript:void(0);" onclick="javascript:batchDelete()"class="easyui-linkbutton" title="删除" plain="true" iconCls="icon-cut" id="delBtn">删除</a>
	</div>
	
	<!-- 添加-->
	<div id="htm_edit">
		<form id="edit_form" method="post">
			<table id="htm_edit_table" width="480">
				<tbody>
					<tr>
						<td>组名称:</td>
						<td><input id="edit_form_groupName" class="clear-input" name="groupName"/></td>
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
						<td>序号(可为空):</td>
						<td><input id="edit_form_serialNumber" class="clear-input" name="serialNumber"/></td>
					</tr>
					<tr>
						<td><input style="display:none" readonly="readonly" id="edit_form_id" class="clear-input" name="id"></td>
					</tr>
					<tr>
						<td class="opt_btn" colspan="2" style="text-align: center;padding-top: 10px;">
							<a class="easyui-linkbutton" id="edit_form_submit_btn" iconCls="icon-ok" onclick="javascript:save();">确定</a> 
							<a class="easyui-linkbutton" iconCls="icon-cancel" onclick="$('#htm_edit').window('close');">取消</a>
						</td>
					</tr>
					<tr class="loading none">
						<td colspan="2" style="text-align: center; padding-top: 10px; vertical-align:middle;">
							<img alt="" src="<c:url value='/resources/images/loading.gif'/>" style="vertical-align:middle;">
							<span style="vertical-align:middle;">请稍后...</span>
						</td>
					</tr>
				</tbody>
			</table>
		</form>
	</div>
	
</body>
</html>