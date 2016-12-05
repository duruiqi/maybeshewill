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
	<script type="text/javascript">
		$(function(){
			var firmInfoMsg='${requestScope.firmInfoMsg}';
			if(firmInfoMsg)alert(firmInfoMsg);
		});
	</script>
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
		<form action="${pageContext.request.contextPath}/companyInfoAction.do" method="post" enctype="multipart/form-data">
			<input type="hidden" name="method" value="modFirmInfo"/>
			<input type="hidden" name="firmId" value="${firm.firmId}"/>
			<span class="my_error">${requestScope.errorMsg.msg}</span>
			
			<ul class="forminfo">
				<li>
					<label>公司名称</label>
					<input name="firmName" type="text" value="${firm.firmName}" class="dfinput" />
					<span class="my_error">${requestScope.errorMsg.nameError}</span>
				</li>
				<li>
					<label>注册资金</label>
					<input name="loginFund" type="text" class="dfinput" value="${firm.loginFund}"/>
					<span class="my_error">${requestScope.errorMsg.fundError}</span>
				</li>
				<li>
					<label>公司地址</label>
					<input name="firmAddr" type="text" value="${firm.firmAddr}" class="dfinput" />
					<span class="my_error">${requestScope.errorMsg.addrError}</span>
				</li>
				<li>
					<label>公司图片</label>
					<input name="image" type="file" />
					<span class="my_error">${requestScope.errorMsg.imgError}</span>
					<i>注意</i>
				</li>
				<li>
					<label>成立时间</label>
					<input type="text" name="founds" class="dfinput date_style" value="${firm == null?'成立时间':firm.founds}"
					onclick="JavaScript:this.value=''" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>
					<span class="my_error">${requestScope.errorMsg.foundsError}</span>
				</li>
				
				<li>
					<label>网站备案信息</label><input name="beiAnInfo" type="text" class="dfinput" value="${firm.beiAnInfo}" />
					<span class="my_error">${requestScope.errorMsg.beiAnError}</span>
				</li>
				
				<li>
					<label>介绍</label>
					<textarea rows="20" cols="100" style="border:1px solid #95B8E7;font-size:16px; margin-top:10px;" name="introduce">${firm.introduce}</textarea><br/>
					<span class="my_error">${requestScope.errorMsg.introduceError}</span>
				</li>
				<c:choose>
					<c:when test="${firm.state == '0'}">
						<li>
							<label>是否启用</label>
							<select name="state" class="dfinput" style="width:100px;">
								<option value="1" ${firm.state == '1'?'selected':''}>启用</option>
								<option value="0" ${firm.state == '0'?'selected':''}>未启用</option>
							</select>
							<span class="my_error">${requestScope.errorMsg.stateError}</span>
						</li>
					</c:when>
				</c:choose>
				<li>
					<label>&nbsp;</label>
					<input type="submit" class="btn" value="确认修改" /></li>
			</ul>
		</form>

	</div>
	<p style="height:100px;"></p>

</body>

</html>
