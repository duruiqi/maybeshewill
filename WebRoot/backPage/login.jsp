<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>欢迎登录后台管理系统</title>
<link href="${pageContext.request.contextPath}/backPage/css/style.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" src="${pageContext.request.contextPath}/backPage/js/jquery.js"></script>
<script src="${pageContext.request.contextPath}/backPage/js/cloud.js" type="text/javascript"></script>

<script type="text/javascript">
	$(function() {
		$('.loginbox').css({
			'position' : 'absolute',
			'left' : ($(window).width() - 692) / 2
		});
		
		$(window).resize(function() {
			$('.loginbox').css({
				'position' : 'absolute',
				'left' : ($(window).width() - 692) / 2
			});
		});

		var url = "${pageContext.request.contextPath}/adminAction.do?method=drawValidateImg&nocache="
				+ new Date().getTime();
		$("#showCode").attr("src", url);
		$("#change").click(function() {
				url = "${pageContext.request.contextPath}/adminAction.do?method=drawValidateImg&nocache="
				+ new Date().getTime();
				$("#showCode").attr("src", url);
			});
	});
</script>
</head>

<body
	style="background-color:#1c77ac;
	 background-image:url('${pageContext.request.contextPath}/backPage/images/light.png');
	  background-repeat:no-repeat; background-position:center top;">



	<div id="mainBody">
		<div id="cloud1" class="cloud"></div>
		<div id="cloud2" class="cloud"></div>
	</div>


	<div class="logintop">
		<span>欢迎登录后台管理界面平台</span>
		<ul>
			<li><a href="#">帮助</a></li>
			<li><a href="#">关于</a></li>
		</ul>
	</div>

	<div class="loginbody">

		<span class="systemlogo"></span>

		<div class="loginbox" style="width:800px;">
		<form action="${pageContext.request.contextPath}/adminAction.do" method="post">
			<input type="hidden" name="method" value="adminLogin"/>
			<ul>
				<b style="color:red;margin-bottom:10px;display: inline-block;">${errors.loginError}</b>
					<li>
						<input name="adminName" type="text" class="loginuser" value="${form == null ?'用户名':form.adminName}" onclick="JavaScript:this.value=''" /><br />
						<b style="color:red;font-size:14px;">${errors.nameError}</b>
					</li>
					<li>
						<input name="adminPasswd" type="password" class="loginpwd"/><br />
						<b style="color:red;font-size:14px;">${errors.passwordError}</b></li>
					<li class="yzm">
						<span>
							<input name="validateCode" type="text" value="验证码" onclick="JavaScript:this.value=''" /></span>
							<a href="javascript:void(0);" id="change">
								<img id="showCode" src="" alt="验证码" /> 换一换?</a><br />
							<b style="color:red;font-size:14px;">${errors.vlidateError}</b>
					</li>
	
					<li>
						<input type="submit" class="loginbtn" value="登录"/>
						<label><input name="mark" type="checkbox" value="1"/>记住密码</label>
						<label><a href="#">忘记密码？</a></label>
					</li>
			</ul>
		</form>


		</div>

	</div>
	<div class="loginbm" >版权所有 2014</div>




</body>

</html>
