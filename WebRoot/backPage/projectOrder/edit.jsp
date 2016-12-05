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


<title></title>
<style type="text/css">
	.date_style{width:100px;color:gray;}
	.my_error{color:red;font-weight: bold;}
</style>
	<script type="text/javascript">
		$(function(){
			var projectOrderInfo='${requestScope.projectOrderInfo}';
			if(projectOrderInfo)alert(projectOrderInfo);
		});
	</script>
</head>

<body style="min-width:800px;padding:5px;">

	<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li><a href="#">首页</a></li>
			<li><a href="#">编辑工程订单</a></li>
		</ul>
	</div>

	<div class="formbody">

		<div class="formtitle">
			<span>基本信息</span>
		</div>
		<form action="${pageContext.request.contextPath}/projectOrderAction.do" method="post" enctype="multipart/form-data">
			<input type="hidden" name="method" value="modProjectOrder"/>
			<input type="hidden" name="orderId" value="${form.orderId}"/>
			<span class="my_error">${requestScope.errorMsg.msg}</span>
			
			<ul class="forminfo">
					<li>
					<label>工程名称</label>
					<input name="projectName" type="text" value="${form.projectName}" class="dfinput" /><i>不能超过20个字符</i>
					<span class="my_error">${requestScope.errorMsg.pNameError}</span>
				</li>
				<li>
					<label>工程类别</label>
				<!-- <input name="projectType" type="text" class="dfinput" /> -->
					<select name="projectType" class="dfinput" style="width:100px;">
						<option value="道路灯具" ${form.projectType == '道路灯具'?'selected':''}>道路灯具</option>
						<option value="庭院灯具" ${form.projectType == '庭院灯具'?'selected':''}>庭院灯具</option>
					</select>
				<span class="my_error">${requestScope.errorMsg.pTypeError}</span>
				</li>
				<li>
					<label>工程地址</label>
					<input name="projectAddr" type="text" value="${form.projectAddr}" class="dfinput" />
					<span class="my_error">${requestScope.errorMsg.pAddrError}</span>
				</li>
				
				<li>
					<label>工程图片</label>
					<input name="image" type="file" />
					<span class="my_error">${requestScope.errorMsg.imgError}</span>
				</li>
				
				<li>
					<label>工程定价</label>
					<input name="projectPrice" type="text" class="dfinput easyui-numberbox" value="${form.projectPrice}"
					style="height:34px;" data-options="min:0,precision:2,groupSeparator:',',prefix:'￥'"/>
					<span class="my_error">${requestScope.errorMsg.pPriceError}</span>
				</li>
				
				<li>
					<label>工程详细</label>
					<textarea rows="10" cols="100" style="border:1px solid #95B8E7;font-size:16px; margin-top:10px;" name="projectDetail">${form.projectDetail}</textarea>
					<span class="my_error">${requestScope.errorMsg.pDetailError}</span>
				</li>
				
				<li>
					<label>工程时间</label>
					<input type="text" name="startDate" class="dfinput date_style" value="${form == null?'开始时间':form.startDate}"
					onclick="JavaScript:this.value=''" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>
					<input type="text" name="endDate" class="dfinput date_style" value="${form == null?'结束时间':form.endDate}"
					onclick="JavaScript:this.value=''"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>
					<span class="my_error">${requestScope.errorMsg.startDateError} ${requestScope.errorMsg.endDateError}</span>
				</li>
				
				<li>
					<label>客户单位</label><input name="customerUnit" type="text" class="dfinput" value="${form.customerUnit}" />
					<span class="my_error">${requestScope.errorMsg.cUnitError}</span>
				</li>
				<li>
					<label>工程负责人</label><input name="ename" type="text" class="dfinput" value="${form.employee.ename}" />
					<span class="my_error">${requestScope.errorMsg.enameError}</span>
				</li>
				<li>
					<label>&nbsp;</label>
					<input type="submit" class="btn" value="确认保存" /></li>
			</ul>
		</form>

	</div>

<p style="height:100px;"></p>

</body>

</html>
