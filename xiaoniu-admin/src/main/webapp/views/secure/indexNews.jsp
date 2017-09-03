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
	commonTable.loadDateURI = "/secure/indexNews/queryList";
	commonTable.batchUpdateValidURI = "/secure/indexNews/batchUpdateValid?strIds=";
	commonTable.batchDeleteURI = "/secure/indexNews/batchDelete?strIds=";
	commonTable.updateURI = "/secure/indexNews/save";
	commonTable.insertURI = "/secure/indexNews/save";
	commonTable.title = "首页新闻";
	commonTable.nowrap = false;
	commonTable.tableQueryParams = {
			orderBy:'serial_number desc,id desc',
			lang:0
	}
	commonTable.columns = [
		{field:'ck',checkbox:true},
		{field:'id', title: '新闻ID',align:'center',  hidden:true},
		{field:'title',title: '描述',align:'left',width:340},
		{field:'serialNumber',title: '序号',align:'center'},
		publishColumn,
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
		$("#edit_form_lang").combobox('setValue',row.lang);
		$("#edit-form").attr("action",commonTable.updateURI);
	};
	
	$(function(){
		showPageLoading();
		commonTable.defineAddWindow(460, 290);
		commonTable.init();
		removePageLoading();
	});
	
	function updateLang(lang){
		var rows = $('#html_table').datagrid('getSelections');	
		if(isSelected(rows)){
			var ids = [];
			for (var i = 0; i < rows.length; i++) {
				var row = rows[i];
					ids.push(row['id']);
			}
			$.post("/secure/indexNews/batchUpdateIndexNewsLang?strIds="+ids,{'lang':lang},function(result){
				if(result.resultCode == 0){
					$.messager.alert('提示',"成功更新" + ids.length + "条记录！");
					$("#html_table").datagrid("reload");
				}else{
					$.messager.alert('提示',result['msg']);
				}
			},"json");
		}
	}
	
	function showLang(lang){
		commonTable.tableQueryParams.lang = lang;
		$("#html_table").datagrid("reload");
	}
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
			<a href="javascript:void(0);" onclick="javascript:commonTable.batchCancelPublish()"class="easyui-linkbutton" title="撤销" plain="true" iconCls="icon-tip">撤销发布</a>
			<a href="javascript:void(0);" onclick="javascript:showLang(0)"class="easyui-linkbutton" title="只显示中文版" plain="true" iconCls="icon-save">只显示中文版</a>
			<a href="javascript:void(0);" onclick="javascript:showLang(1)"class="easyui-linkbutton" title="只显示英文版" plain="true" iconCls="icon-save">只显示英文版</a>
			<a href="javascript:void(0);" onclick="javascript:updateLang(0)"class="easyui-linkbutton" title="迁移到中文版" plain="true" iconCls="icon-undo">迁移到中文版</a>
			<a href="javascript:void(0);" onclick="javascript:updateLang(1)"class="easyui-linkbutton" title="迁移到英文版" plain="true" iconCls="icon-redo">迁移到英文版</a>
		</div>
		
		<!-- 添加 -->
		<div id="htm_edit">
			<form id="edit_form" method="post">
				<table id="htm_edit_table" width="450">
					<tbody>
						<tr>
							<td>标题:</td>
							<td><input id="edit_form_title" name="title" class="clear-textbox easyui-textbox" maxlength="255" data-options="multiline:true"  required="true" style="height: 100px;"/></td>
						</tr>
						<tr>
							<td>序号:</td>
							<td><input id="edit_form_serialNumber" name="serialNumber" class="clear-numberbox easyui-numberbox" required="true" /></td>
						</tr>
						<tr>
							<td>发布状态:</td>
							<td>
								<select id="edit_form_valid" name="valid" class="easyui-combobox clear-combobox">
									<option value="1">发布</option>
									<option value="0">未发布</option>
								</select>
							</td>
						</tr>
						<tr>
							<td>语言:</td>
							<td>
								<select class="easyui-combobox" required="true" id="edit_form_lang" name="lang">
									<option value="0">中文</option>
									<option value="1">英文</option>
								</select>
							</td>
						</tr>
						<tr>
							<td><input style="display:none" name="id" readonly="readonly" id="edit_form_id"  class="clear-input"></td>
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