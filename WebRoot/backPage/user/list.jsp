<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>

<link href="${pageContext.request.contextPath}/backPage/css/base.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/backPage/css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/backPage/js/jquery.js"></script>
<script type="text/javascript">
		
		function sepPage(destPage) {
			window.location.href = "${pageContext.request.contextPath}/userAction.do?method=getUserList&curPage="
					+ destPage;
		}
		
		function updateBefore(userId){
			if(!userId)return;
			window.location.href="${pageContext.request.contextPath}/userAction.do?method=getUser&userId="+userId;
		}
		
		$(function(){
			$("#mySubmit").click(function(){
				var url = "${pageContext.request.contextPath}/userAction.do";
				var params = $("#form").serialize();
				$.ajax({
					url:url,
					type:"post",
					data:params,
					dataType:"html",
					success:function(data){
						$("#needAsysPart").children().remove();
						$("#needAsysPart").children().empty();
						$("#needAsysPart").append(data);
					},error:function(){
						alert("请求数据失败！");
					}
					
				});
			});
		});
</script>
<style type="text/css">
	.queryTable{min-width:800px;}
	.queryTable tr td{height:40px;line-height:40px;padding:3px;}
	.queryTable tr td select{width:120px;}
	.queryTable tr td input{width:150px;}
	.alignLeft{text-align:left;}
	#mySubmit{width:140px;background:#3D96C9;
			text-align:center;font-size:16px;color:#fff;
			display:block;height:35px;
			-moz-border-radius:4px; -webkit-border-radius:4px; border-radius:4px;
	}
	#mySubmit:hover{color:#fff;}
</style>
</head>


<body style="min-width:800px;padding:5px;">

	<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li><a href="#">首页</a></li>
			<li><a href="#">会员信息</a></li>
			<li><a href="#">基本内容</a></li>
		</ul>
	</div>

<div class="rightinfo">
		<form id="form">
			<input type="hidden" name="method" value="asynGetUserList"/>
			<table class="queryTable">
				<tr>
					<td class="alignLeft">会员名字</td>
					<td class="alignLeft">
						<input name="userName" type="text" class="dfinput" />
					</td>
					
					<td class="alignLeft">是否激活</td>
					<td class="alignLeft">
						<select name="state" class="dfinput">
							<option value="">===请选择===</option>
							<option value="1">激活</option>
							<option value="0">未激活</option>
						</select>
					</td>
					
				</tr>
				<tr>
					<td class="alignLeft">是否登录</td>
					<td class="alignLeft">
						<select name="loginState" class="dfinput">
							<option value="">==请选择===</option>
							<option value="1">登录</option>
							<option value="0">未登录</option>
						</select>
					</td>
					
					<td class="alignLeft">备注</td>
					<td class="alignLeft"><input name="remark" type="text" class="dfinput" /></td>
					
				</tr>
				<tr>
					<td class="alignLeft" colspan="2"><a href="javascript:void(0);" id="mySubmit">提交</a></td>
					<td class="alignLeft" colspan="2"><input type="reset" class="btn"  value="重置" /></td>
				</tr>
			</table>
		</form>
		<div id="needAsysPart">
		<table class="tablelist" style="margin-top:20px;">
			<thead>
				<tr>
					<th>用户名</th>
					<th>邮箱</th>
					<th>激活状态</th>
					<th>登录状态</th>
					<th>备注</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${requestScope.pagingBean.dataList}" var="user">
					<tr>
						<td>${user.userName }</td>
						<td>${user.email }</td>
						<td>${user.state eq true ?'激活':'未激活'}</td>
						<td>${user.loginState eq true ?'登录':'未登录'}</td>
						<td>${user.remark }</td>
						<td>
							<a href="javascript:void(0);" onclick="updateBefore('${user.userId}');" class="tablelink">修改备注</a> 
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

	<!-- 分页 -->
		<%@include file="../paging.jsp"%>
	</div>
</div>
	<script type="text/javascript">
		$('.tablelist tbody tr:odd').addClass('odd');
	</script>
</body>

</html>
