<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
    pageContext.setAttribute("basePath", request.getContextPath()) ;
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="../css/style.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="../css/font-awesome.min.css" type="text/css">
<style type="text/css">
	.my_font{color:#6BB4D5;font-size:16px;display:inline-block;width:16px;margin-left:16px;}
</style>
<script language="JavaScript" src="../js/jquery.js"></script>

<script type="text/javascript">
	$(function() {
		//导航切换
		$(".menuson .header").click(
				function() {
					var $parent = $(this).parent();
					$(".menuson>li.active").not($parent).removeClass(
							"active open").find('.sub-menus').hide();

					$parent.addClass("active");
					if (!!$(this).next('.sub-menus').size()) {
						if ($parent.hasClass("open")) {
							$parent.removeClass("open").find('.sub-menus')
									.hide();
						} else {
							$parent.addClass("open").find('.sub-menus').show();
						}

					}
				});

		// 三级菜单点击
		$('.sub-menus li').click(function(e) {
			$(".sub-menus li.active").removeClass("active")
			$(this).addClass("active");
		});

		function common(obj) {
			obj.css("background", "#F2EAD6");
			var dds = obj.parent("dd").siblings("dd");
			for (var i = 0; i < dds.length; i++) {
				$(dds[i]).children(".title").css("background", "");
			}
		}
		$('.title').click(function() {
			common($(this));
			var $ul = $(this).next('ul');
			$('dd').find('.menuson').slideUp();
			if ($ul.is(':visible')) {
				$(this).next('.menuson').slideUp();
			} else {
				$(this).next('.menuson').slideDown();
			}
		});
		$('.title').parent("dd").mouseout(function(){
			$(this).children(".title").css("background", "");
		});
		
	});
	function addBefore(){
		window.location.href="${basePath}/backPage/projectOrder/add.jsp";
	}
</script>


</head>

<body style="background:#f0f9fd;">
	<div class="lefttop">
		<span></span>功能导航
	</div>

	<dl class="leftmenu">
			
		<dd>
			<div class="title">
				<span class="fa fa-home my_font"></span>首页管理
			</div>
			<ul class="menuson">
				<li>
					<div class="header">
						<cite></cite> <a href="${basePath }/mainLogoAction.do?method=getMainLogoList" target="rightFrame">公司图标</a>
					</div>
				</li>
				
				<li>
					<div class="header">
						<cite></cite> <a href="${basePath }/mainBannerAction.do?method=getMainBannerList" target="rightFrame">广告图片</a>
					</div>
				</li>
			</ul>
		</dd>
		
		<dd>
			<div class="title">
				<span class="fa fa-rocket my_font"></span>市场管理
			</div>
			<ul class="menuson">
				<li>
					<div class="header">
						<cite></cite> <a href="${basePath}/projectOrderAction.do?method=getProjectOrderList" target="rightFrame">工程订单</a>
					</div>
					<ul class="sub-menus">
						<li><a  href="${basePath}/backPage/projectOrder/add.jsp" target="rightFrame">添加工程订单</a></li>
					</ul>
				</li>
			</ul>
		</dd>

		
		<dd>
			<div class="title">
				<span class="fa fa-windows my_font"></span>产品管理
			</div>
			<ul class="menuson">
				<li>
					<div class="header">
						<cite></cite> <a href="${basePath}/produceAction.do?method=getProduceList" target="rightFrame">产品</a>
					</div>
					<ul class="sub-menus">
						<li><a href="${basePath}/backPage/produce/add.jsp" target="rightFrame">增加产品</a></li>
					</ul>
				</li>
			</ul>
		</dd>
				
		<dd>
			<div class="title">
				<span class="fa fa-newspaper-o  my_font"></span>新闻管理
			</div>
			<ul class="menuson">
				<li>
					<div class="header">
						<cite></cite> <a href="${basePath}/newsAction.do?method=getNewsList" target="rightFrame">新闻动态</a>
					</div>
					<ul class="sub-menus">
						<li><a href="${basePath}/backPage/news/add.jsp" target="rightFrame">添加企业新闻</a></li>
					</ul>
				</li>
			</ul>
		</dd>
		
		<dd>
			<div class="title">
				<span><img src="../images/leftico01.png" /></span>部门管理
			</div>
			<ul class="menuson">
				<li>
					<div class="headerq">
						<cite></cite> <a href="${pageContext.request.contextPath}/departmentAction.do?method=getAllDepartment" target="rightFrame">所有部门</a>
					</div>
					<div class="headerq">
						<cite></cite> <a  href="${pageContext.request.contextPath}/departmentAction.do?method=addddd" target="rightFrame">增加部门</a>
					</div>
				</li>
			</ul>
		</dd>
		
		<dd>
			<div class="title">
				<span><img src="../images/leftico01.png" /></span>员工管理
			</div>
			<ul class="menuson">
				<li>
					<div class="headerq">
						<cite></cite> <a href="${pageContext.request.contextPath}/employeeAction.do?method=getEmployeeList " target="rightFrame">员工列表</a>
					</div>
					<div class="headerq">
						<cite></cite> <a href="${pageContext.request.contextPath}/employeeAction.do?method=addBefore" target="rightFrame">增加员工</a>
					</div>
<!-- 					<ul class="sub-menus">
						<li><a href="javascript:;">增加员工</a></li>
						<li><a href="javascript:;">编辑员工</a></li>
						<li><a href="javascript:;">删除员工</a></li>
					</ul> -->
				</li>
			</ul>
		</dd>
		
		<dd>
			<div class="title">
				<i class="fa fa-cny my_font"></i>薪资管理
			</div>
			<ul class="menuson">
				<li>
					<div class="header">
						<cite></cite><a href="${pageContext.request.contextPath}/salaryAction.do?method=getSalaryList " target="rightFrame">薪资列表</a>
					</div>
					<ul class="sub-menus">
						<li><a href="${pageContext.request.contextPath}/salaryAction.do?method=getSalary">编辑条目</a></li>
						<li><a href="javascript:;">删除条目</a></li>
					</ul>
				</li>
			</ul>
		</dd>
		
		<dd>
			<div class="title">
				<span class="fa fa-hourglass-2 my_font"></span>后勤管理
			</div>
			<ul class="menuson">
				<li>
					<div class="header">
						<cite></cite> <a href="${basePath}/logisticsAction.do?method=getPurchaseList" target="rightFrame">采购</a>
					</div>
					<ul class="sub-menus">
						<li><a href="${basePath}/backPage/logistics/add.jsp" target="rightFrame">增加采购项</a></li>
					</ul>
				</li>
				<li>
					<div class="header">
						<cite></cite> <a href="${basePath}/logisticsAction.do?method=getSsEmployee" target="rightFrame">食宿</a>
					</div>
				</li>
			</ul>
		</dd>
		
		<dd>
			<div class="title">
				<span class="fa fa-apple my_font"></span>公司信息
			</div>
			<ul class="menuson">
				<li>
					<div class="header">
						<cite></cite> <a href="${basePath}/companyInfoAction.do?method=getFirmInfoList" target="rightFrame">公司简介</a>
					</div>
					<ul class="sub-menus">
						<li><a href="${basePath}/backPage/companyInfo/add.jsp" target="rightFrame">增加简介</a></li>
					</ul>
				</li>
				
				<li>
					<div class="header">
						<cite></cite> <a href="${basePath}/contactUsAction.do?method=getContactUsList" target="rightFrame">联系我们</a>
					</div>
					<ul class="sub-menus">
						<li><a href="${pageContext.request.contextPath}/contactUsAction.do?method=getContactUs&usId=1" target="rightFrame">编辑</a></li>
					</ul>
				</li>
			</ul>
		</dd>
		
		<dd>
			<div class="title">
				<span class="fa fa-comment my_font"></span>留言板
			</div>
			<ul class="menuson">
				<li>
					<div class="header">
						<cite></cite> <a href="index.html" target="rightFrame">留言</a>
					</div>
					<ul class="sub-menus">
						<li><a href="javascript:;">已发留言</a></li>
						<li><a href="javascript:;">草稿</a></li>
					</ul>
				</li>
			</ul>
		</dd>

	</dl>

</body>
</html>
