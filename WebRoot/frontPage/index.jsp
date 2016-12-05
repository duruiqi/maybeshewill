<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="zh-cn">
<head>
<meta charset="utf-8">

<title>首页</title>
</head>
<body>
	<%@include file="./part/top.jsp"%>
<div class="main clearfix ofHidden block yh">
	 <div class="focusBox radius10 myStyle" style="z-index:-1;">
			<ul class="pic">
					<li><a href="#" target="_blank"><img src="${basePath}/upload/images/a.jpg" width="1200"height="400"/></a></li>
					<li><a href="#" target="_blank"><img src="${basePath}/upload/images/b.jpg" width="1200"height="400"/></a></li>
					<li><a href="#" target="_blank"><img src="${basePath}/upload/images/c.jpg" width="1200"height="400"/></a></li>
					<li><a href="#" target="_blank"><img src="${basePath}/upload/images/d.jpg" width="1200"height="400"/></a></li>
					<li><a href="#" target="_blank"><img src="${basePath}/upload/images/e.jpg" width="1200"height="400"/></a></li>
					<li><a href="#" target="_blank"><img src="${basePath}/upload/images/f.jpg" width="1200"height="400"/></a></li>
			</ul>
			<ul class="hd">
				<li></li>
				<li></li>
				<li></li>
				<li></li>
			</ul>
	</div>
	<%-- 左侧 --%>
	<%@include file="./part/left.jsp"%>
	
	<!--右侧-->
    <div class="main_right fright">
    <div class="clearfix">
        
    	<!--公司简介-->
    	<div class="company fleft">
        	<div class="t1"><a href=""><img src="images/more.jpg"></a><span>公司简介</span></div>
            <div class="nr f13">
            <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;拆旧 各种砸墙、砸地砖 铲墙皮、旧房改造，拆隔墙门窗等 清理垃圾 建筑垃圾 垃圾清理 敲墙 敲墙、改结构 酒店、商场,宾馆拆旧工程： 室内结构包括（拆吊顶、拆电缆电线、拆通风系统、拆隔间、拆地板、砸墙、砸地平,室外结构包括：拆广告牌、拆霓虹灯、拆铝合金门窗等。 建筑工地： 拆活动房、拆配电房、拆建筑废料、拆电线电缆、拆水暖器件、拆钢管扣件及门窗材料等。...<a href="" class="c_red">[详细]</a></p>
            <p><img src="images/gs_t.jpg" width="395" height="105"></p>
            </div>
        </div>
        
        <!--新闻中心-->
        <div class="news fright">
        	<div class="t1"><a href=""><img src="images/more.jpg"></a><span>新闻中心</span></div>
            <ul>
            	<li><a href="">这里是新闻标题这里是新闻标题这里是新闻标题</a></li>
                <li><a href="">这里是新闻标题这里是新闻标题这里是新闻标题</a></li>
                <li><a href="">这里是新闻标题这里是新闻标题这里是新闻标题</a></li>
                <li><a href="">这里是新闻标题这里是新闻标题这里是新闻标题</a></li>
                <li><a href="">这里是新闻标题这里是新闻标题这里是新闻标题</a></li>
                <li><a href="">这里是新闻标题这里是新闻标题这里是新闻标题</a></li>
                <li><a href="">这里是新闻标题这里是新闻标题这里是新闻标题</a></li>
                <li><a href="">这里是新闻标题这里是新闻标题这里是新闻标题</a></li>
            </ul>
        </div>
        
        
        </div>
        
        <!--产品展示-->
        <div class="cp_show clearfix">
        	<div class="title t1"><a href=""><img src="images/more.jpg"></a>案例展示</div>
            <div class="picScroll">
		
		<ul>
			<li><a target="_blank" href="#"><img _src="images/cp_cp.png" src="images/blank.png" /><p>案例展示名称</p></a></li>
			<li><a target="_blank" href="#"><img _src="images/cp_cp.png" src="images/blank.png" /><p>案例展示名称</p></a></li>
			<li><a target="_blank" href="#"><img _src="images/cp_cp.png" src="images/blank.png" /><p>案例展示名称</p></a></li>
			<li><a target="_blank" href="#"><img _src="images/cp_cp.png" src="images/blank.png" /><p>案例展示名称</p></a></li>
			<li><a target="_blank" href="#"><img _src="images/cp_cp.png" src="images/blank.png" /><p>案例展示名称</p></a></li>
			<li><a target="_blank" href="#"><img _src="images/cp_cp.png" src="images/blank.png" /><p>案例展示名称</p></a></li>
			<li><a target="_blank" href="#"><img _src="images/cp_cp.png" src="images/blank.png" /><p>案例展示名称</p></a></li>
			<li><a target="_blank" href="#"><img _src="images/cp_cp.png" src="images/blank.png" /><p>案例展示名称</p></a></li>
			<li><a target="_blank" href="#"><img _src="images/cp_cp.png" src="images/blank.png" /><p>案例展示名称</p></a></li>
			<li><a target="_blank" href="#"><img _src="images/cp_cp.png" src="images/blank.png" /><p>案例展示名称</p></a></li>
		</ul>

	</div>
        </div>
        
    </div>
        
    </div>
</div>

<%@include file="./part/footer.jsp"%>

</body>
</html>