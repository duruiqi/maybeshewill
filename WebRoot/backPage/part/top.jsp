<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="../css/style.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" src="../js/jquery.js"></script>
<script src="../js/Clock.js" type="text/javascript"></script>
<script type="text/javascript">
	$(function() {
		//顶部导航切换
		$(".nav li a").click(function() {
			$(".nav li a.selected").removeClass("selected");
			$(this).addClass("selected");
		});
	});
</script>


</head>

<body style="background:url(../images/topbg.gif) repeat-x;min-width:1200px;">
	<div class="topleft">
		<a href="main.html" target="_parent"> 
		<img src="../images/logo.png" title="系统首页" /></a>
	</div>

	<ul class="nav">		
		<li>
			<a href="${pageContext.request.contextPath}/backPage/alterPassword.jsp" target="rightFrame" class="selected">
				<img src="../images/d-icon03.png"  title="修改密码" />
				<h2>修改密码</h2>
			</a>
		</li>
		<li>
			<a href="default.html" target="rightFrame" class="selected">
				<img src="../images/useradd.png" height="48" width="48" title="角色管理" />
				<h2>角色管理</h2>
			</a>
		</li>
		<li>
			<a href="${pageContext.request.contextPath}/userAction.do?method=getUserList" target="rightFrame" class="selected">
				<img src="../images/vip.ico" title="会员信息" />
				<h2>会员信息</h2>
			</a>
		</li>
		<li>
			<a href="${pageContext.request.contextPath}/mapAction.do?method=getMapData&mapId=1&flag=backPage" target="rightFrame" class="selected">
				<img src="../images/map.png" title="地图设置" />
				<h2>地图设置</h2>
			</a>
		</li>
			
	</ul>
	<div class="topright">
		<ul>
			<li><span><img src="../images/help.png" title="帮助"
					class="helpimg" /></span><a href="#">帮助</a></li>
			<li><a href="#">关于</a></li>
			<li><a href="${pageContext.request.contextPath}/adminAction.do?method=quit" target="_parent">退出</a></li>
		</ul>

		<div class="user">
			<span>admin</span> <i>消息</i> <b>5</b>
		</div>
	</div>

	<ul class="top_right2">
		<li><a href="javascript:history.go(-1);">
			<img src="../images/nav_back.gif" align="absMiddle" border="0">后退</a></li>
		<li><a href="javascript:history.go(1);">
			<img src="../images/nav_forward.gif" align="absMiddle" border="0">前进</a></li>
		<li><span id=clock></span></li>
	</ul>
	<div class="cls"></div>
	<script type="text/javascript">
		var clock = new Clock();
		clock.display(document.getElementById("clock"));
	</script>

</body>
</html>