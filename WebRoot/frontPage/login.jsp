<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<meta name="keywords" content="成都义鸿照明有限公司" />
<meta name="description" content="成都义鸿照明有限公司" />
<title>成都义鸿照明有限公司会员注册</title>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.form.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/frontPage/css/lcb_style.css" />
<link href="${pageContext.request.contextPath}/frontPage/css/master.css" type="text/css" rel="stylesheet" />
<link href="${pageContext.request.contextPath}/frontPage/css/base.css" type="text/css" rel="stylesheet" />

<script type="text/javascript">
	$(function(){
		var url = "${pageContext.request.contextPath}/userAction.do?method=drawValidateImg&nocache="
		+ new Date().getTime();
		$("#showCode").attr("src", url);
		$("#change").click(function() {
				url = "${pageContext.request.contextPath}/userAction.do?method=drawValidateImg&nocache="
				+ new Date().getTime();
				$("#showCode").attr("src", url);
			});
	});

</script>
</head>

<body id="nav_btn02">
  <div  style="height:110px;width:auto;background:#0174C7;border:1px solid #0174C7;">
	  <div id="logo" >
	    <h1>
		<a href="index.jsp">
			<img src="images/logo.png"  alt="义鸿照明" style="float:left;width:114px;height:40px;"/>
		</a>
			<span class="logo_span"> 会员登录</span></h1>
	   	
		  <ul id="logo_right">
	      	<li class="zi"><a href="index.jsp" style="color:#fff">公司主页</a></li>
	    </ul>
	  </div>
  </div>
  <!--logo end-->
  <div id="nav_bg"></div><!--nav end-->
  <div id="body" style="height:580px;">
    <div class="zhuce_body" id="registerLoad">
      <div class="zhuce_left">
      	<h3 style="color:red;">${requestScope.msg}</h3>
        <form method="post" action="${pageContext.request.contextPath}/userAction.do">
       		<input type="hidden" name="method" value="userLogin"/>
          <div class="input">
          	<label><span style="color:#F00">*</span>用户名：</label>
          	<input type="text" placeholder="6位以上数字或字母组合" class="text" value="${requestScope.form.userName}"
          	 id="userName" name="userName" tabindex="1" />
          	 <span style="color:red">${requestScope.errorMsg.nameError}</span>
          </div>
          <div class="input">
          	<label><span style="color:#F00">*</span>密 码：</label>
          	<input type="password" placeholder="6位以上登录密码" class="text" value="${requestScope.form.userPasswd}"
          	 id="userPasswd" name="userPasswd" tabindex="2"/>
          	 <span style="color:red">${requestScope.errorMsg.passwordError}</span>
          </div>
          
          <div class="input">
          	<label><span style="color:#F00">*</span>验证码：</label>
          	<input type="text" placeholder="验证码" class="yanzheng"   name="validateCode" size="4" />  
			<a href="javascript:void(0);" id="change">
			<img id="showCode" src="" alt="验证码" style="vertical-align:middle;width:80px;height:35px;"width="80px" height="35px" /> 换一换?</a>
			<span style="color:red">${requestScope.errorMsg.vlidateError}</span>
          </div>
          <div class="input">
          	<input id="register_btn" type="submit" class="button"	value="立即登录"/>
          </div>
        </form>
      </div><!--zhuce_left end-->
      <div class="zhuce_right">
        <div class="denglu">
          <span style="float:left; line-height:35px;">没有账号？请点击</span> <span><a href="${pageContext.request.contextPath}/frontPage/register.jsp" class="zhuce_denglu">注册</a></span>
        </div>
      </div><!--zhuce_right end-->
    </div><!--zhuce_body end-->
    
  </div><!--body end-->
	<%@include file="./part/footer.jsp"%>
</body>
</html>