<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"><meta name="_csrf" content="${_csrf.token}"/> <meta name="_csrf_header" content="${_csrf.headerName}"/>
<title>工作地点</title>
<jsp:include page="../public/common/head.jsp"></jsp:include>
<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/xiaoniu/CRUD.css'/>?v=1"/>
<script type="text/javascript" src="<c:url value='/resources/js/xiaoniu/dateTool.js'/>?r=1134"></script>
<script type="text/javascript" src="<c:url value='/resources/js/xiaoniu/common.js'/>?r=4"></script>
<script type="text/javascript">
	commonTable.loadDateURI = "/secure/workAddr/queryList";
	commonTable.batchUpdateValidURI = "/secure/workAddr/batchUpdateValid?strIds=";
	commonTable.batchDeleteURI = "/secure/workAddr/batchDelete?strIds=";
	commonTable.updateURI = "/secure/workAddr/update";
	commonTable.insertURI = "/secure/workAddr/insert";
	commonTable.title = "工作地点列表";
	commonTable.columns = [
		{field:'ck',checkbox:true},
		{field:'id', title: 'ID',align:'center'},
		{field:'address',title: '工作地点',align:'center',width:120},
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
		$("#edit_form_address").val(row.address);
		$("#edit_form_id").val(row.id);
		$('#edit_form_valid').combobox("setValue",row.valid);
	};
	
	$(function(){
		showPageLoading();
		commonTable.defineAddWindow(460, 200);
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
		<a href="javascript:void(0);" onclick="javascript:commonTable.initAddWindow()"class="easyui-linkbutton" title="添加" plain="true" iconCls="icon-add" id="addBtn">添加</a>
		<a href="javascript:void(0);" onclick="javascript:commonTable.batchDelete()"class="easyui-linkbutton" title="删除" plain="true" iconCls="icon-cut" id="delBtn">删除</a>
	</div>
	
	<!-- 添加-->
	<div id="htm_edit">
		<form id="edit_form" method="post">
			<table id="htm_edit_table" width="450">
				<tbody>
					<tr>
						<td>工作地点:</td>
						<td><input id="edit_form_address" name="address" class="clear-input"/></td>
					</tr>
					<tr>
						<td>有效性:</td>
						<td>
							<select id="edit_form_valid" name="valid" class="easyui-combobox clear-combobox">
								<option value="1">有效</option>
								<option value="0">无效</option>
							</select>
						</td>
					</tr>
					<tr>
						<td><input style="display:none" name="id" readonly="readonly" id="edit_form_id"></td>
					</tr>
					<tr>
						<td class="opt_btn" colspan="2" style="text-align: center;padding-top: 10px;">
							<a class="easyui-linkbutton" id="edit_form_submit_btn" iconCls="icon-ok" onclick="javascript:commonTable.save();">确定</a> 
							<a class="easyui-linkbutton" iconCls="icon-cancel" onclick="$('#htm_edit').window('close');">取消</a>
							<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
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