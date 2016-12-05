<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	request.setAttribute("basePath", request.getContextPath());
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="${basePath}/backPage/css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${basePath}/backPage/js/jquery.js"></script>

<script type="text/javascript">
		function addBefore(){
			window.location.href="${basePath}/backPage/mainBanner/add.jsp";
		}
	
		function deleteItems(bId){
			if(!bId)return;
			window.location.href="${basePath}/mainBannerAction.do?method=delMainBanner&bId=" + bId;
		}
		
		function updateBefore(bId){
			if(!bId)return;
			window.location.href="${basePath}/mainBannerAction.do?method=getMainBanner&bId=" + bId;
		}
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
			<li><a href="#">前台幻灯片</a></li>
			<li><a href="#">基本内容</a></li>
		</ul>
	</div>

	<div class="rightinfo">
		<div class="tools">
			<ul class="toolbar">
				<li>
					<a href="javascript:void(0);" onclick="addBefore()">
					<img src="${basePath}/backPage/images/t01.png" />添加</a>
				</li>
			</ul>
			<h2 style="color:red;">${requestScope.msg}</h2>
		</div>
		<table class="tablelist">
			<thead>
				<tr>
					<th style="padding-bottom:5px;text-align:center;">广告图片</th>
					<th>间隔时间</th>
					<th>是否在网站显示</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${requestScope.mainBannerList}" var="mainBanner">
					<tr>
						<td style="text-align:center;padding:5px 0px 5px 0px;"><span><img src="${mainBanner.bannerImg}" alt="图片" style="width:276px;height:71px;"/></span></td>
						<td>${mainBanner.timeslice}</td>
						<td>${mainBanner.state?"显示":"不显示"}</td>
						<td>
							<a href="javascript:void(0);" onclick="updateBefore('${mainBanner.bId}');" class="tablelink">编辑</a> 
							<a href="javascript:void(0);"  onclick="deleteItems('${mainBanner.bId}');" class="tablelink">删除</a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	<p style="margin-top:20px;"><i class="fa fa-warning" style="color:red;font-size:16px;">
			</i>注意！<strong style="color:red;">是否启用</strong>栏必须有一行的指定为启用，否则该数据将无法在网站显示！</p>

</div>


	<script type="text/javascript">
		$('.tablelist tbody tr:odd').addClass('odd');
	</script>
</body>

</html>
