<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta name="keywords" content="成都义鸿照明有限公司" />
<meta name="description" content="成都义鸿照明有限公司" />
<title>成都义鸿照明有限公司会员注册</title>

<script type="text/javascript" src="${pageContext.request.contextPath}/backPage/js/jquery.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/backPage/js/jquery.validate.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/backPage/js/lang/messages_zh.min.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/frontPage/css/lcb_style.css" />
<link href="${pageContext.request.contextPath}/frontPage/css/master.css" type="text/css" rel="stylesheet" />
<link href="${pageContext.request.contextPath}/frontPage/css/base.css" type="text/css" rel="stylesheet" />
<script type="text/javascript">
	$(function(){
		var url = "${pageContext.request.contextPath}/userAction.do?method=drawValidateImg&nocache="
		+ new Date().getTime();
		$("#showCode").attr("src", url);
		$("#change").click(function() {
				url = "${pageContext.request.contextPath}/userAction.do?method=drawValidateImg&nocache="
				+ new Date().getTime();
				$("#showCode").attr("src", url);
			});
	});

</script>
<style type="text/css">
.my_error,.error {color: red;float:left;}
</style>
</head>
<body id="nav_btn02">
	<div
		style="height:110px;width:auto;background:#0174C7;border:1px solid #0174C7;">
		<div id="logo">
			<h1>
				<a href="index.jsp"> <img src="images/logo.png" alt="义鸿照明"
					style="float:left;width:114px;height:40px;" /></a> <span
					class="logo_span"> 注册中心</span>
			</h1>
			<ul id="logo_right">

				<li class="zi"><a href="index.jsp" style="color:#fff">公司主页</a></li>

			</ul>
		</div>
	</div>
	<!--logo end-->
	<div id="nav_bg"></div>
	<!--nav end-->
	<div id="body" style="height:580px;">
		<h3 class="my_error">${requestScope.msg}</h3>
		<div class="zhuce_body" id="registerLoad">
			<div class="zhuce_left">
				<form id="form" action="${pageContext.request.contextPath}/userAction.do" method="post">
					<input type="hidden" name="method" value="userRegister"/>
					<div class="input">
						<label><span style="color:#F00">*</span>用户名：</label>
						<input type="text" placeholder="5位以上数字或字母组合" class="text" value="${requestScope.form.userName}"
						id="userName" name="userName" tabindex="1" />
					</div>
					<span class="my_error">${requestScope.errorMsg.nameError}</span>
					<div class="input">
						<label><span style="color:#F00">*</span>登录密码：</label> 
						<input type="password"  placeholder="5位以上登录密码" class="text" value="${requestScope.form.userPasswd}"
						id="userPasswd" name="userPasswd" tabindex="2" />
					</div>
					<span class="my_error">${requestScope.errorMsg.passwordError}</span>
					<div class="input">
						<label><span style="color:#F00">*</span>确认密码：</label>
						<input type="password" placeholder="重复密码" class="text" id="confirmPassword" 
						value="${requestScope.confirmPassword}" name="confirmPassword" />
					</div>
					<span class="my_error">${requestScope.errorMsg.confirmPwdError}</span>
					<div class="input">
						<label><span style="color:#F00">*</span>邮箱：</label> 
						<input type="text" placeholder="用于激活用户" class="text" value="${requestScope.form.email}"
						id="email"	name="email" tabindex="5"/>
					</div>
					<span class="my_error">${requestScope.errorMsg.emailError}</span>
					<div class="input">
						<label><span style="color:#F00">*</span>验证码：</label>
						<input type="text" placeholder="验证码" class="yanzheng" id="validateCode" name="validateCode" size="4" />
						<a href="javascript:void(0);" id="change">
						<img id="showCode" src="" alt="验证码" style="vertical-align:middle;width:80px;height:35px;"width="80px" height="35px" />换一换?</a>
					</div>
					<span class="my_error">${requestScope.errorMsg.vlidateError}</span>
					<div class="input">
						<input id="register_btn" type="submit" class="button"	value="立即注册"/>
					</div>
				</form>
			</div>
	<script>

	$(function(){
/* 		var addIco = {
				"okTag":"<span class='checkMark' style='width:80px;'><img src='images/ok.ico' alt='ok.ico'style='width:20px;heigth:20px;'/>通过</span>",
				"noTag":"<span class='checkMark' style='width:80px;'><img src='images/no.ico' alt='no.ico'style='width:20px;heigth:20px;'/>已存在</span>",
				"mark":""
			};
		

		function callBack(){
			alert($(this)[0].nodeName);
			$("#userName,#email").blur(function(){
				alert("xxxxxxx");
				var url="${pageContext.request.contextPath}/userAction.do";

					if($(this).attr("name") == 'userName'){
					    $("#userName-error").css("display","none");
						param = {"method":"aysnCheckParam","userName":$(this).val()};
						addIco.mark = "#userName";
						
					}else if($(this).attr("name") == 'email'){
						 $("#email-error").css("display","none");
						param={"method":"aysnCheckParam","email":$(this).val()};
						addIco.mark = "#email"; 
					}
					
					$.post(url,param,function(data){
						if($(this).before('.checkmark')){
							$(this).before(".checkMark").remove();
						}
						if(data == 'ok'){
							$(addIco.mark).before(addIco.okTag);
						}else if(data == 'no'){
							$(addIco.mark).before(addIco.noTag);			
						}
						
					},"text");
				
			});
		} */
		
		// 在键盘按下并释放及提交后验证提交表单
		  $("#form").validate({
			    rules: {
			    	userName:{
			    	required: true,
			    	rangelength: [1,8]
			        },
			        userPasswd:{
			        	required: true,
			        	rangelength: [5,16],
				        minlength: 5
			        },
				      confirmPassword: {
				        required: true,
				        rangelength: [5,16],
				        equalTo: "#userPasswd"
				      },email:{
				    	  required: true,
					      rangelength: [5,32],
					      email:true
				      },validateCode:{
				    	 required: true,
				      }
				     },
				    messages: {
				    	userName: {
					        required: "请输入",
					        rangelength: "长度必须1~8之间"
					      },
					   userPasswd:{
						   required: "请再次输入密码",
						   rangelength: "长度必须5~16之间"
					   },
				      confirmPassword: {
				        required: "请再次输入密码",
				        rangelength: "长度必须5~16之间!",
				        equalTo: "两次输入不一致!"
				      },email:{
				    	  required: "请输入邮箱地址",
				    	  rangelength: "长度必须5~32之间!",
					      email:"邮箱格式错误"
				      },validateCode:{
				    	 required: "请输入验证码",
				      }
				    }
		});
	});
	</script>	
		
			
			<!--zhuce_left end-->
			<div class="zhuce_right">
				<div class="denglu">
					<span style="float:left; line-height:35px;">已有账号？请点击</span> <span><a
						href="${pageContext.request.contextPath}/frontPage/login.jsp"
						class="zhuce_denglu">登录</a></span>
				</div>
			</div>
			<!--zhuce_right end-->
		</div>
		<!--zhuce_body end-->

	</div>
	<!--body end-->
	<%@include file="./part/footer.jsp"%>
</body>
</html>