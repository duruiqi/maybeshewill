<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<link href="${pageContext.request.contextPath}/backPage/css/style.css" rel="stylesheet" type="text/css" />
<style type="text/css">
.my_error,.error {
	color: red;
	font-weight: bold;
}

#send {
	text-align: center;
	border: 1px solid #ccc;
	display: inline-block;
	width: 100px;
	height: 26px;
	line-height: 26px;
}
</style>
<script type="text/javascript" src="${pageContext.request.contextPath}/backPage/js/jquery.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/backPage/js/jquery.validate.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/backPage/js/lang/messages_zh.min.js"></script>
</head>

<body>

	<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li><a href="#">首页</a></li>
			<li><a href="#">修改密码</a></li>
		</ul>
	</div>

	<div class="formbody">

		<div class="formtitle">
			<span>修改密码</span>
		</div>

		<form id="form"	action="${pageContext.request.contextPath}/adminAction.do" method="post">
			<input type="hidden" name="method" value="modAdminPasswd" /> <input
				type="hidden" name="adminId" value="${sessionScope.account.adminId}">
			<span class="my_error">${requestScope.errorMsg.msg}</span>
			<ul class="forminfo">
				<li><label for="oldPassword">旧密码</label> <input
					name="oldPassword" id="oldPassword" type="password" class="dfinput" />
					<span class="my_error">${requestScope.errorMsg.oldPwdError}</span></li>
				<li><label for="newPassword">新密码</label> <input name="newPassword"
					id="newPassword" type="password" class="dfinput" /> <span
					class="my_error">${requestScope.errorMsg.newPwdError}</span></li>
				<li><label for="confirm_password">确认密码</label> <input
					name="confirm_password" id="confirm_password" type="password"
					class="dfinput" /> <span class="my_error">${requestScope.errorMsg.conrimPwdError}</span>
				</li>

				<li><label>&nbsp;</label> <input type="submit" value="确认修改"
					class="dfinput submit" style="width:100px;" /></li>
			</ul>
		</form>

	</div>

	<script>

	$(function(){
		// 在键盘按下并释放及提交后验证提交表单
		  $("#form").validate({
			    rules: {
			    	oldPassword:{
			    	required: true,
			        minlength: 5},
			        newPassword: {
				        required: true,
				        myPasswordRule: true,
				        minlength: 5
				      },
				      confirm_password: {
				        required: true,
				        minlength: 5,
				        equalTo: "#newPassword"
				      }
				     },
				    messages: {
				    	oldPassword: {
					        required: "请输入原始密码",
					        minlength: "密码长度不能小于5!"
					      },
					    newPassword: {
				        required: "请输入密码",
				        minlength: "密码长度不能小于5！"
				      },
				      confirm_password: {
				        required: "请再次输入密码",
				        minlength: "密码长度不能小于 5!",
				        equalTo: "两次密码输入不一致!"
				      }
				    }
		});
	});
	</script>
</body>
</html>