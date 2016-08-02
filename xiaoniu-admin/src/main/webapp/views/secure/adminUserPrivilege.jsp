<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>管理员权限管理</title>
<jsp:include page="../../public/common/head.jsp"></jsp:include>
<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/CRUD.css'/>"/>
<script type="text/javascript" src="<c:url value='/resources/js/common/dateTool.js'/>?r=1134"></script>
<script type="text/javascript" src="<c:url value='/resources/js/xiaoniu/common.js'/>?r=1134"></script>
<script type="text/javascript">
	commonTable.loadDateURI = "<c:url value='/secure/adminUserPrivilege/queryAdminUserInfoVOList'/>";
	commonTable.batchUpdateValidURI = "<c:url value='/secure/adminUserPrivilege/batchDelete?strIds='/>";
	commonTable.batchDeleteURI = "<c:url value='/secure/adminUserPrivilege/batchUpdateValid?strIds='/>";
	commonTable.updateURI = "<c:url value='/secure/adminUserPrivilege/update'/>";
	commonTable.insertURI = "<c:url value='/secure/adminUserPrivilege/insert'/>";
	commonTable.title = "管理员权限管理";
	commonTable.columns = [
		{field:'ck',checkbox:true},
		{field:'id', title: 'ID',align:'center' },
		{field:'userId',title: '用户ID',align:'center'},
		{field:'userName',title: '用户名',align:'center',width:120},
		{field:'pvgName',title: '组名称',align:'center',width:120},
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
	];
	commonTable.addWindowCloseCallBack = function(){
		$("#edit_form_privilege").combogrid('clear');
		$("#edit_form_adminUser").combobox('clear');
	};

	var privilegeQueryParams = {};
	
	$(function(){
		showPageLoading();
		commonTable.defineAddWindow(500, 290);
		commonTable.init();
		
		$('#edit_form_privilege').combogrid({
			panelWidth:500,
		    panelHeight:350,
		    title:"权限列表",
		    loadMsg:'加载中，请稍后...',
			pageList: [10,20],
			editable: false,
		    multiple: true,
		   	toolbar:"#privilege_tb",
		   	url:"<c:url value='/secure/privileges/queryPrivileges'/>",
		    idField:'id',
		    textField:'pvgName',
		    pagination:true,
		    onClickCell: function(rowIndex, field, value){
		    	if(field == 'opt') 
		    		eee(field); // 强制报错停止脚本运行
		    },
		    columns:[[
				{field : 'ck',checkbox : true },
				{field:'id', title: '权限ID',align:'center', width:100 },
				{field:'pvgName',title: '权限名称',align:'center',width:140},
				{field:'groupId', title: '所在组ID',align:'center', width:80 },
				{field:'groupName',title: '所在组',align:'center',width:140},
		    ]],
		    queryParams:privilegeQueryParams,
		    onLoadSuccess:function(data) {
		    	if(data.result == 0) {
					if(data.maxId > privilegeMaxId) {
						privilegeMaxId = data.maxId;
						privilegeQueryParams.maxId = privilegeMaxId;
					}
				}
		    },
		});
		var p = $('#edit_form_privilege').combogrid('grid').datagrid('getPager');
		p.pagination({
			onBeforeRefresh : function(pageNumber, pageSize) {
				if(pageNumber <= 1) {
					privilegeMaxId = data.maxId;
					privilegeQueryParams.maxId = privilegeMaxId;
				}
			}
		});
		
		$("#adminUser-input").combobox({
			valueField:'id',
			textField:'userName',
			selectOnNavigation:false,
			url:"<c:url value='/secure/adminUserInfo/list'/>",
			onSelect:function(rec){
				tableQueryParams.maxId = 0;
				tableQueryParams.userId = rec.id;
				$("#html_table").datagrid("load",tableQueryParams);
			}
		});
		
		$("#edit_form_adminUser").combobox({
			valueField:'id',
			textField:'userName',
			selectOnNavigation:false,
			url:"<c:url value='/secure/adminUserInfo/list'/>"
		});
		removePageLoading();
	});
	
	//初始化添加段子窗口
	function initAddWindow(){
		$("#edit_form").attr("action","<c:url value='/secure/adminUserPrivilege/insert'/>");
		$("#edit_form .opt_btn").hide();
		$("#edit_form .loading").show();
		$("#htm_edit").window('open');
		$("#edit_form .opt_btn").show();
		$("#edit_form .loading").hide();
	}
	
	//添加段子submit
	function save(){
		var privilegeIds 	= $("#edit_form_privilege").combogrid('getValues'),
		userId = $("#edit_form_adminUser").combobox('getValue'),
			valid	= $('#edit_form_valid').combobox("getValue");
		$.post($("#edit_form").attr("action"),{
			'userId' :userId,
			'privilegeIds'	 :privilegeIds.toString(),
			'valid'	 :valid,
		},function(result){
			if ( result['resultCode'] == 0 ) {
				$("#html_table").datagrid("reload");
				$('#htm_edit').window('close');
			} else {
				$.messager.alert('提示',result['msg']);
			}
		},"json");
	}
	
	
	function searchPrivilege(){
		privilegeMaxId = 0;
		var pvgName = $('#ss_privilege').searchbox('getValue');
		userQueryParams = {
			'maxId' : privilegeMaxId,
			'pvgName' : pvgName
		};
		$("#edit_form_privilege").combogrid('grid').datagrid("load",userQueryParams);
	}
	
	
</script>
</head>
<body>
	<div id="html_table">
	
	</div>
	
	<!-- tool bar -->
	<div id="table_tb" style="padding:5px;height:auto" class="none">
		<input id="adminUser-input" />
		<a href="javascript:void(0);" onclick="javascript:initAddWindow()"class="easyui-linkbutton" title="添加" plain="true" iconCls="icon-add" id="addBtn">为管理员添加权限</a>
		<a href="javascript:void(0);" onclick="javascript:batchDelete()"class="easyui-linkbutton" title="删除" plain="true" iconCls="icon-cut" id="delBtn">删除</a>
	</div>
	
	<!-- 添加-->
	<div id="htm_edit">
		<form id="edit_form" method="post">
			<table id="htm_edit_table" width="480">
				<tbody>
					<tr>
						<td>管理员:</td>
						<td><input id="edit_form_adminUser" /></td>
					</tr>
					<tr>
						<td>权限:</td>
						<td><input id="edit_form_privilege"/></td>
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
						<td><input style="display:none" readonly="readonly" id="edit_form_id"></td>
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
	
	<div id="privilege_tb" style="padding:5px;height:auto" class="none">
		<input id="ss_privilege" searcher="searchPrivilege" class="easyui-searchbox" prompt="搜索权限" style="width:100px;"></input>
	</div>
	
</body>
</html>