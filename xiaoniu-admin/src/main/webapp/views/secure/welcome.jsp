<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% String lang = request.getParameter("lang"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>首页</title>
<jsp:include page="../public/common/head.jsp"></jsp:include>
<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/xiaoniu/CRUD.css'/>"/>
<link rel="stylesheet" href="/resources/kindeditor-4.1.10/themes/default/default.css" />
<script type="text/javascript" src="<c:url value='/resources/js/xiaoniu/dateTool.js'/>?r=1134"></script>
<script type="text/javascript" src="<c:url value='/resources/js/xiaoniu/common.js'/>?r=31"></script>
<script type="text/javascript" src="/resources/kindeditor-4.1.10/kindeditor-all-min.js"></script>
<script type="text/javascript" src="/resources/kindeditor-4.1.10/lang/zh_CN.js"></script>
<script type="text/javascript">
	var lang = <%=lang%>;
	var PluginUpload;
	$(function(){
		contentHeight = jQuery(window).height();
		showPageLoading();
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
	
	function initUpdateWhoWindow(type,index,id){
		$("#display-none-index").val(index);
		$("#display-none-id").val(id);
		$("#display-none-type").val(type);
		$("#edit-div-h2").html($("#main-div .content-h2:eq("+ index +")").html());
		
		$("#edit-div-introdution").textbox('setValue',$("#main-div .content-p:eq("+ index +")").html());
		$("#edit-div-banner").textbox('setValue',
				$("#main-div .content-img:eq("+ index +")").attr('src'));
		$('#edit-img-banner').attr('src',
				$("#main-div .content-img:eq("+ index +")").attr('src'));
		
		$("#edit-div-media").textbox('setValue',
				$("#main-div .content-img:eq("+ index +")").attr('media'));
		
		
		$('#edit-introdution-showOrHide').removeClass('none');
		$("#edit-banner-showOrHide").removeClass('none');
		if($('#edit-img-banner').attr('src') != ""){
			$('#edit-img-banner').removeClass('none');
		}

		$("#main-div").addClass("none");
		$("#edit-div").removeClass("none");
	}
	
	function cancel(){
		$(".clear-easyui-textbox").textbox('setValue','');
		$(".clear-easyui-datetimebox").datetimebox('clear');
		$(".clear-easyui-combobox").combobox('clear');
		$(".clear-input").val('');
		$("#edit-banner-showOrHide").addClass('none');
		$('#edit-introdution-showOrHide').addClass('none');
		$('#edit-img-banner').addClass('none');
		
		$("#edit-div").addClass("none");
		$("#main-div").removeClass("none");
	}
	
	function save(){
		var $form = $("#edit-form");
		$("#edit-form .opt_btn").hide();
		$("#edit-form .loading").show();
		$.post($form.attr('action'),$form.serialize(),function(result){
			$("#edit-form .opt_btn").show();
			$("#edit-form .loading").hide();
			if ( result['resultCode'] == 0 ) {
				var index = $("#display-none-index").val();
				$("#main-div .content-p:eq("+ index +")").html($("#edit-div-introdution").textbox('getValue'));
				$("#main-div .content-img:eq("+ index +")").attr('src',$("#edit-div-banner").textbox('getValue'));
                $('.description-div:eq('+ index+')').css('background-image','url("'+$("#edit-div-banner").textbox('getValue')+'")');
				cancel();
			} else {
				$.messager.alert('提示',result['msg']);
			}
		});
	}
	function jump(lang){
		location.href='/secure/pageIntrodution/welcome.html?lang='+lang;
	}
</script>
<style type="text/css">
	.float-right{float: right;}
	.content-p{
		font-size: 16px;
		line-height: 33px;
		width: 702px;
		max-width: 702px;
		letter-spacing: 2px;
		margin: 0 auto;
		padding-bottom: 42px;
	}
	#main-div{max-width: 1200px;margin: auto;}
	.content-div{margin: auto;width:100%;height:100%;color:#fff;}
	.description-div{width:100%;height:600px;text-align: center;margin: auto;border-bottom: 1px solid #eee;padding-top: 10px;padding-bottom: 10px;}
	.description-div h2{margin-top: 250px;}
	#edit-div{text-align: center;}

    .btns a{text-decoration:none;text-align: center;display:inline-block;width:110px;height:50px;line-height: 50px;font-size: 16px;color:#FFF;border-radius: 5px;background: #ce2f10;margin-right: 10px;}

</style>
</head>
<body>
		<div id="main-div">
			<div class="btns">
				<c:if test="${lang eq 1 }">
					<a class="btn1" href="javascript:jump(0);">查看中文版</a>
				</c:if>
				<c:if test="${lang eq 0 }">
		        	<a class="btn1" href="javascript:jump(1);">查看英文版</a>
		        </c:if>
		    </div>
			<div class="content-div">
				<div class="description-div" style='background-image: url("${who.extCover}")'>
					<a href="javascript:void(0);" onclick="javascript:initUpdateWhoWindow(23,0,${who.id})" class="easyui-linkbutton float-right" title="修改" plain="true" iconCls="icon-edit" id="addBtn">修改</a>
					<h2 class="content-h2">家庭金融的超级入口</h2>
					<p class="content-p">${who.introdution }</p>
					<img class="content-img none" alt="" src="${who.extCover }" media="${who.extMedia }">
				</div>
				
				<div class="description-div" style='background-image: url("${doWhat.extCover}")'>
					<a href="javascript:void(0);" onclick="javascript:initUpdateWhoWindow(24,1,${doWhat.id})" class="easyui-linkbutton float-right" title="修改" plain="true" iconCls="icon-edit" id="addBtn">修改</a>
					<h2 class="content-h2">我们做什么</h2>
					<p class="content-p">${doWhat.introdution }</p>
					<img class="content-img none" alt="" src="${doWhat.extCover }" media="${doWhat.extMedia }">
				</div>
				
				<div class="description-div" style='background-image: url("${voice.extCover}")'>
					<a href="javascript:void(0);" onclick="javascript:initUpdateWhoWindow(29,2,${voice.id})" class="easyui-linkbutton float-right" title="修改" plain="true" iconCls="icon-edit" id="addBtn">修改</a>
					<h2 class="content-h2">我们用行动定义自己</h2>
					<p class="content-p">${voice.introdution }</p>
					<img class="content-img none" alt="" src="${voice.extCover }" media="${voice.extMedia }">
				</div>
				
				<div class="description-div " style='background-image: url("${family.extCover}")'>
					<a href="javascript:void(0);" onclick="javascript:initUpdateWhoWindow(25,3,${family.id})" class="easyui-linkbutton float-right" title="修改" plain="true" iconCls="icon-edit" id="addBtn">修改</a>
					<h2 class="content-h2">我们的思考</h2>
					<p class="content-p ">${family.introdution }</p>
					<img class="content-img  none" alt="" src="${family.extCover }" media="${family.extMedia }">
				</div>

				<div class="description-div " style='background-image: url("${sports.extCover}")'>
					<a href="javascript:void(0);" onclick="javascript:initUpdateWhoWindow(26,4,${sports.id})" class="easyui-linkbutton float-right" title="修改" plain="true" iconCls="icon-edit" id="addBtn">修改</a>
					<h2 class="content-h2">我们的公众形象</h2>
					<p class="content-p ">${sports.introdution }</p>
					<img class="content-img none" alt="" src="${sports.extCover }" media="${sports.extMedia }">
				</div>

				<div class="description-div " style='background-image: url("${welfare.extCover}")'>
					<a href="javascript:void(0);" onclick="javascript:initUpdateWhoWindow(27,5,${welfare.id})" class="easyui-linkbutton float-right" title="修改" plain="true" iconCls="icon-edit" id="addBtn">修改</a>
					<h2 class="content-h2">我们的超越与想象</h2>
					<p class="content-p ">${welfare.introdution }</p>
					<img class="content-img none" alt="" src="${welfare.extCover }" media="${welfare.extMedia }">
				</div>

				<div class="description-div " style='background-image: url("${education.extCover}")'>
					<a href="javascript:void(0);" onclick="javascript:initUpdateWhoWindow(28,6,${education.id})" class="easyui-linkbutton float-right" title="修改" plain="true" iconCls="icon-edit" id="addBtn">修改</a>
					<h2 class="content-h2">我们渴望拥有你</h2>
					<p class="content-p ">${education.introdution }</p>
					<img class="content-img none" alt="" src="${education.extCover }" media="${education.extMedia }">
				</div>
			</div>
		</div>
		<!-- 添加 -->
		<div id="edit-div" class="none" >
			<form id="edit-form" method="post" action="/secure/pageIntrodution/save">
				<div class="">
					<h2 id="edit-div-h2" class="content-h2">家庭金融的超级入口</h2>
					<div class="none " id="edit-introdution-showOrHide">
						<input  id="edit-div-introdution" name="introdution" class="easyui-textbox clear-easyui-textbox" maxlength="512" data-options="multiline:true" prompt="描述" style="width: 703px;height: 158px;"/>
					</div>

					<div class="none " id="edit-banner-showOrHide">
						<input id="edit-div-banner"  name="extCover" class="easyui-textbox clear-easyui-textbox"   prompt="封面图片" style="width:628px;"/>
						<input type="button" id="btn-banner-upload"  value="选择图片"/><br>
						<img id="edit-img-banner" alt="" src="" class="none" style="max-width: 908px;">
					</div>
				</div>
				
				<div class="opt_btn"  style="text-align: center;padding-top: 20px;">
					<a class="easyui-linkbutton" id="import-form-submit-btn" iconCls="icon-ok" onclick="javascript:save();">确定</a> 
					<a class="easyui-linkbutton" iconCls="icon-cancel" onclick="cancel();">取消</a>
				</div>
				<div class="loading none" style="text-align: center; padding-top: 20px; vertical-align:middle;">
					<img alt="" src="/resources/images/loading.gif" style="vertical-align:middle;">
					<span style="vertical-align:middle;">请稍后...</span>
				</div>
				
				<div id="display-none-input" class="none">
					<input id="display-none-id" name="id" class="clear-input">
					<input id="display-none-type" name="type" class="clear-input">
					<input id="display-none-lang" name="lang" value="${lang }">
					<input id="display-none-terminal" name="terminal" value="0">
					<input id="display-none-valid" name="valid" value="1">
					<input id="display-none-index" >
				</div>
			</form>
		</div>
		
</body>
</html>