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
		//验证表单数据
		function validate(Form){
		 $("#form").validate({
			    rules: {
			    	ename:{
			    	required: true,
			    	rangelength: [1,8]
			        	},
					email:{
				    required: true,
					rangelength: [5,32],
					email:true
				    	},
					phone:{
				    required: true,
					rangelength: [11],
						}
				    },
				    messages: {
				     ename: {
					 required: "请输入员工名字",
					 rangelength: "长度必须1~8之间"
					      },
					 email:{
				     required: "请输入邮箱地址",
				     rangelength: "长度必须5~32之间!",
					 email:"邮箱格式错误"
				      },
				     phone:{
					 required: "请输入手机号",
				     rangelength: "长度必须是11位!",
					  }
				 }
			});			
		}
	</script>
</head>


<body style="min-width:800px;padding:5px;">
	<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li><a href="#">员工管理</a></li>
			<li><a href="#">编辑信息</a></li>
		</ul>
	</div>
	<div class="formbody">
		<div class="formtitle">
			<span>编辑员工</span>
		</div>
		<form action="${pageContext.request.contextPath}/employeeAction.do" method="post" enctype="multipart/form-data">
			<input type="hidden" name="method" value="addEmployee" />
			<ul class="forminfo">
				<li>
					<label>员工名字</label>
					<input name="ename"  id="ename" type="text" class="dfinput" value="${emp.ename}" />
					<span class="my_error">${requestScope.errorMsg.pAddrError}</span>
				</li>
				
				<li>
					<label>直属上司</label>
					<input name="mgr" type="text" class="dfinput"  value="${emp.mgr}"/>
					<span class="my_error">${requestScope.errorMsg.pDetailError}</span>
				</li>
				
				<li>
					<label>进公司的时间</label>
					<input type="text" name="hiredate" class="dfinput date_style" value="${emp == null?'入职时间':emp.hiredate}"
					onclick="JavaScript:this.value=''" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>
					<span class="my_error">${requestScope.errorMsg.startDateError} ${requestScope.errorMsg.endDateError}</span>
					<br/>
				</li>
				<br/>
				<li>
					<label>性别</label>
					<select name="gender" class="dfinput">
									<option value="男">男</option>
									<option value="女">女</option>
					</select>
					<span class="my_error">${requestScope.errorMsg.pDetailError}</span>
				</li>
				<li>
					<label>生日</label>
					<input type="text" name="birthday" class="dfinput date_style" value="${emp == null?'生日':emp.birthday}"
					
					onclick="JavaScript:this.value=''" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>
					<span class="my_error">${requestScope.errorMsg.startDateError} ${requestScope.errorMsg.endDateError}</span>
				</li>				
				<li>
					<label>电话号码</label>
					<input name="phone" id="phone" type="text" class="dfinput"  value="${emp.phone}"/>
					<span class="my_error">${requestScope.errorMsg.pDetailError}</span>
				</li>				
				<li>
					<label>QQ</label>
					<input name="qq" type="text" class="dfinput"  value="${emp.qq}"/>
					<span class="my_error">${requestScope.errorMsg.pDetailError}</span>
				</li>
				<li>
					<label>email</label>
					<input name="email" id="email" type="text" class="dfinput"  value="${emp.email}"/>
					<span class="my_error">${requestScope.errorMsg.pDetailError}</span>
				</li>
				<li>
					<label>education</label>
					<input name="education" type="text" class="dfinput"  value="${emp.education}"/>
					<span class="my_error">${requestScope.errorMsg.pDetailError}</span>
				</li>	
				<li>
					<label>工作</label>
					<input name="job" type="text" class="dfinput"  value="${emp.job}"/>
					<span class="my_error">${requestScope.errorMsg.pDetailError}</span>
					<br/>
				</li>
				<br/>
				<li>
					<label>所在部门</label>
					<select name="did" class="dfinput">
						<c:forEach var="depart" items="${departList}">
							<c:choose>
								<c:when test="${emp.did==depart.dId}">
									<option value="${depart.dId}" selected="selected">${depart.dname}</option>
								</c:when>
								<c:otherwise>
									<option value="${depart.dId}">${depart.dname}</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</select>
					<span class="my_error">${requestScope.errorMsg.pDetailError}</span>
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
