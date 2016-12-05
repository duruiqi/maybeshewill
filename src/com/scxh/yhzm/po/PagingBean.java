package com.scxh.yhzm.po;

import java.util.List;

/**
 * bars的长度默认为5，如果要自定义则请调用setBarsLength方法来修改
 * 
 * @author liwei
 *
 * @param <T>
 */
public class PagingBean<T> {
	private List<T> dataList;
	private Integer curPage;
	private Integer pageSize;
	//private Integer totalPage;
	private Integer totalRecord;
	private Integer previous;
	private Integer next;
	private Integer [] bars;
	private Integer barsLength = 5;
	public PagingBean(){}
	
	/**
	 * @param totalRecord 总纪录数
	 * @param curPage 当前页码
	 * @param pageSize 一页显示的纪录数
	 * @param dataList 查询返回的集合数据
	 * initBaseParam()--->next,previous,bars[]的赋值操作
	 */
	public PagingBean(Integer totalRecord,Integer curPage,Integer pageSize,List<T> dataList) {
		this.dataList = dataList;
		this.curPage = curPage;
		this.pageSize = pageSize;
		this.totalRecord = totalRecord;
		initBaseParam();
	}
	public List<T> getDataList() {
		return dataList;
	}

	public void setDataList(List<T> dataList) {
		this.dataList = dataList;
	}

	public Integer getCurPage() {
		return curPage;
	}

	public void setCurPage(Integer curPage) {
		this.curPage = curPage;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getTotalPage() {
		
		Integer mod = totalRecord % pageSize;
		Integer div = totalRecord / pageSize;
		return mod == 0 ? div : div+1;
	}

	public Integer getTotalRecord() {
		return totalRecord;
	}

	public void setTotalRecord(Integer totalRecord) {
		this.totalRecord = totalRecord;
	}

	public Integer getPrevious() {
		if (this.curPage - 1 < 1) {
			this.previous = 1;
		} else {
			this.previous = this.curPage - 1;
		}
		return previous;
	}

	public Integer getNext() {
		if (this.curPage + 1 > this.getTotalPage()) {
			this.next = this.getTotalPage();
		} else {
			this.next = this.curPage + 1;
		}
		return next;
	}

	public void setBarsLength(Integer barsLength) {
		this.barsLength = barsLength;
	}

	public Integer[] getBars() {
		int startPage;
		Integer endPage;

		if (this.getTotalPage() <= barsLength) {// 小于基本的长度
			bars = new Integer[this.getTotalPage()];
			startPage = 1;
			endPage = this.getTotalPage();
		} else {
			bars = new Integer[barsLength];
			startPage = curPage - barsLength / 2;
			
			Integer mod = barsLength % 2;
			Integer div = barsLength / 2;
			int temp = mod == 0 ? div : div+1;
			
			endPage = curPage + temp - 1;
			if (startPage < 1) {// 左出头
				startPage = 1;
				endPage = barsLength;
			}
			if (endPage > this.getTotalPage()) {// 右出头
				endPage = this.getTotalPage();
				startPage = this.getTotalPage() - (barsLength - 1);
			}
		}
		
		for (int i = startPage,j=0; i <= endPage; i++) {
			bars[j++] = i;
		}
		return bars;
	}
	/***
	 * 在从数据库中获取了基本的参数后调用此方法来完成对前台必要参数的赋值
	 *  next,previous,int bars[]
	 */
	public void initBaseParam(){
		this.getBars();
		this.getNext();
		this.getPrevious();
	}
}
