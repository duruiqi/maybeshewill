<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

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
<title>编辑部门</title>
<style type="text/css">
	.date_style{width:100px;color:gray;}
	.my_error{color:red;font-weight: bold;}
</style>
	<script type="text/javascript">
		$(function(){
			var departmentInfo='${requestScope.departmentInfo}';
			if(departmentInfo)alert(departmentInfo);
		});
		
		//验证表单数据
		function validate(Form){
			var dname=Form.dname.value;
			var principal=Form.principal.value;
			var found=Form.found.value;
			
			if(dname==""){
				alert("部门名称必须填写!");
				return false;
			}
			
			if(principal==""){
				alert("部门负责人必须填写!");
				return false;
			}
			if(found==""){
				alert("成立时间必须填写!");
				return false;
			}
		}
	</script>
</head>

<body style="min-width:800px;padding:5px;">
	<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li><a href="#">首页</a></li>
			<li><a href="#">编辑部门</a></li>
		</ul>
	</div>
	<div class="formbody">
		<div class="formtitle">
			<span>编辑部门</span>
		</div>
		<form action="${pageContext.request.contextPath}/departmentAction.do" method="post" enctype="multipart/form-data">
			<input type="hidden" name="method" value="modDepartment"/>
			<input name="dId" type="hidden" class="dfinput" value="${department.dId}" />
			<ul class="forminfo">
				<li>
					<label>部门名称</label>
					<input name="dname" type="text" class="dfinput" value="${department.dname}" />
					<span class="my_error">${requestScope.errorMsg.pAddrError}</span>
				</li>
				<li>
					<label>部门负责人</label>
					<input name="principal" type="text" class="dfinput"  value="${department.principal}"/>
					<span class="my_error">${requestScope.errorMsg.pDetailError}</span>
				</li>
				<li>
					<label>成立时间</label>
					<input type="text" name="found" class="dfinput date_style" value="${department == null?'成立时间':department.found}"
					onclick="JavaScript:this.value=''" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>
					<span class="my_error">${requestScope.errorMsg.startDateError} ${requestScope.errorMsg.endDateError}</span>
				</li>
				
				<li>
					<label>&nbsp;</label>
					<input type="submit" class="btn" value="确认保存" onclick="return validate(this.form)"/></li>
			</ul>
		</form>
	</div>
</body>
</html>
