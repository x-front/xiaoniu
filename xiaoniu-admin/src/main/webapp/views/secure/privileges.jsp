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
	var maxId=0;
	var myPageSize=10;
	var tableQueryParams = {};
	var tableInit = function(){
		loadTableDate(1);
	};
	var loadDateUrl="<c:url value='/secure/privileges/queryPrivileges'/>";
	var deleteURI = "<c:url value='/secure/privileges/deletePrivileges?strIds='/>";
	var recordIdKey='id';
	
	function loadTableDate(pageNum) {
		$("#html_table").datagrid({
			title:"权限列表",
			loadMsg:"加载中....",
			url	   :	loadDateUrl,
			fitColumns:true,
			fit:true,
			queryParams : tableQueryParams,
			pagination: true,
			pageNumber: pageNum,
			toolbar:'#table_tb',
			idField:recordIdKey,
			pagination:true,
			rownumbers:true,
			columns:[[
			    {field:'ck',checkbox:true},
			    {field:'id', title: '权限ID',align:'center' },
				{field:'pvgName',title: '权限名称',align:'center',width:120},
				{field:'groupId', title: '所在组ID',align:'center' },
				{field:'groupName',title: '所在组',align:'center',width:120},
				{field:'pageUrl',title: '页面路径',align:'left',width:320},
				{field:'icon',title: 'icon',align:'center'},
				{field:'serialNumber',title: '系列号',align:'center'},
				{field:'valid',title: '有效性',align:'center',
					formatter: function(value,row,index){
						if(value == 1) {
		  					img = "<c:url value='/resources/js/easyUI/themes/icons/ok.png'/>";
		  					return "<img title='有效' class='htm_column_img'  src='" + img + "'/>";
		  				}
		  				img = "<c:url value='/resources/js/easyUI/themes/icons/tip.png'/>";
		  				return "<img title='无效' class='htm_column_img' src='" + img + "'/>";
		  			}
				},
				{field:'operator',title: '操作',align:'center',
					formatter: function(value,row,index){
		  				return "<a href='#' onclick='javascript:initUpdatePrivilegeWindow(\""+ row.groupId + "\",\"" + row.pvgName 
		  						+ "\",\"" + row.pageUrl + "\",\"" + row.icon + "\",\"" + row.id + "\",\"" + row.serialNumber +"\")'>修改</a>";
		  			}
				},
			]],
			onLoadSuccess:function(data) {
		    	if(data.result == 0) {
					if(data.maxId > maxId) {
						maxId = data.maxId;
						tableQueryParams.maxId = maxId;
					}
				}
		    },
		});
		
		var p = $('#html_table').datagrid('getPager');
		p.pagination({
					beforePageText : "页码",
					afterPageText : '共 {pages} 页',
					displayMsg: '第 {from} 到 {to} 共 {total} 条记录',
				    onBeforeRefresh : function(pageNumber, pageSize) {
						if(pageNumber <= 1) {
							maxId = 0;
							tableQueryParams.maxId = maxId;
						}
					}
				});
		
	}
	
	$(function(){
		$("#htm_edit").window({
			title : '添加',
			modal : true,
			width : 500,
			height : 290,
			shadow : false,
			closed : true,
			minimizable : false,
			maximizable : false,
			collapsible : false,
			iconCls : 'icon-add',
			resizable : false,
			onClose : function(){
				$("#edit_form_groupId").combobox("clear");
				$("#edit_form_pvgName").val('');
				$("#edit_form_pageUrl").val('');
				$("#edit_form_id").val('');
				$("#edit_form_icon").val('');
				$("#edit_form_serialNumber").val('');
			}
		});
		
		$("#edit_form_groupId").combobox({
			valueField:'id',
			textField:'groupName',
			selectOnNavigation:false,
			url:"<c:url value='/secure/privilegesGroup/list'/>"
		});
		
		tableInit();
	});
	
	//初始化添加段子窗口
	function initAddPrivilegeWindow(){
		$("#htm_edit").panel({title:"添加"});
		$("#edit_form").attr("action","<c:url value='/secure/privileges/addPrivilege'/>");
		$("#edit_form_icon").val("icon-nav");
		$("#edit_form .opt_btn").hide();
		$("#edit_form .loading").show();
		$("#htm_edit").window('open');
		$("#edit_form .opt_btn").show();
		$("#edit_form .loading").hide();
	}
	
	function initUpdatePrivilegeWindow(groupId,pvgName,pageUrl,icon,id,serialNumber){
		$("#htm_edit").panel({title:"修改"});
		$("#edit_form").attr("action","<c:url value='/secure/privileges/updatePrivilege'/>");
		$("#edit_form_groupId").combobox("setValue",groupId);
		$("#edit_form_pvgName").val(pvgName);
		$("#edit_form_pageUrl").val(pageUrl);
		$("#edit_form_icon").val(icon);
		$("#edit_form_id").val(id);
		$("#edit_form_serialNumber").val(serialNumber);
		$("#edit_form .opt_btn").hide();
		$("#edit_form .loading").show();
		$("#htm_edit").window('open');
		$("#edit_form .opt_btn").show();
		$("#edit_form .loading").hide();
	}
	
	//添加段子submit
	function savePrivilege(){
		var groupId = $("#edit_form_groupId").combobox("getValue");
		var pvgName = $("#edit_form_pvgName").val();
		var pageUrl = $("#edit_form_pageUrl").val();
		var icon 	= $("#edit_form_icon").val();
		var id		= $("#edit_form_id").val();
		var serialNumber	= $("#edit_form_serialNumber").val();
		var valid	= $('#edit_form_valid').combobox("getValue");
		$.post($("#edit_form").attr("action"),{
			'groupId':groupId,
			'pvgName':pvgName,
			'pageUrl':pageUrl,
			'icon'	 :icon,
			'id'	 :id,
			'valid'	 :valid,
			'serialNumber':serialNumber
		},function(result){
			if ( result['resultCode'] == 0 ) {
				$("#html_table").datagrid("reload");
				$('#htm_edit').window('close');
			} else {
				$.messager.alert('提示',result['msg']);
			}
		},"json");
	}
	
	//初始化删除段子窗口
	function initDelPrivilegeWindow() {
		del();
	}
	
	/**
	 * 操作记录
	 */
	function del() {
		var rows = $('#html_table').datagrid('getSelections');	
		if(isSelected(rows)){
			$.messager.confirm('操作记录', '您确定要操作已选中的记录?', function(r){ 	
				if(r){				
					var ids = [];
					for(var i=0;i<rows.length;i+=1){		
						ids.push(rows[i]['id']);	
						rowIndex = $('#html_table').datagrid('getRowIndex',rows[i]);				
					}	
					$('#html_table').datagrid('clearSelections'); //清除所有已选择的记录，避免重复提交id值	
					$('#html_table').datagrid('loading');
					$.post(deleteURI + ids,function(result){
						$('#html_table').datagrid('loaded');
						if(result['resultCode'] == 0) {
							$.messager.alert('提示',result['msg'] + ids.length + "条记录！");
							$("#html_table").datagrid("reload");
						} else {
							$.messager.alert('提示',result['msg']);
						}
						return false;
					});	
				}	
			});		
		}	
	}
	
	
	/**
	 * 判断是否选中要删除的记录
	 */
	function isSelected(rows) {
		if(rows.length > 0){
			return true;
		}else{
			$.messager.alert('操作失败','请先选择记录，再执行操作!','error');
			return false;
		}
	}
	
	/**
	 * 显示载入提示
	 */
	function showPageLoading() {
		var $loading = $("<div></div>");
		$loading.text('载入中...').addClass('page_loading_tip');
		$("body").append($loading);
	}

	/**
	 * 移除载入提示
	 */
	function removePageLoading() {
		$(".page_loading_tip").remove();
	}
	
	function searchPrivilege(){
		var pvgName = $('#ss_privilege').searchbox('getValue');
		maxId = 0;
		tableQueryParams.maxId = maxId;
		tableQueryParams.pvgName = pvgName;
		$("#html_table").datagrid("load",tableQueryParams);
	}
