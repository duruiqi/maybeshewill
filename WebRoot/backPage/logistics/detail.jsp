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
			<h2>公司基本信息</h2>
		</div>
		<div class="main">
			<p class="short-input ue-clear newstyle">
				<label>公司名称：</label>${requestScope.firm.firmName}
			</p>
			<p class="short-input ue-clear newstyle">
				<label>公司成立时间：</label>${requestScope.firm.founds}
			</p>
			<p class="long-input ue-clear newstyle">
				<label>注册资金：</label>${requestScope.firm.loginFund}
			</p>
			<p class="long-input ue-clear newstyle">
				<label>公司图片：</label>
				<c:forEach items="${requestScope.firm.imgAddrs}" var="img">
					<img src="${img}" alt="公司图片" height="100" width="100"/>
				</c:forEach>
			</p>
			<p class="long-input ue-clear newstyle">
				<label>公司地址：</label>${requestScope.firm.firmAddr}
			</p>
			<p class="short-input ue-clear newstyle">
				<label>网站备案信息：</label>${requestScope.firm.beiAnInfo}
			</p>
			<p class="short-input ue-clear newstyle">
					<label>公司介绍:</label>${requestScope.firm.introduce}
			</p>
			<p class="short-input ue-clear newstyle">
				<label>是否启用：</label>${requestScope.firm.state eq true ?'启用':'未启用'}
			</p>
			<p class="short-input ue-clear newstyle">
				<a href="javascript:history.go(-1);" class="mySubmit">返回</a>
			</p>
			
		</div>

</div>
<p style="height:100px;"></p>

</body>

</html>
