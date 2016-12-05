<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/backPage/css/base.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/backPage/css/info-reg.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/backPage/js/jquery.js"></script>



<title></title>
<style type="text/css">
.date_style {
	width: 100px;
	color: gray;
}

.my_error {
	color: red;
	font-weight: bold;
}
	.mySubmit{
		background: #68B86C;
		font-size: 16px;
		padding: 3px;
		height: 30px;
		width: 70px;
		line-height: 30px;
		color:#fff;
		vertical-align:middle;
		-moz-border-radius:4px;
		 -webkit-border-radius:4px; 
		 border-radius:4px;
		margin:10px;
		display:block;
		text-align: center;
	}
	.mySubmit:hover{background: #559558;}
	.myImg{float:left;width:100px;margin-right:20px;}
</style>
</head>

<body style="min-width:800px;padding:5px;">

	<div class="formbody">
		<div class="title">
			<h2>删除公司图片</h2>
		</div>
		<div class="main">
		
			<form id="form" action="${pageContext.request.contextPath}/companyInfoAction.do" method="post">
				<input type="hidden" name="method" value="delFirmImg"/>
				<input type="hidden" name="firmId" value="${requestScope.firm.firmId}"/>
				<input type="hidden" name="flag" value="updateImg"/>
				<p class="short-input ue-clear newstyle">
					<label>公司名称：</label>${requestScope.firm.firmName}
				</p>
				<div class="long-input ue-clear newstyle" style="padding:10px;">
					<h3 class="myImg" style="margin-left:20px;">公司图片：</h3>
					
						<c:forEach items="${requestScope.firm.imgAddrs}" var="img">
										<div class="myImg">
											<img src="${img}" alt="公司图片" height="100" width="100"/>
											<span>请选择<input name="delImgStr" type="checkbox" value='${img}'/></span>
										</div>
						</c:forEach>
						<c:if test="${requestScope.firm.imgAddrs eq null}">图片暂未上传</c:if>
				</div>
				<c:if test="${requestScope.firm.imgAddrs != null}">
					<p class="short-input ue-clear newstyle">
						<a href="javascript:void(0)" class="mySubmit" id="mySubmit">确定删除</a>
					</p>
				</c:if>
			</form>
			<script type="text/javascript">
				$(function(){
					
					$("#mySubmit").click(function(){
						var flag = false;
						
						$('.myImg span>input:checkbox').each(function(){
							
							if($(this).is(":checked")){
								flag = true;
							}
						});
						
						if(flag){
							$("#form").submit();
						}else{
							alert("请选择要删除的图片！");
						}
					});
				});
			</script>
		</div>

</div>
<p style="height:100px;"></p>

</body>

</html>