</script>
</head>
<body>
	<div id="html_table">
	
	</div>
	
	<!-- tool bar -->
	<div id="table_tb" style="padding:5px;height:auto" class="none">
		<a href="javascript:void(0);" onclick="javascript:initAddPrivilegeWindow()"class="easyui-linkbutton" title="添加" plain="true" iconCls="icon-add" id="addBtn">添加</a>
		<a href="javascript:void(0);" onclick="javascript:initDelPrivilegeWindow()"class="easyui-linkbutton" title="删除" plain="true" iconCls="icon-cut" id="delBtn">删除</a>
		<input id="ss_privilege" searcher="searchPrivilege" class="easyui-searchbox" prompt="搜索权限" style="width:100px;"></input>
	</div>
	
	<!-- 添加-->
	<div id="htm_edit">
		<form id="edit_form" method="post">
			<table id="htm_edit_table" width="480">
				<tbody>
					<tr>
						<td>权限组ID:</td>
						<td><input id="edit_form_groupId" ></td>
					</tr>
					<tr>
						<td>权限名称:</td>
						<td><input id="edit_form_pvgName" /></td>
					</tr>
					<tr>
						<td>页面路径:</td>
						<td><input id="edit_form_pageUrl" /></td>
					</tr>
					<tr>
						<td>icon:</td>
						<td><input id="edit_form_icon" value="icon-nav"/></td>
					</tr>
					<tr>
						<td>有效性:</td>
						<td>
							<select id="edit_form_valid" class="easyui-combobox">
								<option value="1">有效</option>
								<option value="0">无效</option>
							</select>
						</td>
					</tr>
					<tr>
						<td>序号(可为空):</td>
						<td><input id="edit_form_serialNumber" /></td>
					</tr>
					<tr>
						<td><input style="display:none" readonly="readonly" id="edit_form_id"></td>
					</tr>
					<tr>
						<td class="opt_btn" colspan="2" style="text-align: center;padding-top: 10px;">
							<a class="easyui-linkbutton" id="edit_form_submit_btn" iconCls="icon-ok" onclick="javascript:savePrivilege();">确定</a> 
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