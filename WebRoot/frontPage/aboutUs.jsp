<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="zh-cn">
<head>
<meta charset="utf-8">
<title>关于我们/公司介绍</title>
<style type="text/css">
 .scd_mr {
	width: 900px;
	float: right;
	margin-top: 12px;
	text-align: left;
	line-height: 200%;
	font-size: 13px;
	color: #555555;
	padding: 1px 0 5px;
}

 .scd_mr h5 {
	width: 100%;
	font-size: 14px;
	margin-bottom: 6px;
}
</style>
</head>


<body style="min-width:800px;padding:5px;">

	<%@include file="./part/top.jsp"%>
<div class="main clearfix ofHidden block yh">
		<%-- 左侧 --%>
		<%@include file="./part/left.jsp"%>
		<!--右侧-->
		<div id="right" class="main_right fright">
			<div class="scd_mr">
            	<div class="about clearfix">
                	<img width="450px" height="234px" style=" float:left;"  src="../images/company.jpg">
                	<p><h5>公司简介</h5></p>
                	<p style="width:370px; float:left;">&nbsp; &nbsp;&nbsp;成都义泓照明工程有限公司专注于路灯供应链，为行业制造企业、物流运输企业、连锁商超提供一站式信息化解决方案、管理咨询、软硬件集成项目实施和售后维护。高思通达信息技术有限公司管理咨询团队大多数是曾在华为、富士康、中兴等大型企业采购、供应链部门任职多年，在供应链管理方面有丰富运营管理经验。</p>
                    <p style="width:370px; float:left;">2014年高思与INFOR、KEWILL结成重要项目合作伙伴。</p>
                </div>
            </div>
			<div class="scd_mr">
            	<div class="about clearfix">
                	<p><h5>企业法人</h5></p>
                	<p style="width:370px; float:left;">&nbsp; &nbsp;&nbsp;N先生 从事路灯开发很多年 是路灯开发的领头人 N先生 于某年和某人建立了公司 </p>
                    <p style="width:370px; float:left;">2014年高思与INFOR、KEWILL结成重要项目合作伙伴。</p>
                </div>
            </div>
			<div class="scd_mr">
            	<div class="about clearfix">
                	<p><h5>公司地址</h5></p>
                	<p style="width:370px; float:left;">&nbsp; &nbsp;&nbsp;本公司位于高新区高新区高新 具体地址如下</p></br>
                    <p style="width:370px; float:left;">高新区高新区高新区高新区高新区高新区高新区</p>
                </div>
            </div>
<!-- 			<div class="scd_mr">
            	<div class="about clearfix">
            	<img width="450px" height="161px" style=" float:left;"  src="../images/company.jpg">
            	<img width="450px" height="161px" style=" float:right;"  src="../images/company.jpg">
                </div>
            </div> -->
		</div>
	</div>
	
	<%@include file="./part/footer.jsp"%>
</body>
</html>