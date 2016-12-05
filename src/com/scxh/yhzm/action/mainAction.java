package com.scxh.yhzm.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.scxh.yhzm.po.FirmInfo;
import com.scxh.yhzm.po.IndexBean;
import com.scxh.yhzm.po.MainBanner;
import com.scxh.yhzm.po.MainLogo;
import com.scxh.yhzm.po.News;
import com.scxh.yhzm.po.PagingBean;
import com.scxh.yhzm.po.ProjectOrder;
import com.scxh.yhzm.service.CompanyInfoService;
import com.scxh.yhzm.service.MainBannerService;
import com.scxh.yhzm.service.MainLogoService;
import com.scxh.yhzm.service.NewsService;
import com.scxh.yhzm.service.ProjectOrderService;

public class mainAction extends BaseAction {
	private CompanyInfoService<FirmInfo> companyInfoService;
	private MainLogoService<MainLogo> mainLogoService;
	private MainBannerService<MainBanner> mainBannerService;
	private NewsService<News> newsService;
	private ProjectOrderService<ProjectOrder> projectOrderService;


	public String showMain(HttpServletRequest request,
			HttpServletResponse response){
		
		//-----加载广告
		Map<String,Object> bannerMap = new HashMap<String, Object>();
		bannerMap.put("state", true);
		List<MainBanner> bannerList = mainBannerService.getAllEntry2(bannerMap);
		for(MainBanner banner : bannerList){
			banner.setBannerImg(request.getContextPath() + banner.getBannerImg());
		}
		//-----加载公司简介
		FirmInfo firmInfo = companyInfoService.getEntryByCoulmn(new FirmInfo(true));
		String firmImgAddr = firmInfo.getImagesAddr();
		if(firmImgAddr.contains(",")){
			firmImgAddr = firmImgAddr.substring(0,firmImgAddr.indexOf(","));
		}
		firmInfo.setImagesAddr(firmImgAddr);
		if(firmInfo.getIntroduce().length() > 170){
			String introduce = firmInfo.getIntroduce();
			introduce = introduce.substring(0,170) + "...";
			firmInfo.setIntroduce(introduce);
		}
		//-----加载新闻中心
		Map<String,Object> newsMap = new HashMap<String,Object>();
		String curPage = null;
		newsMap.put("curPage", 1);
		newsMap.put("pageSize", 8);// 一页固定要显示的纪录数
		PagingBean<News> pagingBean = newsService.getAllEntryFY(newsMap);

		for (News news : pagingBean.getDataList()) {
			news.setnImages(request.getContextPath() + news.getnImages());
			news.setnContent(news.getnContent().substring(0, 15) + "...");
		}
		//-----加载工程展示
		Map<String,Object> projectMap = new HashMap<String,Object>();
		projectMap.put("curPage", 1);
		
		projectMap.put("pageSize", 10);//一页固定要显示的纪录数

		PagingBean<ProjectOrder> pagingBean2 = projectOrderService.getAllEntryFY(projectMap);

		for(ProjectOrder po : pagingBean2.getDataList()){
			po.setProjectImg(request.getContextPath() + po.getProjectImg());
		}
		
		IndexBean index = new IndexBean(bannerList,firmInfo,pagingBean.getDataList(),pagingBean2.getDataList());
		request.setAttribute("indexBean", index);
		return "frontPage/index";
	}
	public void asynGetLogo(HttpServletRequest request,
			HttpServletResponse response){
		MainLogo mainLogo = mainLogoService.getEntryByCoulmn(new MainLogo(true));
		
		mainLogo.setMlogo(request.getContextPath() + mainLogo.getMlogo());
		
		try {
			String imgAddr = mainLogo.getMlogo();
			response.getWriter().write(imgAddr);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public void setCompanyInfoService(
			CompanyInfoService<FirmInfo> companyInfoService) {
		this.companyInfoService = companyInfoService;
	}

	public void setMainLogoService(MainLogoService<MainLogo> mainLogoService) {
		this.mainLogoService = mainLogoService;
	}

	public void setMainBannerService(MainBannerService<MainBanner> mainBannerService) {
		this.mainBannerService = mainBannerService;
	}

	public void setNewsService(NewsService<News> newsService) {
		this.newsService = newsService;
	}
	
	public void setProjectOrderService(
			ProjectOrderService<ProjectOrder> projectOrderService) {
		this.projectOrderService = projectOrderService;
	}
}
