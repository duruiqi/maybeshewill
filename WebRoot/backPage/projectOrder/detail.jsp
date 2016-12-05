<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/backPage/css/base.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/backPage/css/info-reg.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/backPage/js/jquery.js"></script>

<title></title>
<style type="text/css">
.date_style {
	width: 100px;
	color: gray;
}

.my_error {
	color: red;
	font-weight: bold;
}
	.mySubmit{
		background: #68B86C;
		font-size: 16px;
		padding: 3px;
		height: 30px;
		width: 70px;
		line-height: 30px;
		color:#fff;
		vertical-align:middle;
		-moz-border-radius:4px;
		 -webkit-border-radius:4px; 
		 border-radius:4px;
		margin:10px;
		display:block;
		text-align: center;
	}
	.mySubmit:hover{background: #559558;}
</style>
<script type="text/javascript">
	$(function() {
		var projectOrderInfo = '${requestScope.projectOrderInfo}';
		if (projectOrderInfo)
			alert(projectOrderInfo);
	});
</script>
</head>

<body style="min-width:800px;padding:5px;">

	<div class="formbody">
		<div class="title">
			<h2>工程详细</h2>
		</div>
		<div class="main">
			<p class="short-input ue-clear newstyle">
				<label>工程名称：</label>${form.projectName}
			</p>
			<p class="short-input ue-clear newstyle">
				<label>工程类别：</label>${form.projectType}
			</p>
			<p class="long-input ue-clear newstyle">
				<label>工程图片：</label><img src="${form.projectImg}" alt="公司图片" height="200" width="200"/>
			</p>
			<p class="long-input ue-clear newstyle">
				<label>工程地址：</label>${form.projectAddr}
			</p>
			<p class="short-input ue-clear newstyle">
					<label>工程定价:</label>${form.projectPrice}
			</p>
			<p class="short-input ue-clear newstyle">
					<label>工程详细:</label>${form.projectDetail}
			</p>
			<p class="short-input ue-clear newstyle">
					<label>工程时间:</label>开始时间：${form.startDate} 结束时间：${form.endDate}
			</p>
			<p class="short-input ue-clear newstyle">
					<label>客户单位:</label>${form.customerUnit}
			</p>
			<p class="short-input ue-clear newstyle">
				<label>工程负责人：</label>${form.employee.ename}
			</p>
			<p class="short-input ue-clear newstyle">
				<a href="javascript:history.go(-1);" class="mySubmit">返回</a>
			</p>
			
		</div>
</div>
<p style="height:100px;"></p>

</body>

</html>
