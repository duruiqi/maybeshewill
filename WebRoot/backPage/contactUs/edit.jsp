<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="${pageContext.request.contextPath}/backPage/css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/backPage/js/jquery.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/backPage/js/WdatePicker.js"></script>

	<link rel="stylesheet" href="${pageContext.request.contextPath}/backPage/themes/default/easyui.css" type="text/css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/backPage/themes/icon.css" type="text/css">

	<script type="text/javascript" src="/YHZM/backPage/js/jquery.easyui.min.js"></script>


<style type="text/css">
	.date_style{width:100px;color:gray;}
	.my_error{color:red;font-weight: bold;}
</style>
</head>

<body style="min-width:800px;padding:5px;">

	<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li><a href="#">首页</a></li>
			<li><a href="#">修改公司信息</a></li>
		</ul>
	</div>

	<div class="formbody">

		<div class="formtitle">
			<span>修改信息</span>
		</div>
		<form action="${pageContext.request.contextPath}/contactUsAction.do" method="post" enctype="multipart/form-data">
			<input type="hidden" name="method" value="modContactUs"/>
			<input type="hidden" name="usId" value="${contactUs.usId}"/>
			<span class="my_error">${requestScope.errorMsg.msg}</span>
			
			<ul class="forminfo">
				<li>
					<label>联系人</label>
					<input name="linkman" type="text" value="${contactUs.linkman}" class="dfinput" />
					<span class="my_error">${requestScope.errorMsg.linkmanError}</span>
				</li>
				<li>
					<label>邮箱</label>
					<input name="email" type="text" class="dfinput" value="${contactUs.email}"/>
					<span class="my_error">${requestScope.errorMsg.emailError}</span>
				</li>
				<li>
					<label>QQ号码</label>
					<input name="qq" type="text" value="${contactUs.qq}" class="dfinput" />
					<span class="my_error">${requestScope.errorMsg.qqError}</span>
				</li>
				<li>
					<label>传真</label>
					<input name="fax" type="text" value="${contactUs.fax}" class="dfinput" />
					<span class="my_error">${requestScope.errorMsg.faxError}</span>
				</li>
				<li>
					<label>邮编</label>
					<input type="text" name="postcode" class="dfinput" value="${contactUs.postcode}"/>
					<span class="my_error">${requestScope.errorMsg.postcodeError}</span>
				</li>
				
				<li>
					<label>联系电话</label><input name="phone" type="text" class="dfinput" value="${contactUs.phone}" />
					<span class="my_error">${requestScope.errorMsg.phoneError}</span>
				</li>
				<li>
					<label>联系地址</label><input name="linkAddr" type="text" class="dfinput" value="${contactUs.linkAddr}" />
					<span class="my_error">${requestScope.errorMsg.linkAddrError}</span>
				</li>
				<li>
					<label>&nbsp;</label>
					<input type="submit" class="btn" value="确认修改" /></li>
			</ul>
		</form>

	</div>
	<p style="height:100px;"></p>

</body>

</html>