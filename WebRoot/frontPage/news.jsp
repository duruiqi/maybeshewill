<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html lang="zh-cn">
<head>
<meta charset="utf-8">
<title>新闻</title>
<link href="${basePath}/backPage/css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${basePath}/backPage/js/jquery.js"></script>
<script type="text/javascript" src="${basePath}/backPage/js/WdatePicker.js"></script>
	<link rel="stylesheet" href="${basePath}/backPage/themes/default/easyui.css" type="text/css">
	<link rel="stylesheet" href="${basePath}/backPage/themes/icon.css" type="text/css">
	<script type="text/javascript" src="/YHZM/backPage/js/jquery.easyui.min.js"></script>
	<script type="text/javascript">
		function sepPage(destPage) {
			window.location.href = "${pageContext.request.contextPath}/newsAction.do?method=NewsList&curPage="
					+ destPage;
		}
	</script>
</head>


<body>
	
	<%@include file="./part/top.jsp"%>
	
<div class="main clearfix ofHidden block yh" style="height:70%">
	<%-- 左侧 --%>
	<%@include file="./part/left.jsp"%>
	
	<div class="main_right fright">
	<!--右侧-->
	   	<div class="title clearfix"><font class="yh f16">公司新闻</font><span class="fright f12">网站首页 > <a href="#">新闻中心</a></span></div>
    	<ul class="text_list">
    	<c:forEach items="${requestScope.pagingBean.dataList}" var="news">
    		<li><span>${news.nDate}</span><a href="${pageContext.request.contextPath}/newsAction.do?method=getNewsContent&nid=${news.nid}">${news.nTitle}</a></li>
		</c:forEach>
    	<c:forEach items="${requestScope.pagingBean.dataList}" var="news">
    		<li><span>${news.nDate}</span><a href="${pageContext.request.contextPath}/newsAction.do?method=getNewsContent&nid=${news.nid}">${news.nTitle}</a></li>
		</c:forEach>    	<c:forEach items="${requestScope.pagingBean.dataList}" var="news">
    		<li><span>${news.nDate}</span><a href="${pageContext.request.contextPath}/newsAction.do?method=getNewsContent&nid=${news.nid}">${news.nTitle}</a></li>
		</c:forEach>
      </ul>
  </div>
  <div class="page clearfix">页次：${pagingBean.curPage}/${pagingBean.totalPage} 每页${pagingBean.totalRecord} 总数${pagingBean.totalRecord}   首页  <span><c:if test="${pagingBean.curPage > 1}"><li><a   href="javascript:void(0);" onclick="sepPage(${pagingBean.previous})">上一页<span class="pagepre"></span></a></li></c:if></span> <span><c:if test="${pagingBean.totalPage > pagingBean.curPage}"><li><a href="javascript:void(0);" onclick="sepPage(${pagingBean.next})">下一页<span class="pagenxt"></span></a></li></c:if></span> 尾页   转到:<select name=select onchange="self.location.href=this.options[this.selectedIndex].value"><option value='/news/index.html' selected>  第 1 页</option></select></div>
  
</div>




<%@include file="./part/footer.jsp"%>
</body>
</html>