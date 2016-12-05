package com.scxh.yhzm.po;

//地图实体
public class OnlineMap {
	private Integer mapId;
	private String markerTitle;//标注点名称
	private String markerContent;//标注点内容
	private String markerArea;//标注点所在的区域
	private Double latitude;//纬度
	private Double longitude;//经度
	
	public String getMarkerArea() {
		return markerArea;
	}
	public void setMarkerArea(String markerArea) {
		this.markerArea = markerArea;
	}
	public Integer getMapId() {
		return mapId;
	}
	public void setMapId(Integer mapId) {
		this.mapId = mapId;
	}
	public String getMarkerTitle() {
		return markerTitle;
	}
	public void setMarkerTitle(String markerTitle) {
		this.markerTitle = markerTitle;
	}
	public String getMarkerContent() {
		return markerContent;
	}
	public void setMarkerContent(String markerContent) {
		this.markerContent = markerContent;
	}
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	
}
