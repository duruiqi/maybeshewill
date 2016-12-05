package com.scxh.yhzm.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.scxh.yhzm.po.Admin;

public class CookieUtil {
	
	//读取本地的书签
		public static void readCookie(HttpServletRequest request,
				Map<String, String> cookieInfo) {
			Cookie[] cookies = request.getCookies();
			
			if(cookies != null && cookies.length > 0){
				for(Cookie cookie : cookies){
					String name = cookie.getName();
					String value = null;
					try {
						value = URLEncoder.encode(cookie.getValue(), "UTF-8");//中文书签的本地化方案
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					}
					if("adminName".equals(name)){
						cookieInfo.put(name, value);
					}
					if("adminPasswd".equals(name)){
						cookieInfo.put(name,value);
					}
				}
			}
		}
		
	//保存cookie
		public static void saveCookie(HttpServletResponse response, Admin admin) {
			Cookie adminPasswd = null;
			Cookie adminName = null;			
				try {
					//中文书签的本地化
					adminPasswd = new Cookie("adminPasswd",URLDecoder.decode(admin.getAdminPasswd(),"UTF-8"));
					adminName = new Cookie("adminName",URLDecoder.decode(admin.getAdminName(),"UTF-8"));
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				
				adminPasswd.setMaxAge(60*2);
				adminName.setMaxAge(60*2);
				response.addCookie(adminName);
				response.addCookie(adminPasswd);
		}
}
