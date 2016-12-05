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
		
		function addBefore(){
			window.location.href="${pageContext.request.contextPath}/backPage/companyInfo/add.jsp";
		}
	
		function deleteItems(firmId){
			if(!firmId)return;
			window.location.href="${pageContext.request.contextPath}/companyInfoAction.do?method=delFirmInfo&firmId="+firmId;
		}
		
		function updateBefore(firmId){
			if(!firmId)return;
			window.location.href="${pageContext.request.contextPath}/companyInfoAction.do?method=getFirmInfo&firmId="+firmId;
		}
		
		function manageImgBefore(firmId){
			if(!firmId)return;
			window.location.href="${pageContext.request.contextPath}/companyInfoAction.do?method=getFirmInfo&flag=updateImg&firmId="+firmId;
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
			<li><a href="#">公司信息</a></li>
			<li><a href="#">公司简介</a></li>
		</ul>
	</div>

	<div class="rightinfo">

		<div class="tools">
			<ul class="toolbar">
				<li>
					<a href="javascript:void(0);" onclick="addBefore()">
					<img src="${pageContext.request.contextPath}/backPage/images/t01.png" />添加</a>
				</li>
			</ul>
			<h2 style="color:red;">${requestScope.msg}</h2>
		</div>
		<table class="tablelist">
			<thead>
				<tr>
					<th>公司名称</th>
					<th>成立时间</th>
					<th>注册资金</th>
					<th>公司地址</th>
					<th>是否启用</th>
					<th>详细信息</th>	
					<th>图片操作</th>	
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
					<c:forEach items="${requestScope.firmList}" var="firm">
						<tr>
							<td>${firm.firmName}</td>
							<td>${firm.founds}</td>
							<td>${firm.loginFund}</td>
							<td>${firm.firmAddr}</td>
							<td>${firm.state eq true ?'启用':'未启用'}</td>
							<td><a href="${pageContext.request.contextPath}/companyInfoAction.do?method=getFirmInfo&flag=detail&firmId=${firm.firmId}" class="tablelink" >&gt;详细</a></td>
							<td><a href="javascript:void(0);" onclick="manageImgBefore('${firm.firmId}');" class="tablelink">管理</a></td>
							<td>
								<a href="javascript:void(0);" onclick="updateBefore('${firm.firmId}');" class="tablelink">编辑</a>
								<c:if test="${fn:length(requestScope.firmList) > 1}">
									<a href="javascript:void(0);" onclick="deleteItems('${firm.firmId}');" class="tablelink">删除</a>
								</c:if>
							</td>
						</tr>
					</c:forEach>
			</tbody>
		</table>
		<p style="margin-top:20px;"><i class="fa fa-warning" style="color:red;font-size:16px;"></i>注意！<strong style="color:red;">是否启用</strong>栏必须有一行的公司信息指定为启用，否则公司网站的公司简介信息将无法显示！</p>
	</div>
	
	
	<script type="text/javascript">
		$('.tablelist tbody tr:odd').addClass('odd');
	</script>
</body>

</html>
