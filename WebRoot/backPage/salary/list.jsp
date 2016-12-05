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
			var salinfo='${requestScope.salinfo}';
			if(salinfo)alert(salinfo);
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
			window.location.href = "${pageContext.request.contextPath}/salaryAction.do?method=getSalaryList&curPage="
					+ destPage;
		}
		function addBefore(){
			window.location.href="${pageContext.request.contextPath}/salaryAction.do?method=addBefore";
		}
	
		function deleteItems(eid){
			if(!eid)return;
			window.location.href="${pageContext.request.contextPath}/salaryAction.do?method=delEmployee&eid="+eid;
		}
		
		function updateBefore(eid){
			if(!orderId)return;
			window.location.href="${pageContext.request.contextPath}/salaryAction.do?method=getSalary&sid="+eid;
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
		<form id="delForm" action="${pageContext.request.contextPath}/salaryAction.do" method="post">
			<input type="hidden" name="method" value="delsalary"/>
			<input type="hidden" id="orderId" name="sid" value="${emp.eid}"/>
		<table class="tablelist">
			<thead>
				<tr>
					<th  id="control"><input type="checkbox" /></th>
					<th>薪资id</th>
					<th>名字</th>
					<th>基本薪资</th>
					<th>计件提成</th>
					<th>业务提成</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${requestScope.pagingBean.dataList}" var="sal">
				<tr>
				<td>${sal.sid}</td>
<%-- 				<c:forEach items="${requestScope.emplist}" var="emplist">
				<c:choose>
					<c:when test="${sal.employee.eid==emplist.eid}">
						<td>${emplist.ename }</td>
					</c:when>
			    </c:choose>
				</c:forEach> --%>
				<td>${sal.employee.eid}</td>
				<td>${sal.sBase}</td>
				<td>${sal.sCount}</td>
				<td>${sal.sBusiness}</td>
					<td>
						<a href="javascript:void(0);" onclick="updateBefore('${sal.sid}');" class="tablelink">编辑</a> 
						<a href="javascript:void(0);"  onclick="deleteItems('${sal.sid}');" class="tablelink">删除</a>
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
