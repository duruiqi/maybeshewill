package com.scxh.yhzm.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.scxh.yhzm.po.News;
import com.scxh.yhzm.po.PagingBean;
import com.scxh.yhzm.service.NewsService;

public class NewsAction extends BaseAction {
	
	private NewsService<News> newsService;
	public void setNewsService(NewsService<News> newsService) {
		this.newsService = newsService;
	}
	

	public NewsService<News> getNewsService() {
		return newsService;
	}


	public String addNews(HttpServletRequest request,
			HttpServletResponse response) {
		// 获取到表单所有的项
		Map<String, Object> map = super.parseSpringUpload(request);
		Map<String, String> errorMsg = new HashMap<String, String>();

		// 封装的是上传文件的input的name值
		map.put("imageName", "image");
		
		checkFormParams(map, errorMsg);

		imgIsNull(map, errorMsg);
		String back = "/backPage/news/add";

		if (errorMsg.size() > 0) {// 向增加页面响应错误信息
			request.setAttribute("form", map);
			request.setAttribute("errorMsg", errorMsg);
			return back;
		}

		try {
			int backInfo = newsService.addEntry(map);

			if (backInfo > 0) {
				request.setAttribute("newsInfo", "添加成功！");
				return back;
			} else {
				throw new RuntimeException("添加失败！！");
			}

		} catch (Exception e) {
			e.printStackTrace();
			errorMsg.put("msg", e.getMessage());
			request.setAttribute("form", map);
			request.setAttribute("errorMsg", errorMsg);
			return back;
		}

	}
	
	// 带分页查询所有数据
	public String getNewsList(HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		String curPage = request.getParameter("curPage");// 获取当前页的角标
		map.put("curPage", null == curPage ? 1 : new Integer(curPage));
		map.put("pageSize", countPerPage);// 一页固定要显示的纪录数
		try {
			PagingBean<News> pagingBean = newsService.getAllEntryFY(map);

			for (News news : pagingBean.getDataList()) {
				news.setnImages(request.getContextPath() + news.getnImages());
				news.setnContent(news.getnContent().substring(0, 15) + "...");
			}
			request.setAttribute("pagingBean", pagingBean);
			return "backPage/news/list";
		} catch (Exception e) {
			e.printStackTrace();
			return "500";
		}
	}

	// 修改之前的获取操作
	public String getNews(HttpServletRequest request,
			HttpServletResponse response) {
		String nid = request.getParameter("nid");
		try {
			if (null == nid) {
				request.setAttribute("msg", "提交ID为空,无法查询数据!");
				return getNewsList(request, response);
			}

			News news = newsService.getEntryById(nid);

			if (null != news) {
				request.setAttribute("form", news);
				return "backPage/news/edit";
			}

			return getNewsList(request, response);
			
		} catch (Exception e) {
			request.setAttribute("msg", "查询失败!");
			return getNewsList(request, response);
		}

	}

	// 保存用户做的修改
	public String modNews(HttpServletRequest request,
			HttpServletResponse response) {
		// 获取到表单所有的项
		Map<String, Object> map = super.parseSpringUpload(request);
		Map<String, String> errorMsg = new HashMap<String, String>();

		CommonsMultipartFile uploadImg = (CommonsMultipartFile) map.get("image");

		if (uploadImg.getSize() > 0) {
			// 封装的是上传文件的input的name值
			map.put("imageName", "image");
			map.put("flag", "1");
			imgIsNull(map, errorMsg);
		}
		
		checkFormParams(map, errorMsg);

		String back = "/backPage/news/edit";

		if (errorMsg.size() > 0) {// 向增加页面响应错误信息
			request.setAttribute("form", map);
			request.setAttribute("errorMsg", errorMsg);
			return back;
		}

		try {
			int backInfo = newsService.modEntry(map);

			if (backInfo > 0) {
				request.setAttribute("newsInfo", "修改成功！");
				return getNewsList(request, response);
			} else {
				throw new RuntimeException("修改失败！！");
			}

		} catch (Exception e) {
			errorMsg.put("msg", e.getMessage());
			request.setAttribute("form", map);
			request.setAttribute("errorMsg", errorMsg);
			e.printStackTrace();
			return back;
		}

	}

	/**
	 * @param request
	 * @param response
	 * @return
	 */
	public String delNews(HttpServletRequest request,
			HttpServletResponse response) {
		if (null == BASE_PATH){
			BASE_PATH = getServletContext().getRealPath("/");
		}
		
		Map<String,Object> map = super.baseParamstoMap(request);
		
		map.put("basePath", BASE_PATH);

		try {
			Integer count = newsService.deleteEntry(map);
			if (null == count || count < 1)
				request.setAttribute("msg", "删除失败!");
		} catch (Exception e) {
			request.setAttribute("msg", "删除失败!");
			e.printStackTrace();
		}
		return getNewsList(request, response);
	}


	// 公共方法用于校验客户端上传的文件和请求传递的参数是否合理，并向super.parseSpringUpload(request)返回的map对象中封装少量的数据
	public void checkFormParams(Map<String, Object> map,
			Map<String, String> errorMsg) {
		
		String nTitle = (String) map.get("nTitle");

		String nContent = (String) map.get("nContent");

		String nDate = (String) map.get("nDate");

		String customerUnit = (String) map.get("customerUnit");

		checkStr(nTitle, errorMsg, "nTitleError", "标题不能为空");
		checkStr(nContent, errorMsg, "nContentError", "内容不能为空");
		checkStr(nDate, errorMsg, "nDateError", "发布的日期不能为空");
		
		checkStr(customerUnit, errorMsg, "cUnitError", "客户单位不能为空");

		BASE_PATH = getServletContext().getRealPath("/");
		map.put("basePath", BASE_PATH);
	}
	
	
	//这里是给前台页面写的获取新闻列表
	public String NewsList(HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		String curPage = request.getParameter("curPage");// 获取当前页的角标
		map.put("curPage", null == curPage ? 1 : new Integer(curPage));
		map.put("pageSize", countPerPage);// 一页固定要显示的纪录数
		try {
			PagingBean<News> pagingBean = newsService.getAllEntryFY(map);

			for (News news : pagingBean.getDataList()) {
				news.setnImages(request.getContextPath() + news.getnImages());
				news.setnContent(news.getnContent().substring(0, 15) + "...");
			}
			request.setAttribute("pagingBean", pagingBean);
			return "frontPage/news";
		} catch (Exception e) {
			e.printStackTrace();
			return "500";
		}
	}
	
	//给前台的新闻详情页写的
	public String getNewsContent(HttpServletRequest request,
			HttpServletResponse response) {
		String nid = request.getParameter("nid");
		
		
			if (null == nid) {
				request.setAttribute("msg", "提交ID为空,无法查询数据!");
				return "frontPage/news";
			}
			
			News news = newsService.getEntryById(nid);
			
			if (null == news) {
				return "frontPage/news";
			}
			
			request.setAttribute("form", news);
			
			return "frontPage/newsContent";
	}
	
	
}
