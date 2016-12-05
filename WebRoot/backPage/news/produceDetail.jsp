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
			<h2>产品详细</h2>
		</div>
		<div class="main">
			<p class="short-input ue-clear newstyle">
				<label>灯具名称：</label>${form.proName}
			</p>
			<p class="short-input ue-clear newstyle">
				<label>灯具型号：</label>${form.proType}
			</p>
			<p class="long-input ue-clear newstyle">
				<label>灯具图片：</label><img src="${form.proImages}" alt="公司图片" height="200" width="100"/>
			</p>
			<p class="long-input ue-clear newstyle">
				<label>灯具类别：</label>${form.proCatogroy}
			</p>
			<p class="long-input ue-clear newstyle">
				<label>灯具材质 ：</label>${form.proMaterial}
			</p>
			<p class="short-input ue-clear newstyle">
					<label>产品产地:</label>${form.proArea}
			</p>
			<p class="short-input ue-clear newstyle">
					<label>产品摘要:</label>${form.proSummary}
			</p>
			<p class="short-input ue-clear newstyle">
					<label>产品详细描述:</label>${form.proDesc}
			</p>
			<p class="short-input ue-clear newstyle">
					<label>产品的基本参数:</label>${form.proParameter}
			</p>
			<p class="short-input ue-clear newstyle">
				<label>是否在前台显示：</label>${form.state?'显示':'不显示'}
			</p>
			<p class="short-input ue-clear newstyle">
				<a href="javascript:history.go(-1);" class="mySubmit">返回</a>
			</p>
		</div>
</div>
<p style="height:100px;"></p>

</body>

</html>
