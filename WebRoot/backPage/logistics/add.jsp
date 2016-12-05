<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
    pageContext.setAttribute("basePath", request.getContextPath()) ;
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="${basePath}/backPage/css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${basePath}/backPage/js/jquery.js"></script>
<script type="text/javascript" src="${basePath}/backPage/js/WdatePicker.js"></script>

	<link rel="stylesheet" href="${basePath}/backPage/themes/default/easyui.css" type="text/css">
	<link rel="stylesheet" href="${basePath}/backPage/themes/icon.css" type="text/css">

	<script type="text/javascript" src="/YHZM/backPage/js/jquery.easyui.min.js"></script>


<style type="text/css">
	.date_style{width:100px;color:gray;}
	.my_error{color:red;font-weight: bold;}
</style>
	<script type="text/javascript">
		$(function(){
			var purchaseMsg='${requestScope.purchaseMsg}';
			if(purchaseMsg)alert(purchaseMsg);
		});
	</script>
</head>

<body style="min-width:800px;padding:5px;">

	<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li><a href="#">首页</a></li>
			<li><a href="#">添加采购</a></li>
		</ul>
	</div>

	<div class="formbody">

		<div class="formtitle">
			<span>基本信息</span>
		</div>
		<form action="${basePath}/logisticsAction.do" method="post" enctype="multipart/form-data">
			<input type="hidden" name="method" value="addPurchase"/>
			<span class="my_error">${requestScope.errorMsg.msg}</span>
			
			<ul class="forminfo">
				<li>
					<label>材料名称</label>
					<input name="materialName" type="text" value="${form.materialName}" class="dfinput" />
					<span class="my_error">${requestScope.errorMsg.mNameError}</span>
				</li>
				
				<li>
					<label>材料材质</label>
				<input name="materialType" type="text" class="dfinput" value="${form.materialType}"/>
				<span class="my_error">${requestScope.errorMsg.mTypeError}</span>
				</li>
				
				<li>
					<label>采购数量</label>
					<input name="purCount" type="text" value="${form.purCount}" class="dfinput" />
					<span class="my_error">${requestScope.errorMsg.purCountError}</span>
				</li>
				
				<li>
					<label>采购金额</label>
					<input name="purPrice" type="text" class="dfinput easyui-numberbox" value="${form.purPrice}"
					style="height:34px;" data-options="min:0,precision:2,groupSeparator:',',prefix:'￥'"/>
					<span class="my_error">${requestScope.errorMsg.purPriceError}</span>
				</li>
				
				<li>
					<label>采购时间</label>
					<input type="text" name="purDate" class="dfinput date_style" value="${form == null?'采购时间':form.purDate}"
					onclick="JavaScript:this.value=''" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>
					<span class="my_error">${requestScope.errorMsg.purDateError}</span>
				</li>
				
				<li>
					<label>是否付款</label>
					<select name="isPay" class="dfinput" style="width:100px;margin-top:5px;">
						<option value="1" ${form.isPay ? 'chcked':''}>已付款</option>
						<option value="0" ${form.isPay ? '':'checked'}>未付款</option>
					</select>
					<span class="my_error">${requestScope.errorMsg.isPayError}</span>
				</li>
				
				<li>
					<label>付款类别</label>
					<select name="payMethod" class="dfinput" style="width:100px;margin-top:5px;">
						<option ${form.payMethod =='转账'? 'chcked':''}>转账</option>
						<option  ${form.payMethod =='现金'? 'checked':''}>现金</option>
					</select>
					<span class="my_error">${requestScope.errorMsg.payMethodError}</span>
				</li>
				
				<li>
					<label>申购部门</label>
					<select name="dname" id="select" class="dfinput" style="width:100px;margin-top:5px;">
						<option value="">===请选择===</option>
					</select>
					<span class="my_error">${requestScope.errorMsg.dIdError}</span>
				</li>
				
				<li>
					<label>&nbsp;</label>
					<input type="submit" class="btn" value="提交" />
				</li>
				
			</ul>
		</form>
		<script type="text/javascript">
				$.ajax({
					type:"post",
					url:"${basePath}/departmentAction.do",
					data:{"method":"asynGetDepartmentList"},
					dataType:"json",
					success:function(data){
						var options = $("#select").children();
						while(options.length > 1){
							options.eq(1).remove();
						}
						var str = '';
						for(var i = 0;i < data.length;i++){
							var dname = data[i].dname;
							str += "<option>" + dname +"</option>";
						}
						$("#select").append(str);
					},
					error:function(){
						alert("请求数据失败！");
					}
				});
			
		</script>
	</div>
	<p style="height:100px;"></p>

</body>

</html>




