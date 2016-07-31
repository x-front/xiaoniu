<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>登陆</title>
<meta name="robots" content="index,follow" />
<meta name="renderer" content="webkit">
<meta http-equiv="Cache-Control" content="no-siteapp,no-transform" />
<meta name ="viewport" content ="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no">
<meta name="format-detection" content="telephone=no,email=no" />
<meta name="msapplication-tap-highlight" content="no">
<jsp:include page="./common/head.jsp"></jsp:include>
<link rel="stylesheet" href="/resources/css/login/css/reset.css" type="text/css">
<link rel="stylesheet" href="/resources/css/login/css/common.css" type="text/css">
<style type="text/css">
.display-none{display:none;}
.error{color:red;}
#login-tips{
	position: relative;
	top: -19px;
	height: 1px;
	text-align: center;
	}
</style>
<!--[if lt IE 9]>
<script type="text/javascript" src="/resources/css/3rd/login/js/html5.js"></script>
<script type="text/javascript" src="/resources/css/3rd/login/js/respond.min.js"></script>
<![endif]-->
<script type="text/javascript" src="/resources/css/3rd/login/js/jquery-1.8.3.min.js"></script>
<script>
$(function(){
	$('.content_main input').focus(function(){
		$(this).css('border-color','#5bacf9');
		})
	$('.content_main input').blur(function(){
		$(this).css('border-color','#e7e6e6');
		})
	$('.put_one').focus(function(){
		$(this).parent().addClass('user_color');
		$("#login-tips").addClass("display-none");
		})
	$('.put_one').blur(function(){
		$(this).parent().removeClass('user_color');
		})
	$('.put_two').focus(function(){
		$(this).parent().addClass('password_color');
		$("#login-tips").addClass("display-none");
		})
	$('.put_two').blur(function(){
		$(this).parent().removeClass('password_color');
	});
	$("#validateImg").click(function(){
			validateImg="<c:url value='/public/validateImage'/>";
			$("#validateImg").attr("src",validateImg+"?"+Math.random());
		});
	$("input[name=j_validate_code]").blur(function(){
		$("#login-tips").addClass("display-none");
	});
	$("input[name=j_validate_code]").focus(function(){
		$("#login-tips").addClass("display-none");
	});
});
function loginSubmit(){
		var username = $("input[name=j_username]").val();
		if($.trim(username) == ""){
			$("#login-tips").html("账户不能为空");
			$("#login-tips").removeClass("display-none");
			return ;
		}
		var pwd = $("input[name=j_password]").val();
		if($.trim(pwd) == ""){
			$("#login-tips").html("密码不能为空");
			$("#login-tips").removeClass("display-none");
			return ;
		}
		$.post("/j_spring_security_check",$("#loginForm").serialize(),function(result){
			if(result.resultCode == 0){
				window.location.href = "/secure/index.html";
			}else{
				if(result.msg){
					if(result.msg == "Bad credentials"){
						$("#login-tips").html("账户或密码错误");
					}else{
						$("#login-tips").html(result.msg);
					}
				}else{
					$("#login-tips").html("账户或密码错误");
				}
				$("#login-tips").removeClass("display-none");
				$("#validateImg").click();
			}
		});
	}
</script>

</head>
<body class="login_bg">
	<header>
    	<h3>华睿信内容管理系统</h3>
    </header>
    <div class="content">
    	<div class="tm_bd"><div class="tm_bg"></div> </div>
        <div class="con_bg">
    	<h3>管理员登录</h3>
    	<div class=" error tips display-none" id="login-tips"></div>
        <div class="content_main fr">
			<s:url value="/j_spring_security_check" var="login" />
			<form action="${login}" method="post" id="loginForm">
				<p class="user">
					<span>用户名：</span><input type="text" name="j_username" class="put_one">
				</p>
				<p class="password">
					<span>密   码：</span><input type="password" name="j_password" class="put_two">
				</p>
				<p class="yzm"><span>验证码：</span><input type="text" name="j_validate_code" maxlength="4"><em><img src="<c:url value='/public/validateImage'/>" id="validateImg" width="85px" height="30p"></em></p>
				<p class="btn">
					<input type="button" class="login_btn" value="" onclick="loginSubmit()">
					<input type="button" class="reset_btn" onclick="$('#loginForm').find('input').val('')">
				</p>
			</form>
        </div>
        </div>
    </div>
    <footer>
    	<p>技术支持：中为科技</p>
    </footer>
</body>
</html>