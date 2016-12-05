<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<% pageContext.setAttribute("basePath", request.getContextPath()); %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link href="${basePath}/frontPage/css/master.css" type="text/css" rel="stylesheet" />
<link href="${basePath}/frontPage/css/base.css" type="text/css" rel="stylesheet" />
<link rel="stylesheet" href="${basePath}/frontPage/css/font-awesome.min.css" type="text/css"/>
<script type="text/javascript" src="${basePath}/js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="${basePath}/frontPage/js/jquery.SuperSlide.2.1.1.js"></script>

<script type="text/javascript">
	$(function(){
		//加载左侧和底部的数据
		$.ajax({
			type:"post",
			url:"${basePath}/contactUsAction.do",
			data:{"method":"aysnGetContactUs","usId":"1"},
			dataType:"json",
			success:function(data){
				$("#contantUs").text(data.phone);
				$("#firmAddr").text(data.linkAddr);
				$("#beiAnInfo").text(data.firmInfo.beiAnInfo);
				$("#linkMan").text(data.linkman);
				$("#phone").text(data.phone);
				$("#qq").prop("href","http://wpa.qq.com/msgrd?v=3&uin="+ data.qq +"&site=qq&menu=yes");
				$("#linkAddr").text(data.linkAddr);
			},
			error:function(){alert("请求失败！");}
		});
		
		//在加载这个共用的模型的时候会异步的向mainAction.do的action发送异步请求来获得logo的地址
		
		$.ajax({
			type:"post",
			url:"${basePath}/mainAction.do",
			data:{"method":"asynGetLogo"},
			dataType:"text",
			success:function(data){
				$("#img").attr("src",data);
			},
			error:function(){
				
			}
			
		});
		
	});
</script>

<div class="head clearfix yh" style="background:url('${basePath}/frontPage/images/bg.jpg');">
	<!--logo-->
	
    <div class="logo block clearfix">
    	<a href="/" class="fleft"><img id="img" src=""></a>
		<div class="head_right">
			<input type="text" name="sereach" style="outline:0;"/>&nbsp;&nbsp;<span class="fa fa-search"></span> 全站搜索&nbsp;&nbsp;&nbsp;&nbsp;
			<c:choose>
				<c:when test="${sessionScope.user != null}">
					<span style="display:inline-block;color:#fff;padding:4px;">欢迎您! <img src="${basePath}/frontPage/images/vip.ico" alt="会员"/><span style="color:orange">会员<i class="fa fa-hand-o-right"></i>　${sessionScope.user.userName}</span></span>&nbsp;&nbsp;&nbsp;&nbsp;
					<a href="${basePath}/userAction.do?method=userQuit">退出登录</a>
				</c:when>
				<c:otherwise>
						<a href="${basePath}/frontPage/register.jsp">会员注册</a>&nbsp;&nbsp;&nbsp;&nbsp;
						<a href="${basePath}/frontPage/login.jsp">登录</a>
				</c:otherwise>
			</c:choose>

		</div>
    </div>
    <!--nav-->
	<div class="nav clearfix">
    	<ul class="block">
        	<li><a href="index.jsp">首页</a></li>
            <li><a href="${basePath}/companyInfoAction.do?method=showFrimInfoUi" class="L">公司简介</a></li>
            <li><a href="${pageContext.request.contextPath}/newsAction.do?method=NewsList" class="L">新闻动态</a></li>
            <li>
            	<a href="${basePath}/produceAction.do?method=showProductUi" class="L">产品展示</a><br/>
            	<dl class="dropdown-menu" id="dropdown-menu">
            		<dd><a href="#">庭院灯具</a></dd>
            		<dd><a href="#">道路灯具</a></dd>
            	</dl>
            </li>
            <li><a href="#" class="L">工程展示</a></li>
            <li><a href="${basePath}/mapAction.do?method=getMapData&mapId=1" class="L">在线地图</a></li>
            <li><a href="${basePath}/frontPage/contact.jsp" class="L">联系我们</a></li>
        </ul>
    </div>
    
    <script type="text/javascript">
		$(function(){
			$("#dropdown-menu").parent().hover(function(){
					$("#dropdown-menu").fadeToggle("slow");
					return false;
			});
		});    
	</script>
<!-- script -->
</div>
<script src="${basePath}/frontPage/js/all.js" type="text/javascript"></script>
