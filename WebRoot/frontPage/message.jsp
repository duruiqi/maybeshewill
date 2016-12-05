<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta name="keywords" content="成都义鸿照明有限公司" />
<meta name="description" content="成都义鸿照明有限公司" />
<title>成都义鸿照明有限公司会员注册</title>

<script type="text/javascript" src="${pageContext.request.contextPath}/backPage/js/jquery.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/backPage/js/jquery.validate.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/backPage/js/lang/messages_zh.min.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/frontPage/css/lcb_style.css" />
<link href="${pageContext.request.contextPath}/frontPage/css/master.css" type="text/css" rel="stylesheet" />
<link href="${pageContext.request.contextPath}/frontPage/css/base.css" type="text/css" rel="stylesheet" />
<script type="text/javascript">
	$(function(){
		var url = "${pageContext.request.contextPath}/userAction.do?method=drawValidateImg&nocache="
		+ new Date().getTime();
		$("#showCode").attr("src", url);
		$("#change").click(function() {
				url = "${pageContext.request.contextPath}/userAction.do?method=drawValidateImg&nocache="
				+ new Date().getTime();
				$("#showCode").attr("src", url);
			});
	});

</script>
<style type="text/css">
.my_error,.error {color: red;float:left;}
h3 a{color:blue;}
h3 a:hover{text-decoration: underline;}
</style>
</head>
<body id="nav_btn02">
	<div
		style="height:110px;width:auto;background:#0174C7;border:1px solid #0174C7;">
		<div id="logo">
			<h1>
				<a href="index.jsp"> <img src="images/logo.png" alt="义鸿照明"
					style="float:left;width:114px;height:40px;" /></a> <span
					class="logo_span"> 注册中心</span>
			</h1>
			<ul id="logo_right">

				<li class="zi"><a href="index.jsp" style="color:#fff">公司主页</a></li>

			</ul>
		</div>
	</div>
	<div id="body" style="height:580px;margin:20px;">
		<h3>${sessionScope.msg}</h3>
	</div>
	<!--body end-->
	<%@include file="./part/footer.jsp"%>
</body>
</html>
