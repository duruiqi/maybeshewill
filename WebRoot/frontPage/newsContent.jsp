<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html lang="zh-cn">
<head>
<meta charset="utf-8">
<title>新闻</title>
</head>

<body>

	<%@include file="./part/top.jsp"%>

	<div class="main clearfix ofHidden block yh" style="height:70%">
		<%-- 左侧 --%>
		<%@include file="./part/left.jsp"%>

		<div class="main_right fright">
			<!--右侧-->

			<div class="title clearfix">
				<font class="yh f16">公司新闻</font><span class="fright f12">网站首页
					&lt; <a href="#">新闻中心</a>
				</span>
			</div>

			<div class="newsnr">

				<h1 class="bt">${requestScope.form.nTitle}</h1>
				<div class="date">
					<span>${requestScope.form.nDate}</span><span>来源：<a
						href="http://cpc.people.com.cn/" target="_blank">${requestScope.form.customerUnit}</a></span><span>浏览数:
						18</span>
				</div>
				<div class="nr">
					<p style="text-indent: 2em">${requestScope.form.nContent}</p>
					</p>
				</div>

				<div class="share clearfix">
					<div class="fleft">
						<a href="javascript:window.print()" class="print">打印本页</a>
					</div>
					<div class="fleft">
						<a href="javascript:window.close()" class="close">关闭窗口</a>
					</div>
				</div>

				<div class="down clearfix">
					<div class="fright" style="width:300px">
						<div class="bdsharebuttonbox" data-tag="share_1" >
							<a class="bds_mshare" data-cmd="mshare"></a> <a class="bds_qzone"
								data-cmd="qzone" href="#"></a> <a class="bds_tsina"
								data-cmd="tsina"></a> <a class="bds_baidu" data-cmd="baidu"></a>
							<a class="bds_renren" data-cmd="renren"></a> <a class="bds_tqq"
								data-cmd="tqq"></a> <a class="bds_more" data-cmd="more">更多</a> <a
								class="bds_count" data-cmd="count"></a>
						</div>
						<script>
							window._bd_share_config = {
								common : {
									bdText : 'xxxxx分享内容',
									bdDesc : 'xxxxx分享摘要',
									bdUrl : 'http://localhost:8080${basePath}/frontPage/index.jsp',
									bdPic : 'localhost:8080/${basePath}/upload/images/a.jpg'
								},
								share : [ {
									"bdSize" : 16
								} ],
								slide : [ {
									bdImg : 0,
									bdPos : "right",
									bdTop : 100
								} ],
								image : [ {
									viewType : 'list',
									viewPos : 'top',
									viewColor : 'black',
									viewSize : '16',
									viewList : [ 'qzone', 'tsina', 'huaban',
											'tqq', 'renren' ]
								} ],
								selectShare : [ {
									"bdselectMiniList" : [ 'qzone', 'tqq',
											'kaixin001', 'bdxc', 'tqf' ]
								} ]
							}
							with (document)
								0[(getElementsByTagName('head')[0] || body)
										.appendChild(createElement('script')).src = 'http://bdimg.share.baidu.com/static/api/js/share.js?cdnversion='
										+ ~(-new Date() / 36e5)];
						</script>
					</div>

					<div class="fleft">
						<p>
							上一篇：<a href="">新闻列表</a>
						</p>
						<p>
							上一篇：<a href="">新闻列表</a>
						</p>
					</div>
				</div>
			</div>
		</div>
	</div>
	<%@include file="./part/footer.jsp"%>
</body>
</html>