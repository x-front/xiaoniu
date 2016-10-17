<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% String type = request.getParameter("type"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新闻信息</title>
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
	commonTable.title = "文章列表";
	commonTable.tableQueryParams = {
			orderBy:'serial_number asc,id desc',
			type:'<%=type%>'
	}
	commonTable.columns = [
		{field:'ck',checkbox:true},
		{field:'id', title: 'ID',align:'center'},
		{field:'banner', title: '封面图',align:'center',  hidden:true},
		{field:'title',title: '标题', align:'center',width:200},
		{field:'source',title: '来源',align:'center'},
		{field:'summary',title: '摘要',align:'center',width:340},
		publishTimeColumn,
		{field:'clickTimes',title: '点击次数',align:'center'},
		{field:'serialNumber',title: '序号',align:'center'},
		publishColumn,
		createTimeColumn,
		updateTimeColumn,
		{field:'operator',title: '操作',align:'center',
			formatter: function(value,row,index){
					var str = "<div style='margin: 8px 0;'><a href='#' onclick='javascript:initUpdateNewsWindow("+index+")'>修改</a><div>";
					str += "<div style='margin: 8px 0;'><a href='#' onclick='javascript:initChangeTypeWindow("+index+")'>移动</a></div>";
					return str;
				}
		},
		{field:'preview',title: '预览',align:'center',
			formatter: function(value,row,index){
					var str = "";
					
					var typeStr = "";
					switch(row.type){
					case 1:typeStr = "beautiful-f-inside.html";break;
					case 2:typeStr = "beautiful-t-inside.html";break;
					case 3:typeStr = "beautiful-g-inside.html";break;
					case 4:typeStr = "beautiful-j-inside.html";break;
					case 5:typeStr = "news-t-inside.html";break;
					case 6:typeStr = "news-m-inside.html";break;
					case 7:typeStr = "idea-inside.html";break;
					case 8:typeStr = "audio-inside.html";break;
					case 9:typeStr = "about-w-inside.html";break;
					case 10:typeStr = "idea-inside.html";break;
					case 11:typeStr = "news-t-inside.html";break;
					case 15:typeStr = "news-w-inside.html";break;
					}
					
					str += "<a target='_blank' href='/resources/static/"+typeStr+"?id="+row.id+"'>预览</a>";
					return str;
				}
		},
	];
	
	var contextEditor;
	var PluginUpload;
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
				height:contentHeight - 200,
				newlineTag : "br",
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
		$("#move-div").window({
			title : '移动',
			modal : true,
			width : 300,
			height : 180,
			shadow : false,
			closed : true,
			minimizable : false,
			maximizable : false,
			collapsible : false,
			draggable : true,
			iconCls : 'icon-add',
			resizable : false,
			onClose : function(){
				$("#move-div-display-none-id").val('');
				$("#move-div-title").html('');
			},
		});
		
		
	});
	
	function initUpdateNewsWindow(index){
		var rows = $("#html_table").datagrid("getRows"),
		row = rows[index];
		$("#display-none-id").val(row.id);
		$('#display-none-type').val(row.type);
		$("#edit-div-source").textbox('setValue',row.source);
		$("#edit-div-title").textbox('setValue',row.title);
		$("#edit-div-banner").textbox('setValue',row.banner);
		$('#edit-img-banner').attr('src',row.banner).removeClass('none');
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
	
	function initChangeTypeWindow(index){
		var rows = $("#html_table").datagrid("getRows"),
		row = rows[index];
		$("#move-div-display-none-id").val(row.id);
		$("#move-div-title").html(row.title);
		$('#more-div-type').combobox('setValue',row.type);
		$("#move-form .opt_btn").hide();
		$("#move-form .loading").show();
		$("#move-div").window('open');
		$("#move-form .opt_btn").show();
		$("#move-form .loading").hide();
	}
	
	function changeType(){
		var $form = $("#move-form");
		$("#move-form .opt_btn").hide();
		$("#move-form .loading").show();
		$.post($form.attr('action'),{
			'id':$('#move-div-display-none-id').val(),
			'type':$('#more-div-type').combobox('getValue')
			},function(result){
			$("#move-form .opt_btn").show();
			$("#move-form .loading").hide();
			if ( result['resultCode'] == 0 ) {
				$("#move-div").window('close');
				$("#html_table").datagrid("reload");
				$(".clear-input").val('');
				$("#move-div-title").html('');
			} else {
				$.messager.alert('提示',result['msg']);
			}
		});
	}
	
	function set2Top(){
		var rows = $('#html_table').datagrid('getSelections');	
		if(rows.length == 1){
			$.messager.confirm('操作记录', '置顶是将该文章放到首位展示。只能将一篇新闻放到首位，若是之前某篇设置了置顶，则其将会被取消置顶，并将其序列号置为1.您确定要将选中的记录置顶?', function(r){ 	
				if(r){
					var id = rows[0]['id'];
					var type = rows[0]['type'];
					$.post("/secure/news/setTop",{'id':id,'type':type},function(result){
						$('#html_table').datagrid('loaded');
						if(result['resultCode'] == 0) {
							$.messager.alert('提示',"成功置顶！");
							$("#html_table").datagrid("reload");
						} else {
							$.messager.alert('提示',result['msg']);
						}
						return false;
					});	
				}	
			});		
		} else if(rows.length < 1){
			$.messager.alert('提示','请选择要置顶的文章');
		}else {
			$.messager.alert('提示','只能将一篇文章置顶');
		}
	}
	
	function set2IndexNews(){
		var rows = $('#html_table').datagrid('getSelections');
		if(isSelected(rows)){
			$.messager.confirm('操作记录', "确定添加到首页新闻列表？", function(r){ 
				if(r){	
					for(var i=0;i<rows.length;i+=1){
						$.post("/secure/indexNews/save",{
							'id':rows[i]['id'],
							'title':rows[i]['title'],
							'valid':0,
							'serialNumber':1000
						},function(result){
							if(result.resultCode == 0){
								$.messager.alert('提示', "成功添加！");
								console.log('success to  save ' + rows[i]['id'] + ' to index news');
							}else{
								console.log('failed to save ' + rows[i]['id'] + ' to index news');
							}
						},'json');
					}
				}
			});
		}
	}
</script>
<style type="text/css">
	#edit-div{width: 1000px;margin: auto;margin-top: 10px;}

	#div-banner{
		height: 150px;width: 255px;float: left;overflow: hidden;text-align: center;
		vertical-align: middle;
	}
	#edit-div-banner{width:170px;}
	
	#div-content-info{height: 150px;width: 255px;float: left;text-align: center;overflow: hidden;}
	#div-content-info input{margin-bottom: 5px;width: 170px;}
	#div-content-info select{width: 174px;}
	#div-title{height: 150px;width: 490px;float: right;}
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
			<c:if test="${type ge 1 and type le 4 or type eq 9}">
				<a href="javascript:void(0);" onclick="javascript:set2Top()"class="easyui-linkbutton" title="置顶" plain="true" iconCls="icon-filter">置顶</a>
			</c:if>
			<a href="javascript:void(0);" onclick="javascript:set2IndexNews()"class="easyui-linkbutton" title="添加" plain="true" iconCls="icon-edit" >添加到首页新闻列表</a>
		</div>
		
		<!-- 移动 -->
		<div id="move-div" >
			<form id="move-form" action="/secure/news/update">
				<table  width="280">
					<tbody>
						<tr>
							<td style="width:60px;color: #444;text-align: right;">文     章:</td>
							<td><div id="move-div-title" style="height: 50px;width: 200px;"></div></td>
						</tr>
						<tr>
							<td style="width:60px;color: #444;text-align: right;">移动到:</td>
							<td>
								<select class="easyui-combobox" required="true" id="more-div-type" name="type" style="width:200px;">
									<option value="1">美好家庭</option>
									<option value="2">美好体育</option>
									<option value="3">美好公益</option>
									<option value="4">美好教育</option>
									<option value="5">最新动态</option>
									<option value="6">媒体报道</option>
									<option value="7">小牛思想</option>
									<option value="8">小牛视频</option>
									<option value="9">牛人文化</option>
									<option value="10">小牛思想声音</option>
									<option value="11">小牛新闻</option>
									<option value="15">小牛看世界</option>
								</select>
							</td>
						</tr>
						<tr>
							<td class="opt_btn" colspan="2" style="text-align: center;padding-top: 10px;">
								<a class="easyui-linkbutton" id="edit_form_submit_btn" iconCls="icon-ok" onclick="javascript:changeType();">确定</a> 
								<a class="easyui-linkbutton" iconCls="icon-cancel" onclick="$('#move-div').window('close');">取消</a>
							</td>
						</tr>
						<tr class="loading none">
							<td colspan="2" style="text-align: center; padding-top: 10px; vertical-align:middle;">
								<img alt="" src="/resources/images/loading.gif" style="vertical-align:middle;">
								<span style="vertical-align:middle;">请稍后...</span>
							</td>
						</tr>
				
						<div id="move-div-display-none-input" class="none">
							<input id="move-div-display-none-id" name="id" class="clear-input">
						</div>
					</tbody>
				</table>
			</form>
		</div>
		
		<!-- 添加 -->
		<div id="edit-div" class="none" >
			<form id="edit-form" method="post">
				<div id="div-banner">
					
					<c:choose>
						<c:when test="${type == 10 }">
							<input id="edit-div-banner" required="true" name="banner" class="easyui-textbox clear-easyui-textbox"  prompt="封面图(641*425)"/>
						</c:when>
						<c:when test="${type == 11 }">
							<input id="edit-div-banner" required="true" name="banner" class="easyui-textbox clear-easyui-textbox"  prompt="封面图(981*499)"/>
						</c:when>
						<c:when test="${type ge 1 and type le 4}">
							<input id="edit-div-banner" required="true" name="banner" class="easyui-textbox clear-easyui-textbox"  prompt="封面图(981*499)"/>
						</c:when>
						<c:otherwise>
							<input id="edit-div-banner" required="true" name="banner" class="easyui-textbox clear-easyui-textbox"  prompt="封面图(354*185)"/>
						</c:otherwise>
					</c:choose>
					
					<input type="button" id="btn-banner-upload" value="选择图片"/>
					<img id="edit-img-banner" alt="" src="" class="none" style="width: 203px;height: 102px;">
				</div>
				
				<div id="div-content-info" >
					<input id="edit-div-source" name="source" required="true" class="easyui-textbox clear-easyui-textbox "  prompt="来源"/>
					<input id="edit-div-publishTime" name="publishTime" required="true" class="easyui-datetimebox clear-easyui-datetimebox " prompt="发布时间"/>
					<input id="edit-div-clickTimes" name="clickTimes" required="true" class="easyui-numberbox clear-easyui-numberbox " prompt="点击次数" data-options="min:0"/>
					<input id="edit-div-serialNumber" name="serialNumber" required="true" class="easyui-numberbox clear-easyui-numberbox " prompt="序号(越小排序越靠前)"/>
					<select class="easyui-combobox" required="true" id="edit-div-valid" name="valid">
						<option value="0">提交后不发布</option>
						<option value="1">提交后直接发布</option>
					</select>
				</div>
				<div id="div-title" >
					<input id="edit-div-title" name="title" class="easyui-textbox clear-easyui-textbox " required="true" prompt="标题" style="width:490px"/>
					<input  id="edit-div-summary" name="summary" class="easyui-textbox clear-easyui-textbox" required="true" data-options="multiline:true" prompt="摘要" style="width:488px;;height: 100px;"/>
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
					<input id="display-none-top" name="top" class="" value="0">
				</div>
			</form>
		</div>
		
</body>
</html>