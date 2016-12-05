<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="${pageContext.request.contextPath}/backPage/css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/backPage/js/jquery.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/backPage/js/WdatePicker.js"></script>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/backPage/themes/default/easyui.css" type="text/css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/backPage/themes/icon.css" type="text/css">
	<script type="text/javascript" src="/YHZM/backPage/js/jquery.easyui.min.js"></script>
	<title>修改信息</title>
<style type="text/css">
	.date_style{width:100px;color:gray;}
	.my_error{color:red;font-weight: bold;}
</style>
	<script type="text/javascript">
		$(function(){
			var Employeeinfo='${requestScope.Employeeinfo}';
			if(Employeeinfo)alert(Employeeinfo);
		});
		
		
	</script>
</head>


<body style="min-width:800px;padding:5px;">
	<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li><a href="#">薪资管理</a></li>
			<li><a href="#">编辑信息</a></li>
		</ul>
	</div>
	<div class="formbody">
		<div class="formtitle">
			<span>编辑薪资</span>
			
	<!--
			
	private Integer sid;//
	private Double sBase;//基本薪资
	private Double sCount;//计件提成
	private Double sBusiness;//业务提成
	private Employee employee;//外键 
			 -->
		</div>
		<form action="${pageContext.request.contextPath}/salaryAction.do" method="post" enctype="multipart/form-data">
			<input type="hidden" name="method" value="updateSalary" />
			<ul class="forminfo">
				<li>
					<label>薪水id</label>
					<input name="ename"  id="ename" type="text" class="dfinput" value="${salary.sid}" />
				</li>
				
				<li>
					<label>员工id</label>
					<input name="mgr" type="text" class="dfinput"  value="${salary.employee.eid}"/>
				</li>
				
				<li>
					<label>基本工资</label>
					<input name="mgr" type="text" class="dfinput"  value="${salary.sBase}"/>	
					<br/>
				</li>
				<br/>
				<li>
					<label>计件提成</label>
					<input name="mgr" type="text" class="dfinput"  value="${salary.sCount}"/>			
				</li>				
				<li>
					<label>业务提成</label>
					<input name="phone" id="phone" type="text" class="dfinput"  value="${salary.sBusiness}"/>
				</li>		
								<li>
					<label>&nbsp;</label>
					<input type="submit" class="btn" value="确认保存" onclick="return validate(this.form)"/>
				</li>		
			</ul>
		</form>
	</div>
</body>
</html>
