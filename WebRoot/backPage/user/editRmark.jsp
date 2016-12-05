<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="${pageContext.request.contextPath}/backPage/css/style.css"
	rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/backPage/js/jquery.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/backPage/js/editor/kindeditor.js"></script>

<script type="text/javascript">
	KE.show({
		id : 'remark',
		cssPath : './index.css'
	});
</script>
<style type="text/css">
.mySubmit {
	background: #68B86C;
	font-size: 16px;
	padding: 3px;
	height: 36px;
	width: 150px;
	line-height: 30px;
	color:#fff;
	vertical-align:middle;
	-moz-border-radius:4px; -webkit-border-radius:4px; border-radius:4px;
	margin:50px 0px 0px 260px;
}
.mySubmit:hover{background: #559558;}
h3{margin-bottom:20px;}

</style>
</head>


<body style="min-width:800px;padding:5px;">

	<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li><a href="#">首页</a></li>
			<li><a href="#">会员</a></li>
			<li><a href="#">修改备注</a></li>
		</ul>
	</div>

	<div class="rightinfo" style="padding:20px;">
		<form action="${pageContext.request.contextPath}/userAction.do"
			method="post">
			<input type="hidden" name="method" value="modUserRemark" /> <input
				type="hidden" name="userId" value="${requestScope.user.userId}" />
			<h3>修改备注</h3>
			<textarea id="remark" name="remark"
				style="width:700px;height:250px;visibility:hidden;">
   				${requestScope.user.remark}
   			</textarea>
			<input type="submit" value="确认修改" class="mySubmit" />
		</form>
	</div>
</body>

</html>
