<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
    pageContext.setAttribute("basePath", request.getContextPath()) ;
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="${basePath}/backPage/css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${basePath}/backPage/js/jquery.js"></script>
<style type="text/css">
	#control,.delItems,#delete{display:none;};
	
</style>
</head>


<body style="min-width:800px;padding:5px;">

	<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li><a href="#">首页</a></li>
			<li><a href="#">后勤管理</a></li>
			<li><a href="#">食宿</a></li>
		</ul>
	</div>

	<div class="rightinfo">
	<table class="tablelist">
			<thead>
				<tr>
					<th>职务</th>
					<th>姓名</th>
					<th>联系电话</th>
				</tr>
			</thead>
		
			<tbody>
				<c:forEach items="${requestScope.empList}" var="emp">
					<tr>
						<td>${emp.job}</td>
						<td>${emp.ename}</td>
						<td>${emp.phone}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>


	</div>
</body>

</html>





