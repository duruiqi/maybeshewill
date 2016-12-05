<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<link href="${pageContext.request.contextPath}/backPage/css/style.css" rel="stylesheet" type="text/css" />


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
							<a href="javascript:void(0);" onclick="updateBefore('${pOrder.orderId}');" class="tablelink">修改备注</a> 
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

	<!-- 分页 -->
		<%@include file="../paging.jsp"%>
	</div>
	<script type="text/javascript">
		$('.tablelist tbody tr:odd').addClass('odd');
	</script>
