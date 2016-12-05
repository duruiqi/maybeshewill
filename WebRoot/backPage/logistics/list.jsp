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

<script type="text/javascript">
		$(document).ready(function() {
			var data = {purIds:"",flag:false};//js对象
			$(".click").click(function() {
				$('.delItems input:checkbox').each(function(){
					
					if($(this).is(":checked")){
						data.flag = true;
						data.purIds += $(this).val() + ",";
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
				$('#purId').val(data.purIds);
				$('#delForm').submit();
				$(".tip").fadeOut(100);
			});
	
			$(".cancel").click(function() {
				$(".tip").fadeOut(100);
			});
		});
		function sepPage(destPage) {
			window.location.href = "${basePath}/logisticsAction.do?method=getPurchaseList&curPage="
					+ destPage;
		}
		function addBefore(){
			window.location.href="${basePath}/backPage/logistics/add.jsp";
		}
	
		function deleteItems(purId){
			if(!purId)return;
			window.location.href="${basePath}/logisticsAction.do?method=delPurchase&purId="+purId;
		}
		
		function updateBefore(purId){
			if(!purId)return;
			window.location.href="${basePath}/logisticsAction.do?method=getPurchase&purId="+purId;
		}
		
		function exportBefore(){
			window.location.href="${basePath}/logisticsAction.do?method=exportExcel";
		
		}
		
		$(function(){
			var msg='${requestScope.msg}';
			if(msg)alert(msg);
		});
		
</script>
<script type="text/javascript">
	$(function(){
		$("#excelSubmit").click(function(){
			$("#excleFrom").submit();
		});
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
			<li><a href="#">后勤管理</a></li>
			<li><a href="#">采购</a></li>
		</ul>
	</div>

	<div class="rightinfo">

		<div class="tools">
			<form id="excleFrom" action="${basePath}/logisticsAction.do" method="post" enctype="multipart/form-data">
			<input type="hidden" name="method" value="importExcel"/>
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
				<li style="padding-left:5px;">
					<input type="file" name="excleFile" style="width:130px;"/>
					<a href="javascript:void(0);" id="excelSubmit">
					<img src="${basePath}/backPage/images/excelImport.png"/>导入excel表</a>
				</li>
				<li>
					<a href="javascript:void(0);" onclick="exportBefore()">
					<img src="${basePath}/backPage/images/excelExport.png"/>导出为excel表</a>
				</li>
			</ul>
			</form>
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
		<form id="delForm" action="${basePath}/logisticsAction.do" method="post">
			<input type="hidden" name="method" value="delPurchase"/>
			<input type="hidden" id="purId" name="purId" value=""/>
				<table class="tablelist">
			<thead>
				<tr>
					<th  id="control"><input type="checkbox" /></th>
					<th>采购单号</th>
					<th>材料名称</th>
					<th>材料材质</th>
					
					<th>采购数量</th>
					<th>采购时间</th>	
					<th>采购金额</th>
					
					<th>是否付款</th>
					<th>付款类别</th>	
					
					<th>申请部门</th>						
					<th>操作</th>
				</tr>
			</thead>
		
			<tbody>
					<c:forEach items="${requestScope.pagingBean.dataList}" var="purchase">
						<tr>
							<td class="delItems"><input type="checkbox" value="${purchase.purId}" /></td>
							<td>${purchase.purId}</td>
							<td>${purchase.materialName}</td>
							<td>${purchase.materialType}</td>
							<td>${purchase.purCount}</td>
							<td>${purchase.purDate}</td>
							<td><fmt:formatNumber value="${purchase.purPrice}" var="myPrice" pattern="###,###,###,###.00"/>
								￥${myPrice}元
							</td>
							<td>${purchase.isPay?'已付款':'未付款'}</td>
							<td>${purchase.payMethod}</td>
							<td>${purchase.department.dname}</td>
							<td>
								<a href="javascript:void(0);" onclick="updateBefore('${purchase.purId}');" class="tablelink">编辑</a>
								<a href="javascript:void(0);" onclick="deleteItems('${purchase.purId}');" class="tablelink">删除</a>
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





