package com.scxh.yhzm.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.scxh.yhzm.po.OnlineMap;
import com.scxh.yhzm.service.MapService;

public class MapAction extends BaseAction{
	private MapService<OnlineMap> mapService;

	public void setMapService(MapService<OnlineMap> mapService) {
		this.mapService = mapService;
	}
	
	public String getMapData(HttpServletRequest request,
			HttpServletResponse response) {
		String mapId = request.getParameter("mapId");
		String flag = request.getParameter("flag");
		OnlineMap onlineMap = mapService.getEntryById(mapId);
		request.setAttribute("onlineMap", onlineMap);
		if("backPage".equals(flag)){
			return "backPage/map/setupMap";
		}
		return "frontPage/onlineMap";
	}
	
	public String modMapData(HttpServletRequest request,
			HttpServletResponse response) {
		Map<String,Object> map = super.parseSpringUpload(request);
		Map<String,String> errorMsg = new HashMap<String,String>();
		checkFormParams(map, errorMsg);
		if(errorMsg.size() > 0){
			request.setAttribute("onlineMap", map);
			request.setAttribute("errorMsg", errorMsg);
			return "backPage/map/setupMap";
		}
		Integer back = mapService.modEntry(map);
		if(back > 0){
			request.setAttribute("msg", "更新地图信息成功！");
			return "backPage/message";
			
		}else{
			request.setAttribute("onlineMap", map);
			request.setAttribute("errorMsg", errorMsg);
			request.setAttribute("msg", "更新地图信息失败！");
			return "backPage/map/setupMap";
		}
	}

	
	public void checkFormParams(Map<String, Object> map, Map<String, String> errorMsg) {
		
		
		String markerTitle = (String) map.get("markerTitle");
		
		String markerContent = (String) map.get("markerContent");
		
		String markerArea = (String) map.get("markerArea");
		
		String latitude = (String) map.get("latitude");
		
		String longitude = (String) map.get("longitude");
		
		checkStr(markerTitle, errorMsg, "mTitleError","标注点标题不能为空");
		checkStr(markerContent, errorMsg,"mContentError", "标注点内容不能为空");
		checkStr(markerArea, errorMsg,"mAreaError", "标注区域不能为空");
		checkStr(latitude, errorMsg,"latitudeError", "纬度不能为空");
		checkStr(longitude, errorMsg,"longitudeError", "经度不能为空");
	}
	
}
