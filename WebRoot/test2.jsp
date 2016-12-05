<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="js/jquery-1.9.1.js"></script>

<script type="text/javascript">
	$(function() {

		var url = "${pageContext.request.contextPath}/loginAction.do?method=drawValidateImg&nocache=" + new Date().getTime();
		$("#showCode").attr("src", url);

		$("#show").click(function() {
			url = "${pageContext.request.contextPath}/loginAction.do?method=drawValidateImg&nocache=" + new Date().getTime();
			$("#showCode").attr("src", url);
		});
	});
</script>
</head>
<body>

	<!-- 	<a href="javascript:void(0);" id="show"><img id="showCode" src=""/></a> -->
</body>
</html>