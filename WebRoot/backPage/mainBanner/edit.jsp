<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

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
	<script type="text/javascript">
		$(function(){
			var mainBannerMsg='${requestScope.mainBannerMsg}';
			if(mainBannerMsg)alert(mainBannerMsg);
		});
	</script>
</head>

<body style="min-width:800px;padding:5px;">

	<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li><a href="#">首页</a></li>
			<li><a href="#">编辑广告</a></li>
		</ul>
	</div>

	<div class="formbody">

		<div class="formtitle">
			<span>基本信息</span>
		</div>
		<form action="${pageContext.request.contextPath}/mainBannerAction.do" method="post" enctype="multipart/form-data">
			<input type="hidden" name="method" value="modMainBanner"/>
			<input type="hidden" name="bId" value="${form.bId}"/>
			<span class="my_error">${requestScope.errorMsg.msg}</span>
			
			<ul class="forminfo">
				<li>
					<label>广告图片</label>
					<input name="image" type="file" />
					<span class="my_error">${requestScope.errorMsg.imgError}</span>
				</li>
				
				<li>
					<label>间隔时间</label>
					<input name="timeslice" type="text" class="dfinput" style="width:100px;" value="${form.timeslice}"/>
					<span class="my_error">${requestScope.errorMsg.timesliceError}</span>
				</li>
				
				<li>
					<label>是否显示</label>
					<select name="state" class="dfinput" style="width:100px;margin-top:10px;">
						<option value="1" ${form.state == '1'?'selected':''}>启用</option>
						<option value="0" ${form.state == '0'?'selected':''}>未启用</option>
					</select>
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