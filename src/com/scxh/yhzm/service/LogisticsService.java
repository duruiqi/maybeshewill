package com.scxh.yhzm.service;

import java.io.OutputStream;
import java.util.List;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.scxh.yhzm.po.Purchase;

public interface LogisticsService<T> extends BaseService<T>{

	void importExcel(CommonsMultipartFile excleFile);

	void exportExcel(List<Purchase> purchaseList,OutputStream outputStream);
}
