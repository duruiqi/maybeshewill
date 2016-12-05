<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>员工列表</title>
<link href="${pageContext.request.contextPath}/backPage/css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/backPage/js/jquery.js"></script>

<script type="text/javascript">
		$(function(){
			var Employeeinfo='${requestScope.Employeeinfo}';
			if(Employeeinfo)alert(Employeeinfo);
		});
		$(document).ready(function() {
			var data = {orderIds:"",flag:false};//js对象
			$(".click").click(function() {
				$('.delItems input:checkbox').each(function(){
					
					if($(this).is(":checked")){
						data.flag = true;
						data.orderIds += $(this).val() + ",";
					}
				});
				if(data.flag){
					$(".tip").fadeIn(200);
				}else{
					alert("请选择要删除的行！");
				}
			});
	
			$(".tiptop a").click(function() {
				$(".tip").fadeOut(200);
			});
			
			$(".sure").click(function() {
				alert($('#orderId').val());
				$('#delForm').submit();
				$(".tip").fadeOut(100);
			});
	
			$(".cancel").click(function() {
				$(".tip").fadeOut(100);
			});
		});
		function sepPage(destPage) {
			window.location.href = "${pageContext.request.contextPath}/employeeAction.do?method=getEmployeeList&curPage="
					+ destPage;
		}
		function addBefore(){
			window.location.href="${pageContext.request.contextPath}/employeeAction.do?method=addBefore";
		}
	
		function deleteItems(eid){
			if(!eid)return;
			window.location.href="${pageContext.request.contextPath}/employeeAction.do?method=delEmployee&eid="+eid;
		}
		
		function updateBefore(eid){
			if(!orderId)return;
			window.location.href="${pageContext.request.contextPath}/employeeAction.do?method=getEmployee&eid="+eid;
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
			<li><a href="#">员工管理</a></li>
			<li><a href="#">员工列表</a></li>
		</ul>
	</div>
	<div class="rightinfo">
		<div class="tools">
			<ul class="toolbar">
				<li>
					<a href="javascript:void(0);" onclick="addBefore()">
					<img src="${pageContext.request.contextPath}/backPage/images/t01.png" />添加</a>
				</li>
				
				<li id="batchDel">
					<a href="javascript:void(0);">
					<img src="${pageContext.request.contextPath}/backPage/images/t03.png" />批量删除</a>
				</li>
				<li id="delete" class="click" style="background:#F2E37C;">
					<a href="javascript:void(0);">
					<img src="${pageContext.request.contextPath}/backPage/images/t03.png" />删除选中项</a>
				</li>
			</ul>
			<h2 style="color:red;">${sessionScope.msg}</h2>
		</div>
		<script type="text/javascript">
			$(function(){
				$('#batchDel').click(function(){
					$('#control,.delItems').show();
					$(this).hide();
					$("#delete").show();
				});
				
				$('#control input').click(function(){
					//alert($(this).attr("checked"));
					if($(this).attr("checked")){
						$('.delItems input:checkbox').attr("checked","checked");
					}else{
						$('.delItems input:checkbox').attr("checked",null);
					} 
				});
								
			});
		</script>
		<form id="delForm" action="${pageContext.request.contextPath}/employee.do" method="post">
			<input type="hidden" name="method" value="delEmployee"/>
			<input type="hidden" id="orderId" name="eid" value="${emp.eid}"/>
		<table class="tablelist">
			<thead>
				<tr>
					<th  id="control"><input type="checkbox" /></th>
					<th>员工id</th>
					<th>员工名字</th>
					<th>直属上司</th>
					<th>进公司的时间</th>
					<th>性别</th>
					<th>生日</th>
					<th>电话号码</th>
					<th>QQ</th>
					<th>email</th>
					<th>education</th>
					<th>所属部门</th>
					<th>职位</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${requestScope.pagingBean.dataList}" var="emp">
					<tr>
					<td>${emp.eid}</td>
					<td>${emp.ename}</td>
						<c:forEach var="dept" items="${departList}">
							<c:choose>
								<c:when test="${emp.department.dId==dept.dId}">
								<td>${dept.principal}</td>
								</c:when>
							</c:choose>
						</c:forEach>
					<%-- <td>${emp.mgr}</td> --%>
					<td>${emp.hiredate}</td>
					<td>${emp.gender}</td>
					<td>${emp.birthday}</td>
					<td>${emp.phone}</td>
					<td>${emp.qq}</td>
					<td>${emp.email}</td>
					<td>${emp.education}</td>
						<c:forEach var="depart" items="${departList}">
							<c:choose>
								<c:when test="${emp.department.dId==depart.dId}">
								<td>${depart.dname}</td>
								</c:when>
							</c:choose>
						</c:forEach>
						<td>${emp.job}</td>
					<td>
						<a href="javascript:void(0);" onclick="updateBefore('${emp.eid}');" class="tablelink">编辑</a> 
						<a href="javascript:void(0);"  onclick="deleteItems('${emp.eid}');" class="tablelink">删除</a>
					</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</form>

	<!-- 分页 -->
		<%@include file="../paging.jsp"%>


		<div class="tip">
			<div class="tiptop">
				<span>提示信息</span><a></a>
			</div>

			<div class="tipinfo">
				<span><img src="${pageContext.request.contextPath}/backPage/images/ticon.png" /></span>
				<div class="tipright">
					<p>是否确认删除 ？</p>
					<cite>如果是请点击确定按钮 ，否则请点取消。</cite>
				</div>
			</div>
			<div class="tipbtn">
				<input name="" type="button" class="sure" value="确定" />&nbsp; <input
					name="" type="button" class="cancel" value="取消" />
			</div>
		</div>
	</div>
	<script type="text/javascript">
		$('.tablelist tbody tr:odd').addClass('odd');
	</script>
</body>
</html>
