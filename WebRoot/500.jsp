<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>ErrorJSP</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

  </head>
  
  <body style="background-image: url('errorImages/500.png'); background-repeat: no-repeat;">
  	<center>
  		<a href="javascript:void(0);" onclick="history.back();" style="display:block;width:100px;height:100px;background-image: url('errorImages/btn.png'); background-repeat: no-repeat;background-position:center;position: relative;top:450px;"></a>
  	</center>
  </body>
</html>
