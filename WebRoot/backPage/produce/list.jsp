<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	request.setAttribute("basePath", request.getContextPath());
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

<script type="text/javascript">
		$(document).ready(function() {
			var data = {proIds:"",flag:false};//js对象
			$(".click").click(function() {
				$('.delItems input:checkbox').each(function(){
					
					if($(this).is(":checked")){
						data.flag = true;
						data.proIds += $(this).val() + ",";
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
				$('#proId').val(data.proIds);
				$('#delForm').submit();
				$(".tip").fadeOut(100);
			});
	
			$(".cancel").click(function() {
				$(".tip").fadeOut(100);
			});
		});
		function sepPage(destPage) {
			window.location.href = "${basePath}/produceAction.do?method=getProduceList&curPage="
					+ destPage;
		}
		function addBefore(){
			window.location.href="${basePath}/backPage/produce/add.jsp";
		}
	
		function deleteItems(proId){
			if(!proId)return;
			window.location.href="${basePath}/produceAction.do?method=delProduce&proId="+proId;
		}
		
		function updateBefore(proId){
			if(!proId)return;
			window.location.href="${basePath}/produceAction.do?method=getProduce&proId="+proId;
		}
		function detailBefore(proId){
			if(!proId)return;
			window.location.href="${basePath}/produceAction.do?method=getProduceDetail&proId="+proId;
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
			<li><a href="#">产品管理</a></li>
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
				
				<li id="batchDel">
					<a href="javascript:void(0);">
					<img src="${basePath}/backPage/images/t03.png" />批量删除</a>
				</li>
				<li id="delete" class="click" style="background:#F2E37C;">
					<a href="javascript:void(0);">
					<img src="${basePath}/backPage/images/t03.png" />删除选中项</a>
				</li>
			</ul>
			<h2 style="color:red;">${requestScope.msg}</h2>
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
		<form id="delForm" action="${basePath}/produceAction.do" method="post">
			<input type="hidden" name="method" value="delProduce"/>
			<input type="hidden" id="proId" name="proId" value=""/>
		<table class="tablelist">
			<thead>
				<tr>
					<th  id="control"><input type="checkbox" /></th>
					<th>灯具名称</th>
					<th>灯具型号</th>
					<th style="padding-bottom:5px;text-align:center;">灯具图片</th>
					<th>灯具类别</th>
					<th>产品摘要</th>
					<th>灯具材质</th>
					<th>是否在网站显示</th>
					<th>详细</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${requestScope.pagingBean.dataList}" var="produce">
					<tr>
						<td class="delItems"><input type="checkbox" value="${produce.proId}" /></td>
						<td>${produce.proName}</td>
						<td>${produce.proType}</td>
						<td style="text-align:center;padding:5px 0px 5px 0px;"><span><img src="${produce.proImages}" alt="产品图片" style="width:70px;height:120px;"/></span></td>
						<td>${produce.proCatogroy}</td>
						<td>${produce.proSummary}</td>
						<td>${produce.proMaterial}</td>
						<td>${produce.state?"显示":"不显示"}</td>
						<td><a href="javascript:void(0);" class="tablelink" onclick="detailBefore('${produce.proId}')">&gt;详细</a></td>
						<td>
							<a href="javascript:void(0);" onclick="updateBefore('${produce.proId}');" class="tablelink">编辑</a> 
							<a href="javascript:void(0);"  onclick="deleteItems('${produce.proId}');" class="tablelink">删除</a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</form>
<p style="margin-top:20px;"><i class="fa fa-warning" style="color:red;font-size:16px;">
			</i>注意！<strong style="color:red;">是否启用</strong>栏必须有一行的指定为启用，否则该数据将无法在网站显示！</p>
	<!-- 分页 -->
		<%@include file="../paging.jsp"%>


		<div class="tip">
			<div class="tiptop">
				<span>提示信息</span><a></a>
			</div>

			<div class="tipinfo">
				<span><img src="${basePath}/backPage/images/ticon.png" /></span>
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
