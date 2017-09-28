<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% String type = request.getParameter("type"); %>
<% String lang = request.getParameter("lang"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"><meta name="_csrf" content="${_csrf.token}"/> <meta name="_csrf_header" content="${_csrf.headerName}"/>
<title>核心团队信息</title>
<jsp:include page="../public/common/head.jsp"></jsp:include>
<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/xiaoniu/CRUD.css'/>"/>
<link rel="stylesheet" href="/resources/kindeditor-4.1.10/themes/default/default.css" />
<script type="text/javascript" src="<c:url value='/resources/js/xiaoniu/dateTool.js'/>?r=1134"></script>
<script type="text/javascript" src="<c:url value='/resources/js/xiaoniu/common.js'/>?r=31"></script>
<script type="text/javascript" src="/resources/kindeditor-4.1.10/kindeditor-all.js"></script>
<script type="text/javascript" src="/resources/kindeditor-4.1.10/lang/zh_CN.js"></script>
<script type="text/javascript">
	commonTable.loadDateURI = "/secure/leader/queryList";
	commonTable.batchUpdateValidURI = "/secure/leader/batchUpdateValid?strIds=";
	commonTable.batchDeleteURI = "/secure/leader/batchDelete?strIds=";
	commonTable.updateURI = "/secure/leader/update";
	commonTable.insertURI = "/secure/leader/insert";
	commonTable.title = "团队列表";
	commonTable.nowrap = false;
	commonTable.tableQueryParams = {
			orderBy:'serial_number desc,id desc',
			lang:0
	}
	commonTable.columns = [
		{field:'ck',checkbox:true},
		{field:'id', title: 'ID',align:'center',  hidden:true},
		{field:'banner', title: '相片',align:'center',  
			formatter: function(value,row,index){
				return "<img title='无效' style='width:39px;height:52px;' class='htm_column_img' src='" + value + "'/>";
			}	
		},
		{field:'name',title: '名称', align:'center',},
		/*{field:'position',title: '职位',align:'center'},*/
		{field:'summary',title: '描述',align:'left',width:340},
		{field:'serialNumber',title: '序号',align:'center'},
		validColumn,
		createTimeColumn,
		updateTimeColumn,
		{field:'operator',title: '操作',align:'center',
			formatter: function(value,row,index){
					return "<a href='#' onclick='javascript:initUpdateLeaderWindow("+index+")'>修改</a>";
				}
		},
	];
	
	var PluginUpload;
	var contentHeight;
	var type = <%=type%>;
	$(function(){
		contentHeight = jQuery(window).height();
		showPageLoading();
		commonTable.init();
		removePageLoading();
		KindEditor.ready(function(K) {
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
	
	function initUpdateLeaderWindow(index){
		var rows = $("#html_table").datagrid("getRows"),
		row = rows[index];
		$("#display-none-id").val(row.id);
		$("#edit-div-name").textbox('setValue',row.name);
		$("#edit-div-position").textbox('setValue',row.position);
		$("#edit-div-banner").textbox('setValue',row.banner);
		$('#edit-img-banner').attr('src',row.banner).removeClass('none');
		$("#edit-div-summary").textbox('setValue',row.summary);
		$("#edit-div-serialNumber").numberbox('setValue',row.serialNumber);
		$("#edit-div-valid").combobox('setValue',row.valid);
		$("#edit_div_lang").combobox('setValue',row.lang);
		$("#edit-form").attr("action",commonTable.updateURI);
		$(".datagrid").addClass("none");
		$("#edit-div").removeClass("none");
	}
	
	function initAddLeaderWindow(){
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
	
	function updateLang(lang){
		var rows = $('#html_table').datagrid('getSelections');	
		if(isSelected(rows)){
			var ids = [];
			for (var i = 0; i < rows.length; i++) {
				var row = rows[i];
					ids.push(row['id']);
			}
			$.post("/secure/leader/batchUpdateLeaderLang?strIds="+ids,{'lang':lang,'${_csrf.parameterName}':'${_csrf.token}'},function(result){
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
		$("#edit_form_lang").combobox('setValue',lang);
		$("#html_table").datagrid("reload");
	}
</script>
<style type="text/css">
	#edit-div{width: 1000px;margin: auto;margin-top: 30px;}

	#div-banner{
		height: 230px;width: 297px;float: left;overflow: hidden;
	}
	#edit-div-banner{width:158px;}
	
	#div-title{height: 230px;width: 703px;float: left;}
	
	#edit-div .textbox {margin-bottom:5px}
</style>
</head>
<body>
		<div id="html_table" >
		</div>
		
		<!-- tool bar -->
		<div id="table_tb" style="padding:5px;height:auto" class="none">
			<a href="javascript:void(0);" onclick="javascript:initAddLeaderWindow()"class="easyui-linkbutton" title="添加" plain="true" iconCls="icon-add" id="addBtn">添加</a>
			<a href="javascript:void(0);" onclick="javascript:commonTable.batchDelete()"class="easyui-linkbutton" title="删除" plain="true" iconCls="icon-cancel" id="delBtn">删除</a>
			<a href="javascript:void(0);" onclick="javascript:commonTable.batchPublish()"class="easyui-linkbutton" title="发布" plain="true" iconCls="icon-ok">发布</a>
			<a href="javascript:void(0);" onclick="javascript:commonTable.batchCancelPublish()"class="easyui-linkbutton" title="撤销" plain="true" iconCls="icon-tip">撤销发布</a>
			<a href="javascript:void(0);" onclick="javascript:showLang(0)"class="easyui-linkbutton" title="只显示中文版" plain="true" iconCls="icon-save">只显示中文版</a>
			<a href="javascript:void(0);" onclick="javascript:showLang(1)"class="easyui-linkbutton" title="只显示英文版" plain="true" iconCls="icon-save">只显示英文版</a>
			<a href="javascript:void(0);" onclick="javascript:updateLang(0)"class="easyui-linkbutton" title="迁移到中文版" plain="true" iconCls="icon-undo">迁移到中文版</a>
			<a href="javascript:void(0);" onclick="javascript:updateLang(1)"class="easyui-linkbutton" title="迁移到英文版" plain="true" iconCls="icon-redo">迁移到英文版</a>
		</div>
		
		<!-- 添加 -->
		<div id="edit-div" class="none" >
			<form id="edit-form" method="post">
				<div id="div-banner">
					<input id="edit-div-banner" required="true" name="banner" class="easyui-textbox clear-easyui-textbox"  prompt="相片(159x159)"/>
					<input type="button" id="btn-banner-upload" value="选择图片"/>
					<img id="edit-img-banner" alt="" src="" class="none" style="width: 158px;height: 209px;">
				</div>
				
				<div id="div-title" >
					<select class="easyui-combobox clear-easyui-combobox" required="true" id="edit-div-valid" name="valid" style="width:130px">
						<option value="0">提交后不发布</option>
						<option value="1">提交后直接发布</option>
					</select>
					<select class="easyui-combobox" id="edit_form_lang" name="lang" style="width:68px">
						<option value="0">中文</option>
						<option value="1">英文</option>
					</select>
					<input id="edit-div-serialNumber" name="serialNumber" required="true" class="easyui-numberbox clear-easyui-numberbox " prompt="序号(越大排序越靠前)" style="width:490px"/>
					<input id="edit-div-name" name="name" required="true" class="easyui-textbox clear-easyui-textbox " maxlength="12" prompt="名字" style="width:204px"/>
					<%--<input id="edit-div-position" name="position" class="easyui-textbox clear-easyui-textbox " maxlength="128" required="true" prompt="职位" style="width:490px"/>--%>
					<input  id="edit-div-summary" name="summary" class="easyui-textbox clear-easyui-textbox" maxlength="512" required="true" data-options="multiline:true" prompt="描述" style="width: 703px;height: 178px;"/>
				</div>
				
				<div class="opt_btn"  style="text-align: center;padding-top: 250px;">
					<a class="easyui-linkbutton" id="import-form-submit-btn" iconCls="icon-ok" onclick="javascript:save();">确定</a> 
					<a class="easyui-linkbutton" iconCls="icon-cancel" onclick="cancel();">取消</a>
					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
				</div>
				<div class="loading none" style="text-align: center; padding-top: 250px; vertical-align:middle;">
					<img alt="" src="/resources/images/loading.gif" style="vertical-align:middle;">
					<span style="vertical-align:middle;">请稍后...</span>
				</div>
				
				<div id="display-none-input" class="none">
					<input id="display-none-id" name="id" class="clear-input">
					<input id="display-none-terminal" name="terminal" value="0">
				</div>
			</form>
		</div>
		
</body>
</html>