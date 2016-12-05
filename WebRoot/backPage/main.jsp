<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>信息管理系统界面</title>
</head>
<frameset rows="88,*,31" cols="*" frameborder="no" border="0" framespacing="0">
  <frame src="${pageContext.request.contextPath}/backPage/part/top.jsp" name="topFrame" scrolling="NO" noresize="noresize" id="topFrame" title="topFrame" />
  <frameset cols="187,*" frameborder="no" border="0" framespacing="0">
    <frame src="${pageContext.request.contextPath}/backPage/part/left.jsp" name="leftFrame" scrolling="No" noresize="noresize" id="leftFrame" title="leftFrame" />
    <frame src="${pageContext.request.contextPath}/backPage/default.jsp" name="rightFrame" id="rightFrame" title="rightFrame" />
  </frameset>
  <frame src="${pageContext.request.contextPath}/backPage/part/footer.jsp" name="bottomFrame" scrolling="NO" noresize="noresize" id="bottomFrame" title="bottomFrame" />
</frameset>
<noframes>
<body>
</body></noframes>
</html>
