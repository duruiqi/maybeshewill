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
			var newsInfo='${requestScope.newsInfo}';
			if(newsInfo)alert(newsInfo);
		});
	</script>
</head>

<body style="min-width:800px;padding:5px;">

	<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li><a href="#">首页</a></li>
			<li><a href="#">修改新闻</a></li>
		</ul>
	</div>

	<div class="formbody">

		<div class="formtitle">
			<span>基本信息</span>
		</div>
		<form action="${pageContext.request.contextPath}/newsAction.do" method="post" enctype="multipart/form-data">
			<input type="hidden" name="method" value="modNews"/>
			<input type="hidden" name="nid" value="${form.nid}"/>
			<span class="my_error">${requestScope.errorMsg.msg}</span>
			
			<ul class="forminfo">
					<li>
					<label>新闻的标题</label>
					<input name="nTitle" type="text" value="${form.nTitle}" class="dfinput" />
					<span class="my_error">${requestScope.errorMsg.nTitleError}</span>
				</li>
						
				<li>
					<label>新闻的内容</label>
					<textarea rows="15" cols="100" style="border:1px solid #95B8E7;font-size:16px; margin-top:10px;" 
					name="nContent">${form.nContent}</textarea>
					<span class="my_error">${requestScope.errorMsg.nContentError}</span>
				</li>
				
				<li>
					<label>新闻图片</label>
					<input name="image" type="file" />
					<span class="my_error">${requestScope.errorMsg.imgError}</span>
				</li>
				<li>
					<label>新闻日期</label>
					<input type="text" name="nDate" class="dfinput date_style" value="${form == null?'新闻日期':form.nDate}"
					onclick="JavaScript:this.value=''" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>
					<span class="my_error">${requestScope.errorMsg.nDateError}</span>
				</li>
				
				<li>
					<label>客户单位</label>
					<input name="customerUnit" type="text" class="dfinput" value="${form.customerUnit}"/>
					<span class="my_error">${requestScope.errorMsg.cUnitError}</span>
				</li>
				
				<li>
					<label>是否展示</label>
					<select name="state" class="dfinput" style="width:100px;">
						<option value="1" ${form.state == '1'?'selected':''}>展示</option>
						<option value="0" ${form.state == '0'?'selected':''}>不展示</option>
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