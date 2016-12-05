<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="${pageContext.request.contextPath}/backPage/css/style.css"
	rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/backPage/js/jquery.js"></script>
<title></title>
<style type="text/css">
	.mySubmit{
		background: #68B86C;
		font-size: 16px;
		padding: 3px;
		height: 34px;
		width: 100px;
		line-height: 30px;
		color:#fff;
		vertical-align:middle;
		-moz-border-radius:4px;
		 -webkit-border-radius:4px; 
		 border-radius:4px;
		margin:10px;
	}
	.mySubmit:hover{background: #559558;}
	.myspan{margin-left:36px;}
	.myerror{color:red;}
</style>
</head>

<body style="min-width:800px;padding:5px;">

	<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li><a href="#">首页</a></li>
			<li><a href="#">地图位置</a></li>
		</ul>
	</div>

	<div class="formbody" style="height:600px;">

		<div class="formtitle">
			<span>基本信息</span>
		</div>
		<form action="${pageContext.request.contextPath}/mapAction.do"	method="post"  enctype="multipart/form-data">
			<input type="hidden" name="method" value="modMapData"/>
			<input type="hidden" name="mapId" value="${requestScope.onlineMap.mapId}"/>
		<div>
			
			<p>
				 <i class="myspan">纬度：</i><input name="latitude" type="text" class="dfinput" value="${requestScope.onlineMap.latitude}"
				 id="txtlatitude"	style="width:200px;margin-bottom:5px;" />
				 <i class="myerror">${requestScope.errorMsg.latitudeError}</i> 
				 <i class="myspan">经度：</i><input name="longitude" type="text" class="dfinput"	value="${requestScope.onlineMap.longitude}"
				 id="txtLongitude" style="width:200px;margin-bottom:5px;" /> 
				 <i class="myerror">${requestScope.errorMsg.longitudeError}</i>
				<i>标注点所在区域：</i><input name="markerArea" type="text" class="dfinput" value="${requestScope.onlineMap.markerArea}"
				id="txtAreaCode" style="width:200px;" />
				<i class="myerror">${requestScope.errorMsg.mAreaError}</i>
			</p>
			<p>
				<i>标注点名称：</i><input name="markerTitle" type="text" class="dfinput" value="${requestScope.onlineMap.markerTitle}"
				style="width:200px;" /> 
				<i class="myerror">${requestScope.errorMsg.mTitleError}</i>
				<i>标注点内容：</i><input name="markerContent" type="text" class="dfinput" value="${requestScope.onlineMap.markerContent}"
				 style="width:200px;" /> 
				 <i class="myerror">${requestScope.errorMsg.mContentError}</i>
			</p>
		</div>
		<div>
			<input type="submit" value="确认修改" class="mySubmit" />
		</div>
		<p style="border-top:1px solid #ccc;height:30px;"></p>
		<div style="width: 360px;float:left; margin-right:30px;" id="Div1">
						<div class="sel_container">
						<p>
							搜索: <input id="txtarea" type="text" class="dfinput" style="width:170px;"/> 
							<input id="areaSearch" type="button" class="mySubmit" value="搜索" style="cursor: pointer" />
						</p>
						<strong id="curCity">成都市</strong> [<a id="curCityText"
								href="javascript:void(0)">更换城市</a>]
						<strong>
							<a style="font-size:14px;color:orange;" href="${pageContext.request.contextPath}/backPage/map/mapHelp.jsp">地图帮助</a>
						</strong>
						</div>
						<div class="map_popup" id="cityList" style="display:none;">
							<div class="popup_main">
								<div class="title">城市列表</div>
								<div class="cityList" id="citylist_container"></div>
								<button id="popup_close"></button>
							</div>
						</div>
						<script type="text/javascript">
							$(function(){
								$("#curCityText").click(function(){
									if($(this).parent().siblings(".map_popup").is(":visible")){ 
										$(this).parent().parent().css({"height":"400px","overflow":"scroll"});
									}
									else{
										$(this).parent().parent().css({"height":"","overflow":""});
									}
								});
							});
						</script>
			</div>
		<div style="width:600px;height:400px; margin-bottom:100px;border: 1px solid gray;float:left;"id="container"></div>
	</form>
			<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=3BmRN4G42Uo9cNxxi3pr7gGkxh8vPFt9"></script>
		   <script type="text/javascript" src="http://api.map.baidu.com/library/CityList/1.2/src/CityList_min.js"></script>


		<script type="text/javascript">
			var map = new BMap.Map("container");
			map.centerAndZoom(new BMap.Point(104.068916,30.592197), 11);
			map.addControl(new BMap.NavigationControl());
			map.addControl(new BMap.ScaleControl());
			map.addControl(new BMap.OverviewMapControl());
			map.addControl(new BMap.MapTypeControl());
			//搜索 
			document.getElementById("areaSearch").onclick = function() {
				var searchTxt = document.getElementById("txtarea").value;
				var local = new BMap.LocalSearch(map, {
					renderOptions:{map: map}
				});
				local.search(searchTxt);
			};
			map.enableScrollWheelZoom();
			// 创建CityList对象，并放在citylist_container节点内 
			var myCl = new BMapLib.CityList({
				container : "citylist_container",
				map : map
			});
			// 给城市点击时，添加相关操作 
			myCl.addEventListener("cityclick", function(e) {
				// 修改当前城市显示 
				document.getElementById("curCity").innerHTML = e.name;
				// 点击后隐藏城市列表 
				document.getElementById("cityList").style.display = "none";
			});
			// 给“更换城市”链接添加点击操作 
			document.getElementById("curCityText").onclick = function() {
				
				var cl = document.getElementById("cityList");
				if (cl.style.display == "none") {
					cl.style.display = "";
				} else {
					cl.style.display = "none";
				}
			};
			// 给城市列表上的关闭按钮添加点击操作 
			document.getElementById("popup_close").onclick = function() {
				var cl = document.getElementById("cityList");
				if (cl.style.display == "none") {
					cl.style.display = "";
				} else {
					cl.style.display = "none";
				}
			};

			map.addEventListener("click", function(e) {
				document.getElementById("txtlatitude").value = e.point.lat;
				document.getElementById("txtLongitude").value = e.point.lng;
				map.clearOverlays();
				var pointMarker = new BMap.Point(e.point.lng, e.point.lat); // 创建标注的坐标 
				addMarker(pointMarker);
				geocodeSearch(pointMarker);
			});

			function addMarker(point) {
				var myIcon = new BMap.Icon("mk_icon.png",
						new BMap.Size(21, 25), {
							offset : new BMap.Size(21, 21),
							imageOffset : new BMap.Size(0, -21)
						});
				var marker = new BMap.Marker(point, {
					icon : myIcon
				});
				map.addOverlay(marker);
			}
			function geocodeSearch(pt) {
				var myGeo = new BMap.Geocoder();
				myGeo
						.getLocation(
								pt,
								function(rs) {
									var addComp = rs.addressComponents;
									document.getElementById("txtAreaCode").value = addComp.province
											+ ", "
											+ addComp.city
											+ ", "
											+ addComp.district;
								});
			}
			
			
		    //标注点数组
		    var markerArr = [{title:'${requestScope.onlineMap.markerTitle}',content:'${requestScope.onlineMap.markerContent}'
		    	,point:"${requestScope.onlineMap.longitude}|${requestScope.onlineMap.latitude}",isOpen:1,icon:{w:23,h:25,l:46,t:21,x:9,lb:12}}
				 ];
		    //创建marker
		    function addMarker(){
		        for(var i=0;i<markerArr.length;i++){
		            var json = markerArr[i];
		            var p0 = json.point.split("|")[0];
		            var p1 = json.point.split("|")[1];
		            var point = new BMap.Point(p0,p1);
					var iconImg = createIcon(json.icon);
		            var marker = new BMap.Marker(point,{icon:iconImg});
					var iw = createInfoWindow(i);
					var label = new BMap.Label(json.title,{"offset":new BMap.Size(json.icon.lb-json.icon.x+10,-20)});
					marker.setLabel(label);
					marker.setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画
		            map.addOverlay(marker);
		            label.setStyle({
		                        borderColor:"#808080",
		                        color:"#333",
		                        cursor:"pointer"
		            });
					
		            
					(function(){
						var index = i;
						var _iw = createInfoWindow(i);
						var _marker = marker;
						_marker.addEventListener("click",function(){
						    this.openInfoWindow(_iw);
					    });
					    _iw.addEventListener("open",function(){
						    _marker.getLabel().hide();
					    })
					    _iw.addEventListener("close",function(){
						    _marker.getLabel().show();
					    })
						label.addEventListener("click",function(){
						    _marker.openInfoWindow(_iw);
					    })
						if(!!json.isOpen){
							label.hide();
							_marker.openInfoWindow(_iw);
						}
					})()
		        }
		        
		    }
		    //创建InfoWindow
		    function createInfoWindow(i){
		        var json = markerArr[i];
		        var iw = new BMap.InfoWindow("<b class='iw_poi_title' title='" + json.title + "'>" + json.title + "</b><div class='iw_poi_content'>"+json.content+"</div>");
		        return iw;
		    }
		    //创建一个Icon
		    function createIcon(json){
		        var icon = new BMap.Icon("http://app.baidu.com/map/images/us_mk_icon.png", new BMap.Size(json.w,json.h),{imageOffset: new BMap.Size(-json.l,-json.t),infoWindowOffset:new BMap.Size(json.lb+5,1),offset:new BMap.Size(json.x,json.h)})
		        return icon;
		    }
		    addMarker();
		</script>


	</div>

</body>

</html>
