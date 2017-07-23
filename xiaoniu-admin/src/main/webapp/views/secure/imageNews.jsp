<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>小牛图集</title>
<jsp:include page="../public/common/head.jsp"></jsp:include>
<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/xiaoniu/CRUD.css'/>"/>
<link rel="stylesheet" href="/resources/kindeditor-4.1.10/themes/default/default.css" />
<script type="text/javascript" src="<c:url value='/resources/js/xiaoniu/dateTool.js'/>?r=1134"></script>
<script type="text/javascript" src="<c:url value='/resources/js/xiaoniu/common.js'/>?r=31"></script>
<script type="text/javascript" src="/resources/kindeditor-4.1.10/kindeditor-all-min.js"></script>
<script type="text/javascript" src="/resources/kindeditor-4.1.10/lang/zh_CN.js"></script>
<style type="text/css" rel="stylesheet">
     body{height:100%;width:100%}
     *{margin:0;padding:0}
     form ul,li{list-style: none}
     form a{text-decoration: none;outline: none;color:#FFF;text-align: center;}
     .main_c li img{vertical-align: middle;width:100%;max-width: 100%;}
     .clear{clear:both}
     .wrap{width:1000px;margin:0 auto 0;padding:20px 20px 0 20px}
     .btns{text-align: center;}
     .btns a{display:inline-block;width:110px;height:50px;line-height: 50px;font-size: 16px;color:#FFF;border-radius: 5px;background: #ce2f10;margin-right: 10px;}
     .btns .btn2{background: #4863ff}
     .btns .btn3{background: #29bd4d}
     .main a{display:inline-block;width:50px;height:180px;line-height: 180px;border-radius: 5px;font-size: 40px;}
     .main_c{float:left;width:100%;overflow: hidden;overflow-x:scroll;border:1px solid #eeeeee;height:400px;text-align: center;position:relative;box-sizing: border-box}
     .main_c ul{position:absolute;left:0;top:0;}
     .main_c li{width:200px;;margin:0 5px;float:left;height:400px;box-sizing: border-box;position:relative}
     .main_c li p{position:absolute;bottom:0;left:0;font-size: 16px;line-height: 24px;width:100%;height: 230px;overflow: hidden;}
     .main_c li div{width:100%;height:128px;overflow: hidden;margin-top: 30px;opacity: 0.4;}
     .left-td{width:90px;}
     .main_c ul .selected div{    
	    opacity: 1;
	  }
	  #div-preview{width: 100%;height: 100%;}
	  #preview-iframe {
	  	width:100%;frameborder:0;
	  	height: 100%;
	  }
	  .close-preview-btn{position: absolute;top: 5px;left: 4px;}
	  .close-preview-btn a{display:inline-block;width:110px;height:50px;line-height: 50px;font-size: 16px;color:#FFF;border-radius: 5px;background: #ce2f10;margin-right: 10px;text-align: center;}
 </style>
<script type="text/javascript">
    function resizeImageList(){
        var w=parseInt($(".main_c ul li").css("width").slice(0,-2));
        var i=$(".main_c ul li").length;
        $(".main_c ul").css("width",i*(w+10)+"px");
        $(".main_c ul li").unbind("click");
       	$(".main_c ul li").click(function(){
   			$(".main_c ul li").removeClass("selected");
   			$(this).addClass("selected");
   		});
        
    };
</script>
<script type="text/javascript">
	commonTable.loadDateURI = "/secure/imageNews/queryImageHeadList";
	commonTable.batchUpdateValidURI = "/secure/imageNews/batchUpdateValids?strIds=";
	commonTable.batchDeleteURI = "/secure/imageNews/batchDel?strIds=";
	commonTable.title = "新闻图集";
	commonTable.nowrap = false;
	commonTable.tableQueryParams = {
			lang:0,
			orderBy:'serial_number desc,id desc'
	}
	commonTable.columns = [
		{field:'ck',checkbox:true},
		{field:'id', title: 'ID',align:'center',  hidden:true},
		{field:'title',title: '标题',align:'left',width:240},
		{field:'imgUrl1',title: '图片连接',align:'left',width:240},
		{field:'serialNumber',title: '序号',align:'center'},
		validColumn,
		createTimeColumn,
		updateTimeColumn,
		{field:'operator',title: '操作',align:'center',
			formatter: function(value,row,index){
				return "<a href='#' onclick='javascript:initUpdateNewsWindow("+index+")'>修改</a>";
				}
		},
	];
	
	$(function(){
		showPageLoading();
		
		$("#htm_edit").window({
			title : '添加',
			modal : true,
			width : 500,
			height : 500,
			shadow : false,
			closed : true,
			minimizable : false,
			maximizable : false,
			collapsible : false,
			draggable : true,
			iconCls : 'icon-add',
			resizable : false,
			onClose : function(){
				$("#htm_edit .clear-input").val('');
				$("#htm_edit .clear-textbox").textbox('setValue','');
				$("#htm_edit .clear-numberbox").numberbox('setValue',1);
				$("#htm_edit .clear-combobox").combobox('setValue',1);
				$('#htm_edit_img_preview').addClass('none');
			},
		});
		
		
		KindEditor.ready(function(K) {
			PluginUpload = K.editor({
				cssPath : '/resources/kindeditor-4.1.10/plugins/code/prettify.css',
				uploadJson : '/secure/aliyunOss/upload_json',
				fileManagerJson : '/secure/aliyunOss/file_manager_json',
				allowFileManager : true,
			});
		});
		
		$("#btn-banner-upload").click(function(){
			PluginUpload.loadPlugin('image',function(){
				PluginUpload.plugin.imageDialog({
					imageUrl : $("#edit-div-banner").textbox('getValue'),
					clickFn : function(url, title, width, height, border, align){
						$('#edit-div-banner').textbox('setValue',url);
						$('#htm_edit_img_preview').attr('src',url).removeClass('none');
						PluginUpload.hideDialog();
					}
				});
			});
		});
		
		commonTable.init();
		removePageLoading();
		
		
		$(".right").click(function(){
			var w=parseInt($(".main_c ul li").css("width").slice(0,-2));
		 	var uw=parseInt($(".main_c ul").css("width").slice(0,-2));
	        var mw=parseInt($(".main_c").css("width").slice(0,-2));
	        var l=parseInt($(".main_c ul").css("left").slice(0,-2));
	        if(l==0){
	            $(".main_c ul").animate({"left":-w+"px" });
	        }else{
	            if(l==-(uw-mw-(w+10))||l==-(uw-mw)){
	                $(".main_c ul").animate({"left":-(uw-mw)+"px"})
	            }else if((-(l+w))%(w+10)==0||l==-w){
	                $(".main_c ul").animate({"left":(l-(w+10))+"px" });
	            }
	
	        }
	    });
	    $(".left").click(function(){
	    	var w=parseInt($(".main_c ul li").css("width").slice(0,-2));
	    	var uw=parseInt($(".main_c ul").css("width").slice(0,-2));
        	var mw=parseInt($(".main_c").css("width").slice(0,-2));
	        var l=parseInt($(".main_c ul").css("left").slice(0,-2));
	        if(l==-(uw-mw)){
	            $(".main_c ul").animate({"left":l+(w+10)+"px"});
	        }else{
	            if(l==-w||l==0){
	                $(".main_c ul").animate({"left":"0"});
	
	            }else if((-(l+w))%(w+10)==0){
	                $(".main_c ul").animate({"left":l+(w+10)+"px"});
	            }
	        }
	    });
	});
	
	function initAddNewsWindow(){
		$(".datagrid").addClass("none");
		$("#edit-div").removeClass("none");
	}
	
	function initUpdateNewsWindow(index){
		showPageLoading();
		var rows = $("#html_table").datagrid("getRows"),
		row = rows[index];
		var id = row['id'];
		$.post("/secure/imageNews/queryImageNewsByNewsId",{'newsId':id},function(result){
			if(result.resultCode == 0){
				$('#edit-div-showtime').datebox('setValue',row['showtime']);
				var list = result.list;
				for(var i=0;i<list.length;i++){
					var item = list[i];
					doAddSingleImage(item.content,item.image);
				}
				$("#display-none-id").val(id);
				$("#edit-div-title").textbox("setValue",row.title);
				$("#edit_form_valid").combobox("setValue",row.valid);
				$("#edit_form_lang").combobox("setValue",row.lang);
				$('#edit-div-showtime').datebox('setValue',dateTools.LongTimeToSimpleFormatDate(row.showTime));
				$(".datagrid").addClass("none");
				$("#edit-div").removeClass("none");
			}else{
				$.messager.alert('提示',result['msg']);
			}
		},"json");
		removePageLoading();
	}
	
	function closeAddNewsWindow(){
		$(".clear-input").val('');
		$(".clear-textbox").textbox('setValue','');
		clearOldImgs();
		$(".datagrid").removeClass("none");
		$("#edit-div").addClass("none");
	}
	
	function submitNewsWindow(){
		var length = $('.main_c li').length;
		var showtime = $('#edit-div-showtime').datebox('getValue');
		var postData = [];
		var image1='',image2='',image3='';
		var title = $("#edit-div-title").textbox("getValue");
		var valid = $("#edit_form_valid").combobox("getValue");
		var lang = $("#edit_form_lang").combobox("getValue");
		var id = $("#display-none-id").val();
		for(var i=0; i<length; i++){
			var node = $('.main_c li:eq('+i+')'); 
			var imgUrl = node.find('img:eq(0)').attr('src');
			var content = node.find('p:eq(0)').html();
			postData.push({
				'image':imgUrl,
				'content':content,
				'valid':1,
				'serialNumber':i});
			if(i==0){
				image1 = imgUrl;
			}
			if(i==1){
				image2 = imgUrl;
			}
			if(i==2){
				image3 = imgUrl;
			}
		}
		
		if(!showtime){
			showtime = (new Date()).getTime();
		}else{
			showtime = (new Date(showtime)).getTime();
		}
		if(postData.length > 2){
			$.post("/secure/imageNews/saveImageNews",{
				'id':id,
				'showTime':showtime,
				'title':title,
				'img1':image1,
				'img2':image2,
				'img3':image3,
				'valid':valid,
				'lang' : lang,
				'data':JSON.stringify(postData)
			},function(result){
				if(result.resultCode == 0){
					$("#html_table").datagrid("reload");
					$.messager.alert('提示',result['msg']);
					closeAddNewsWindow();
				}else{
					$.messager.alert('提示',result['msg']);
				}
			},'json');
		}else{
			$.messager.alert('提示',"至少需要三张图片，不能提交");
		}
	}
	
	function getShowTime(){
		var showtime = $('#edit-div-showtime').datebox('getValue');
		if(!showtime){
			showtime = (new Date()).getTime();
		}else{
			showtime = (new Date(showtime)).getTime();
		}
		return showtime;
	}
	
	function initAddImageWindow(){
		$("#htm_edit").window('open');
	}
	
	function addSingleImage(){
		var content = $('#htm_edit_img_desc').val();
		var imgUrl = $('#edit-div-banner').textbox('getValue');
		var index = $('#htm_edit_index').val();
		if(index != 1){
			doAddSingleImage(content,imgUrl);
		}else{
			var node = $(".main_c ul .selected");
			node.find("img").eq(0).attr("src",imgUrl);
			node.find("p").eq(0).html(content);
		}
		$("#htm_edit").window('close');
	}
	
	function doAddSingleImage(content,imgUrl){
		var html = '<li class="selected"><div class="li-div-img"><img src="'+imgUrl+'" alt=""/></div><p>'+content+'</p></li>';
		$(".main_c ul li").removeClass("selected");
		$(".main_c ul:eq(0)").append(html);
		resizeImageList();
		var liCount=$(".main_c ul li").length;
		if(liCount >= 5){
			$(".right").click();
		}
	}
	
	function clearOldImgs(){
		$(".main_c ul:eq(0)").html('');
	}
	
	function LiMoveToPre(){
		$(".main_c ul  .selected").prev().before($(".main_c ul .selected"));
	}
	
	function LiMoveToNext(){
		$(".main_c ul .selected").next().after($(".main_c ul .selected"));
	}
	
	function initUpdateImageWindow(){
		var node = $(".main_c ul .selected");
		if(node.length <= 0){
			$.messager.alert('提示',"请选择图文");
			return;
		}
		var imgUrl = node.find("img").eq(0).attr("src");
		var html = node.find("p").eq(0).html();
		$('#htm_edit_img_desc').val(html);
		$('#edit-div-banner').textbox('setValue',imgUrl);
		$('#htm_edit_index').val(1);
		$("#htm_edit").window('open');
	}
	
	function removeLi(){
		$.messager.confirm('操作记录', '您确定要删除已选中的图片?', function(r){ 	
			if(r){
				$(".main_c ul .selected").remove();
			}
		});
	}
	
	function preview(){
		$("#edit-div").addClass("none");
		$("#preview-iframe").attr("src","/resources/static/pics_in.html?v=1");
		$("#div-preview").show();
	}
	
	function closePreview(){
		$("#div-preview").hide();
		$("#edit-div").removeClass("none");
	}
	
	function updateLang(lang){
		var rows = $('#html_table').datagrid('getSelections');	
		if(isSelected(rows)){
			var ids = [];
			for (var i = 0; i < rows.length; i++) {
				var row = rows[i];
				for(var i=0;i<rows.length;i+=1){		
					ids.push(row['id']);	
				}
			}
			$.post("/secure/imageNews/batchUpdateHeadImageNewsLang?strIds="+ids,{'lang':lang},function(result){
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
			<a href="javascript:void(0);" onclick="javascript:initAddNewsWindow()"class="easyui-linkbutton" title="添加" plain="true" iconCls="icon-add" id="addBtn">添加</a>
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
				<!-- 图片 -->
				<div class="wrap">
					<div style="margin-bottom:8px;text-align: center;">
						<span style="margin-right:10px;">显示时间:</span><input style="width:140px;" class="easyui-datebox clear-datebox" id="edit-div-showtime" prompt="请选择图集时间" >
						<span style="margin-left:40px;margin-right:10px;">语言:</span><select id="edit_form_lang" name="lang" class="easyui-combobox clear-combobox">
												<option value="0">中文</option>
												<option value="1">英文</option>
											</select>
						<span style="margin-left:40px;margin-right:10px;">是否发布:</span><select id="edit_form_valid" name="valid" class="easyui-combobox clear-combobox">
												<option value="0">未发布</option>
												<option value="1">发布</option>
											</select>
					</div>
					<div style="margin-bottom:4px;text-align: center;">
						<span>标题：</span><input style="width:600px;" class="easyui-textbox clear-textbox" id="edit-div-title" name="title" prompt="请输入图集标题" required="required">
					</div>
				    <div class="main">
				        <div class="main_c">
				            <ul>
				            </ul>
				        </div>
				        <div class="clear"></div>
				    </div>
				    <div class="btns">
				        <a class="btn1" href="javascript:initAddImageWindow();">添加图文</a>
				        <a class="btn2" href="javascript:initUpdateImageWindow();">修改图文</a>
				        <a class="btn3" href="javascript:removeLi();">删除图文</a>
				        <a class="btn1" href="javascript:LiMoveToPre();">向前移动</a>
				        <a class="btn2" href="javascript:LiMoveToNext();">向后移动</a>
				        <a class="btn3" href="javascript:preview();">预览</a>
				    </div>
				</div>
				
				<div class="opt_btn"  style="text-align: center;padding-top: 10px;">
					<a class="easyui-linkbutton" id="import-form-submit-btn" iconCls="icon-ok" onclick="javascript:submitNewsWindow();">确定</a> 
					<a class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:closeAddNewsWindow();">取消</a>
				</div>
				<div class="loading none" style="text-align: center; padding-top: 10px; vertical-align:middle;">
					<img alt="" src="/resources/images/loading.gif" style="vertical-align:middle;">
					<span style="vertical-align:middle;">请稍后...</span>
				</div>
				
				<div id="display-none-input" class="none">
					<input id="display-none-id" name="id" class="clear-input">
					<input id="display-none-type" name="type" class="clear-input">
					<input id="display-none-top" name="top" class="clear-input" value="0">
				</div>
			</form>
		</div>
		
		<div id="htm_edit">
			<form id="edit_form" method="post">
				<table id="htm_edit_table" width="480">
					<tr>
						<td class="left-td">选择图片</td>
						<td class="right-td">
							<input id="edit-div-banner" class="clear-textbox easyui-textbox" style="width:250px;">
							<input type="button" id="btn-banner-upload" value="选择图片" style="width:80px;"/>
						</td>
					</tr>
					<tr>
						<td>图片预览</td>
						<td>
							<div id="htm_edit_img_div" style="width:340px;height: 250px;text-align: center;overflow: hidden;">
								<img alt="" src="" id="htm_edit_img_preview" class="none">
							</div>
						</td>
					</tr>
					<tr>
						<td>文字说明</td>
						<td><textarea style="width: 340px;height: 100px;" id="htm_edit_img_desc" class="clear-input"></textarea></td>
					</tr>
					<tr>
						<td><input style="display:none"  readonly="readonly" id="htm_edit_index"  class="clear-input"></td>
						<td><input style="display:none"  readonly="readonly" id="htm_edit_id"  class="clear-input"></td>
					</tr>
					<tr>
						<td class="opt_btn" colspan="2" style="text-align: center;padding-top: 10px;">
							<a class="easyui-linkbutton" iconCls="icon-ok" onclick="javascript:addSingleImage();">确定</a> 
							<a class="easyui-linkbutton" iconCls="icon-cancel" onclick="$('#htm_edit').window('close');">取消</a>
						</td>
					</tr>
				</table>
			</form>
		</div>
		
		<!-- iframe -->
		<div id="div-preview" class="none">
			<div class="close-preview-btn">
				<a class="btn1" href="javascript:closePreview();">关闭预览</a>		
			</div>
			<iframe id="preview-iframe">
			</iframe>
		</div>
</body>
</html>