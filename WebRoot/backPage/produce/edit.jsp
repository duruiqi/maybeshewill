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
			var produceInfo='${requestScope.produceInfo}';
			if(produceInfo)alert(produceInfo);
		});
	</script>
</head>

<body style="min-width:800px;padding:5px;">

	<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li><a href="#">首页</a></li>
			<li><a href="#">修改产品</a></li>
		</ul>
	</div>

	<div class="formbody">

		<div class="formtitle">
			<span>基本信息</span>
		</div>
		<form action="${pageContext.request.contextPath}/produceAction.do" method="post" enctype="multipart/form-data">
			<input type="hidden" name="method" value="modProduce"/>
			<input type="hidden" name="proId" value="${form.proId }">
			<span class="my_error">${requestScope.errorMsg.msg}</span>
			
			<ul class="forminfo">
				<li>
					<label>灯具名称</label>
					<input name="proName" type="text" value="${form.proName}" class="dfinput" />
					<span class="my_error">${requestScope.errorMsg.nameError}</span>
				</li>
				<li>
					<label>灯具型号</label>
					<input name="proType" type="text" class="dfinput" value="${form.proType}"/>
					<span class="my_error">${requestScope.errorMsg.pTypeError}</span>
				</li>
				<li>
					<label>灯具图片</label>
					<input name="image" type="file" />
					<span class="my_error">${requestScope.errorMsg.imgError}</span>
				</li>
				<li>
					<label>灯具类别</label>
					<select id="cc" class="easyui-combobox" name="proCatogroy"style="width:200px;height:32px;outline:0px;">   
						<option ${'道路灯具' eq form.proCatogroy?'selected':''}>道路灯具</option>
						<option ${'庭院灯具' eq form.proCatogroy?'selected':''}>庭院灯具</option>
					</select> 
					
					<span class="my_error">${requestScope.errorMsg.pCatogroyError}</span>
				</li>
				
				<li>
					<label>灯具材质 </label>
					<select id="cc" class="easyui-combobox" name="proMaterial"style="width:200px;height:32px;outline:0px;">  
						<option ${'不锈钢' eq form.proMaterial?'selected':''}>不锈钢</option>
						<option ${'碳钢' eq form.proMaterial?'selected':''}>碳钢</option>
					</select>
					<span class="my_error">${requestScope.errorMsg.pMaterial}</span>
				</li>
								<li>
					<label>产品产地</label>
					<input name="proArea" type="text" class="dfinput" value="${form.proArea}"/>
					<span class="my_error">${requestScope.errorMsg.proAreaError}</span>
				</li>
				
				<li>
					<label>产品摘要</label>
					<input name="proSummary" type="text" class="dfinput" value="${form.proSummary}"/>
					<span class="my_error">${requestScope.errorMsg.proDescError}</span>
				</li>
				
				<li>
					<label>产品详细描述</label>
					<textarea rows="15" cols="100" style="border:1px solid #95B8E7;font-size:16px; margin-top:10px;" name="proDesc">${form.proDesc}</textarea>
					<span class="my_error">${requestScope.errorMsg.proAreaError}</span>
				</li>
				
				<li>
					<label>产品的基本参数</label>
					<textarea rows="5" cols="100" style="border:1px solid #95B8E7;font-size:16px; margin-top:10px;" name="proParameter">${form.proParameter}</textarea>
					<span class="my_error">${requestScope.errorMsg.proParamError}</span>
				</li>
				
				<li>
					<label>是否显示</label>
					<select name="state" class="dfinput" style="width:100px;">
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