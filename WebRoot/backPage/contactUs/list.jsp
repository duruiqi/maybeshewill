<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="${pageContext.request.contextPath}/backPage/css/style.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/frontPage/css/font-awesome.min.css" type="text/css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/backPage/js/jquery.js"></script>

<script type="text/javascript">
		
		function updateBefore(usId){
			if(!usId)return;
			window.location.href="${pageContext.request.contextPath}/contactUsAction.do?method=getContactUs&usId=" + usId;
		}
		
		$(function(){
			var contactUsMsg='${requestScope.contactUsMsg}';
			if(contactUsMsg)alert(contactUsMsg);
		});
</script>
<style type="text/css">
	#control,.delItems,#delete{display:none;};
	
</style>
</head>


<body style="min-width:800px;padding:5px;">

	<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li><a href="#">首页</a></li>
			<li><a href="#">公司信息</a></li>
			<li><a href="#">公司简介</a></li>
		</ul>
	</div>

	<div class="rightinfo">

		<h2 style="color:red;">${requestScope.msg}</h2>
		<table class="tablelist">
			<thead>
				<tr>
					<th>联系人</th>
					<th>邮箱</th>
					<th>qq</th>
					<th>传真</th>
					<th>邮编</th>
					<th>联系电话</th>					
					<th>联系地址</th>					
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
					<c:forEach items="${requestScope.contactUsList}" var="cu">
						<tr>
							<td>${cu.linkman}</td>
							<td>${cu.email}</td>
							<td>${cu.qq}</td>
							<td>${cu.fax}</td>
							<td>${cu.postcode}</td>
							<td>${cu.phone}</td>
							<td>${cu.linkAddr}</td>
							<td>
								<a href="javascript:void(0);" onclick="updateBefore('${cu.usId}');" class="tablelink">编辑</a>
							</td>
						</tr>
					</c:forEach>
			</tbody>
		</table>
	</div>
	
	
	<script type="text/javascript">
		$('.tablelist tbody tr:odd').addClass('odd');
	</script>
</body>

</html>
