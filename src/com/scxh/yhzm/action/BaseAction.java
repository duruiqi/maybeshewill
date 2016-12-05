package com.scxh.yhzm.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

public class BaseAction extends MultiActionController {
	/**
	 * 工程路径
	 */
	protected static String BASE_PATH;
	/**
	 * 每页显示5条记录
	 */
	protected static final Integer countPerPage = 5;

	/**
	 * 文件上传对象
	 */
	protected static ServletFileUpload fileUpload;

	static {
		DiskFileItemFactory factory = new DiskFileItemFactory();
		fileUpload = new ServletFileUpload(factory);
		fileUpload.setHeaderEncoding("UTF-8");
	}

	public BaseAction() {
	}

	/**
	 * 完成Spring文件上传
	 * 
	 * @param request
	 * @return
	 */
	protected Map<String, Object> parseSpringUpload(HttpServletRequest request) {
		MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
		Map<String, String[]> paramMap = multiRequest.getParameterMap();
		MultiValueMap<String, MultipartFile> fileMap = multiRequest
				.getMultiFileMap();

		Set<Map.Entry<String, String[]>> paramEntrys = paramMap.entrySet();
		Set<Map.Entry<String, List<MultipartFile>>> fileEntrys = fileMap
				.entrySet();

		Map<String, Object> map = new HashMap<String, Object>();
		
		for (Map.Entry<String, String[]> entry : paramEntrys) {
			String[] values = entry.getValue();
			if (null == values || 0 >= values.length)
				continue;
			String key = entry.getKey();
			if (values.length > 1) {
				map.put(key, values);
			} else {
				map.put(key, values[0]);
			}
		}

		for (Map.Entry<String, List<MultipartFile>> entry : fileEntrys) {
			List<MultipartFile> values = entry.getValue();
			if (null == values || 0 >= values.size())
				continue;
			String key = entry.getKey();
			if (values.size() > 1) {
				map.put(key, values);
			} else {
				map.put(key, values.get(0));
			}
		}
		
		return map;
	}

	/**
	 * 用于处理基本参数向map<String,Object>的封装
	 * @param request HttpServletRequest
	 * @return Map
	 */
	protected Map<String,Object> baseParamstoMap(HttpServletRequest request){
		
		Map<String,String[]> map = request.getParameterMap();
		Map<String,Object> paramsMap = new HashMap<String, Object>();
		
		for (Map.Entry<String, String[]> entry : map.entrySet()) {
			String[] values = entry.getValue();
			if (null == values || 0 >= values.length)
				continue;
			String key = entry.getKey();
			if (values.length > 1) {
				paramsMap.put(key, values);
			} else {
				paramsMap.put(key, values[0]);
			}
		}
		return paramsMap;
		
	}
	/**
	 * 完成文件通用上传解析
	 * 
	 * @param request
	 * @return
	 */
	protected Map<String, Object> parseCommonUpload(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			Map<String, List<Object>> temp = new HashMap<String, List<Object>>();
			List<FileItem> list = fileUpload.parseRequest(request);
			for (FileItem item : list) {
				String paramName = item.getFieldName();
				Object paramValue = null;
				if (item.isFormField()) {
					paramValue = item.getString("UTF-8");
				} else {
					paramValue = item;
				}

				List<Object> listValue = temp.get(paramName);
				if (null == listValue) {
					listValue = new ArrayList<Object>();
					temp.put(paramName, listValue);
				}
				listValue.add(paramValue);
			}

			Set<Map.Entry<String, List<Object>>> entrys = temp.entrySet();
			for (Map.Entry<String, List<Object>> entry : entrys) {
				List<Object> values = entry.getValue();
				if (null == values || 0 >= values.size())
					continue;
				String key = entry.getKey();
				if (values.size() > 1) {
					map.put(key, values);
				} else {
					map.put(key, values.get(0));
				}
			}
			return map;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	

	/**
	 * 用于检查表单数据中图片文件类型是否符合规范
	 * @param map 封装有多部分数据的Map，其中包含了文件对象
	 * @param errorMsg 用于封装错误信息的 Map
	 */
	protected static void imgIsNull(Map<String, Object> map, Map<String, String> errorMsg) {
		String imageName = (String) map.get("imageName");
		CommonsMultipartFile uploadImg = (CommonsMultipartFile) map
				.get(imageName);
		String contentType = uploadImg.getContentType();
		// 对图片的非空判断
		if (uploadImg.getSize() == 0) {
			errorMsg.put("imgError", "必须上传图片文件！");
		} else if (!(contentType.equals("image/png")
				|| contentType.equals("image/jpeg") || contentType
					.equals("image/gif"))) {
			errorMsg.put("imgError", "不支持的文件类型！");
		}
	}

	/**
	 * 用于检查基本类型的表单数据的非空
	 * @param str 要检查的字符串
	 * @param errorMsg 用于封装错误信息的Map<String,String>
	 * @param errorName 指定错误信息的键
	 * @param errorValue 指定错误提示信息
	 */
	protected static void checkStr(String str, Map<String, String> errorMsg,
			String errorName, String errorValue) {
		if (str == null || str.trim().isEmpty()) {
			errorMsg.put(errorName, errorValue);
		}
	}

}
