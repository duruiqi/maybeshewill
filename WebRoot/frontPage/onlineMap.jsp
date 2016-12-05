<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="zh-cn">
<head>
<meta charset="utf-8">
<title>在线地图</title>
</head>


<body>
	<%@include file="./part/top.jsp"%>
<div class="main clearfix ofHidden block yh">
	<%-- 左侧 --%>
	<%@include file="./part/left.jsp"%>

	<!--右侧-->
	  <div class="main_right fright">
		<%@include file="./part/map.jsp"%> 
	</div>
</div>

<%@include file="./part/footer.jsp"%>

</body>
</html>