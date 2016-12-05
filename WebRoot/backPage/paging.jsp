<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link href="${pageContext.request.contextPath}/backPage/css/style.css" rel="stylesheet" type="text/css" />
<div class="pagin" style="padding-bottom:100px;">
			<div class="message">
				共<i class="blue">${pagingBean.totalRecord}</i>
				条记录，当前第&nbsp;<i class="blue">${pagingBean.curPage}&nbsp;</i>页
				/共${pagingBean.totalPage}页
			</div>
			
			<ul class="paginList">
				<c:if test="${pagingBean.curPage > 1}">
					<li class="paginItem">
						<a class=cmdField href="javascript:void(0);" onclick="sepPage(${pagingBean.previous})">
						<span class="pagepre"></span></a>
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
									<a href="javascript:void(0);" style="background:#ccc">${i}</a>
								</li>
							</c:otherwise>
						</c:choose>
				</c:forEach>
				
				<c:if test="${pagingBean.totalPage > pagingBean.curPage}">
					<li class="paginItem">
						<a class=cmdField href="javascript:void(0);" onclick="sepPage(${pagingBean.next})">
						<span class="pagenxt"></span></a>
					</li>
				</c:if>
			</ul>
</div>