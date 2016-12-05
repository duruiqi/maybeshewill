<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<% pageContext.setAttribute("basePath",request.getContextPath()); %>
<!doctype html>
<html lang="zh-cn">
<head>
<meta charset="utf-8">
<title>首页</title>
<script type="text/javascript">

	function sepPage(destPage) {
		window.location.href = "${basePath}/produceAction.do?method=showProductUi&curPage="
				+ destPage;
	}
</script>
<style type="text/css">

.paginList{}
#paing_a{float:left;width:300px;margin:20px 100px 0px 100px;}
.pagenxt,.pagepre {
	width: 100px;
	height: 30px;
	display:inline-block;
	border:1px solid #ccc;
	background:#ccc;
}
.paginList li{float:left;width:30px;height:20px;font-size:14px;margin-top:20px;}
</style>
</head>


<body>
	<%@include file="./part/top.jsp"%>
<div class="main clearfix ofHidden block yh">
	<%-- 左侧 --%>
	<%@include file="./part/left.jsp"%>

	<!--右侧-->
    <div class="main_right fright">
    	<div class="title clearfix"><font class="yh f16">最近更新</font><span class="fright f12">灯具产品 > <a href="#">所有产品</a></span></div>
    	<ul class="img_list">
    		<c:forEach items="${requestScope.pagingBean.dataList}" var="product">
		        <li class="radius_4">
		        	<dl>
		        		<dt style="text-algin:center;"><img class="zoom-img radius_4" src="${product.proImages}" alt="灯具图片无法显示"/></dt>
		        		<dt style="text-algin:left;margin-top:5px;">产品编号：${product.proType}</dt>
		        		<dd style="text-algin:left;">应用范围：${product.proCatogroy}</dd>
		        	</dl>
		        </li>
	        </c:forEach>
      </ul>
      
     <div class="page clearfix">
     	<div id="paing_a">共<span class="blue">${pagingBean.totalRecord}</span>条记录，当前第&nbsp;<span class="blue">${pagingBean.curPage}&nbsp;</span>页	/共${pagingBean.totalPage}页
     	</div>	
     		<ul class="paginList">
				<c:if test="${pagingBean.curPage > 1}">
					<li  class="pagepre radius8" style="width:100px;">
						<a href="javascript:void(0);" onclick="sepPage(${pagingBean.previous})">
						<span>上一页</span></a>
					</li>
				
				</c:if>
				
				<c:forEach var="i" items="${pagingBean.bars}">
						<c:choose>
							<c:when test="${pagingBean.curPage != i}">
								<li class="paginItem">
									<a href="javascript:void(0);" onclick="sepPage(${i})">${i}</a>
								</li>
							</c:when>
							<c:otherwise>
								<li class="paginItem">
									<span style="color:red;">${i}</span>
								</li>
							</c:otherwise>
						</c:choose>
				</c:forEach>
				
				<c:if test="${pagingBean.totalPage > pagingBean.curPage}">
					<li class="pagenxt radius8" style="width:100px;">
						<a href="javascript:void(0);" onclick="sepPage(${pagingBean.next})">
						<span>下一页</span></a>
					</li>
				</c:if>
			</ul>
			<div class="clear:both;"></div>
     </div>
    </div>
<%-- <%@include file="./part/paging.jsp"%> --%>
</div>

<%@include file="./part/footer.jsp"%>

</body>
</html>